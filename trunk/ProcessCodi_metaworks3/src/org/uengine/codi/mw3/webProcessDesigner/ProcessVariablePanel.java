package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.kernel.ProcessVariable;

public class ProcessVariablePanel implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String editorId;
	@Id
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
	String parentEditorId;
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}

	ArrayList<ProcessVariable> variableList;
		public ArrayList<ProcessVariable> getVariableList() {
			return variableList;
		}
		public void setVariableList(ArrayList<ProcessVariable> variableList) {
			this.variableList = variableList;
		}
	ProcessVariable editedProcessVariable;	
		@Hidden
		public ProcessVariable getEditedProcessVariable() {
			return editedProcessVariable;
		}
		public void setEditedProcessVariable(ProcessVariable editedProcessVariable) {
			this.editedProcessVariable = editedProcessVariable;
		}
		
	public ProcessVariablePanel(){
		setMetaworksContext(new MetaworksContext());
		variableList = new ArrayList<ProcessVariable>();
	}
	
	@ServiceMethod(callByContent=true)
	public void addWholeVariable(){
		if( editedProcessVariable != null ){
			editedProcessVariable.setMetaworksContext(new MetaworksContext());
			editedProcessVariable.getMetaworksContext().setHow("menu");
			editedProcessVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			if( !this.getVariableList().contains(editedProcessVariable)){
				this.getVariableList().add(editedProcessVariable);
			}
		}
	}
	@ServiceMethod(callByContent=true)
	public void addVariable(){
		if( editedProcessVariable != null ){
			editedProcessVariable.setMetaworksContext(new MetaworksContext());
			editedProcessVariable.getMetaworksContext().setHow("list");
			editedProcessVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			if( !this.getVariableList().contains(editedProcessVariable)){
				this.getVariableList().add(editedProcessVariable);
			}
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showWholeVariable(){
		
		ProcessVariablePanel processVariablePanel = new ProcessVariablePanel();
		processVariablePanel.setEditorId(editorId+"_dummy");
		processVariablePanel.setParentEditorId(editorId);
		processVariablePanel.setVariableList(this.getVariableList());
		
		processVariablePanel.getMetaworksContext().setHow("list");
		processVariablePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(500);
		modalWindow.setHeight(500);
		modalWindow.setTitle("변수정의 리스트");
		modalWindow.setPanel(processVariablePanel);
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object addVariablePopup(){
		
		editedProcessVariable = new ProcessVariable();
		editedProcessVariable.setCurrentEditorId(this.getParentEditorId());
		editedProcessVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		editedProcessVariable.getMetaworksContext().setHow("setting");
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		modalWindow.setTitle("");
		modalWindow.setPanel(editedProcessVariable);
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object editVariable(){
		int selectedIdx = variableList.indexOf(editedProcessVariable);
		ProcessVariable processVariable = variableList.get(selectedIdx);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		modalWindow.setTitle("");
		modalWindow.setPanel(processVariable);
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] removeVariable(){
		if( editedProcessVariable != null && editedProcessVariable.getName() != null ){
			this.getVariableList().remove(editedProcessVariable);
			if( wholeProcessVariablePanel != null ){
				wholeProcessVariablePanel.getVariableList().remove(editedProcessVariable);
				return new Object[]{this, wholeProcessVariablePanel};
			}
			return new Object[]{this};
		}
		
		return null;
	}
	
	@AutowiredFromClient(select="typeof parentEditorId!='undefined' && parentEditorId==autowiredObject.editorId")
	transient public ProcessVariablePanel wholeProcessVariablePanel;
}
