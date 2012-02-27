package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;

@Face(ejsPath = "genericfaces/Tab.ejs", options = { "hideLabels" }, values = { "true" })
public class WebServiceAdapterContentPanel {

	public WebServiceAdapterContentPanel() {
		setMetaworksContext(new MetaworksContext());
	}
	
	WebServiceDefinition webServiceDefinition;
	
	public WebServiceDefinition getWebServiceDefinition() {
		return webServiceDefinition;
	}

	public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition) {
		this.webServiceDefinition = webServiceDefinition;
	}

	public void newWebServiceAdapter(String parentFolder) throws Exception {
		
		webServiceDefinition = new WebServiceDefinition();
		webServiceDefinition.setParentFolder(parentFolder);

	}

	transient MetaworksContext metaworksContext;

	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}

	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
}
