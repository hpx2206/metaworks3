package org.uengine.processmarket;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.Session;

@Face(
	ejsPath="genericfaces/Window.ejs",
	options={"hideAddBtn", "hideLabels"},
	values={"true", "true"},
	displayName="$Category"
)
public class MarketCategoryPanel {
	
	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		
		public void setCategory(ICategory category) {
			this.category = category;
		}
	

	public MarketCategoryPanel(Session session) {
		
		Category categorysession = new Category();
		categorysession.session = session;
		
	}
}
