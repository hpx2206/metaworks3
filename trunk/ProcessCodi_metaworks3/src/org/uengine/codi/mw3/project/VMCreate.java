package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.uengine.codi.ITool;

public class VMCreate implements ITool {
	
	String ip;
		@Face(displayName="VM IP:")
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}

	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
