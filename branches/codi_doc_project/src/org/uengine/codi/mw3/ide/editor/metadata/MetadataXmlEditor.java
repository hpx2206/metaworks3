package org.uengine.codi.mw3.ide.editor.metadata;

import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;

public class MetadataXmlEditor extends Editor {

	public MetadataXmlEditor(){
		super();
	}
	public MetadataXmlEditor(ResourceNode resourceNode){
		super(resourceNode);
		this.setType("metadata");
	}
	
	public Object save(){
		return super.save();
	}
	
	public Object append() throws Exception{
		
		return null;
	}
}
