package org.uengine.codi.mw3.marketplace.model;

import org.uengine.codi.ITool; 
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Order;

@Face(displayName="application", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class Application implements ITool{ 

	public Application() {
	}

	String name;
		@Order(value=1)
		@Face(displayName="앱 이름")
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name = name;
		}

	String category;
		@Order(value=2)
		@Face(displayName="종류")
		public String getCategory(){
			return category;
		}
		public void setCategory(String category){
			this.category = category;
		}

	@Override
	public void onLoad() throws Exception {
	}

	@Override
	public void beforeComplete() throws Exception {
	}

	@Override
	public void afterComplete() throws Exception {
	}

}