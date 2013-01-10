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
import org.uengine.kernel.Role;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

public class FollowerSelectCommand {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public InstanceFollowers instanceFollowers;
	
	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public FollowerSelectTab followerSelectTab;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] addFollowers() throws Exception{
		String instId = instanceFollowers.getInstanceId();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
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
		
		InstanceFollowers followers = new InstanceFollowers();
		followers.setInstanceId(instId);
		followers.load();
		
		followers.getFollowers().getMetaworksContext().setWhen(Followers.CONTEXT_WHERE_INFOLLOWERS);
		followers.getDeptFollowers().getMetaworksContext().setHow(Followers.CONTEXT_WHERE_DEPTFOLLOWERS);
		
		/// send notification to the follower 
//		final boolean postByMe = getUserId().equals(session.getUser().getUserId());
//		if(!postByMe){ //ignore myself
//			Notification noti = new Notification();
//			
//			noti.setNotiId(System.currentTimeMillis()); //TODO: why generated is hard to use
//			noti.setUserId(getUserId());
//			noti.setActorId(session.getUser().getUserId());
//			noti.setConfirm(false);
//			noti.setInputDate(Calendar.getInstance().getTime());
//			//noti.setTaskId(getTaskId());
//			noti.setInstId(new Long(instId));
//			noti.setActAbstract(session.getUser().getName() + " added "  + getName()+ " to '" + instance.databaseMe().getName() + "'");
//
//			noti.createDatabaseMe();
//			noti.flushDatabaseMe();
//		
//		
//			String followerSessionId = Login.getSessionIdWithUserId(getUserId());
//			
//			try{
//				//NEW WAY IS GOOD
//				Browser.withSession(followerSessionId, new Runnable(){
//	
//					@Override
//					public void run() {
//						//refresh notification badge
//						if(!postByMe)
//							ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + NotificationBadge.class.getName() + "').refresh", new Object[]{});
//						
//					}
//					
//				});
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		////// end
		
		return new Object[]{new Refresh(followers) , new Remover(new ModalWindow())};
	}
	
}
