package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class TopicTitle  implements ContextAware{
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	String topicId;
		@Hidden
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}

	String topicTitle;
	@Face(displayName="$topicTitle")
		public String getTopicTitle() {
			return topicTitle;
		}
		public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}
	boolean topicSecuopt;				
	@Face(displayName="$topicSecuopt")
		public boolean isTopicSecuopt() {
			return topicSecuopt;
		}
		public void setTopicSecuopt(boolean topicSecuopt) {
			this.topicSecuopt = topicSecuopt;
		}
	String url;
	@Face(displayName="$topicUrl")
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	@Face(displayName="$make")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		System.out.println(this.getTopicId());
		
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getTopicId());
		
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			wfNode.setName(this.getTopicTitle());
			wfNode.setType("topic");
			wfNode.setSecuopt(topicSecuopt ? "1" : "0");
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			wfNode.createMe();
			
			TopicMapping tm = new TopicMapping();
			tm.setTopicId(wfNode.getId());
			tm.setUserId(session.getUser().getUserId());
			tm.setUserName(session.getUser().getName());
			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
			
			tm.saveMe();
		}else{
			wfNode.copyFrom(wfNode.databaseMe());
			wfNode.setName(this.getTopicTitle());
			wfNode.saveMe();
		}
		
		/*
		TopicNode topicNode = new TopicNode();
		topicNode.setId(wfNode.getId());
		topicNode.setName(wfNode.getName());
		topicNode.session = session;
		*/

		TopicNode topicNode = new TopicNode();
		topicNode.setId(wfNode.getId());
		topicNode.setName(wfNode.getName());
		
		
		System.out.println("save");
		
		/*
		TopicPanel topicPanel = new TopicPanel();
		topicPanel.session = session;
		topicPanel.load();
		
		return new Object[]{new Refresh(topicPanel), new Remover(new ModalWindow())};
		*/
		
		//return new Object[]{(InstanceListPanel)topicNode.loadTopic()[1], new Remover(new ModalWindow())};
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen()))
			return new Object[]{new ToAppend(new TopicPanel(), topicNode), new Remover(new ModalWindow())};
		else
			return new Object[]{new Refresh(topicNode), new Remover(new ModalWindow())};
	}
	
	
	@AutowiredFromClient
	transient public Session session;
	
}
