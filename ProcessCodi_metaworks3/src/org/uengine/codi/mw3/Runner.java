package org.uengine.codi.mw3;

import javax.servlet.http.HttpSession;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;

public class Runner {

	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object run() throws Exception{
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		httpSession.setAttribute("projectId", this.getProjectId());
		
		CodiClassLoader cl = CodiClassLoader.createClassLoader(projectId, null, true);
		Object object = cl.loadClass(className).newInstance();
		
		return object;
		//Thread.currentThread().setContextClassLoader(cl);
	}
	
}
