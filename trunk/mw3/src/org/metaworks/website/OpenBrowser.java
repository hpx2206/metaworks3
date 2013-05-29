package org.metaworks.website;

public class OpenBrowser {
	
	public OpenBrowser(String url) {
		this.setOpenUrl(url);
	}
	
	String openUrl;
		public String getOpenUrl() {
			return openUrl;
		}
		public void setOpenUrl(String openUrl) {
			this.openUrl = openUrl;
		}

}
