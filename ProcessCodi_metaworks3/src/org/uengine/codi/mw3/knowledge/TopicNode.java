package org.uengine.codi.mw3.knowledge;

import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.AddLocalContactPanel;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.EmployeeList;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.UnifiedAddContactPanel;

public class TopicNode extends Database<ITopicNode> implements ITopicNode {
	 
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
		
		ITopicNode dao = (ITopicNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), ITopicNode.class); 
		dao.set("type", "topic");
		dao.set("userid", session.getEmployee().getEmpCode());
		dao.set("companyId", session.getCompany().getComCode());
		dao.select();
		
		return dao; 
	}
	
	public Object[] loadTopic() throws Exception{
		String title = "토픽 주제 : " + getName();
		session.setWindowTitle(title);
		return Perspective.loadInstanceListPanel(session, "topic", getId());
	}
	
	
	public Object[] remove() throws Exception {
		
		if( session.getUser().getUserId().equalsIgnoreCase(getAuthorId()) || session.getEmployee().getIsAdmin()) {
			// 삭제는 진짜 삭제가 아닌 topic 만 제거를 하여 지식노드에서는 보이도록 설정됨
//			deleteDatabaseMe();
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
	
	public void addUser() throws Exception {
		
	}
	
	public Object[] addTopicUser() throws Exception {
		ModalWindow popup = new ModalWindow();
		UnifiedAddContactPanel addTopicUserPanel = new UnifiedAddContactPanel(session);
		
		AddLocalContactPanel localContactPanel = new AddLocalContactPanel();
		IEmployee employee = new Employee();
		employee.getMetaworksContext().setWhere("addTopic");
		employee.getMetaworksContext().setHow("tree");
		employee.setGlobalCom(session.getEmployee().getGlobalCom());
		
		EmployeeList employeeList = new EmployeeList();			
		employeeList.setEmployee(employee.findByDeptOther());
		
		localContactPanel.setDeptEmployee(employeeList);
		addTopicUserPanel.setLocalContact(localContactPanel);
		
		popup.setPanel(addTopicUserPanel);
		popup.setTitle("토픽구성원 추가");
		
		return new Object[]{popup};
	}
	

	@AutowiredFromClient
	transient public Session session;
}
