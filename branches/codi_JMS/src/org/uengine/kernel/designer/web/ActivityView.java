package org.uengine.kernel.designer.web;

import java.io.Serializable;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.ide.compare.CompareOriginFilePanel;
import org.uengine.codi.mw3.model.ParameterValue;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.processexplorer.ProcessFormPanel;
import org.uengine.codi.mw3.processexplorer.ProcessSubAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.DocumentationSub;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.IDrawDesigne;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ParameterContextPanel;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.ReceiveActivity;

public class ActivityView extends CanvasDTO  implements ContextAware{
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	
	
	String tracingTag;
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}
	
	/*  viewer 부분에서  필요한 정보들 transient */
	transient String instStatus;
		public String getInstStatus() {
			return instStatus;
		}
		public void setInstStatus(String instStatus) {
			this.instStatus = instStatus;
		}
	transient String backgroundColor;
		public String getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(String backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
	transient Activity activity;
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}

	String tooltip;
		public String getTooltip() {
			return tooltip;
		}
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}
	transient Object element;
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
	transient	boolean drawByCanvas;
		public boolean isDrawByCanvas() {
			return drawByCanvas;
		}
		public void setDrawByCanvas(boolean drawByCanvas) {
			this.drawByCanvas = drawByCanvas;
		}

	transient PropertiesWindow propertiesWindow;
	@Hidden
		public PropertiesWindow getPropertiesWindow() {
			return propertiesWindow;
		}
		public void setPropertiesWindow(PropertiesWindow propertiesWindow) {
			this.propertiesWindow = propertiesWindow;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showProperties() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		
		ParameterContext[] contexts = null;
		if( activity != null ){
			Class paramClass = activity.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigne)activity).setParentEditorId(this.getEditorId());
				((IDrawDesigne)activity).drawInit();
			}
			
			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
			if( isReceiveActivity ){
				contexts = ((ReceiveActivity)activity).getParameters();
				if( contexts != null ){
					for(int i=0; i < contexts.length; i++){
						contexts[i].setMetaworksContext(new MetaworksContext());
						contexts[i].getMetaworksContext().setHow("list");
					}
				}
			}
			
		}
		activity.setActivityView(this);
		
		if( "definitionDiffView".equals(this.getViewType()) ){
			activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			activity.getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		}else{
			activity.setMetaworksContext(new MetaworksContext());
			activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			activity.getDocumentation().setMetaworksContext(new MetaworksContext());
			activity.getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}
		// 변수설정
		ParameterContextPanel parameterContextPanel = new ParameterContextPanel();
		parameterContextPanel.setParameterContext(contexts);
		parameterContextPanel.setEditorId(activity.getName() + "_" + activity.getTracingTag());
		parameterContextPanel.setParentEditorId(this.getEditorId());
		parameterContextPanel.load();
		
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		activityWindow.getActivityPanel().setParameterContextPanel(parameterContextPanel);
		modalWindow.setTitle("엑티비티 설정");
		modalWindow.setPanel(activityWindow);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		return modalWindow;
	}
	
	@ServiceMethod
	public ModalWindow showDefinitionMonitor() throws Exception {
		return null;
	}

	@AutowiredFromClient
	public ProcessAttributePanel processAttributePanel;

	@AutowiredFromClient
	public ProcessSubAttributePanel processSubAttributePanel;
	
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showActivityDocument() throws Exception {
		ModalWindow modalWindow = new ModalWindow();
		ParameterValue[] parameters = new ParameterValue[0];
		if( this.getActivity() != null &&  this.getActivity() instanceof HumanActivity ){
			HumanActivity humanActivity = (HumanActivity)this.getActivity();
			parameters = new ParameterValue[humanActivity.getParameters().length];
			for(int i=0; i<humanActivity.getParameters().length; i++){
				ParameterContext pc = humanActivity.getParameters()[i];
				
				parameters[i] = new ParameterValue();
				
				ParameterValue pv = parameters[i];
				pv.setVariableName(pc.getVariable().getName());
				pv.setArgument(pc.getArgument().getText());
									
				
				MetaworksContext mc = new MetaworksContext();
				
				mc.setWhen( MetaworksContext.WHEN_VIEW);					
				pv.setMetaworksContext(mc);
				
				
				Object processVariableValue = new Object();
				Class variableType = Class.forName(pc.getVariable().getTypeInputter());
//			
//				if(variableType == String.class){
//					parameters[i].setValueString((String) processVariableValue);
////				}else if(Long.class.isAssignableFrom(variableType)){
////					parameters[i].setValueNumber((Number) processVariableValue);
////				}else if(Calendar.class.isAssignableFrom(variableType)){
////					parameters[i].setValueCalendar((Calendar) processVariableValue);
//				}else 
				
				if(variableType == ComplexType.class){
						ComplexType complexType = (ComplexType)pc.getVariable().getDefaultValue();
						complexType.setDesignerMode(false);
						processVariableValue = (Serializable) complexType.getTypeClass().newInstance();
						
						if(processVariableValue instanceof ContextAware){
							((ContextAware)processVariableValue).setMetaworksContext(mc);
						}
						
						if(processVariableValue instanceof NeedArrangementToSerialize){
							((NeedArrangementToSerialize)processVariableValue).afterDeserialization();
						}
						
						if(processVariableValue instanceof ITool){
							((ITool)processVariableValue).onLoad();
						}
					
				}else{
					
					if(variableType==Boolean.class){
						processVariableValue = new Boolean(false);
					}else if(variableType==Number.class){
						processVariableValue = new Integer(0);
					}else if(variableType==String.class){
						processVariableValue = new String();
					}else
						processVariableValue = (Serializable) variableType.newInstance();
				}
				
				pv.setValueObject(processVariableValue);
			}
			
			TransactionContext.getThreadLocalInstance().setSharedContext(
					ITool.ITOOL_MAP_KEY, null);
		}
		modalWindow.setPanel(parameters);
		modalWindow.setTitle("액티비티뷰");
		modalWindow.setWidth(400);
		modalWindow.setHeight(400);
		
		return modalWindow;
	}
	
	
	@ServiceMethod(callByContent = true)
	public ModalWindow showActiDocument() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		
		ParameterContext[] contexts = null;
		if( activity != null ){
			Class paramClass = activity.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigne)activity).setParentEditorId(this.getEditorId());
				((IDrawDesigne)activity).drawInit();
			}
			
			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
			if( isReceiveActivity ){
				contexts = ((ReceiveActivity)activity).getParameters();
				if( contexts != null ){
					for(int i=0; i < contexts.length; i++){
						contexts[i].setMetaworksContext(new MetaworksContext());
						contexts[i].getMetaworksContext().setHow("list");
					}
				}
			}
			
		}
		activity.setActivityView(this);
		
		activity.setMetaworksContext(new MetaworksContext());
		activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		activity.getDocumentation().setMetaworksContext(new MetaworksContext());
		activity.getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		// 변수설정
		ParameterContextPanel parameterContextPanel = new ParameterContextPanel();
		parameterContextPanel.setParameterContext(contexts);
		parameterContextPanel.setEditorId(activity.getName() + "_" + activity.getTracingTag());
		parameterContextPanel.setParentEditorId(this.getEditorId());
		parameterContextPanel.load();
		
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		activityWindow.getActivityPanel().setParameterContextPanel(parameterContextPanel);
		modalWindow.setPanel(activityWindow);
		modalWindow.setTitle("액티비티설정");
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		return modalWindow;
	}
	
	
	@ServiceMethod(callByContent = true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] copyRightToLeft() {
		Long ID_PREFIX = Math.round(Math.random() * 10000);
		this.setId("OG_" + ID_PREFIX + "_0");
		this.setEditorId(CompareOriginFilePanel.FILE_LOCATION);
		this.setViewType("definitionDiffEdit");
		return new Object[]{this} ;
	}
}
