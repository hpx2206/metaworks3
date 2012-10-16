package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicPanel;
import org.uengine.codi.mw3.knowledge.TopicTitle;

public class TopicPerspective extends Perspective {

	public TopicPerspective() throws Exception {
		setLabel("Topic");
	}
	
	@Override
	protected void loadChildren() throws Exception {
		topicMap = new TopicPanel();
		topicMap.session = session;
		topicMap.load();
	}

	TopicPanel topicMap;
		public TopicPanel getTopicMap() {
			return topicMap;
		}
		public void setTopicMap(TopicPanel topicMap) {
			this.topicMap = topicMap;
		}
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="$addTopic")
	public ModalWindow addTopic() throws Exception{
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setMetaworksContext(new MetaworksContext());
		topicTitle.getMetaworksContext().setWhen("edit");
		topicTitle.session = session;
		return new ModalWindow(topicTitle , 600, 400,  "토픽추가");
	}
		
}
