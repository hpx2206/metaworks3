package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class DocumentDrag {

	Long taskId;
		@Id
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
		@ServiceMethod(mouseBinding="drag")
		public Session cut(){
			session.setClipboard(this);
			return session;
		}
		
		@AutowiredFromClient
		public Session session;
	}
