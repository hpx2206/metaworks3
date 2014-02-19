package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.FieldDescriptor;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dwr.MetaworksRemoteService;

public class VariableSelectBox extends SelectBox {
	
	VariableSelectBox childSelectBox;
		public VariableSelectBox getChildSelectBox() {
			return childSelectBox;
		}
		public void setChildSelectBox(VariableSelectBox childSelectBox) {
			this.childSelectBox = childSelectBox;
		}
		
	public VariableSelectBox(){
		super();
	}
	
	@ServiceMethod( callByContent=true,eventBinding = "change", bindingFor = "childSelectBox")
	public void makeValiableChoiceChild() throws Exception{
		int beginIndex = childSelectBox.getSelected().indexOf("[");
		int endIndex = childSelectBox.getSelected().indexOf("]");
		VariableSelectBox child = null;
		String selectClass = childSelectBox.getSelected().substring(beginIndex+1, endIndex);
		if( selectClass != null && selectClass.startsWith("org.uengine.codi.mw3")){
			WebObjectType wot2 = MetaworksRemoteService.getInstance().getMetaworksType(selectClass); 
			WebFieldDescriptor wfields2[] = wot2.getFieldDescriptors();
			FieldDescriptor fields2[] = wot2.metaworks2Type().getFieldDescriptors();
			child = new VariableSelectBox();
			child.setId(childSelectBox.getId()+".");
			for(int k=0; k<fields2.length; k++){
				WebFieldDescriptor wfd2 = wfields2[k];
				String classType = wfd2.getClassName().substring(wfd2.getClassName().lastIndexOf(".")+1);
				String displayName = "".equals(wfd2.getDisplayName()) ? wfd2.getName() : wfd2.getDisplayName();
				
				child.add("["+classType+"]"+displayName, "["+wfd2.getClassName()+"]"+wfd2.getName());
			}
		}
		childSelectBox.setChildSelectBox(child);
	}
}
