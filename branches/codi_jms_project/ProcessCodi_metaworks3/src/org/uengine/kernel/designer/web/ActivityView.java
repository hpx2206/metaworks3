package org.uengine.kernel.designer.web;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;
import org.uengine.kernel.Activity;

public class ActivityView extends CanvasDTO  implements ContextAware{
	MetaworksContext metaworksContext;
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
		
	String activityClass;
		public String getActivityClass() {
			return activityClass;
		}
		public void setActivityClass(String activityClass) {
			this.activityClass = activityClass;
		}
		
	String classType;
		public String getClassType() {
			return classType;
		}
		public void setClassType(String classType) {
			this.classType = classType;
		}
	String roleName;
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
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
		
	transient PropertiesWindow propertiesWindow;
	@Hidden
		public PropertiesWindow getPropertiesWindow() {
			return propertiesWindow;
		}
		public void setPropertiesWindow(PropertiesWindow propertiesWindow) {
			this.propertiesWindow = propertiesWindow;
		}
	
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		/*
		Object activityObject = propertiesWindow.getPanel();
		if( activityObject != null ){
			Class paramClass = activityObject.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigne)activityObject).drawInit();
			}
		}
		return this.getPropertiesWindow();
		*/
			
//		ModalWindow popup = new ModalWindow();
		Popup popup = new Popup();
		
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		activity.setActivityView(this);
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		
		popup.setPanel(activityWindow);
		popup.setWidth(700);
		popup.setHeight(500);
		
		return popup;
	}
	
	@ServiceMethod
	public ModalWindow showDefinitionMonitor() throws Exception{
		return null;
	 }

}
