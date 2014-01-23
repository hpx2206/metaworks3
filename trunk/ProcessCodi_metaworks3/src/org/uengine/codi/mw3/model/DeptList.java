package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;

public class DeptList implements ContextAware {
	
	public DeptList() {
		setMetaworksContext(new MetaworksContext());
	}
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	IDept dept;
		public IDept getDept() {
			return dept;
		}
		public void setDept(IDept dept) {
			this.dept = dept;
		}
		
	Follower follower;
		public Follower getFollower() {
			return follower;
		}
		public void setFollower(Follower follower) {
			this.follower = follower;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public void loadWithCheckFollowedDept(Session session) throws Exception{
		IDept dept = null;
		if( this.getFollower() != null ){
			dept = this.getFollower().findDepts(null);
		}
		
		dept.getMetaworksContext().setHow("addFollower");
		dept.getMetaworksContext().setWhere(User.WHERE_ADDFOLLOWER);
		
		setDept(dept);
		
	}
	
}