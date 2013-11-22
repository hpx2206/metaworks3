package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class RemoteNewPage {
	
	public RemoteNewPage() throws Exception {
	}
	
	public RemoteNewPage(Session session) throws Exception {
		this(session, "New Page", "http://www.uengine.org");
	}
	
	public RemoteNewPage(Session session, String title, String pageUrl) throws Exception {
		this(session, title, pageUrl, false);
	}
	
	public RemoteNewPage(Session session, String title, String pageUrl, boolean adminMode) throws Exception {
		this();
		
		ProcessTopPanel processTopPanel = new ProcessTopPanel(session);
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size:300, north__size:52");
		outerLayout.setNorth(processTopPanel);
		
		Window wfWindow = new Window();	
		
		IFrame ifr = new IFrame(pageUrl);
		//ifr.setWidth(1024);
		//ifr.setHeight(568);
		wfWindow.setPanel(ifr);
		outerLayout.setCenter(wfWindow);
		
		wfWindow.setTitle(title);			
		
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
		
	Session session;
		
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}	
}
