package org.uengine.codi.mw3.model;

public class Application {
	
	public Session session;
	
	String topCenterPanelType;
		public String getTopCenterPanelType() {
			return topCenterPanelType;
		}
		public void setTopCenterPanelType(String topCenterPanelType) {
			this.topCenterPanelType = topCenterPanelType;
		}

	public Application(){
		this.setTopCenterPanelType(TopCenterPanel.HOW_TRAY);
	}
	
	public TopCenterPanel loadTopCenterPanel(Session session) throws Exception {
		TopCenterPanel contentTopPanel = new TopCenterPanel(this.getTopCenterPanelType());
		contentTopPanel.session = session;
		contentTopPanel.load();
		
		return contentTopPanel; 
	}
}
