package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.Window;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;

public class PinterestMain {

	public PinterestMain(Session session) throws Exception{
//		Layout outerLayout = new Layout();
//		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, north__size:52");
//		outerLayout.setNorth(new ProcessTopPanel(session));
//		
		
		InstanceListPanel instanceListPanel;
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];

		instanceListPanel.session = session;

		instanceListPanel.setMetaworksContext(new MetaworksContext());
		instanceListPanel.getMetaworksContext().setWhere("pinterest");
		instanceListPanel.getInstanceList().setMetaworksContext(new MetaworksContext());
		instanceListPanel.getInstanceList().getMetaworksContext().setWhere("pinterest");
		instanceListPanel.getInstanceList().getInstances().getMetaworksContext().setWhere("pinterest");
//		
		setInstanceListPanel(instanceListPanel);
		NewInstancePanel newInstancePanel2 = new NewInstancePanel();
		newInstancePanel2.session = session;
		newInstancePanel2.load(session);
		setNewInstancePanel(newInstancePanel2);
		setProcessTopPanel(new ProcessTopPanel(session));
		
//		outerLayout.setCenter(instanceListPanel);	
//		outerLayout.setName("center");
//		
//		setLayout(outerLayout);
		
		setPageNavigator(new PageNavigator("process"));	

	}
	
	NewInstancePanel newInstancePanel;
		
		public NewInstancePanel getNewInstancePanel() {
			return newInstancePanel;
		}
		public void setNewInstancePanel(NewInstancePanel newInstancePanel) {
			this.newInstancePanel = newInstancePanel;
		}

	ProcessTopPanel processTopPanel;

		public ProcessTopPanel getProcessTopPanel() {
			return processTopPanel;
		}
		public void setProcessTopPanel(ProcessTopPanel processTopPanel) {
			this.processTopPanel = processTopPanel;
		}
		
	InstanceListPanel instanceListPanel;

		public InstanceListPanel getInstanceListPanel() {
			return instanceListPanel;
		}
		public void setInstanceListPanel(InstanceListPanel instanceListPanel) {
			this.instanceListPanel = instanceListPanel;
		}
	
//	Layout layout;
//		public Layout getLayout() {
//			return layout;
//		}
//		public void setLayout(Layout layout) {
//			this.layout = layout;
//		}
//

	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}
		
	Logo logo;
		public Logo getLogo() {
			return logo;
		}
		public void setLogo(Logo logo) {
			this.logo = logo;
		}		
}

