package org.uengine.codi.mw3.knowledge;

import org.metaworks.Remover;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmanager.ProcessManagerRemote;


public class ProjectNode extends TopicNode implements IProjectNode {
	
	public final static String TYPE_PROJECT = "project";
	
	public ProjectNode(){
		this.setType(TYPE_PROJECT);
	}
	
	public Object[] loadTopic() throws Exception {
		// TODO Auto-generated method stub

		if(pageNavigator != null && "knowlege".equals(pageNavigator.getPageName())){
			return new Object[]{new BrainstormPanel(this.getId())};
		}else{
			
			String title = "프로젝트: " + getName();
			Object[] returnObject = Perspective.loadInstanceListPanel(session, TYPE_PROJECT, getId(), title);
			
			return returnObject;
		}
	}

	public static IProjectNode load(Session session) throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select * from bpm_knol knol");
//		sb.append(" where knol.type = ?type");
//		sb.append(" and knol.companyId = ?companyId");
//		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
//		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
//		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
//		sb.append(" order by no");
//
//		IProjectNode dao = (IProjectNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IProjectNode.class); 
//		dao.set("type", "project");
//		dao.set("userid", session.getEmployee().getEmpCode());
//		dao.set("companyId", session.getCompany().getComCode());
//		dao.select();
		
		IProjectNode dao  = (IProjectNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), "select * from bpm_knol where type= ?type and companyId=?companyId order by name", IProjectNode.class);
		dao.set("type", TYPE_PROJECT);
		
		// TODO: modify multi tenancy
		dao.set("companyId", session.getCompany().getComCode());
		dao.select();

		return dao;
	}
	
	public static IProjectNode completedProject(String companyId) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT knol.id, knol.name ");
		sql.append("  FROM bpm_knol knol");
//		sql.append("  FROM bpm_procinst inst  ");
		sql.append(" WHERE knol.type=?type AND knol.companyid=?companyid");
//		sql.append("	AND knol.linkedinstid=inst.instid");
		
		
		
		IProjectNode dao  = (IProjectNode) Database.sql(IProjectNode.class, sql.toString());
		
		dao.setType(TYPE_PROJECT);
		dao.setCompanyId(companyId);
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
		return new Object[]{new Remover(this)};
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	
}