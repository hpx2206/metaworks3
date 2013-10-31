package org.uengine.codi.mw3.nkia;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class Nkia {
	
	@AutowiredFromClient
	public Session session;
	
	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
		
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}		

		
	@ServiceMethod
	public void load() throws Exception {
		
		NkiaTopPanel top = new NkiaTopPanel(session);
		
		
		String url  = GlobalContext.getPropertyString("nkia.call.url");
		
		IFrame goNkia = new IFrame();
		goNkia.setSrc(url);
		
		NkiaCenterPanel center = new NkiaCenterPanel();
		center.setTadpoleHome(goNkia);
		
		Layout mainLayout = new Layout();
		
		mainLayout.setName("center");
		mainLayout.setId("main");
		mainLayout.setCenter(center);
		
		Layout storeLayout = new Layout();
		storeLayout.setNorth(top);
		storeLayout.setCenter(mainLayout);
		
		this.setLayout(storeLayout);
		
		pageNavigator = new PageNavigator();
		
	}

}
