package examples;

import org.metaworks.widget.ModalWindow;

public interface IPerspectiveInfo {

	public String getType();
	public void setType(String type);
	
//	public Session session;
	public void add();
	public Object[] delete();
	public ModalWindow modify()  throws Exception;
	public void load();
	public void followersLoad() throws Exception;
}
