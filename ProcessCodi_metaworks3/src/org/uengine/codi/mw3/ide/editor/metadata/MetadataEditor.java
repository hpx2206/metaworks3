package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.MultiPageEditor;

public class MetadataEditor extends MultiPageEditor{

	public MetadataEditor(){
		super();
	}
	public MetadataEditor(ResourceNode resourceNode){
		super(resourceNode);
		this.setType("metadata");
	}
	
	public void loadPage() throws Exception{
		this.setCallServiceFunction(true);
		this.setCallSelectFunctionName("selectPage");
		
		MetadataXmlEditor metadataXmlEditor = new MetadataXmlEditor(this.getResourceNode());
		createPage(metadataXmlEditor, "xml");
		
		MetadataContentDesigner metadataContentDesigner = new MetadataContentDesigner(this.getResourceNode());
		metadataContentDesigner.load();
		createPage(metadataContentDesigner, "designer");
	}
	
	@ServiceMethod(callByContent=true)
	public Object selectPage() throws Exception{
//		System.out.println("ddddddd");
		return null;
	}
}
