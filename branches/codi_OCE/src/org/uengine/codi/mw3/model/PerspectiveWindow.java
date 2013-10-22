package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.oce.OcePerspectivePanel;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs",  
options={"hideLabels", "minimize", "color", "hideTitleBar"}, 
values={"true", "true", "blue", "true"})
public class PerspectiveWindow extends Window {
	
	public PerspectiveWindow() throws Exception {
		this(null);		
	}
	
	public PerspectiveWindow(Session session) throws Exception {
		
		//oce의 경우 ocepersppective 로 이동합니다.
		if("oce".equals(session.getUx())){
			//oce dashboard에서 codi 서비스를 이용하고자 할경우 codi의 perspective를 가지고 옵니다.
			if(session.getLastSelectedItem() != null && OceMain.ACTION_DASHBOARD.equals(session.getLastSelectedItem())){
				setPanel(new OcePerspectivePanel(session));
			}else{
				setPanel(new PerspectivePanel(session));
			}
		}else{
			setPanel(new PerspectivePanel(session));
		}
	}

}
