package org.uengine.codi.mw3.webProcessDesigner;

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
	
}
