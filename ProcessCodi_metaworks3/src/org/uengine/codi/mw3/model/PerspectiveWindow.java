package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs",  
//nipa, 데모 반영 위해서 관점/친구 영역 컬러 원복합니다.(회색->파랑색)
/*
options={"hideLabels", "minimize", "color"}, 
values={"true", "true","gray"})
*/
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class PerspectiveWindow extends Window {
	
	public PerspectiveWindow() throws Exception {
		this(null);		
	}
	
	public PerspectiveWindow(Session session) throws Exception {
		setPanel(new PerspectivePanel(session));
		// title 을 '관점' 으로 셋팅해줌..
		setTitle("$Navigation");
	}

}
