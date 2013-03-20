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
	
	IListing listing;
		public IListing getListing() {
			return listing;
		}
		public void setListing(IListing listing) {
			this.listing = listing;
		}
	
		
	@AutowiredFromClient
	public Session session;

	
	public void load() throws Exception {
		
		Listing findlisting = new Listing();
		
		findlisting.session = session;
		findlisting.setVendorId(session.getCompany().getComCode());
		
		listing = findlisting.findNewApps();
		listing.getMetaworksContext().setWhen("newApps");

		
		setListing(listing);
		
		
	}

}
