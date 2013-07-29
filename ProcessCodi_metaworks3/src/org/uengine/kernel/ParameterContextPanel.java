package org.uengine.kernel;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.ModalWindow;
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
		
	HumanActivity humanActivity;
		public HumanActivity getHumanActivity() {
			return humanActivity;
		}
		public void setHumanActivity(HumanActivity humanActivity) {
			this.humanActivity = humanActivity;
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
		choice.setId("expression");
		choice.add("선택", "");
		choice.add("Text", "text");
		choice.add("Number", "number");
		choice.add("Date", "date");
		choice.add("Yes or No", "Yes or No");
//		choice.add("File", "File");
//		choice.add("Activity Selection", "Activity Selection");
//		choice.add("Knowledge Type" ,"knowledgelType");
//		choice.add("Process Variable" ,"variable");
		choice.add("Complex Type" ,"complexType");
		choice.add("Html Form" ,"htmlType" );
		setDataType(choice);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		ParameterContext[] contexts = new ParameterContext[1];
		ParameterContext pmc = new ParameterContext();
		
		TextContext text = new TextContext();
		
		ProcessVariable pv = new ProcessVariable();
		pv.setType(ComplexType.class);
		
		pmc.setArgument(text);
		pmc.setVariable(pv);
		contexts[0] = pmc;
		
		HumanActivity humanActivity = this.getHumanActivity();
		humanActivity.setParameters(contexts);
		//  , new Refresh(humanActivity)
		return new Object[]{new Remover(new ModalWindow() , true)};
	}
}
