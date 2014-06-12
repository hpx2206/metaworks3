package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class FollowerPanel {
	
	String id;
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	Followers followers;
		public Followers getFollowers() {
			return followers;
		}
		public void setFollowers(Followers followers) {
			this.followers = followers;
		}
	
	public FollowerPanel(String id){
		this(id, null);
	}
	
	public FollowerPanel(String id, Followers followers){
		this.setId(id);
		this.setFollowers(followers);
	}

}
