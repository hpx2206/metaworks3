package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.MultiPageEditor;

public class MetadataEditor extends MultiPageEditor{

	public MetadataEditor(){
		super();
	}
	public MetadataEditor(ResourceNode resourceNode){
		super(resourceNode);
		this.setType(TreeNode.TYPE_FILE_METADATA);
	}
	
	public void loadPage() throws Exception{
		this.setCallServiceFunction(true);
		this.setCallSelectFunctionName("selectPage");
		
		MetadataContentDesigner metadataContentDesigner = new MetadataContentDesigner(this.getResourceNode());
		metadataContentDesigner.load();
		createPage(metadataContentDesigner, "designer");
		
//		MetadataXmlEditor metadataXmlEditor = new MetadataXmlEditor(this.getResourceNode());
//		createPage(metadataXmlEditor, "xml");
		
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object selectPage() throws Exception{
//		System.out.println("ddddddd");
		return null;
	}
	
	@Override
	@ServiceMethod(callByContent=true)
	public Object save(){
		MetadataXmlEditor metadataXmlEditor = null;
		MetadataContentDesigner metadataContentDesigner = null;
		
		Object[] panel = this.getPagePanel();
		if( panel != null && panel.length > 0 ){
			for( int i = 0 ;  i < panel.length; i++){
				// 1. xml 데이터 저장
				if( panel[i] instanceof MetadataXmlEditor ){
					metadataXmlEditor = (MetadataXmlEditor)panel[i];
				}else if( panel[i] instanceof MetadataContentDesigner ){
					metadataContentDesigner = (MetadataContentDesigner)panel[i];
				}
			}
		}

		if(metadataContentDesigner != null && metadataContentDesigner.getCustomizeProperies().getMetadataXML() != null){
			String content = metadataContentDesigner.getCustomizeProperies().getMetadataXML().toXmlXStream();
			
			this.setContent(content);
		}
		
		return super.save();
	}
	
}
