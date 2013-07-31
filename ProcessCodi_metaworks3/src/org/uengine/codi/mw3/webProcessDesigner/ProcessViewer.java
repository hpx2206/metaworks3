package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.metaworks.common.MetaworksUtil;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;

public class ProcessViewer {

	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	public ProcessViewer(){
		processDesignerContainer = new ProcessDesignerContainer();
	}
		
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}
		
	public void load(){
		// TODO  fullPath 가 필요함
		InputStream is = null;
		ByteArrayOutputStream bao = null;
		
		try {
			bao = new ByteArrayOutputStream();
			
			//if(TYPE_FILE.equals(this.getType())){
				File file = new File(getAlias());
				if(file.exists()){					
					try {
						is = new FileInputStream(file);
					} catch (Exception e) {
						e.printStackTrace();
					}			
				}
				
			MetaworksUtil.copyStream(is, bao);
			
			ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString(GlobalContext.ENCODING));
			this.processDesignerContainer.setViewType(viewType);
			this.processDesignerContainer.load(def);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			if(bao != null){
				try {
					bao.close();
					bao = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
