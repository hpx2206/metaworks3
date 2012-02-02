package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;

@Face(ejsPath="genericfaces/Window.ejs")
public class InstanceViewPublic {
	
	@Autowired
	InstanceViewContent instanceViewContent;
	
	public InstanceViewPublic(){
		System.out.println("InstanceViewPublic");
	}
	
	@ServiceMethod
	public InstanceViewContent load() throws Exception {
		System.out.println("InstanceViewPublic.load()");
		
		IInstance instances = (IInstance) Database.sql(IInstance.class, 
				  "select * from bpm_procinst where instId=?instId"
			);
		
		instances.setInstId((long)90);
		instances.select();
		
		if(instances.next()){		
			instanceViewContent.load(instances);
		
			return instanceViewContent;
		}else{
			throw new Exception("No such instance !" );
		}
		
	}
}
