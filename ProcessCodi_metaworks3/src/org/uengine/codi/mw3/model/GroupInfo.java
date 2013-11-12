package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.TypeSelector;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicFollowers;

public class GroupInfo extends PerspectiveInfo{
	

	TopicFollowers topicFollowers;
	
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


	String linkedInstId;
	String parentId;
	int no;
	String authorid;
	String vistype;
	String conntype;
	String url;
	String thumbnail;
	String secuopt;
	String companyId;
	String refId;
	String budget;
	String effort;
	String benefit;
	String penalty;
	Date startDate;
	Date endDate;
	String description;
	String ex1;
	String ext;
	boolean isreleased;
	boolean isdistributed;
	String projectalias;
	
	
	

	@Override
	public void load() {
		// TODO Auto-generated method stub
		System.out.println("group");
		
	}
	
	
	@Override
	public void followersLoad() throws Exception {
		topicFollowers =  new TopicFollowers();
//		topicFollowers.session = session;
		topicFollowers.load();
	}
	

}
