package org.uengine.codi.mw3.common;

import org.uengine.codi.mw3.admin.TopPanel;
import org.uengine.codi.mw3.model.PersonalPerspective;

public class MainPanel {

	TopPanel topPanel;
	public TopPanel getTopPanel() {
		return topPanel;
	}
	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}
	
	PersonalPerspective personalPerspective;
	public PersonalPerspective getPersonalPerspective() {
		return personalPerspective;
	}

	public void setPersonalPerspective(PersonalPerspective personalPerspective) {
		this.personalPerspective = personalPerspective;
	}

	Object appPanel;
		public Object getAppPanel() {
			return appPanel;
		}
		public void setAppPanel(Object appPanel) {
			this.appPanel = appPanel;
		}
		
	public MainPanel(){
		
	}
	
	public MainPanel(Object appPanel){
		this(null, appPanel);
	}

	public MainPanel(TopPanel topPanel, Object appPanel){
		setTopPanel(topPanel);
		setAppPanel(appPanel);
	}
}
