package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Session;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="New Apps"
	)
public class MarketplaceEastPanel {
	
	public MarketplaceEastPanel(){
		
	}
	
	IApp listing;
		public IApp getListing() {
			return listing;
		}
		public void setListing(IApp listing) {
			this.listing = listing;
		}
	
		
	@AutowiredFromClient
	public Session session;

	
	public Object load() throws Exception {

		App findListing = new App();
		findListing.setComcode(session.getCompany().getComCode());
		findListing.session = session;
		
		IApp listing = findListing.findNewApps();
		listing.getMetaworksContext().setWhen("newApps");
		
		this.setListing(listing);
		
		return listing;
		
	}

}
