package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;


public class PerspectiveInfo implements IPerspectiveInfo{
	
	@AutowiredFromClient
	public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	Followers followers;
		public Followers getFollowers() {
			return followers;
		}
		public void setFollowers(Followers followers) {
			this.followers = followers;
		}
		
	MetaworksFile logoFile;
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	Boolean isJoined;
		public Boolean getIsJoined() {
			return isJoined;
		}
		public void setIsJoined(Boolean isJoined) {
			this.isJoined = isJoined;
		}
		
		
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalWindow modify() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() throws Exception {
		this.setIsJoinME();
	}

	@Override
	public void followersLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIsJoinME() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Available(condition="isJoined == true")
	public Object[] unSubscribe() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	@Available(condition="isJoined == false")
	public Object[] subscribe() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void refresh() throws Exception {
		this.load();
	}
	

}
