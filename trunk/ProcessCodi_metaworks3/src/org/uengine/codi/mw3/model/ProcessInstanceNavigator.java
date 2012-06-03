package org.uengine.codi.mw3.model;

import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessInstanceNavigator {
	
	public void load(String instanceId) throws Exception{
		Instance instance = new Instance();
//		instance.setInstId(new Long(instanceId));
		
//		rootInstance = instance.databaseMe();
//		rootInstance.getMetaworksContext().setWhere("instanceNavigator");
//		
		subInstances = instance.sql("select * from bpm_procinst where rootInstId = ?rootInstId");
		subInstances.setRootInstId(new Long(instanceId));
		subInstances.select();
		//subInstances.setInstId(rootInstance.getInstId());
		subInstances.getMetaworksContext().setWhere("instanceNavigator");
		
		
	}
	
//	IInstance rootInstance;
//		
//		public IInstance getRootInstance() {
//			return rootInstance;
//		}
//	
//		public void setRootInstance(IInstance rootInstance) {
//			this.rootInstance = rootInstance;
//		}
		
	IInstance subInstances;

		public IInstance getSubInstances() {
			return subInstances;
		}
	
		public void setSubInstances(IInstance subInstances) {
			this.subInstances = subInstances;
		}

	public ProcessManagerRemote processManager;

}

