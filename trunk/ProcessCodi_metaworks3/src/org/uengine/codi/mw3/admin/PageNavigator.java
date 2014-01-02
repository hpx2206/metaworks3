package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.ide.CloudIDE;
import org.uengine.codi.mw3.knowledge.Knowledge;
import org.uengine.codi.mw3.marketplace.AppMap;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.MainLMS;
import org.uengine.codi.mw3.model.MainSNS;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.PinterestMain;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.processexplorer.ProcessExplorer;
import org.uengine.codi.mw3.selfservice.SelfService;
import org.uengine.codi.mw3.tadpole.Tadpole;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmarket.Market;

public class PageNavigator{
	
	public final static String USE_SNS = GlobalContext.getPropertyString("sns.use", "1");
	public final static String USE_IDE = GlobalContext.getPropertyString("ide.use", "1");
	public final static String USE_SELF_SERVICE_PORTAL = GlobalContext.getPropertyString("selfserviceportal.use", "0");
	public final static String USE_NKIA = GlobalContext.getPropertyString("nkia.use", "0");
	public final static String USE_TADPOLE = GlobalContext.getPropertyString("tadpole.use", "0");
	public final static String USE_KNOWLEDGE = GlobalContext.getPropertyString("knowledge.use", "1");
	public final static String USE_MARKETPLACE = GlobalContext.getPropertyString("marketplace.use", "0");
	

	@AutowiredFromClient
	public Session session;

	public PageNavigator() {
		this.setSns("1".equals(PageNavigator.USE_SNS));
		this.setSns("1".equals(PageNavigator.USE_IDE));
		this.setSns("1".equals(PageNavigator.USE_KNOWLEDGE));
	}
	
	public PageNavigator(String pageName) {
		this.setPageName(pageName);

		this.setSns("1".equals(PageNavigator.USE_SNS));
		this.setSns("1".equals(PageNavigator.USE_IDE));
		this.setSns("1".equals(PageNavigator.USE_KNOWLEDGE));
	}
	
	boolean admin;
		public boolean isAdmin() {
			return admin;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

	boolean sns;
		public boolean isSns() {
			return sns;
		}
		public void setSns(boolean sns) {
			this.sns = sns;
		}

	boolean ide;
		public boolean isIde() {
			return ide;
		}
		public void setIde(boolean ide) {
			this.ide = ide;
		}

	boolean knowlege;
		public boolean isKnowlege() {
			return knowlege;
		}
		public void setKnowlege(boolean knowlege) {
			this.knowlege = knowlege;
		}
		
	boolean oce;
		public boolean isOce() {
			return oce;
		}
		public void setOce(boolean oce) {
			this.oce = oce;
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
		
		// TODO: 이 클래스 없애야함 cjw
		/*
		CloudIDE cloudIDE = new CloudIDE();
		cloudIDE.setPageNavigator(new PageNavigator());
		cloudIDE.load(session);
			
		return new MainPanel(cloudIDE);
		*/
		
		return null;
		
//		return new MainPanel(new IDE(session));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcess() throws Exception {
		
//		session.getEmployee().setPreferUX(null);
		session.setLastPerspecteType(null);

/*		String preferUX = session.getEmployee().getPreferUX();
		if("sns".equals(preferUX)){
			return goSns();
		}else{*/
			return new MainPanel(new Main(session));
		//}
	}
	@ServiceMethod(callByContent=true)
	public MainPanel goLMS() throws Exception {
		return new MainPanel(new MainLMS(session));
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
		
		// TODO: 이 클래스 없애야함 cjw
		/*
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.setPageNavigator(new PageNavigator());
		marketplace.load();
		
		return new MainPanel(marketplace);
		*/
		
		return null;
	}
	
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goTadPole() throws Exception {
		
		Tadpole tadpole = new Tadpole();
		tadpole.session = session;
		tadpole.load();
		
		return new MainPanel(tadpole);
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goNkia() throws Exception {
		
//		Nkia nkia = new Nkia();
//		nkia.session = session;
//		nkia.load();
//		
//		return new MainPanel(nkia);
		System.out.println("paasManager" + session.getAccessToken());
		return new MainPanel(new RemoteNewPage(session, "IaaS Admin", "http://192.168.212.52/kiat_sso.jsp?access_token=" + session.getAccessToken() + "&user_id=" + session.getUser().getUserId())); //paasManager
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goSelfServicePortal() throws Exception {
		
		SelfService selfService = new SelfService();
		selfService.setPageNavigator(new PageNavigator());
		selfService.session = session;
		selfService.load();
		
		return new MainPanel(selfService);
		
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true)
	public MainPanel goAppMap() throws Exception {
		
		AppMap appMap = new AppMap();
		appMap.session = session;
		appMap.load();

		return new MainPanel(appMap);
		
	}
	

	@ServiceMethod(callByContent=true)
	@Test(scenario="first", starter=true, instruction="<br><br><br><br><br><br>R&D 지식모드 (Knowledge Mode) 로 넘어갑니다..", next="autowiredObject.org.uengine.codi.mw3.knowledge.WfNode.add()")	
	public MainPanel goKnowledge() throws Exception {
		return new MainPanel(new Knowledge(session));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcessExplorer() throws Exception {
		ProcessExplorer processExplorer = new ProcessExplorer();
		processExplorer.load(session); 
			
		return new MainPanel(processExplorer);
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goDashBoard() throws Exception {
		return new MainPanel(new OceMain(session));
	}
}
