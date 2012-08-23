package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.directwebremoting.json.types.JsonObject;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ProcessDesignerWebContentPanel extends ContentWindow implements ContextAware {
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	String alias;
	@Id
	@Hidden
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	ProcessDesignerCanvas cell[];
		public ProcessDesignerCanvas[] getCell() {
			return cell;
		}
		public void setCell(ProcessDesignerCanvas[] cell) {
			this.cell = cell;
		}
		
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		ArrayList<ProcessDesignerCanvas> cells = new ArrayList<ProcessDesignerCanvas>();
		if( cell != null){
			for(int i = 0; i < cell.length; i++){
				cells.add(cell[i]);
			}
		}
		// generate source file
		String sourceCodeBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();
		File sourceCodeFile = new File(sourceCodeBase + "/" + "test/process.process2");
		sourceCodeFile.getParentFile().mkdirs();
		//sourceCodeFile.createNewFile();
		
		FileWriter writer = new FileWriter(sourceCodeFile);
		writer.write(GlobalContext.serialize(cells, Object.class));
		writer.close();

		//processManager.addProcessDefinition("definition name", 0, "description", false, processDefinitionString, savingFolder, alias, alias, "process");
		
		return null;
	}
	
	public void load() throws Exception{
		
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias().substring(0, getAlias().indexOf(".")) + ".process2");
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			// bao.toString() xml 로 받은 데이터를 json 객체로 변환시켜줌
			ArrayList<ProcessDesignerCanvas> cellsList = (ArrayList<ProcessDesignerCanvas>) GlobalContext.deserialize(bao.toString());
			if( cellsList != null){
				ProcessDesignerCanvas []cells = new ProcessDesignerCanvas[cellsList.size()];
				for(int i = 0; i < cellsList.size(); i++){
					cells[i] = (ProcessDesignerCanvas)cellsList.get(i);
				}
				this.setCell(cells);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}
