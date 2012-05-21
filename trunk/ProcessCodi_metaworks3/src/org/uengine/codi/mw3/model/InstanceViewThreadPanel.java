package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceViewThreadPanel {
	
	public InstanceViewThreadPanel(){}
	
	protected InstanceViewThreadPanel(String instanceId) throws Exception{
		setThread(WorkItem.find(instanceId));
	}


	IWorkItem thread;
		public IWorkItem getThread() {
			return thread;
		}
		public void setThread(IWorkItem thread) {
			this.thread = thread;
		}	
	

}
