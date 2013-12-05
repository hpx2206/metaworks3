package org.uengine.codi.mw3.knowledge;

import java.util.Calendar;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.DAOUtil;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceDrag;
import org.uengine.codi.mw3.model.InstanceList;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.RecentItem;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;

public class KnowledgeMarketNode extends Database<IKnowledgeMarketNode> implements IKnowledgeMarketNode {
	
	public final static String DEFAULT_CONTACT_COUNT = "5";
	public final static String KNOWLEGE = "knowlege";
	public final static String TOPIC = "topic";
	public final static String HTML = "html";
	public final static int MODIFY_POPUP_HEIGHT = 250;
	public final static int MODIFY_POPUP_WIDTH = 500;
	
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

	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String companyId;
		public String getCompanyId() {
			return companyId;
		}
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		
	public static IKnowledgeMarketNode load(Session session) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol knol");
		sb.append(" left join recentItem item on item.itemId = knol.id and item.empcode = ?userid and item.itemType=?type");
		sb.append(" where knol.type = ?type");
		sb.append(" and knol.companyId = ?companyId");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
		sb.append(" order by updateDate desc limit " + GlobalContext.getPropertyString("topic.more.count", DEFAULT_CONTACT_COUNT));
		
		IKnowledgeMarketNode dao = (IKnowledgeMarketNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IKnowledgeMarketNode.class); 
		dao.set("type", "market");
		dao.set("userid", session.getEmployee().getEmpCode());
		dao.set("companyId", session.getCompany().getComCode());
		dao.select();
		
		return dao; 
	}
	
	public static IKnowledgeMarketNode moreView(Session session) throws Exception {
		
		DAOUtil daoUtil = new DAOUtil();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol knol");
		sb.append(" left join recentItem item on item.itemId = knol.id and item.empcode = ?userid and item.itemType=?type");
		sb.append(" where knol.type = ?type");
		sb.append(" and knol.companyId = ?companyId");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
		sb.append(" order by updateDate desc");
		
		IKnowledgeMarketNode dao = (IKnowledgeMarketNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IKnowledgeMarketNode.class); 
		dao.set("type", "market");
		dao.set("userid", session.getEmployee().getEmpCode());
		dao.set("companyId", session.getCompany().getComCode());
		dao.select();
		
		return dao; 
	}
	
	public Object[] loadKnowledgeMarket() throws Exception{
		if(pageNavigator != null && KNOWLEGE.equals(pageNavigator.getPageName())){
			return new Object[]{new BrainstormPanel(this.getId())};
			
		}else{
			String title = "주제 : " + getName();
			Object[] returnObject = Perspective.loadInstanceListPanel(session, TOPIC, getId(), title);
			
			// recentItem 에 create
			RecentItem recentItem = new RecentItem();
			recentItem.setEmpCode(session.getEmployee().getEmpCode());
			recentItem.setItemId(this.getId());
			recentItem.setItemType(this.getType());
			recentItem.setUpdateDate(Calendar.getInstance().getTime());
			
			recentItem.add();
			
			return returnObject;
		}
	}
	
	
	public Object[] remove() throws Exception {
		
//		if( !session.getUser().getUserId().equalsIgnoreCase(getAuthorId()) || !session.getEmployee().getIsAdmin()) {
//			throw new Exception("$OnlyEditAdmin");
//		}
		// 삭제는 진짜 삭제가 아닌 topic 만 제거를 하여 지식노드에서는 보이도록 설정됨
		// deleteDatabaseMe();
		StringBuffer sb = new StringBuffer();
		sb.append("update bpm_knol");
		sb.append("   set type = null ");
		sb.append(" where id=?marketId");
		
		IKnowledgeMarketNode updateNode = (IKnowledgeMarketNode) sql(IKnowledgeMarketNode.class, sb.toString());
		updateNode.set("marketId", this.getId());
		
		updateNode.update();
		
		return new Object[]{new Remover(this)};
	}
	

	public ModalWindow modify() throws Exception {
		if( !session.getUser().getUserId().equalsIgnoreCase(getAuthorId()) || !session.getEmployee().getIsAdmin()) {
			throw new Exception("$OnlyEditAdmin");
		}
		KnowledgeMarketTitle knowledgeMarketTitle = new KnowledgeMarketTitle();
		knowledgeMarketTitle.setKnowledgeMarketId(this.getId());
		knowledgeMarketTitle.setKnowledgeMarketTitle(this.getName());
		knowledgeMarketTitle.setMetaworksContext(new MetaworksContext());
		knowledgeMarketTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		knowledgeMarketTitle.session = session;
		return new ModalWindow(knowledgeMarketTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$RegionEdit");

	}
	
	public ModalWindow exportHtml() throws Exception {
		KnowledgeMarketTitle knowledgeMarketTitle = new KnowledgeMarketTitle();
		knowledgeMarketTitle.setKnowledgeMarketId(this.getId());
		knowledgeMarketTitle.setKnowledgeMarketTitle(this.getName());
		knowledgeMarketTitle.getMetaworksContext().setHow(HTML);
		knowledgeMarketTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		knowledgeMarketTitle.makeHtml();
				
		return new ModalWindow(knowledgeMarketTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "$ExportHtml");
	}
	
	public void addUser() throws Exception {
		
	}

	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof InstanceDrag){
			InstanceDrag instanceInClipboard = (InstanceDrag) clipboard;
			
			Instance locatorForInstanceInClipboard = new Instance();
			locatorForInstanceInClipboard.setInstId(instanceInClipboard.getInstanceId());
			locatorForInstanceInClipboard.databaseMe().setTopicId(this.getId());			
			locatorForInstanceInClipboard.flushDatabaseMe();
			
			InstanceList instList = new InstanceList(session);
			instList.load();
			
			return new Object[]{new Refresh(instList)};
		}
		return null;
	}
	
	@AutowiredFromClient
	transient public Session session;
	
//	@AutowiredFromClient
//	public ContentWindow contentWindow;
}
