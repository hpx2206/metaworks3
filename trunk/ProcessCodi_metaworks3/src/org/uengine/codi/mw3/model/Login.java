package org.uengine.codi.mw3.model;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.CodiClassLoader;

public class Login extends Database<ILogin> implements ILogin{
	

		
	@Id
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String portrait;
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		
	boolean isAdmin;
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

	/*
	@Override
	public Main login() throws Exception {
		try{
			setAdmin(databaseMe().isAdmin());
		}catch(Exception e){}
		
		Main main = new Main(this);
		
		
		return main;
	}
	*/
	@Override
	public Main login() throws Exception {
//		try{
//			setAdmin(databaseMe().isAdmin());
//		}catch(Exception e){}
//		
//		Main admin = new Main(this);
//		
//		//setting the facebook user Id into session attribute;
//		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
//		session.setAttribute("userId", getUserId());
//		
//		if(new File(CodiClassLoader.mySourceCodeBase()).exists()){
//			session.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());
//		}
//		
//		return admin;
		return null;
	}
		
	
}
