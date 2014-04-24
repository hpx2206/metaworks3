package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;

public class MetadataXmlEditor extends Editor {

	public MetadataXmlEditor(){
		super();
	}
	public MetadataXmlEditor(ResourceNode resourceNode){
		super(resourceNode);
		this.setType(TreeNode.TYPE_FILE_METADATA);
	}
	
	public Object save() throws Exception{
		return super.save();
	}
	
	public Object append() throws Exception{
		
		return null;
	}
}
