package org.uengine.codi.mw3.model;

import java.lang.reflect.Method;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;

public class OrganizationPerspectiveApp extends Perspective{
	
	public OrganizationPerspectiveApp(){
		setLabel("App");
		
	}
	
	
	IAppMapping appList;
		public IAppMapping getAppList() {
			return appList;
		}
	
		public void setAppList(IAppMapping appList) {
			this.appList = appList;
		}
		

	protected void loadChildren() throws Exception {
		
		AppMapping appMp = new AppMapping();
		appMp.setComCode(session.getCompany().getComCode());
		appMp.setIsDeleted(false);
		
		IAppMapping addedApps =appMp.findMyApps();
		addedApps.getMetaworksContext().setWhen("filter");
		
		setAppList(addedApps);
		
	}

	protected void unloadChildren() throws Exception {
		setAppList(null);
	}
	
	@ServiceMethod(callByContent=true)
	public void openApp() throws Exception {
		
		App app = new App();
		app.setAppId(this.getAppList().getAppId());
		
		String url = app.findMe().getUrl();
		
        String os = System.getProperty("os.name");
        Runtime runtime = Runtime.getRuntime();
 
        try {
        	
        	// Block for Windows Platform
        	if (os.startsWith("Windows")) {
        		
        		String cmd = "rundll32 url.dll,FileProtocolHandler " + url;
        		Process p = runtime.exec(cmd);
        	}
        	
        	// Block for Mac OS
        	else if (os.startsWith("Mac OS")) {
        		
        		Class fileMgr = Class.forName("com.apple.eio.FileManager");
        		Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });

        		openURL.invoke(null, new Object[] { url });
        		
        	}
        	
        } catch (Exception x) {
        	
        	System.err.println("Exception occurd while invoking Browser!");
        	x.printStackTrace();
        	
        }
		
		
	}
	
}
