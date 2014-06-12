package org.uengine.codi.mw3.ide.editor.process;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.compare.FileComparePanel;
import org.uengine.codi.mw3.ide.editor.Editor;

public class ProcessMergeEditor  extends Editor{
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	FileComparePanel fileComparePanel;
		public FileComparePanel getFileComparePanel() {
			return fileComparePanel;
		}
		public void setFileComparePanel(FileComparePanel fileComparePanel) {
			this.fileComparePanel = fileComparePanel;
		}
	public ProcessMergeEditor(){
	}
	public ProcessMergeEditor(ResourceNode resourceNode)  throws Exception{
		super(resourceNode);
		this.setType("processCompare");
		fileComparePanel = new FileComparePanel();
		fileComparePanel.setSelectedProcessAlias(resourceNode.getPath());
		fileComparePanel.load();
		
	}
	
	@Override
	@ServiceMethod(callByContent=true)
	public Object save(){
		try {
			this.getFileComparePanel().saveMe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
