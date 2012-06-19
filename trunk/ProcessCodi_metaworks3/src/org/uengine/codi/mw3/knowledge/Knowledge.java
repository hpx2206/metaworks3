package org.uengine.codi.mw3.knowledge;

import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.ContentPanel;
import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;

public class Knowledge {

	public Knowledge() throws Exception {
	}
	
	public Knowledge(Session session) throws Exception {
		this();
		
		setSession(session);
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size:300, north__size:52");
		outerLayout.setNorth(new ProcessTopPanel(session));
		
		Window wfWindow = new Window();
			WfPanel panel = new WfPanel();
			panel.load("-1");//user.getUserId());
			wfWindow.setPanel(panel);
			wfWindow.setTitle("Knowledge Map");
		
		outerLayout.setCenter(wfWindow);
		
		Mashup mashup = new Mashup();
		ContentWindow mashupContentWindow = new ContentWindow(mashup);
		mashupContentWindow.setTitle("Mashup");
		
		outerLayout.setEast(mashupContentWindow);
		
//		mashupGoogleImage = mashup.getMashupGoogleImage();
		
		setLayout(outerLayout);		
		
		setPageNavigator(new PageNavigator("knowlege"));	
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
