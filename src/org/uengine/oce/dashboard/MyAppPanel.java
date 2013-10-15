package org.uengine.oce.dashboard;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalPanel;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.marketplace.MyVendor;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;

public class MyAppPanel {
	
	private final int MYAPPS_LIMINTCOUNT = 5;
	
	public MyAppPanel() {
		this.setMyAppsList(new ArrayList<AppMapping>());
	}
	
	
	ArrayList<AppMapping> myAppsList;
		public ArrayList<AppMapping> getMyAppsList() {
			return myAppsList;
		}
		public void setMyAppsList(ArrayList<AppMapping> myAppsList) {
			this.myAppsList = myAppsList;
		}

	@AutowiredFromClient
	Session session;
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object[] load() throws Exception {
		
		AppMapping myapps = new AppMapping();
		
		myapps.setComCode(session.getCompany().getComCode());
		myapps.setIsDeleted(false);
		myapps.session = session;
		IAppMapping getAppsList = myapps.findMyApps(MYAPPS_LIMINTCOUNT);
		
		while(getAppsList.next()){
			
			AppMapping app = new AppMapping();
			
			app.setAppId(getAppsList.getAppId());
			app.setAppName(getAppsList.getAppName());
			app.setComCode(getAppsList.getComCode());
			app.setIsDeleted(getAppsList.getIsDeleted());
			
			app.setMetaworksContext(new MetaworksContext());
			app.getMetaworksContext().setWhere(OceMain.WHERE_HOME);

			myAppsList.add(app);
			
		}
		
		return new Object[]{myAppsList};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object showAll() throws Exception {
		
		MyVendor myVendor = new MyVendor();
		
		myVendor.setMetaworksContext(new MetaworksContext());
		myVendor.getMetaworksContext().setWhere(OceMain.WHERE_HOME);
		myVendor.load(session);
		
		return new ModalWindow(new ModalPanel(myVendor), 0, 0, GlobalContext.getLocalizedMessage("$MyApps.all"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object addApp() throws Exception {
		
		App app = new App();
		app.load();
		app.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return new ModalWindow(new ModalPanel(app), 0, 0, GlobalContext.getLocalizedMessage("$MyApps.add"));
	}
	
	
}
	