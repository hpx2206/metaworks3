package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.Session;

public class TopicTitle  implements ContextAware{
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
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
		
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		
		WfNode wfNode = new WfNode();
		wfNode.setName(getTopicTitle());
		wfNode.setType("topic");
		wfNode.setSecuopt(topicSecuopt ? "1" : "0");
		wfNode.setParentId(session.getCompany().getComCode());	
		wfNode.setAuthorId(session.getUser().getUserId());		
		wfNode.setCompanyId(session.getCompany().getComCode());
		wfNode.createMe();
		
		if( topicSecuopt ){
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO BPM_TOPICMAPPING (TOPICID, USERID ) VALUES (?topicId, ?userId) ");
			
			IDAO idao = MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IDAO.class);
			idao.set("topicId"	, wfNode.getId());
			idao.set("userId"	, session.getUser().getUserId());
            idao.insert();
		}
		
		TopicNode topicNode = new TopicNode();
		topicNode.setId(wfNode.getId());
		topicNode.setName(wfNode.getName());
		topicNode.session = session;
		
		return new Object[]{(InstanceListPanel)topicNode.loadTopic()[1] ,new Remover(new ModalWindow())};
	}
	
	@AutowiredFromClient
	transient public Session session;
	
}
