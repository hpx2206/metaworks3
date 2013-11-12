package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicTitle;

public class TopicInfo extends GroupInfo{

	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	public final static String HTML = "html";
	
	String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public Object[] delete() {

		StringBuffer sb = new StringBuffer();
		sb.append("update bpm_knol");
		sb.append("   set type = null ");
		sb.append(" where id=?topicId");
		
//		ITopicNode updateNode = (ITopicNode)sql(ITopicNode.class,	sb.toString());
//		updateNode.set("topicId", this.getId());
		
//		updateNode.update();
		
		
		//this가 아닌 topicNode지우기.
		return new Object[]{new Remover(this)};
	}

	@Override
	public ModalWindow modify() throws Exception {
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.setMetaworksContext(new MetaworksContext());
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
//		topicTitle.session = session;
		return new ModalWindow(topicTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$EditTopic");
	}

	@Override
	public void load() {
		System.out.println("topic");
	}
	
	public ModalWindow exportHtml() throws Exception {
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.getMetaworksContext().setHow(HTML);
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		topicTitle.makeHtml();
				
		return new ModalWindow(topicTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$ExportHtml");
	}
}
