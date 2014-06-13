package org.uengine.codi.mw3.model;

import java.util.Date;
import java.util.HashMap;

public class ClientSessions {

	public HashMap<String, ClientSessionInfo> clientSessionInfo = new HashMap<String, ClientSessionInfo>();
	
	Date lastUpdateTime;
		public Date getLastUpdateTime() {
			return lastUpdateTime;
		}
		public void setLastUpdateTime(Date lastUpdateTime) {
			this.lastUpdateTime = lastUpdateTime;
		}
		
	public HashMap<String, ClientSessionInfo> getClientSessionInfo(){
		return clientSessionInfo;
	}
	
}
