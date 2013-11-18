package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;


public class TopicPanel {
	
	ITopicNode topicNode;
		public ITopicNode getTopicNode() {
			return topicNode;
		}
		public void setTopicNode(ITopicNode topicNode) {
			this.topicNode = topicNode;
		}
		
	boolean isSelectedMore;
		public boolean isSelectedMore() {
			return isSelectedMore;
		}
		public void setSelectedMore(boolean isSelectedMore) {
			this.isSelectedMore = isSelectedMore;
		}

	@ServiceMethod	
	public void load() throws Exception {
		ITopicNode topicNodeList = TopicNode.load(session);
		setTopicNode(topicNodeList);
	}

	@AutowiredFromClient
	transient public Session session;
	
	@ServiceMethod
	public void moreView() throws Exception {
		ITopicNode topicNodeList = TopicNode.moreView(session);
		setTopicNode(topicNodeList);
		setSelectedMore(true);
	}
}
