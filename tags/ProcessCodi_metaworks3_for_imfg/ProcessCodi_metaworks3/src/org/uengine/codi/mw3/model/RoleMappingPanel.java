package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;


@Face(displayName="$RoleMapping", options="hideLabels", values="true")
public class RoleMappingPanel implements ContextAware{
	
	
	MetaworksContext metaworksContext;

		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	ArrayList<IRoleMappingDefinition> roleMappingDefinitions;
	@Face(options="alignment", values="horizontal")

		public ArrayList<IRoleMappingDefinition> getRoleMappingDefinitions() {
			return roleMappingDefinitions;
		}
	
		public void setRoleMappingDefinitions(
				ArrayList<IRoleMappingDefinition> roleMappingDefinitions) {
			this.roleMappingDefinitions = roleMappingDefinitions;
		}
		
		
	public RoleMappingPanel(){}


	public RoleMappingPanel(ProcessMap processMap, Session session) throws RemoteException{
		
		processManager = processMap.processManager;
		
		roleMappingDefinitions = new ArrayList<IRoleMappingDefinition>();
		
		org.uengine.kernel.ProcessDefinition definition = processManager.getProcessDefinition(processMap.getDefId());
		for(org.uengine.kernel.Role role : definition.getRoles()){
			RoleMappingDefinition roleMappingDefinition = new RoleMappingDefinition();
			roleMappingDefinition.setRoleDefId(session.getCompany().getComCode() + "." + processMap.getDefId() + "." + role.getName());
			
			try{
				roleMappingDefinitions.add(roleMappingDefinition.databaseMe());
			}catch(Exception e){
				roleMappingDefinition.setDefId(processMap.getDefId());
				roleMappingDefinition.setRoleName(role.getName());
				roleMappingDefinition.setMappedUser(new User());
				roleMappingDefinition.setComCode(session.getCompany().getComCode());
				roleMappingDefinitions.add(roleMappingDefinition);
			}
		}
	}
	
	public void save() throws Exception{
		for(IRoleMappingDefinition roleMappingDefinition: roleMappingDefinitions){
			
			if(roleMappingDefinition.getMappedUser()!=null && roleMappingDefinition.getMappedUser().getUserId()!=null){
				if(roleMappingDefinition instanceof RoleMappingDefinition){
					
					((RoleMappingDefinition) roleMappingDefinition).createDatabaseMe();
				}else{
					
					RoleMappingDefinition copy = new RoleMappingDefinition();
					copy.copyFrom(roleMappingDefinition);
					
					try{
						copy.syncToDatabaseMe();
					}catch(Exception e){
						copy.createDatabaseMe();
					}
				}
				
			}
		}
		
	}
	
	public void putRoleMappings(ProcessManagerRemote processManager, String instId) throws Exception{
		for(IRoleMappingDefinition roleMappingDefinition: roleMappingDefinitions){
			
			if(roleMappingDefinition.getMappedUser()!=null && roleMappingDefinition.getMappedUser().getUserId()!=null){
				if(roleMappingDefinition.getMappedUser()!=null && roleMappingDefinition.getMappedUser().getUserId()!=null){
					processManager.putRoleMapping(instId, roleMappingDefinition.getRoleName(), roleMappingDefinition.getMappedUser().getUserId());
				}
				
			}
		}
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}

