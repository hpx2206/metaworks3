package org.uengine.kernel;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

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
			
			ProcessVariable val2 = new ProcessVariable();
			val2.setName("3333");
			val2.setDisplayName("3333");
			
			ProcessVariable val3 = new ProcessVariable();
			val3.setName("4444");
			val3.setDisplayName("4444");
			
			activityVariableList.add(val2);
			activityVariableList.add(val3);
		}
		
		ProcessVariable val1 = new ProcessVariable();
		val1.setName("2222");
		val1.setDisplayName("2222");
		
		wholeVariableList.add(val1);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] addWholeVariable() throws Exception{
		return null;
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
