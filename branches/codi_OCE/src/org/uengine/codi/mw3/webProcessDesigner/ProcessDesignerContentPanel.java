package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.processexplorer.ProcessNameView;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ProcessDesignerContentPanel extends ContentWindow implements ContextAware {
	
	public ProcessDesignerContentPanel() throws Exception{
		processDesignerContainer = new ProcessDesignerContainer();
		processNameView = new ProcessNameView();
		processNameView.setMetaworksContext(new MetaworksContext());
		processNameView.getMetaworksContext().setHow("nameChange");
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
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}

	String processName;
		@Hidden
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		
	String basePath;
	@Hidden
		public String getBasePath() {
			return basePath;
		}
		public void setBasePath(String basePath) {
			this.basePath = basePath;
		}
		
	ProcessNameView processNameView;
		public ProcessNameView getProcessNameView() {
			return processNameView;
		}
		public void setProcessNameView(ProcessNameView processNameView) {
			this.processNameView = processNameView;
		}	
	
	public void saveMe(ProcessEditor processEditor) throws Exception{
		ProcessDefinition def = processDesignerContainer.containerToDefinition(processDesignerContainer);
		TextContext text = new TextContext();
		text.setText(this.getProcessName());
		def.setName(text);
		if( processEditor.getProcessDesignerInstanceId() != null ){
			def.setProcessDesignerInstanceId(processEditor.getProcessDesignerInstanceId());
		}
		FileOutputStream fos = null;
		try{
			String path = "";
			if( processEditor.getResourceNode() != null ){
				path = processEditor.getResourceNode().getPath();
			}else{
				path = processEditor.getProcessNode().getPath();
			}
			File file = new File(path);
			fos = new FileOutputStream(file);
			String definitionInString = (String)GlobalContext.serialize(def, ProcessDefinition.class);
			ByteArrayInputStream bai = new ByteArrayInputStream(definitionInString.getBytes(GlobalContext.ENCODING));
			UEngineUtil.copyStream(bai, fos);
		} catch (Exception e) {
			throw e;//e.printStackTrace();
		} finally{
			if(fos!=null)
				fos.close();
		}
	}
	
	@ServiceMethod(callByContent=true)
	public void save(String title, boolean temp) throws Exception{
	}
	public String load(String definitionString) throws Exception {
		ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(definitionString);
		this.processDesignerContainer.setEditorId(alias);
		this.processDesignerContainer.load(def);
		
		processNameView.setFileId(alias);
		processNameView.setAlias(def.getName().getText());
		
		return def.getProcessDesignerInstanceId();
	}
	
	public void loadOld() throws Exception{
		
		String processName = getAlias();
		setProcessName(processName);
		/// read source file
		//File sourceCodeFile = new File(getBasePath() + getAlias());
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + processName);
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
//		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			
			this.load(bao.toString("UTF-8"));
			
			
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public void load() throws Exception{
		
		String processName = getAlias().substring(0, getAlias().indexOf("."));
		setProcessName(processName);
		/// read source file
		File sourceCodeFile = new File(getBasePath() + getAlias());
		//File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + processName + ".process2");
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
//		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			
			this.load(bao.toString("UTF-8"));
			
			
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	@Autowired
	public ProcessManagerRemote processManager;
	
	@AutowiredFromClient
	transient public Session session;
	
}
