package org.uengine.codi.mw3.admin;

import java.lang.reflect.Method;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.Knowledge;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.MainSNS;
import org.uengine.codi.mw3.model.PinterestMain;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmarket.Market;

public class PageNavigator{

	@AutowiredFromClient
	public Session session;
	
	public PageNavigator() {		
	}
	
	public PageNavigator(String pageName) {
		setPageName(pageName);
	}
	
	String pageName;
		@Hidden
		public String getPageName() {
			return pageName;
		}	
		public void setPageName(String pageName) {
			this.pageName = pageName;
		}

	@ServiceMethod(callByContent=true)
	public MainPanel goIDE() throws Exception {		
		return new MainPanel(new IDE(session));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcess() throws Exception {
		String preferUX = session.getEmployee().getPreferUX();
		if("sns".equals(preferUX)){
			return goSns();
		}else{
			return new MainPanel(new Main(session));
		}
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goSns() throws Exception {
		return new MainPanel(new MainSNS(session));
	}

	@ServiceMethod(callByContent=true)
	public MainPanel goPinterest() throws Exception {
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52");
		outerLayout.setCenter(new PinterestMain(session));
		
		return new MainPanel(outerLayout);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goMarket() throws Exception {
		
		Market market = new Market();
		market.load();
		
		return new MainPanel(market);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goMarketplace() throws Exception {
		
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.load();
		
		return new MainPanel(marketplace);
	}
	
	@ServiceMethod(callByContent=true)
	public void goTadPole() throws Exception {
		
		String host = GlobalContext.getPropertyString("pole.server.host");
		String port = GlobalContext.getPropertyString("pole.server.port");
		String uri  = GlobalContext.getPropertyString("pole.call.uri");

		String url = "http://" + host + ":" + port + uri;
		
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
	

	@ServiceMethod(callByContent=true)
	@Test(scenario="first", starter=true, instruction="<br><br><br><br><br><br>R&D 지식모드 (Knowledge Mode) 로 넘어갑니다..", next="autowiredObject.org.uengine.codi.mw3.knowledge.WfNode.add()")	
	public MainPanel goKnowledge() throws Exception {
		return new MainPanel(new Knowledge(session));
	}
	
	
}
