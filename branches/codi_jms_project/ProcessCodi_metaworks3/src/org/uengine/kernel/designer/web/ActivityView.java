package org.uengine.kernel.designer.web;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.compare.CompareOriginFilePanel;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;
import org.uengine.kernel.Activity;
import org.uengine.kernel.IDrawDesigne;
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
	transient ArrayList<ProcessVariable> wholeVariableList;
	@Hidden
		public ArrayList<ProcessVariable> getWholeVariableList() {
			return wholeVariableList;
		}
		public void setWholeVariableList(ArrayList<ProcessVariable> wholeVariableList) {
			this.wholeVariableList = wholeVariableList;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		Popup popup = new Popup();
		
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		
		ArrayList<ProcessVariable> activityVariableList = null;
		if( activity != null ){
			Class paramClass = activity.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigne)activity).drawInit();
			}
			
			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
			if( isReceiveActivity ){
				ParameterContext[] contexts = ((ReceiveActivity)activity).getParameters();
				if( contexts != null && contexts.length > 0){
					activityVariableList = new ArrayList<ProcessVariable>();
					for(int i=0; i < contexts.length; i++){
						ProcessVariable processVariable = contexts[i].getVariable();
						processVariable.setMetaworksContext(new MetaworksContext());
						processVariable.getMetaworksContext().setHow("list");
						processVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
						activityVariableList.add(processVariable);
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
		parameterContextPanel.setWholeVariableList(wholeVariableList);
		parameterContextPanel.setActivityVariableList(activityVariableList);
		parameterContextPanel.load();
		
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		activityWindow.getActivityPanel().setParameterContextPanel(parameterContextPanel);
		popup.setPanel(activityWindow);
		popup.setWidth(700);
		popup.setHeight(500);
		
		return popup;
	}
	
	@ServiceMethod
	public ModalWindow showDefinitionMonitor() throws Exception {
		return null;
	}

	@AutowiredFromClient
	public ProcessAttributePanel processAttributePanel;

	@ServiceMethod(callByContent = true)
	public Object[] showActivityDocument() {
		if( processAttributePanel != null ){
			Documentation documentation = (Documentation)this.getActivity().getDocumentation();
			documentation.setMetaworksContext(new MetaworksContext());
			documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			documentation.getDescription().setMetaworksContext(new MetaworksContext());
			documentation.getDescription().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			processAttributePanel.setDefId(null);
			processAttributePanel.setFileList(null);
			processAttributePanel.setDocumentation(documentation);
			return new Object[] { processAttributePanel };
		}else{
			return null;
		}
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
