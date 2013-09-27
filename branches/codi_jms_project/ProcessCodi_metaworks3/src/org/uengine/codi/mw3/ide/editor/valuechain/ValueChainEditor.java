package org.uengine.codi.mw3.ide.editor.valuechain;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.webProcessDesigner.ValueChainDesignerContentPanel;

public class ValueChainEditor extends Editor{

	ValueChainDesignerContentPanel valueChainDesigner;
		public ValueChainDesignerContentPanel getValueChainDesigner() {
			return valueChainDesigner;
		}
		public void setValueChainDesigner(ValueChainDesignerContentPanel valueChainDesigner) {
			this.valueChainDesigner = valueChainDesigner;
		}
	
	String processDesignerInstanceId;
		public String getProcessDesignerInstanceId() {
			return processDesignerInstanceId;
		}
		public void setProcessDesignerInstanceId(String processDesignerInstanceId) {
			this.processDesignerInstanceId = processDesignerInstanceId;
		}
		
	public ValueChainEditor(){
		
	}
	
	public ValueChainEditor(ResourceNode resourceNode){
		super(resourceNode);
		
		this.setType("valuechain");
		
		try{
			ValueChainDesignerContentPanel valueChainDesignerContentPanel = new ValueChainDesignerContentPanel();
			valueChainDesignerContentPanel.session = resourceNode.session;
			valueChainDesignerContentPanel.setAlias(this.getResourceNode().getPath());
			this.setValueChainDesigner(valueChainDesignerContentPanel);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String load() {
		String definitionString = super.load();
		
		try {
			processDesignerInstanceId = this.getValueChainDesigner().load(definitionString);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return definitionString;
	}
	
	@Override
	@ServiceMethod(callByContent=true)
	public Object save() {
		try {
			this.getValueChainDesigner().saveMe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
