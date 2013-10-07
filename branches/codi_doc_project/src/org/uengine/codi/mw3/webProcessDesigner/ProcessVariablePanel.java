package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.ProcessVariable;

public class ProcessVariablePanel {

	ArrayList<ProcessVariable> variableList;
		public ArrayList<ProcessVariable> getVariableList() {
			return variableList;
		}
		public void setVariableList(ArrayList<ProcessVariable> variableList) {
			this.variableList = variableList;
		}
	ProcessVariable addedProcessVariable;	
		@Hidden
		public ProcessVariable getAddedProcessVariable() {
			return addedProcessVariable;
		}
		public void setAddedProcessVariable(ProcessVariable addedProcessVariable) {
			this.addedProcessVariable = addedProcessVariable;
		}
		
	public ProcessVariablePanel(){
		variableList = new ArrayList<ProcessVariable>();
	}
	
	@ServiceMethod(callByContent=true)
	public void addVariable(){
		if( addedProcessVariable != null ){
			addedProcessVariable.setMetaworksContext(new MetaworksContext());
			addedProcessVariable.getMetaworksContext().setHow("list");
			addedProcessVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			if( !this.getVariableList().contains(addedProcessVariable)){
				this.getVariableList().add(addedProcessVariable);
			}
		}
	}
}
