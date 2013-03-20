package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmarket.Category;
import org.uengine.processmarket.MarketCategoryPanel;

public class MyVendor {
	
	@AutowiredFromClient
	public Session session;
	
	Company vendor;
		public Company getVendor() {
			return vendor;
		}
		public void setVendor(Company vendor) {
			this.vendor = vendor;
		}
	
	IListing listing;
		public IListing getListing() {
			return listing;
		}
		public void setListing(IListing listing) {
			this.listing = listing;
		}

		
	public void load() throws Exception{
		
		vendor = new Company();
		
		vendor.setComCode(session.getCompany().getComCode());
		vendor.setComName(session.getCompany().getComName());
		vendor.setDescription(session.getCompany().getDescription());
		vendor.setRepMail(session.getCompany().getRepMail());
		
		setVendor(vendor);
		
		
		Listing findlisting = new Listing();
		findlisting.setVendorId(session.getCompany().getComCode());
		
		listing = findlisting.findByVendor();
		listing.getMetaworksContext().setWhen("myVendor");

		
		setListing(listing);
		
	}
	
	@ServiceMethod
	public Object createListing() throws Exception {
		
		ListingInformation createListing = new ListingInformation();
		createListing.session = session;
		
		SelectBox categories = new SelectBox();

		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(Category.loadRootCategory());

		if (marketCategory.getCategory().size() > 0) {
			while (marketCategory.getCategory().next()) {

				String categoryId = Integer.toString(marketCategory.getCategory().getCategoryId());
				String categoryName = marketCategory.getCategory().getCategoryName();

				categories.add(categoryName, categoryId);
			}
		}

		createListing.setCategories(categories);
		
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(createListing);
		
		return mainLayout;
	}

}
