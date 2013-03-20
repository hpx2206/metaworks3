package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.Face;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="TopInstall"
	)
public class MarketplaceCenterWindow {

	Object panel;
	
}
