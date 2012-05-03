package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceNameChanger {

	String instanceName;
		@Name
		@Range(size=30)
		//@Face(ejsPath="genericfaces/richText.ejs", options={"cols=")
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

	String instanceId;
		@Id
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

	@ServiceMethod(callByContent=true, keyBinding="enter")
	public IInstance change() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(new Long(instanceId));
		instance.databaseMe().setName(getInstanceName());
		
		return instance.databaseMe();
	}
	
	
	
}
