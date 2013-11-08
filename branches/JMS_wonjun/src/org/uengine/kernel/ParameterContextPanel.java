package org.uengine.kernel;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel;

public class ParameterContextPanel  implements ContextAware{
	
	@AutowiredFromClient(select="typeof parentEditorId!='undefined' && parentEditorId==autowiredObject.editorId")
	transient public ProcessVariablePanel wholeProcessVariablePanel;
	
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
	ParameterContext[] parameterContext;
		public ParameterContext[] getParameterContext() {
			return parameterContext;
		}
		public void setParameterContext(ParameterContext[] parameterContext) {
			this.parameterContext = parameterContext;
		}
	int selectedIndex; 
		public int getSelectedIndex() {
			return selectedIndex;
		}
		public void setSelectedIndex(int selectedIndex) {
			this.selectedIndex = selectedIndex;
		}
	public ParameterContextPanel(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen("edit");
	}
	
	public void load() throws Exception{
		if( parameterContext == null ){
			parameterContext = new ParameterContext[0];
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object addActivityVariable() throws Exception{
		
		ParameterContext parameterContext = new ParameterContext();
		parameterContext.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		parameterContext.getMetaworksContext().setHow("add");
		parameterContext.setParentEditorId(this.getParentEditorId());
		
		SelectBox valBox = new SelectBox();
		
		if( wholeProcessVariablePanel != null ){
			ArrayList<ProcessVariable> variableList = wholeProcessVariablePanel.getVariableList();
			for(int i=0; i < variableList.size() ; i++){
				ProcessVariable processVariable = variableList.get(i);
				valBox.add(processVariable.getDisplayName().getText(), processVariable.getName());
			}
		}
		parameterContext.setType(valBox);
		// TODO role 추가
		// TODO instance 정보 추가
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		modalWindow.setTitle("");
		modalWindow.setPanel(parameterContext);
		
		return modalWindow;
	}
	@ServiceMethod(callByContent=true)
	public void removeActivityVariable() throws Exception{
		if( selectedIndex >= 0){
			ParameterContext[] parameterContexts = this.getParameterContext();
			parameterContexts[selectedIndex] = null;
			
			// array null remove
			ArrayList<ParameterContext> removed = new ArrayList<ParameterContext>();
			for (ParameterContext val : parameterContexts){
				if (val != null){
					removed.add(val);
				}
			}
			this.setParameterContext(removed.toArray(new ParameterContext[0]));
		}
	}
	
}
