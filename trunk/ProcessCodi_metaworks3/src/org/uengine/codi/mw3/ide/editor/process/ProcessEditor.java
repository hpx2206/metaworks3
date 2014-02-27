package org.uengine.codi.mw3.ide.editor.process;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel;

public class ProcessEditor extends Editor {

	ProcessDesignerContentPanel processDesigner;
		public ProcessDesignerContentPanel getProcessDesigner() {
			return processDesigner;
		}
		public void setProcessDesigner(ProcessDesignerContentPanel processDesigner) {
			this.processDesigner = processDesigner;
		}
	String processDesignerInstanceId;
		public String getProcessDesignerInstanceId() {
			return processDesignerInstanceId;
		}
		public void setProcessDesignerInstanceId(String processDesignerInstanceId) {
			this.processDesignerInstanceId = processDesignerInstanceId;
		}
		
	public ProcessEditor(){
		
	}
		
	public ProcessEditor(ResourceNode resourceNode){
		super(resourceNode);
		
		this.setType("process");
		
		try {
			ProcessDesignerContentPanel processDesignerContentPanel = new ProcessDesignerContentPanel();
			processDesignerContentPanel.session = resourceNode.session;
			processDesignerContentPanel.setAlias(this.getResourceNode().getPath());
			this.setProcessDesigner(processDesignerContentPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ProcessEditor(ProcessNode processNode){
		super(processNode);
		this.setType("process");
		
		try {
			ProcessDesignerContentPanel processDesignerContentPanel = new ProcessDesignerContentPanel();
			processDesignerContentPanel.session = processNode.session;
			processDesignerContentPanel.setAlias(this.getResourceNode().getPath());
			this.setProcessDesigner(processDesignerContentPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String load() {
		String definitionString = super.load();
		
		try {
			processDesignerInstanceId = this.getProcessDesigner().load(definitionString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return definitionString;
	}

	@Override
	@ServiceMethod(callByContent=true)
	public Object save() throws Exception{
		this.getProcessDesigner().session = this.session;
		this.getProcessDesigner().saveMe(this);
		return null;
	}
}
