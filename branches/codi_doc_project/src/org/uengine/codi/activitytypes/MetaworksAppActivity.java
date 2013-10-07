package org.uengine.codi.activitytypes;

import java.util.Map;

import org.uengine.contexts.MappingContext;
import org.uengine.kernel.FormActivity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;

public class MetaworksAppActivity extends MetaworksFormActivity{

	
	String appClass;
		public String getAppClass() {
			return appClass;
		}
		public void setAppClass(String appClass) {
			this.appClass = appClass;
		}

	@Override
	public String getTool() {
		// TODO Auto-generated method stub
		return "mw3." + getAppClass();
	}
	
	
}
