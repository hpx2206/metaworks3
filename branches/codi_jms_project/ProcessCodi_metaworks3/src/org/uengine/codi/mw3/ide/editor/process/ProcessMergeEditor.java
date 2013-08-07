package org.uengine.codi.mw3.ide.editor.process;

import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.webProcessDesigner.ProcessMergePanel;

public class ProcessMergeEditor  extends Editor{
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	ProcessMergePanel processMergePanel;
		public ProcessMergePanel getProcessMergePanel() {
			return processMergePanel;
		}
		public void setProcessMergePanel(ProcessMergePanel processMergePanel) {
			this.processMergePanel = processMergePanel;
		}
	public ProcessMergeEditor(){
				
	}
	public ProcessMergeEditor(ResourceNode resourceNode){
		super(resourceNode);
		
		this.setType("processMerge");
	}
}
