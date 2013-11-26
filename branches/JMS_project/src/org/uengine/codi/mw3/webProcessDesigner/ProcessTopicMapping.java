package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.dao.Database;
import org.uengine.codi.mw3.model.IEmployee;

public class ProcessTopicMapping extends Database<IProcessTopicMapping> implements IProcessTopicMapping {

	String processName;
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
	String processPath;
		public String getProcessPath() {
			return processPath;
		}
		public void setProcessPath(String processPath) {
			this.processPath = processPath;
		}
	String topicId;
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		public void updateDatabaseMe() throws Exception{
			/*StringBuffer sb = new StringBuffer();
			sb.append("update ProcessTopicMapping set " +
					" (processName, processPath, topicId, type)" +
					" values(" +
					" ?processName, ?processPath, ?topicId, ?type ) "
					); 
			
			IProcessTopicMapping dao = null;
			dao = sql(sb.toString());
			dao.setProcessName(this.getProcessName());
			dao.setProcessPath(this.getProcessPath());
			dao.setTopicId(this.getTopicId());
			dao.setType(this.getType());
			dao.update();*/
			
		}
		
		public IProcessTopicMapping findByMe(){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append(" ProcessTopicMapping ");
			sb.append(" where processName=?processName ");
			sb.append(" and processPath=?processPath ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setProcessName(this.getProcessName());
				dao.setProcessPath(this.getProcessPath());
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dao; 
		}
		
		public IProcessTopicMapping findByTypeByPath(){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append(" ProcessTopicMapping ");
			sb.append(" where type=?type ");
			sb.append(" and processPath=?processPath ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setType(this.getType());
				dao.setProcessPath(this.getProcessPath());
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dao; 
		}
		
		

		public IProcessTopicMapping findByProcess(String processPath, String type){
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append("ProcessTopicMapping ");
			sb.append("where processPath=?processPath ");
			sb.append("and type=?type ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setProcessPath(processPath);
				dao.setType(type);
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dao; 
		}
		
		public IProcessTopicMapping findByName(){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append("ProcessTopicMapping ");
			sb.append("where processName=?processName ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setProcessName(this.getProcessName());
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dao; 
		}
		
		public IProcessTopicMapping findByTopicId(){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append("ProcessTopicMapping ");
			sb.append("where topicId=?topicId ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setTopicId(this.getTopicId());
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dao; 
		}	
		
	public IProcessTopicMapping findByProcessPath(){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from ");
			sb.append("ProcessTopicMapping ");
			sb.append("where processpath=?processpath ");
			
			IProcessTopicMapping dao = null;
			
			try {
				dao = sql(sb.toString());
				dao.setProcessPath(this.getProcessPath());
				dao.select();
				
				if(!dao.next())
					dao = null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dao; 
		}
	
	public IProcessTopicMapping findByNameByType(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(" ProcessTopicMapping ");
		sb.append(" where processpath=?processpath ");
		sb.append(" and type=?type ");
		sb.append(" and processName=?processName ");
		
		IProcessTopicMapping dao = null;
		
		try {
			dao = sql(sb.toString());
			dao.setProcessPath(this.getProcessPath());
			dao.setType(this.getType());
			dao.setProcessName(this.getProcessName());
			dao.select();
			
			if(!dao.next())
				dao = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao; 
	}
	
}
