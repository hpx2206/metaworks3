package org.uengine.codi.mw3.ide.editor.process;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerWebContentPanel;

public class ProcessEditor extends Editor {

	ProcessDesignerWebContentPanel processDesigner;
		public ProcessDesignerWebContentPanel getProcessDesigner() {
			return processDesigner;
		}
		public void setProcessDesigner(ProcessDesignerWebContentPanel processDesigner) {
			this.processDesigner = processDesigner;
		}

	public ProcessEditor(){
		
	}
		
	public ProcessEditor(ResourceNode resourceNode){
		super(resourceNode);
		
		this.setType("process");
		
		try {
			ProcessDesignerWebContentPanel processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
			processDesignerWebContentPanel.setAlias(this.getResourceNode().getPath());
			
			this.setProcessDesigner(processDesignerWebContentPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String load() {
		String definitionString = super.load();
		
		try {
			this.getProcessDesigner().load(new String(definitionString.getBytes(), "UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return definitionString;
	}

	@Override
	@ServiceMethod(callByContent=true)
	public Object save(){
		try {
			this.getProcessDesigner().saveMe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
