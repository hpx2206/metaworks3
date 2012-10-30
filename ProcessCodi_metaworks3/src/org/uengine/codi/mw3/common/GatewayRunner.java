package org.uengine.codi.mw3.common;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.CodiClassLoader;


public class GatewayRunner implements ContextAware {

	public GatewayRunner(){
	}

	MetaworksContext metaworksContext;
	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String fullClassName;
	@Id
		public String getFullClassName() {
			return fullClassName;
		}
		public void setFullClassName(String className) {
			this.fullClassName = className;
		}
		
		
	String classOwner;
		public String getClassOwner() {
			return classOwner;
		}
		public void setClassOwner(String userId) {
			this.classOwner = userId;
		}
		
	
	Object runningObject;	
			
		public Object getRunningObject() {
			return runningObject;
		}
		public void setRunningObject(Object runningObject) {
			this.runningObject = runningObject;
		}
		
		
	@ServiceMethod(callByContent=true)
	@Face(displayName="Run (Normal)")
	public void run() throws Exception{
		setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		session.setAttribute("userId", getClassOwner());
		
		String myCodeBase = CodiClassLoader.mySourceCodeBase();
		if(myCodeBase!=null && new File(myCodeBase).exists()){
			session.setAttribute("sourceCodeBase", myCodeBase);
		}

		Object o = Thread.currentThread().getContextClassLoader().loadClass(getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
		

		setRunningObject(o);
	}
	
	
}
