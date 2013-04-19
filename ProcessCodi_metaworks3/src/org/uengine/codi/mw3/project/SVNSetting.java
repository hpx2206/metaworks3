package org.uengine.codi.mw3.project;

import org.uengine.codi.ITool;

public class SVNSetting implements ITool {
	
	String svnUrl;
		public String getSvnUrl() {
			return svnUrl;
		}	
		public void setSvnUrl(String svnUrl) {
			this.svnUrl = svnUrl;
		}

	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
