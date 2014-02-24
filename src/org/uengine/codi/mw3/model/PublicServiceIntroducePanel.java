package org.uengine.codi.mw3.model;


public class PublicServiceIntroducePanel {
	
	PublicServiceIntroduceTabPanel publicServiceIntroduceTabPanel;
		public PublicServiceIntroduceTabPanel getPublicServiceIntroduceTabPanel() {
			return publicServiceIntroduceTabPanel;
		}
		public void setPublicServiceIntroduceTabPanel(
				PublicServiceIntroduceTabPanel publicServiceIntroduceTabPanel) {
			this.publicServiceIntroduceTabPanel = publicServiceIntroduceTabPanel;
		}

	public void load() throws Exception {
		PublicServiceIntroduceTabPanel publicServiceIntroduceTabPanel = new PublicServiceIntroduceTabPanel();
		publicServiceIntroduceTabPanel.load();
		
		this.setPublicServiceIntroduceTabPanel(publicServiceIntroduceTabPanel);
	}
	
	public void refreshPanel() throws Exception {
		this.load();
		
	}
	
}
