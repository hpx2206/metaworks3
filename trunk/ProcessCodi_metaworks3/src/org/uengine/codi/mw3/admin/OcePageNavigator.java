package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.Knowledge;
import org.uengine.codi.mw3.marketplace.AppMap;
import org.uengine.codi.mw3.marketplace.Marketplace;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.MainLMS;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.PinterestMain;
import org.uengine.codi.mw3.processexplorer.ProcessExplorer;
import org.uengine.codi.mw3.selfservice.SelfService;
import org.uengine.codi.mw3.tadpole.Tadpole;
import org.uengine.processmarket.Market;

public class OcePageNavigator extends PageNavigator {
	
	public OcePageNavigator() {
		this.setOce("1".equals(StartCodi.USE_OCE));
		this.setProject("1".equals(Perspective.USE_PROJECT));
	}
	
	public OcePageNavigator(String pageName) {
		this.setPageName(pageName);

		this.setOce("1".equals(StartCodi.USE_OCE));
		this.setProject("1".equals(Perspective.USE_PROJECT));
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
		
	boolean project;
		public boolean isProject() {
			return project;
		}
		public void setProject(boolean project) {
			this.project = project;
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
		session.setUx("oce");
		
		CloudIDE cloudIDE = new CloudIDE();
		cloudIDE.setPageNavigator(new OcePageNavigator());
		cloudIDE.load(session);
			
		return new MainPanel(cloudIDE);
		*/
		
		return null;
		
//		return new MainPanel(new IDE(session));
	}
	
	@Override
	public MainPanel goDashBoard() throws Exception {
		
		if(session != null){
			session.setLastSelectedItem(OceMain.ACTION_DASHBOARD);
			session.setUx(OceMain.STATUS_OCE);
		}
		return super.goDashBoard();
	}
	
	
	@ServiceMethod(callByContent=true)
	public MainPanel goProcess() throws Exception {
		
//		session.getEmployee().setPreferUX(null);
		session.setLastPerspecteType(null);
		
		/*		String preferUX = session.getEmployee().getPreferUX();
		if("sns".equals(preferUX)){
			return goSns();
		}else{*/
		return new MainPanel(new OceMain(session));
		//}
	}
	@ServiceMethod(callByContent=true)
	public MainPanel goLMS() throws Exception {
		return new MainPanel(new MainLMS(session));
	}
	
	@ServiceMethod(callByContent=true)
	public MainPanel goSns() throws Exception {
		
		if(session != null){
			session.setLastPerspecteType("allICanSee");
			session.setLastSelectedItem("goSns");
			session.setUx("sns");
		}
		return new MainPanel(new Main(session));
//		return new MainPanel(new MainSNS(session));
		
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
		
		session.setUx("oce");
		
		Marketplace marketplace = new Marketplace();
		marketplace.session = session;
		marketplace.load();
		
		return new MainPanel(marketplace);
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
		
		session.setUx("oce");
		
		SelfService selfService = new SelfService();
		selfService.setPageNavigator(new OcePageNavigator());
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
}
