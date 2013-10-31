package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;

public class ProcessXmlEditor extends Editor{
	
	public ProcessXmlEditor(){
		super();
	}
	
	public ProcessXmlEditor(ProcessNode processNode){
		super(processNode);
		this.setType(TreeNode.TYPE_FILE_PROCESS);
	}
	
	public Object save(){
		return super.save();
	}
	
	public Object append() throws Exception{
		return null;
	}
	
}
