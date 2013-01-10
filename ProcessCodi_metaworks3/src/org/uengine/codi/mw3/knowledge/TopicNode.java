package org.uengine.codi.mw3.knowledge;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;

public class TopicNode extends Database<ITopicNode> implements ITopicNode {
	 
	@AutowiredFromClient
	public PageNavigator pageNavigator; 
	
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
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}
	String authorId;
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
		
	public static ITopicNode load(Session session) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol knol");
		sb.append(" where knol.type = ?type");
		sb.append(" and knol.companyId = ?companyId");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid))) ");
		sb.append(" order by no");
		
		ITopicNode dao = (ITopicNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), ITopicNode.class); 
		dao.set("type", "topic");
		dao.set("userid", session.getEmployee().getEmpCode());
		dao.set("companyId", session.getCompany().getComCode());
		dao.select();
		
		return dao; 
	}
	
	public Object[] loadTopic() throws Exception{
		
		if(pageNavigator != null && "knowlege".equals(pageNavigator.getPageName())){
			return new Object[]{new BrainstormPanel(this.getId())};
		}else{
			String title = "주제 : " + getName();
			Object[] returnObject = Perspective.loadInstanceListPanel(session, "topic", getId(), title);
			
			return returnObject;
		}		
	}
	
	
	public Object[] remove() throws Exception {
		
		if( session.getUser().getUserId().equalsIgnoreCase(getAuthorId()) || session.getEmployee().getIsAdmin()) {
			// 삭제는 진짜 삭제가 아닌 topic 만 제거를 하여 지식노드에서는 보이도록 설정됨
			// deleteDatabaseMe();
			StringBuffer sb = new StringBuffer();
			sb.append("update bpm_knol");
			sb.append("   set type = null ");
			sb.append(" where id=?topicId");
			
			ITopicNode updateNode = (ITopicNode) sql(ITopicNode.class,	sb.toString());
			updateNode.set("topicId", this.getId());
			
			updateNode.update();
			
		} else {
			throw new Exception("관리자나 초기토픽생성자만 수정가능합니다.");
		}
		return new Object[]{new Remover(this)};
	}
	

	public ModalWindow modify() throws Exception {
		System.out.println("modify");	
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.setMetaworksContext(new MetaworksContext());
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		topicTitle.session = session;
		return new ModalWindow(topicTitle , 500, 250,  "토픽수정");

	}
	
	public ModalWindow exportHtml() throws Exception {
		TopicTitle topicTitle = new TopicTitle();
		topicTitle.setTopicId(this.getId());
		topicTitle.setTopicTitle(this.getName());
		topicTitle.getMetaworksContext().setHow("html");
		topicTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		topicTitle.makeHtml();
				
		return new ModalWindow(topicTitle, 500, 250,  "토픽수정");
	}
	
	public void addUser() throws Exception {
		
	}
	
	@AutowiredFromClient
	transient public Session session;
	
//	@AutowiredFromClient
//	public ContentWindow contentWindow;
}
