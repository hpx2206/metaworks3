package org.uengine.dbrepo;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="appdatabase")
public interface IAppDbRepository extends IDAO{
	
	
	public Long getIddatabase();
	public void setIddatabase(Long iddatabase);
	
	@Id
	public String getAppid();
	public void setAppid(String appid);
	
	public String getDbname();
	public void setDbname(String dbname);
	
	public String getUser();
	public void setUser(String user);
	
	public String getPassword();
	public void setPassword(String password);
	
	public int getDbtype();
	public void setDbtype(int dbtype);
	
	public String getRole();
	public void setRole(String role);
	
	public int getMode();
	public void setMode(int mode);
	
	public String getDburl();
	public void setDburl(String dburl);
}

