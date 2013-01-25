package org.uengine.codi.mw3.knowledge;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.ContentPanel;
import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContactPanel;
import org.uengine.codi.mw3.model.ContactWindow;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.NavigationWindow;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;

public class Knowledge {

	public Knowledge() throws Exception {
	}
	
	public Knowledge(Session session) throws Exception {
		this();
		
		setSession(session);
		
		NavigationWindow navigationWindow = new  NavigationWindow(session);
		navigationWindow.getNavigation().setOrganizationPerspectiveDept(null);
		navigationWindow.getNavigation().setOrganizationPerspectiveRole(null);
		navigationWindow.getNavigation().setPersonalPerspective(null);
		navigationWindow.getNavigation().setStrategicPerspective(null);
		navigationWindow.getNavigation().setProcessPerspective(null);
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size:300, north__size:52");
		outerLayout.setNorth(new ProcessTopPanel(session));
		outerLayout.setWest(navigationWindow);
		
		Window wfWindow = new Window();
		wfWindow.setPanel(new BrainstormPanel(session.getCompany().getComCode()));
		/*
			WfPanel panel = new WfPanel();
			
			panel.session = session;
			
//			if("uEngine".equals(session.getCompany().getComCode())){
//				panel.load("-1");
//			}else{
				panel.load(session.getCompany().getComCode());				
//			}
			
			wfWindow.setPanel(panel);
			wfWindow.setTitle("Knowledge Map");
		*/
		
		outerLayout.setCenter(wfWindow);
		
		Mashup mashup = new Mashup();
		ContentWindow mashupContentWindow = new ContentWindow(mashup);
		mashupContentWindow.setTitle("Mashup");
		

//		mashupGoogleImage = mashup.getMashupGoogleImage();
		
		
		setPageNavigator(new PageNavigator("knowlege"));	
		
		Layout innerLayout = new Layout();
		innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, north__spacing_open:5, north__size:'60%'");
		innerLayout.setCenter(new ContactWindow(session.getUser()));
		innerLayout.setNorth(mashupContentWindow);
		
		outerLayout.setEast(innerLayout);
		setLayout(outerLayout);		

	}
//	
//	MashupGoogleImage mashupGoogleImage;
//	@AutowiredToClient //need to explicitly wiring object
//		public MashupGoogleImage getMashupGoogleImage() {
//			return mashupGoogleImage;
//		}
//	
//		public void setMashupGoogleImage(MashupGoogleImage mashupGoogleImage) {
//			this.mashupGoogleImage = mashupGoogleImage;
//		}
//

	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
		
	
	Session session;
	@AutowiredToClient
		public Session getSession() {
			return session;
		}
		public void setSession(Session session) {
			this.session = session;
		}
		
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}	
}
