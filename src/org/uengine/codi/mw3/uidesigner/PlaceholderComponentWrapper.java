package org.uengine.codi.mw3.uidesigner;

import org.metaworks.MetaworksContext;

public class PlaceholderComponentWrapper extends ComponentWrapper {

	public PlaceholderComponentWrapper() {
		super();
		init(new PlaceHolder());
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("design");
	}
	
	

}
