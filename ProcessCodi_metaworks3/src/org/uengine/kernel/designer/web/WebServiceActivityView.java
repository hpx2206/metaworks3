package org.uengine.kernel.designer.web;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.kernel.Activity;
import org.uengine.kernel.IDrawDesigner;
import org.uengine.kernel.Pool;
import org.uengine.kernel.RestWebServiceActivity;
import org.uengine.webservice.WebServiceDefinition;

public class WebServiceActivityView extends ActivityView {
	
	transient Pool pool;
		public Pool getPool() {
			return pool;
		}
		public void setPool(Pool pool) {
			this.pool = pool;
		}

	@Override
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = new ModalWindow();
		
		ActivityWindow activityWindow = new ActivityWindow();
		Activity activity = (Activity)propertiesWindow.getPanel();
		if( activity.getMetaworksContext() == null ){
			activity.setMetaworksContext(new MetaworksContext());
			activity.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}
		if( activity != null ){
			Class paramClass = activity.getClass();
			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
			boolean isDesigner = IDrawDesigner.class.isAssignableFrom(paramClass);
			if( isDesigner ){
				((IDrawDesigner)activity).setParentEditorId(this.getEditorId());
				((IDrawDesigner)activity).drawInit();
			}
			
		}
		activity.setActivityView(this);
		if( activity instanceof RestWebServiceActivity){
			WebServiceDefinition webServiceDefinition = pool.getPoolResolutionContext().getWebServiceConnector().getWebServiceDefinition();
			webServiceDefinition.setParentActivity(activity);
			((RestWebServiceActivity) activity).setWebServiceDefinition(webServiceDefinition);
		}
		activityWindow.setId(this.getId());	// 꼭 필요함
		activityWindow.getActivityPanel().setActivity(activity);
		
		popup.setId(this.getId());				// 꼭 필요함
		popup.setTitle(activity.getDescription() != null ? activity.getDescription().getText() : activity.getName().getText() + "[" + activity.getTracingTag() + "]");
		popup.setPanel(activityWindow);
		popup.setWidth(1000);
		popup.setHeight(700);
		
		return popup;
	}
}
