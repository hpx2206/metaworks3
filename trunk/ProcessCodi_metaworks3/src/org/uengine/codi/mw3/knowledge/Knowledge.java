package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.ContactWindow;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.PerspectivePanel;
import org.uengine.codi.mw3.model.PerspectiveWindow;
import org.uengine.codi.mw3.model.ProcessTopPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicPerspective;

public class Knowledge {

	public Knowledge() throws Exception {
	}
	
	public Knowledge(Session session) throws Exception {
		this();
		
		setSession(session);
		
		// perspective
		TopicPerspective topicPerspective = new TopicPerspective();
		topicPerspective.session = session;
		topicPerspective.select();
		
		PerspectivePanel perspectivePanel = new PerspectivePanel();
		perspectivePanel.setTopicPerspective(topicPerspective);
		
		PerspectiveWindow perspectiveWindow = new PerspectiveWindow();
		perspectiveWindow.setPanel(perspectivePanel);
		
		// contact
		ContactWindow contactWindow = new ContactWindow(session.getUser());
		contactWindow.getContactPanel().setSearchBox(null);
		
		// knowlege node
		
		//wfWindow.setPanel(new BrainstormPanel(session.getCompany().getComCode()));
		WfPanel panel = new WfPanel();
			
		panel.session = session;
			
//		if("uEngine".equals(session.getCompany().getComCode())){
//			panel.load("-1");
//		}else{
			panel.load(session.getCompany().getComCode());				
//		}
		Window wfWindow = new Window();
		wfWindow.setTitle("Knowledge Map");
		wfWindow.setPanel(panel);
		
		
		// mashup
		Mashup mashup = new Mashup();
		ContentWindow mashupContentWindow = new ContentWindow(mashup);
		mashupContentWindow.setTitle("Mashup");
//		mashupGoogleImage = mashup.getMashupGoogleImage();		

		
		// layout
		Layout westLayout = new Layout();
		westLayout.setCenter(perspectiveWindow);
		westLayout.setSouth(contactWindow);
		westLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:'50%'");
		westLayout.setName("west");

		
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5,east__size: '40%', north__size:52");
		outerLayout.setWest(westLayout);
		outerLayout.setNorth(new ProcessTopPanel(session));
		outerLayout.setCenter(wfWindow);
		outerLayout.setEast(mashup);
		outerLayout.setName("center");
		
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
