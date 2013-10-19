package org.uengine.codi.mw3.knowledge;

import java.util.Calendar;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.admin.OcePageNavigator;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.OceMain;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.RecentItem;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.oce.dashboard.DashboardPanel;
import org.uengine.oce.dashboard.DashboardWindow;
import org.uengine.processmanager.ProcessManagerRemote;


public class ProjectNode extends TopicNode implements IProjectNode {
	
	public final static String TYPE_PROJECT = "project";
	
	public ProjectNode(){
		this.setType(TYPE_PROJECT);
	}
	
	@ServiceMethod(callByContent = true)
	public Object[] loadTopic() throws Exception {

		session.setLastPerspecteType(TYPE_PROJECT);
		session.setLastSelectedItem(this.getId());
		
		Perspective perspective = new Perspective();
		perspective.session = session;
		Object[] returnObject =  perspective.loadInstanceListPanel(TYPE_PROJECT, getId());
		String title = "프로젝트: " + getName();
//		Object[] returnObject = Perspective.loadInstanceListPanel(session, TYPE_PROJECT, getId(), title);
		
		// recentItem 에 create
		RecentItem recentItem = new RecentItem();
		recentItem.setEmpCode(session.getEmployee().getEmpCode());
		recentItem.setItemId(this.getId());
		recentItem.setItemType(this.getType());
		recentItem.setUpdateDate(Calendar.getInstance().getTime());
		
		recentItem.add();
		
		DashboardWindow window = new DashboardWindow();
		window.setPanel(returnObject[1]);
		window.setTitle(title);
		
		return new Object[]{window };
	}

	public static IProjectNode load(Session session) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol knol");
		sb.append(" left join recentItem item on item.itemId = knol.id and item.empcode = ?userid and item.itemType=?type");
		sb.append(" where knol.type = ?type");
		sb.append(" and knol.companyId = ?companyId");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
		sb.append(" order by updateDate desc limit " + GlobalContext.getPropertyString("topic.more.count", DEFAULT_CONTACT_COUNT));
		
		String tenantId;
		if(TenantContext.getThreadLocalInstance()!=null && TenantContext.getThreadLocalInstance().getTenantId()!=null){
			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		}else{
			tenantId = session.getCompany().getComCode();
		}
		
		IProjectNode dao = (IProjectNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IProjectNode.class); 
		dao.set("type", TYPE_PROJECT);
		dao.set("userid", session.getEmployee().getEmpCode());
		dao.set("companyId", tenantId);
		dao.select();
		
		

		return dao;
	}
	
	public IProjectNode findById() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT knol.id, knol.name ");
		sql.append("  FROM bpm_knol knol");
		sql.append(" WHERE knol.type=?type AND knol.id=?Id");
		
		IProjectNode dao  = (IProjectNode) Database.sql(IProjectNode.class, sql.toString());
		
		dao.setType(TYPE_PROJECT);
		dao.setId(this.getId());
		dao.select();
		
		return dao;
		
	}
	
	public IProjectNode findByNameForProject() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT knol.id, knol.name ");
		sql.append("  FROM bpm_knol knol");
		sql.append(" WHERE knol.type=?type AND knol.name=?name");
		
		IProjectNode dao  = (IProjectNode) Database.sql(IProjectNode.class, sql.toString());
		
		dao.setType(TYPE_PROJECT);
		dao.setName(this.getName());
		dao.select();
		
		if(dao.next())
			return dao;
		else
			return null;
		
		
	}
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] remove() throws Exception {
		
		if( session.getUser().getUserId().equalsIgnoreCase(getAuthorId()) || session.getEmployee().getIsAdmin()) {
			// 삭제는 진짜 삭제가 아닌 topic 만 제거를 하여 지식노드에서는 보이도록 설정됨
			// deleteDatabaseMe();
			 deleteDatabaseMe();
			
			 IInstance instance = (IInstance)sql(IInstance.class, "select * from bpm_procinst where topicId= ?topicId");
			 instance.set("topicId", this.getId());
			 instance.select();
			 
			 if(instance.size() > 0) {
				instance.next();

				Instance inst = new Instance();
				inst.copyFrom(instance);
				
				inst.deleteDatabaseMe();
			 }
			 
			 
//			StringBuffer sb = new StringBuffer();
//			sb.append("update bpm_knol");
//			sb.append("   set type = null ");
//			sb.append(" where id=?topicId");
//			
//			ITopicNode updateNode = (ITopicNode) sql(ITopicNode.class,	sb.toString());
//			updateNode.set("topicId", this.getId());
//			
//			updateNode.update();
			
		} else {
			throw new Exception("관리자나 초기토픽생성자만 수정가능합니다.");
		}
		
		if(pageNavigator instanceof OcePageNavigator && "process".equals(pageNavigator.getPageName()) && "dashboard".equals(this.session.getLastPerspecteType())){
			DashboardPanel dashboardPanel = new DashboardPanel();
			dashboardPanel.load(session);
			return new Object[]{new MainPanel(new OceMain(session))};
		}
		
		return new Object[]{new Remover(this)};
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	
}