package org.uengine.kernel;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.contexts.ComplexType;

public class ParameterContextPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	ArrayList<ProcessVariable> wholeVariableList;
		public ArrayList<ProcessVariable> getWholeVariableList() {
			return wholeVariableList;
		}
		public void setWholeVariableList(ArrayList<ProcessVariable> wholeVariableList) {
			this.wholeVariableList = wholeVariableList;
		}
		
	ArrayList<ProcessVariable> activityVariableList;
		public ArrayList<ProcessVariable> getActivityVariableList() {
			return activityVariableList;
		}
		public void setActivityVariableList(
				ArrayList<ProcessVariable> activityVariableList) {
			this.activityVariableList = activityVariableList;
		}
		
	public ParameterContextPanel(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen("edit");
	}
	
	public void load() throws Exception{
		if( wholeVariableList == null ){
			wholeVariableList = new ArrayList<ProcessVariable>();
		}
		if( activityVariableList == null ){
			activityVariableList = new ArrayList<ProcessVariable>();
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] addWholeVariable() throws Exception{
		
		ProcessVariable newVariable = new ProcessVariable();
		newVariable.setMetaworksContext(new MetaworksContext());
		newVariable.getMetaworksContext().setHow("setting");
		newVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		newVariable.changeType();
		
		ModalWindow popup = new ModalWindow();
		popup.setWidth(500);
		popup.setHeight(500);
		popup.setTitle("변수추가");
		popup.setPanel(newVariable);
		
		return new Object[]{popup};
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] removeWholeVariable() throws Exception{
		return null;
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] addActivityVariable() throws Exception{
		return null;
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] removeActivityVariable() throws Exception{
		return null;
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		return null;
	}
}
