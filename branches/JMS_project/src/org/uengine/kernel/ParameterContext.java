package org.uengine.kernel;

import java.io.Serializable;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ObjectType;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.Type;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.inputter.RadioInput;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.webProcessDesigner.ProcessVariablePanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.processdesigner.mapper.Transformer;
import org.uengine.processdesigner.mapper.TransformerMapping;

/**
 * @author Jinyoung Jang
 */
public class ParameterContext implements Serializable , ContextAware{
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static final String DIRECTION_IN = "in".intern();
	public static final String DIRECTION_OUT = "out".intern();
	public static final String DIRECTION_INOUT = "in-out".intern();
	
	public static void metaworksCallback_changeMetadata(Type type){
		FieldDescriptor fd;
				
		fd = type.getFieldDescriptor("Direction");		
		fd.setInputter(new RadioInput(
			new String[]{
				GlobalContext.getLocalizedMessage("parametercontext.direction.in.displayname", "in"),
				GlobalContext.getLocalizedMessage("parametercontext.direction.out.displayname", "out"),
				GlobalContext.getLocalizedMessage("parametercontext.direction.inout.displayname", "in-out")
			},
			new Object[]{
				DIRECTION_IN, 
				DIRECTION_OUT, 
				DIRECTION_INOUT, 
			}
		));
	}
	
	public ParameterContext(){
		this.setMetaworksContext(new MetaworksContext());
	}
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	TextContext argument = TextContext.createInstance();
		public TextContext getArgument() {
			if(argument.getText()==null && getVariable()!=null){
				return getVariable().getDisplayName();
			}
			
			return argument;
		}
		public void setArgument(TextContext string) {
			argument = string;
		}

	ProcessVariable variable;
		public ProcessVariable getVariable() {
			return variable;
		}
		public void setVariable(ProcessVariable variable) {
			this.variable = variable;
		}

	transient Object type;
		public Object getType() {
			return type;
		}
		public void setType(Object name) {
			type = name;
		}
	
	String direction;
		public String getDirection() {
			return direction;
		}
		public void setDirection(String i) {
			direction = i;
		}
		
	TransformerMapping transformerMapping;
		public TransformerMapping getTransformerMapping() {
			return transformerMapping;
		}
		public void setTransformerMapping(TransformerMapping transformerMapping) {
			this.transformerMapping = transformerMapping;
		}	
		
	transient String parentEditorId;
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}	
	@AutowiredFromClient
	transient public ParameterContextPanel parameterContextPanel;
	/*
	 * 전체 변수를 확가지고 있는 ProcessVariablePanel
	 * 현재 ProcessVariable 이 팝업창으로 띄어져 있을때, 체크되어 사용된다.
	 */
	@AutowiredFromClient(select="typeof parentEditorId!='undefined' && parentEditorId==autowiredObject.editorId")
	transient public ProcessVariablePanel wholeProcessVariablePanel;
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$Save")
	public Object[] saveVariable() throws Exception{
		ArrayList<Object> returnList = new ArrayList<Object>();
		returnList.add(new Remover(new ModalWindow() , true));
		
		
		if( parameterContextPanel != null ){
			this.getMetaworksContext().setHow("list");
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			ParameterContext[] parameterContexts = parameterContextPanel.getParameterContext();
			ParameterContext[] finalParameterContexts = new ParameterContext[parameterContexts.length+1];
			System.arraycopy(parameterContexts, 0, finalParameterContexts, 0, parameterContexts.length);
			
			if( wholeProcessVariablePanel != null ){
				ProcessVariable processVariable = new ProcessVariable();
				processVariable.setName(((SelectBox)this.getType()).getSelected());
				ArrayList<ProcessVariable> variableList = wholeProcessVariablePanel.getVariableList();
				this.setVariable(variableList.get(variableList.indexOf(processVariable)));
			}
			
			finalParameterContexts[parameterContexts.length] = this;
			parameterContextPanel.parameterContext = finalParameterContexts;
			returnList.add(new Refresh(parameterContextPanel));
		}
		Object[] returnObject = new Object[returnList.size()];
		for( int i =0; i < returnList.size(); i++ ){
			returnObject[i] = returnList.get(i);
		}
		return returnObject;
	}
}
