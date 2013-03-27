package org.uengine.codi.mw3.ide.editor.process;

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
		super();
	}
		
	public ProcessEditor(String filename){
		super(filename);
		
		try {
			ProcessDesignerWebContentPanel processDesignerWebContentPanel = new ProcessDesignerWebContentPanel();
			processDesignerWebContentPanel.setAlias(filename);
			
			this.setProcessDesigner(processDesignerWebContentPanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object save(){
		try {
			this.getProcessDesigner().saveMe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
