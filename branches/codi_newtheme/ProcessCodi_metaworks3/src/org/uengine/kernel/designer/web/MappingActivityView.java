package org.uengine.kernel.designer.web;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.kernel.Activity;
import org.uengine.kernel.IDrawDesigne;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ReceiveActivity;

public class MappingActivityView extends ActivityView {

	@Override
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = new ModalWindow();
		
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
			
		}
		activity.setActivityView(this);
		
		popup.setTitle(activity.getDescription() != null ? activity.getDescription().getText() : activity.getName().getText() + "[" + activity.getTracingTag() + "]");
		popup.setPanel(activity);
		popup.setWidth(900);
		popup.setHeight(700);
		
		return popup;
		
	}
}
