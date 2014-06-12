package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class LikeItem extends Database<ILikeItem> implements ILikeItem {
	public LikeItem() {
		this.loaded = false;
	}
	
	public Long instId;
		public Long getInstId() {
			return instId;
		}
	
		public void setInstId(Long instId) {
			this.instId = instId;
		}
	
	public String empCode;
		public String getEmpCode() {
			return empCode;
		}
	
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		
	public int checked;
		public int getChecked() {
			return checked;
		}
	
		public void setChecked(int checked) {
			this.checked = checked;
		}
		
	public int count;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	boolean loaded;
		public boolean isLoaded() {
			return loaded;
		}
	
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	public ILikeItem checkEmpLikeClick() throws Exception{
	  StringBuffer sb = new StringBuffer();
	  sb.append("select checked, empcode from likeItem ");
	  sb.append(" where instid=?instid ");
	  sb.append(" and empcode=?empcode ");
	  
	  ILikeItem dao = null;
	  
	  try {
		  dao = sql(sb.toString());
		  dao.setInstId(this.getInstId());
		  dao.setEmpCode(this.getEmpCode());
		  dao.select();
	   
		  if(!dao.next())
			  dao = null;
	   
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  
	  return dao;
	}
	
	public void addLikeInstance() throws Exception {
		this.createDatabaseMe();
	}
	
	@ServiceMethod(callByContent=true)
	public void findLikeCount() throws Exception {
	  StringBuffer sb = new StringBuffer();
	  sb.append("select * from likeItem ");
	  sb.append(" where instid=?instid ");
	  // 1은 체크됨. 좋아요를 눌렀다는 의미
	  sb.append(" and checked=1 ");
	 
	  ILikeItem likeItem = (ILikeItem) sql(ILikeItem.class, sb.toString());
	  likeItem.setInstId(this.getInstId());
	  likeItem.select();
	  
	  this.setCount(likeItem.size());
	  this.setLoaded(true);
	  
	  
	}
}
