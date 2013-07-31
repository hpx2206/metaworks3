package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;


public class TopicPanel {
	
	ITopicNode topicNode;
		public ITopicNode getTopicNode() {
			return topicNode;
		}
		public void setTopicNode(ITopicNode topicNode) {
			this.topicNode = topicNode;
		}

	public void load() throws Exception {
		ITopicNode topicNodeList = TopicNode.load(session);
		setTopicNode(topicNodeList);
	}

	@AutowiredFromClient
	transient public Session session;
}
