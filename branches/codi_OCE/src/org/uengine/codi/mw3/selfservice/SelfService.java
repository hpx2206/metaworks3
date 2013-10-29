package org.uengine.codi.mw3.selfservice;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.admin.TopPanel;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;


/**
 * 셀프서비스 포탈 홈 화면.
 * pageNavigator = 우측 상단 페이지 이동 플립
 * SelfServiceNavigator 객체는 cloudTab으로 이루어져있으며, 앱리스트 및 하위 속성을 호출한다.
 * 
 * Layout: Self service portal home.
 * PageNavigator: The flip to go each pages.
 * load(): Set layout at west, center, north
 * 		* west panel: Call Acquisition app's list and property of them
 * 		* center panel: Users(admin) can edit property's value in here (we serve to preview the files or download them)
 * 		* north panel: Top panel is include user's info in session
 * 
 * @author JISUN
 * 
 */
public class SelfService {
	
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
		SelfServiceWindow windowPanel = new SelfServiceWindow(session);
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(windowPanel);
		
		Layout outerLayout = new Layout();
		outerLayout.setCenter(centerLayout);
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__size:52");
		
		if("oce".equals(session.getUx())){
			outerLayout.setNorth(new ProcessTopPanel(session));
		}else{
			outerLayout.setNorth(new TopPanel(session));
		}

		this.setLayout(outerLayout);
	}
		
	@ServiceMethod
	public void load(int appId) throws Exception {
		SelfServiceWindow windowPanel = new SelfServiceWindow(session, appId);
		
		Layout centerLayout = new Layout();
		centerLayout.setId("center");
		centerLayout.setName("center");
		centerLayout.setCenter(windowPanel);
		
		Layout outerLayout = new Layout();
		outerLayout.setCenter(centerLayout);
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__size:52");
		
		if("oce".equals(session.getUx())){
			outerLayout.setNorth(new ProcessTopPanel(session));
		}else{
			outerLayout.setNorth(new TopPanel(session));
		}

		this.setLayout(outerLayout);
		
	}

}
