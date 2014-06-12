package org.uengine.codi.mw3.model;

public class PublicServiceIntroducePanel {
	
	PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo;
		public PublicServiceIntroduceTabInfo getPublicServiceIntroduceTabInfo() {
			return publicServiceIntroduceTabInfo;
		}
		public void setPublicServiceIntroduceTabInfo(
				PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo) {
			this.publicServiceIntroduceTabInfo = publicServiceIntroduceTabInfo;
		}

	public void load() throws Exception {
		if(publicServiceIntroduceTabInfo == null)
			publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
		
		publicServiceIntroduceTabInfo.load();
		this.setPublicServiceIntroduceTabInfo(publicServiceIntroduceTabInfo);
	}
	
}
