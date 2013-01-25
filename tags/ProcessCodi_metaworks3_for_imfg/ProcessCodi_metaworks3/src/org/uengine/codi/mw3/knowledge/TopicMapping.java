package org.uengine.codi.mw3.knowledge;

import java.util.HashMap;
import java.util.Map;

import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.model.IDept;
import org.uengine.codi.mw3.model.IUser;

public class TopicMapping extends Database<ITopicMapping> implements ITopicMapping {
	 
	String topicId;
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
		
	Long topicMappingId;
		@Override
		public Long getTopicMappingId() {
			return topicMappingId;
		}
		@Override
		public void setTopicMappingId(Long topicMappingId) {
			this.topicMappingId = topicMappingId;
		}
		
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
	
	int assigntype;
		public int getAssigntype() {
			return assigntype;
		}
		public void setAssigntype(int assigntype) {
			this.assigntype = assigntype;
		}
		
	public ITopicMapping saveMe() throws Exception {
		
		   Map options = new HashMap();
		   options.put("useTableNameHeader", "false");
		   options.put("onlySequenceTable", "true");
		   
		   KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("bpm_topicmapping", options);
		   kg.select();
		   kg.next();
		     
		   Number number = kg.getKeyNumber();
		   
		   setTopicMappingId(number.longValue());
				   
//		   setTopicMappingId( UniqueKeyGenerator.issueKey("TopicMapping", TransactionContext.getThreadLocalInstance()).toString() );
		   
		   return createDatabaseMe();
	}
	
	public ITopicMapping findByUser() throws Exception {
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from BPM_TOPICMAPPING ");
		selectbf.append(" where topicid = ?topicid");
		selectbf.append(" and userid = ?userid");
		
		ITopicMapping dao = (ITopicMapping) sql(ITopicMapping.class,	selectbf.toString());
		dao.setTopicId( this.getTopicId());
		dao.setUserId( this.getUserId());
		dao.select();
		
		return dao;
	}
	
	public IUser findUser() throws Exception {
		IUser users = (IUser) Database.sql(IUser.class, "select  userId, userName name from BPM_TOPICMAPPING  where topicId=?topicId and assigntype=?assigntype ");
		
		users.set("topicId", this.getTopicId() );
		users.set("assigntype", 0);
		users.select();
		
		return users;
	}
	public IDept findDept() throws Exception {
		IDept dept = (IDept) Database.sql(IDept.class, "select  userId as PARTCODE , userName as  PARTNAME  from BPM_TOPICMAPPING  where topicId=?topicId and assigntype=?assigntype ");
		
		dept.set("topicId", this.getTopicId() );
		dept.set("assigntype", 2);
		dept.select();
		
		return dept;
	}
	
	public void remove() throws Exception {
		deleteDatabaseMe();
	}
}
