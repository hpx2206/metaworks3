package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class InstanceList implements ContextAware {

	final static int PAGE_CNT = 15;
	
	public InstanceList() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("more");
	}
	
	IInstance instances;			
		public IInstance getInstances() {
			return instances;
		}	
		public void setInstances(IInstance instances) {
			this.instances = instances;
		}
	
	IUser user;
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}
	
	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	int page;
		@Id
		@Hidden
		@AutowiredToClient
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
	Long firstInstId;	
		public Long getFirstInstId() {
			return firstInstId;
		}
		public void setFirstInstId(Long firstInstId) {
			this.firstInstId = firstInstId;
		}
		
	Long lastInstId;
		public Long getLastInstId() {
			return lastInstId;
		}
		public void setLastInstId(Long lastInstId) {
			this.lastInstId = lastInstId;
		}

	MetaworksContext metaworksContext;	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	protected void load(IUser user) throws Exception{
		load(user, null);
	}

	protected void load(IUser user, String keyword) throws Exception{
		setUser(user);
		setKeyword(keyword);
		
		instances = (IInstance) Database.sql(IInstance.class, 
					  "select * from bpm_procinst where initEp=?initEp "
				    + ((getLastInstId() != null) ? " and instId<?instId " : "")
					+ (keyword !=null ? " and name like '%" + keyword + "%'" : "") 
					+ " order by starteddate desc limit " + PAGE_CNT
				);
		
		instances.setInitEp(user.getUserId());
		instances.setInstId(getLastInstId());
		instances.select();		
		
		if(instances.next()){
			if(getFirstInstId() != null){
				setFirstInstId(instances.getInstId());
			}
			
			if(instances.last())
				setLastInstId(instances.getInstId());
		}else{
			getMetaworksContext().setWhen("view");
		}
	}
	
	protected void loadBefore(IUser user, String keyword) throws Exception{
		setUser(user);
		setKeyword(keyword);
		
		instances = (IInstance) Database.sql(IInstance.class, 
					  "select * from bpm_procinst where initEp=?initEp "
				    + ((getFirstInstId() != null) ? " and instId>?instId " : "")
					+ (keyword !=null ? " and name like '%" + keyword + "%'" : "") 
					+ " order by starteddate desc"
				);
		
		instances.setInitEp(user.getUserId());
		instances.setInstId(getFirstInstId());
		instances.select();		
		
		if(instances.next()){
			setFirstInstId(instances.getInstId());
		}else{
			getMetaworksContext().setWhen("view");
		}
	}
	
	@ServiceMethod(callByContent=true, target="append_parent")
	public InstanceList more() throws Exception{
		InstanceList instanceList = new InstanceList();
		instanceList.setFirstInstId(getFirstInstId());
		instanceList.setLastInstId(getLastInstId());
		instanceList.load(getUser(), getKeyword());		
		
		return instanceList;
	}
	
	@AutowiredFromClient
	public Session session;

}
