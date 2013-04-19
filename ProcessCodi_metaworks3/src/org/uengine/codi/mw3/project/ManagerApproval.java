package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.component.SelectBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(ejsPath="genericfaces/FormFace.ejs")
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
		setAccept( new SelectBox() ); 
		accept.add( "승인" , "yes" );  
		accept.add( "거부" , "no" );  
	}
	
	SelectBox accept;
		@Face(displayName="승인여부")
		public SelectBox getAccept() {
			return accept;
		}
		public void setAccept(SelectBox accept) {
			this.accept = accept;
		}
		
	String name;
		@NonLoadable
		public String getName() {
			return name;
		}	
		public void setName(String name) {
			this.name = name;
		}
		
	@Autowired
	public ProcessManagerRemote processManager;	

	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}


	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
