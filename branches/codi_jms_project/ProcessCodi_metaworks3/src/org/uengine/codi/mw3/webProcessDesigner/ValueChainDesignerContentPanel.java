package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.MetaworksContext;

public class ValueChainDesignerContentPanel extends ProcessDesignerContentPanel{

	public ValueChainDesignerContentPanel() throws Exception {
		
		super();
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("valuechain");
	}
	
}
