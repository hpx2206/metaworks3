package org.uengine.codi.mw3.model;

import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.widget.IFrame;

public class MainLMS {

	public MainLMS() throws Exception {
		
	}
	public MainLMS(Session session) throws Exception {
		
		ProcessTopPanel processTopPanel = new ProcessTopPanel(session);
		processTopPanel.setPageType("LMS");
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size:300, north__size:52");
		outerLayout.setNorth(processTopPanel);
		
		Window window = new Window();	
		String url = "http://aof4.sns.active4c.4csoft.com:8088/index.jsp?knowledge=Y";
		IFrame ifr = new IFrame(url);
		ifr.setWidth(1024);
		String title = "학습모드";
		ifr.setHeight(568);
		window.setPanel(ifr);
		window.setTitle(title);
		outerLayout.setCenter(window);
		setPageNavigator(new PageNavigator(title));	
		setLayout(outerLayout);
		
	}
	
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
}
