package org.uengine.kernel;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToOpener;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.webProcessDesigner.ConditionInput;
import org.uengine.contexts.ComplexType;
import org.uengine.contexts.TextContext;

public class ParameterContextPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	SelectBox dataType;
		public SelectBox getDataType() {
			return dataType;
		}
		public void setDataType(SelectBox dataType) {
			this.dataType = dataType;
		}
		
	ConditionInput conditionInput;
		public ConditionInput getConditionInput() {
			return conditionInput;
		}
		public void setConditionInput(ConditionInput conditionInput) {
			this.conditionInput = conditionInput;
		}
		
	public ParameterContextPanel(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen("edit");
		dataType = new SelectBox();
		conditionInput = new ConditionInput();
		conditionInput.init();
	}
	
	public void load() throws Exception{
		this.makeDataTypeChoice();
		
//		ParameterContext[] pc = this.getParameters();
//		if( pc == null || pc.length == 0 ){
//			pc = new ParameterContext[1];
//			ParameterContext pcx = new ParameterContext();
//			pc[0] = pcx;
//		}
		
	}
	
	public void makeDataTypeChoice() throws Exception{
		SelectBox choice = new SelectBox();
		// name , value
		choice.setId("expression");
		choice.add("선택", "");
		choice.add("Text", "text");
		choice.add("Number", "number");
		choice.add("Date", "date");
		choice.add("Yes or No", "Yes or No");
		choice.add("ComplexType" ,"complexType");
		setDataType(choice);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		ParameterContext contexts = new ParameterContext();
		TextContext text = new TextContext();
		String selectedText = this.getDataType().getSelectedText();
		ConditionInput expVal = this.getConditionInput();
		Class selectedType = null;
		if( selectedText != null && ( selectedText.equalsIgnoreCase("Text") || selectedText.equalsIgnoreCase("Number")) ){
			text.setText(expVal.getExpressionText());
			if( selectedText.equalsIgnoreCase("Text") ){
				selectedType = String.class;
			}else{
				selectedType = Number.class;
			}
		}else if( selectedText != null && selectedText.equalsIgnoreCase("Yes or No") ){
			selectedText = expVal.getYesNo();
			selectedType = String.class;
		}else if( selectedText != null && selectedText.equalsIgnoreCase("Date") ){
			selectedText = expVal.getExpressionDate().toString();
			selectedType = Date.class;
		}else if( selectedText != null && selectedText.equalsIgnoreCase("ComplexType") ){
			selectedType = ComplexType.class;
			selectedText = "";
		}else{
			throw new MetaworksException("타입을 선택하셔야 합니다.");
		}
		
		
		ProcessVariable pv = new ProcessVariable();
		pv.setType(selectedType);
		
		contexts.setArgument(text);
		contexts.setVariable(pv);
		
		return new Object[]{new Remover(new Popup() , true) , new ToAppend(new ParameterContext() ,contexts)};
	}
}
