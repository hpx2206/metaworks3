package org.uengine.codi.mw3.tadpole;


import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class Tadpole {
	
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
		
		//top
		TadpoleTopPanel top = new TadpoleTopPanel(session);
		
		
		//center
		String host = GlobalContext.getPropertyString("pole.server.host");
		String port = GlobalContext.getPropertyString("pole.server.port");
		String uri  = GlobalContext.getPropertyString("pole.call.uri");
		
		String url = "http://" + host + ":" + port + uri;
		
		IFrame goTadPole = new IFrame();
		goTadPole.setSrc(url);
		
		TadpoleCenterPanel center = new TadpoleCenterPanel();
		center.setTadpoleHome(goTadPole);
		
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
