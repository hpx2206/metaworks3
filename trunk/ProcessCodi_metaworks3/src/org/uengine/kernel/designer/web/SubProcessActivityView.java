package org.uengine.kernel.designer.web;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.process.RequestApproval;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;

public class SubProcessActivityView extends ActivityView{
	
	@Override
	@ServiceMethod(payload={"propertiesWindow"}, target="popup")
	public PropertiesWindow showProperties() throws Exception{
		
		RequestApproval aa = new RequestApproval();
		
		propertiesWindow.setPanel(aa);
//		Object activityObject = propertiesWindow.getPanel();
		
		
//		if( activityObject != null ){
//			Class paramClass = activityObject.getClass();
//			// 현재 클레스가 IDrawDesigne 인터페이스를 상속 받았는지 확인
//			boolean isDesigner = IDrawDesigne.class.isAssignableFrom(paramClass);
//			if( isDesigner ){
//				((IDrawDesigne)activityObject).drawInit();
//			}
//		}
		return this.getPropertiesWindow();
	}
}
