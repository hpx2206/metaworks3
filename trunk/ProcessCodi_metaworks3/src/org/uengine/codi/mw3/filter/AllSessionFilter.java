package org.uengine.codi.mw3.filter;

import java.util.HashMap;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class AllSessionFilter implements ScriptSessionFilter {

	HashMap<String, String> filteredSession;
		public HashMap<String, String> getFilteredSession() {
			return filteredSession;
		}
		public void setFilteredSession(HashMap<String, String> filteredSession) {
			this.filteredSession = filteredSession;
		}

	public AllSessionFilter(HashMap<String, String> allSession){
		this.filteredSession = allSession;
	}
	
	@Override
	public boolean match(ScriptSession scriptsession) {
		if(filteredSession != null && filteredSession.containsValue(scriptsession.getId()))
			return true;
		else
			return false;
	}

}
