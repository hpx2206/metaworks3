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
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		
	/*
	 * 1024,768 이런식으로 셋팅
	 */
	String processDesignerSize;
		public String getProcessDesignerSize() {
			return processDesignerSize;
		}
		public void setProcessDesignerSize(String processDesignerSize) {
			this.processDesignerSize = processDesignerSize;
		}
		
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}
		
	public ProcessViewer(){
		processDesignerContainer = new ProcessDesignerContainer();
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
				File file = new File(getAlias());
				if(file.exists()){					
					try {
						is = new FileInputStream(file);
						MetaworksUtil.copyStream(is, bao);
						
						ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString(GlobalContext.ENCODING));
						this.processDesignerContainer.setViewType(viewType);
						this.processDesignerContainer.setEditorId(editorId);
						this.processDesignerContainer.init();
						this.processDesignerContainer.load(def);
						this.setProcessDesignerSize(def.getProcessDesignerSize());
						
					} catch (Exception e) {
						e.printStackTrace();
					}			
				}
			}
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
