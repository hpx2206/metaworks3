package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.TypeSelector;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.TopicFollowers;

public class GroupInfo extends PerspectiveInfo{
	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}

	@Override
	public void load() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("group");
		
	}
	
	
	
	@Override
	public void followersLoad() throws Exception {
		this.followers =  new TopicFollowers();
		this.followers.session = session;
		this.followers.load();
	}
	

	
	public void convertSecuopt() throws Exception{
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getId());
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(wfNode.getSecuopt())){
			wfNode.setSecuopt("1");
		}else{
			wfNode.setSecuopt("0");
		}
		wfNode.syncToDatabaseMe();
		
		
		
	}
}
