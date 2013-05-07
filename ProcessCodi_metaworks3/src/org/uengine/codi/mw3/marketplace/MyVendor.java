package org.uengine.codi.mw3.marketplace;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.MarketCategoryPanel;

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
	
	IApp listing;
		public IApp getListing() {
			return listing;
		}
		public void setListing(IApp listing) {
			this.listing = listing;
		}

		
	public IApp load() throws Exception{
		
		vendor = new Company();
		
		vendor.setComCode(session.getCompany().getComCode());
		vendor.setComName(session.getCompany().getComName());
		vendor.setDescription(session.getCompany().getDescription());
		vendor.setRepMail(session.getCompany().getRepMail());
		
		setVendor(vendor);
		
		
		App findlisting = new App();
		
		findlisting.setVendorId(session.getCompany().getComCode());
		
		listing = findlisting.findByVendor();
		listing.getMetaworksContext().setWhen("myVendor");
		setListing(listing);
		
		return this.getListing();
		
	}
	
	@ServiceMethod
	public Object createApp() throws Exception {
		
		AppInformation createListing = new AppInformation();
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
		
		SelectBox attachProject = new SelectBox();
		
		IProjectNode projectList = ProjectNode.load(session);
		
		if(projectList.size() > 0) {
			while(projectList.next()){
				String projectId = projectList.getId();
				String projectName = projectList.getName();
				
				
				attachProject.add(projectName, projectId);
			}
		}

		createListing.setCategories(categories);
		createListing.setAttachProject(attachProject);
		
		
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.setAppInfo(createListing);
		
		MarketplaceCenterWindow centerWin = new MarketplaceCenterWindow(session);
		centerWin.setCenterPanel(centerPanel);
	
		Layout mainLayout = new Layout();
		
		mainLayout.setId("main");
		mainLayout.setName("center");
		mainLayout.setCenter(centerWin);
		
		return mainLayout;
	}

}
