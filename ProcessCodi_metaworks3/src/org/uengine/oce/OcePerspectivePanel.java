package org.uengine.oce;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.codi.mw3.model.AppAlfresco;
import org.uengine.codi.mw3.model.AppBBB;
import org.uengine.codi.mw3.model.AppCalendar;
import org.uengine.codi.mw3.model.AppMindMap;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Main;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.OrganizationPerspectiveApp;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.oce.dashboard.DashboardWindow;

public class OcePerspectivePanel extends Perspective{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	
		
	OrganizationPerspectiveApp appPerspective;
		public OrganizationPerspectiveApp getAppPerspective() {
			return appPerspective;
		}
		
		public void setAppPerspective(OrganizationPerspectiveApp appPerspective) {
			this.appPerspective = appPerspective;
		}
		
	ProjectPerspective projectPerspective;
		public ProjectPerspective getProjectPerspective() {
			return projectPerspective;
		}	
		public void setProjectPerspective(ProjectPerspective projectPerspective) {
			this.projectPerspective = projectPerspective;
		}
		
	public OcePerspectivePanel() throws Exception{
		this(null);
	}

	public OcePerspectivePanel(Session session) throws Exception{
		if(session != null){
			//앱
			if("1".equals(Perspective.USE_APP)){
				appPerspective = new OrganizationPerspectiveApp();
				appPerspective.getMetaworksContext().setHow("dashboard");
				appPerspective.getMetaworksContext().setWhere("oce_perspective");
				appPerspective.session = session;
				appPerspective.select();
			}
			//프로젝트
			if("1".equals(Perspective.USE_PROJECT)){
				projectPerspective = new ProjectPerspective();
				projectPerspective.session = session;
				projectPerspective.select();
			}
		
		}//session
		
	}
	
	@ServiceMethod
	public MainPanel loadOce() throws Exception{
		
		if(session != null){
			session.setLastSelectedItem(OceMain.ACTION_DASHBOARD);
			session.setUx(OceMain.STATUS_OCE);
		}

		return new MainPanel(new OceMain(session));
	}
	
	
	@ServiceMethod
	static public Object[] loadAllICanSee() throws Exception{
		
		Object[] returnObject = loadInstanceListPanel(session , "allICanSee", "oce");
		
		String title = GlobalContext.getPropertyString("$All");
		
		DashboardWindow window = new DashboardWindow();
		window.session = session;
		window.setPanel(returnObject);
		window.setTitle(title);

		return new Object[]{session , window};
	}
	
	@ServiceMethod
	public MainPanel goSNS() throws Exception {
		if(session != null){
			session.setLastSelectedItem("goSns");
			session.setUx("sns");
		}
		
		return new MainPanel(new Main(session));
	}

	@ServiceMethod
	public ContentWindow goAlfresco() throws Exception {
		
		// TODO : locale 처리
		return new ContentWindow(new AppAlfresco(), "문서관리");
	}
	
	@ServiceMethod
	public ContentWindow goCalendar() throws Exception {
		
		// TODO : locale 처리
		return new ContentWindow(new AppCalendar(session), "일정관리");
	}

	
	@ServiceMethod
	public ContentWindow goBBB() throws Exception {
		
		// TODO : locale 처리
		return new ContentWindow(new AppBBB(), "화상회의");
	}
	
	@ServiceMethod
	public ContentWindow goMindMap() throws Exception {
		
		// TODO : locale 처리
		return new ContentWindow(new AppMindMap(), "마인드맵");
	}
	

}

