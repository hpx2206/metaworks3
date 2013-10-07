package org.uengine.codi.mw3.selfservice;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.uengine.codi.mw3.model.Session;


/**
 * 
 * Id: key
 * Name: Tab Name
 * 
 * AppNode:
 * 
 * load(): 
 * 
 * @author JISUN
 *
 */
@Face(displayName="$SelfServicePortal", ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class SelfServiceNavigator {
	
	String id;
	@Id
	@Hidden
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	String type;
	@Hidden
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	String name;
		@Name
		@Hidden
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	AppNode appNode;
		public AppNode getAppNode() {
			return appNode;
		}
		public void setAppNode(AppNode appNode) {
			this.appNode = appNode;
		}
		
		
	public SelfServiceNavigator() {
		this.setId("self_service_navigator");
		this.setType(this.getId());
		this.setName("$SelfServicePortal");		
	}
	
	public void load(Session session) throws Exception {
		AppNode appnode = new AppNode();
		appnode.load(session);
		
		this.setAppNode(appnode);
		
	}
}
