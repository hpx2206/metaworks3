package org.uengine.codi.mw3.admin;

import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;


public class IDE {
	public IDE() throws Exception {
	}
	
	public IDE(Session session) throws Exception {
		setPageNavigator(new PageNavigator("ide"));
		
		Layout innerLayout = new Layout();
		
		ContentWindow contentWindow = new ContentWindow();
		contentWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());
		
		ConsoleWindow consoleWindow = new ConsoleWindow();
		consoleWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());
		
		if(session.getDefId() != null){
			ClassDefinition classDefinition = new ClassDefinition();
			classDefinition.setAlias(session.getDefId());
			classDefinition.afterDeserialization();
			classDefinition.getMetaworksContext().setWhen("view");
			
			contentWindow.setPanel(classDefinition);
		}
		
		innerLayout.setCenter(contentWindow);		
		innerLayout.setSouth(consoleWindow);
		
		innerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, south__spacing_open:5, south__size:'20%'");
		
		HintWindow hintWindow = new HintWindow();
		hintWindow.getMetaworksContext().setHow(getPageNavigator().getPageName());
		hintWindow.load(session, getPageNavigator().getPageName());
		
		ResourceWindow resourceWindow = new ResourceWindow();		
		resourceWindow.getMetaworksContext().setHow(pageNavigator.getPageName());
		
		Layout outerLayout = new Layout();
		outerLayout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, east__spacing_open:5, west__size:300, east__size:500, north__size:52");
		outerLayout.setNorth(new TopPanel(session));
		outerLayout.setWest(resourceWindow);
		outerLayout.setEast(hintWindow);
		outerLayout.setCenter(innerLayout);		
		
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
