package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

public class FollowerSelectCommand {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public InstanceFollowers instanceFollowers;
	
	@AutowiredFromClient
	public TopicFollowers topicFollowers;
	
	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public FollowerSelectTab followerSelectTab;
	
	String followerType;
		public String getFollowerType() {
			return followerType;
		}
		public void setFollowerType(String followerType) {
			this.followerType = followerType;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] addFollowers() throws Exception{
		if( Followers.ADD_INSTANCEFOLLOWERS.equals(getFollowerType()) ){
			String instId = instanceFollowers.getInstanceId();
			// 현재 부서트리에서는 사용자를 체크 안하게 되어있지만 확장성을 위하여 employee쪽도 넣는 부분을 구현해 놓음
			ArrayList<TreeNode> checkNodes = followerSelectTab.getDeptTree().getCheckNodes();
			for(int i=0; i<checkNodes.size(); i++){
				TreeNode node = checkNodes.get(i);
				RoleMapping roleMap = RoleMapping.create();
				roleMap.setName(node.getName());
				roleMap.setEndpoint(node.getId());
				roleMap.setResourceName(node.getId());
				
				if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
					roleMap.setAssignType(Role.ASSIGNTYPE_DEPT);
				}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
					roleMap.setAssignType(Role.ASSIGNTYPE_USER);
				}
				processManager.putRoleMapping(instId, roleMap);
				processManager.applyChanges();
			}
			ArrayList<TreeNode> checkEmpNodes = followerSelectTab.getEmployeeTree().getCheckNodes();
			for(int i=0; i<checkEmpNodes.size(); i++){
				TreeNode node = checkEmpNodes.get(i);
				RoleMapping roleMap = RoleMapping.create();
				roleMap.setName(node.getName());
				roleMap.setEndpoint(node.getId());
				roleMap.setResourceName(node.getId());
				
				if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
					roleMap.setAssignType(Role.ASSIGNTYPE_DEPT);
				}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
					roleMap.setAssignType(Role.ASSIGNTYPE_USER);
				}
				processManager.putRoleMapping(instId, roleMap);
				processManager.applyChanges();
			}
			
			instanceFollowers = new InstanceFollowers();
			instanceFollowers.setInstanceId(instId);
			instanceFollowers.load();
			return new Object[]{new Refresh(instanceFollowers) , new Remover(new ModalWindow())};
		}else if( Followers.ADD_TOPICFOLLOWERS.equals(getFollowerType()) ){
			ArrayList<TreeNode> checkNodes = followerSelectTab.getDeptTree().getCheckNodes();
			for(int i=0; i<checkNodes.size(); i++){
				TreeNode node = checkNodes.get(i);
				TopicMapping tm = new TopicMapping();
				tm.setTopicId( session.getLastSelectedItem() );
				tm.setUserId(node.getId());
				tm.setUserName(node.getName());
//				tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
				
				if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
					tm.setAssigntype(2);
				}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
					tm.setAssigntype(0);
				}
				tm.saveMe();
				tm.flushDatabaseMe();
			}
			ArrayList<TreeNode> checkEmpNodes = followerSelectTab.getEmployeeTree().getCheckNodes();
			for(int i=0; i<checkEmpNodes.size(); i++){
				TreeNode node = checkEmpNodes.get(i);
				TopicMapping tm = new TopicMapping();
				tm.setTopicId( session.getLastSelectedItem() );
				tm.setUserId(node.getId());
				tm.setUserName(node.getName());
//				tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
				
				if(DeptTreeNode.TYPE_DEFAULT.equals(node.getType())){
					tm.setAssigntype(2);
				}else if(EmployeeTreeNode.TYPE_DEFAULT.equals(node.getType())){
					tm.setAssigntype(0);
				}
				tm.saveMe();
				tm.flushDatabaseMe();
			}
			
			topicFollowers = new TopicFollowers();
			topicFollowers.session = session;
			topicFollowers.load();
			
			return new Object[]{new Refresh(topicFollowers) , new Remover(new ModalWindow())};
		}
		
		// TODO notification 구현
		
		return null;
	}
	
	@ServiceMethod
	public Remover cancle() throws Exception{
		return new Remover(new ModalWindow());
	}
	
}
