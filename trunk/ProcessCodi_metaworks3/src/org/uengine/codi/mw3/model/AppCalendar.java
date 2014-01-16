package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.layout.Layout;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class AppCalendar {

	Layout content;
	public Layout getContent() {
		return content;
	}
	public void setContent(Layout content) {
		this.content = content;
	}

	public AppCalendar(Session session) throws Exception{
		Layout layout = new Layout();

		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.session = session;
		
		try{
			//instanceListPanel.switchToScheduleCalendar();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		InstanceListWindow instanceListWindow = new InstanceListWindow();
		instanceListWindow.setPanel(instanceListPanel);
		
		NewInstancePanel instancePanel = new NewInstancePanel();
		instancePanel.session = session;
		
		try{
			instancePanel.load(session);
		}catch(Exception e){
			e.printStackTrace();
		}

		ContentWindow contentWindow = new ContentWindow();
		contentWindow.setTitle("$New");
		contentWindow.setPanel(instancePanel);

		layout.setWest(instanceListWindow);
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, west__spacing_open:5, west__size:'40%'");
		layout.setCenter(contentWindow);
		
		this.setContent(layout);
	}
}
