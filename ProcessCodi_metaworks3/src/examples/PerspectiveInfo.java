package examples;

import org.metaworks.annotation.TypeSelector;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.WorkItem;


public class PerspectiveInfo implements IPerspectiveInfo{

	String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModalWindow modify() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void followersLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
