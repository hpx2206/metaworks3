package org.uengine.codi.mw3.marketplace;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class AppList {

	@AutowiredFromClient
	public Session session;
	
	String categoryId;
		@Hidden
		public String getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}	

	IApp value;
		public IApp getValue() {
			return value;
		}
		public void setValue(IApp value) {
			this.value = value;
		}
	
	@ServiceMethod(payload={"categoryId"})
	@Hidden
	public void load(){
		String keyword = null;
		keyword = session.getSearchKeyword();
		
		this.load(this.getCategoryId(), keyword);		
	}
	
	public void load(String categoryId, String keyword){
		try {
			this.setValue(App.findApp(categoryId, keyword));
			this.getValue().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			this.getValue().getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
