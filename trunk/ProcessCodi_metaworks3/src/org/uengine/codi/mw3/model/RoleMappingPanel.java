package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.dao.MetaworksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
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


	public RoleMappingPanel(ProcessManagerRemote processManager, String defId, Session session) throws Exception{
		
		roleMappingDefinitions = new ArrayList<IRoleMappingDefinition>();
		
		org.uengine.kernel.ProcessDefinition definition = processManager.getProcessDefinition(defId);
		for(org.uengine.kernel.Role role : definition.getRoles()){
			RoleMappingDefinition roleMappingDefinition = new RoleMappingDefinition();
			roleMappingDefinition.setRoleDefId(session.getEmployee().getGlobalCom() + "." + defId + "." + role.getName());
			
			try{
				IRoleMappingDefinition roleMappingDef = roleMappingDefinition.databaseMe();
				roleMappingDef.getMappedUser().getMetaworksContext().setHow(IUser.HOW_PICKER);
				
//				if( roleMappingDef.getMappedUser() != null && roleMappingDef.getMappedUser().getUserId() != null ){
//					Employee user = new Employee();
//					user.setEmpCode(roleMappingDef.getMappedUser().getUserId());
//					user.copyFrom(user.databaseMe());
//					System.out.println("user.getEmpName() = " + user.getEmpName());
//					IUser usr = new User();
//					usr.setUserId(roleMappingDef.getMappedUser().getUserId());
//					usr.setName(user.getEmpName());
//					roleMappingDef.setMappedUser(usr);
//				}
//				// TODO 요거 유저가 안들어감 ㅠ_ㅠ
//				System.out.println("roleMappingDef : " + roleMappingDef.getMappedUser().getName());
				roleMappingDefinitions.add(roleMappingDef);
			}catch(Exception e){
				IRoleMappingDefinition roleMappingDef = (IRoleMappingDefinition)MetaworksDAO.createDAOImpl(IRoleMappingDefinition.class);
				roleMappingDef.setRoleDefId(roleMappingDefinition.getRoleDefId());
				roleMappingDef.setDefId(defId);
				roleMappingDef.setRoleName(role.getName());
				roleMappingDef.setMappedUser(new User());
				roleMappingDef.setComCode(session.getEmployee().getGlobalCom());
				roleMappingDef.setRoleDefType(RoleMappingDefinition.ROLE_DEF_TYPE_USER);
				roleMappingDef.getMetaworksContext().setHow(IUser.HOW_PICKER);
				
				roleMappingDefinitions.add(roleMappingDef);
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
			if( RoleMappingDefinition.ROLE_DEF_TYPE_USER.equals(roleMappingDefinition.getRoleDefType() )){
				if(roleMappingDefinition.getMappedUser()!=null && roleMappingDefinition.getMappedUser().getUserId()!=null){
					processManager.putRoleMapping(instId, roleMappingDefinition.getRoleName(), roleMappingDefinition.getMappedUser().getUserId());
				}
			}else if( RoleMappingDefinition.ROLE_DEF_TYPE_ROLE.equals(roleMappingDefinition.getRoleDefType() )){
				if(roleMappingDefinition.getMappedRoleCode()!=null ){
					RoleUser roleUser = new RoleUser();
					roleUser.setRoleCode(roleMappingDefinition.getMappedRoleCode());
					IRoleUser refRoleUser = roleUser.findUserByRoleCode();
					while(refRoleUser.next()){
						processManager.putRoleMapping(instId, roleMappingDefinition.getRoleName(), refRoleUser.getEmpCode() );
					}
					
				}
			}
		}
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
}

