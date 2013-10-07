package org.uengine.codi.mw3.uidesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Tab;
import org.metaworks.widget.TabPanel;
import org.uengine.codi.mw3.model.Session;

public class TabPanelComponentWrapper extends ComponentWrapper{
	
	public TabPanelComponentWrapper(){
	}
	
	public static TabPanelComponentWrapper createNewForPalette(){
		TabPanelComponentWrapper tabPanelCompWrapper = new TabPanelComponentWrapper();
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.addTab(new Tab("tab1", new PlaceholderComponentWrapper()));
		
		tabPanelCompWrapper.init(tabPanel);
		
		return tabPanelCompWrapper;

	}

	@Override
	public void setComponentByDropping(ComponentWrapper compWrapper) {
		if(compWrapper!=null){
			
			if(getComponent() instanceof ComponentWrapper){
				setComponent(((ComponentWrapper)getComponent()).getComponent());
			}
			
			TabPanel tabPanel = (TabPanel) getComponent();
			tabPanel.addTab(new Tab("tab", compWrapper));
		}
		
	}
	

	@ServiceMethod(callByContent=true, inContextMenu=true)
	public ComponentWrapper removeTab(){
		TabPanel tabPanel = (TabPanel) getComponent();
		tabPanel.removeTab((Tab) tabPanel.getTabs().get(0));//selected Index ??
		
		return this;
	}
	
	
}
