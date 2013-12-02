package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
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
	
	@ServiceMethod(target="popup")
	public ModalWindow addTopic() throws Exception{
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setMetaworksContext(new MetaworksContext());
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		topicTitle.setLogoFile(new MetaworksFile());
		topicTitle.session = session;
		return new ModalWindow(topicTitle , 500, 270,  "$addTopic");
	}
		
}
