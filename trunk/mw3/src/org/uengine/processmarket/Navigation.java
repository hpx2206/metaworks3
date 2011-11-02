package org.uengine.processmarket;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(
	ejsPath = "genericfaces/Window.ejs",
	options = { "hideAddBtn", "hideLabels" }, 
	values = { "true", "true" }
)
public class Navigation implements ContextAware {

    MetaworksContext metaworksContext;

    public MetaworksContext getMetaworksContext() {
	return metaworksContext;
    }

    public void setMetaworksContext(MetaworksContext metaworksContext) {
	this.metaworksContext = metaworksContext;
    }

}
