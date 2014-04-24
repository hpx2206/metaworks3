package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.metaworks.annotation.Id;
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
	String editorId;
		@Id
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	String processDesignerInstanceId;
		public String getProcessDesignerInstanceId() {
			return processDesignerInstanceId;
		}
		public void setProcessDesignerInstanceId(String processDesignerInstanceId) {
			this.processDesignerInstanceId = processDesignerInstanceId;
		}	
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}
	int designerMaxX;
		public int getDesignerMaxX() {
			return designerMaxX;
		}
		public void setDesignerMaxX(int designerMaxX) {
			this.designerMaxX = designerMaxX;
		}
	int designerMaxY;
		public int getDesignerMaxY() {
			return designerMaxY;
		}
		public void setDesignerMaxY(int designerMaxY) {
			this.designerMaxY = designerMaxY;
		}
		
	boolean useClassLoader;
		public boolean isUseClassLoader() {
			return useClassLoader;
		}
		public void setUseClassLoader(boolean useClassLoader) {
			this.useClassLoader = useClassLoader;
		}
	public ProcessViewer() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String clsName = GlobalContext.getPropertyString("designerContainer.class","org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer");
		processDesignerContainer = (ProcessDesignerContainer)GlobalContext.loadClass(clsName).newInstance();
	}
		
	public void load(){
		if( editorId == null && getAlias() != null ){
			editorId = getAlias();
		}
		InputStream is = null;
		ByteArrayOutputStream bao = null;
		
		try {
			bao = new ByteArrayOutputStream();
			if( getAlias() != null ){
				if( useClassLoader ){
					is = Thread.currentThread().getContextClassLoader().getResourceAsStream(getAlias());
				}else{
					File file = new File(getAlias());
					is = new FileInputStream(file);
				}
				try {
					if( is != null ){
						MetaworksUtil.copyStream(is, bao);
						
						ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString(GlobalContext.ENCODING));
						this.processDesignerContainer.setViewType(viewType);
						this.processDesignerContainer.setEditorId(editorId);
						this.processDesignerContainer.init();
						this.processDesignerContainer.load(def);
						this.setProcessDesignerInstanceId(def.getProcessDesignerInstanceId());
						this.setTitle(def.getName().getText());
						
						this.setDesignerMaxX(processDesignerContainer.getMaxX());
						this.setDesignerMaxY(processDesignerContainer.getMaxY());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}			
				}
//			}
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
