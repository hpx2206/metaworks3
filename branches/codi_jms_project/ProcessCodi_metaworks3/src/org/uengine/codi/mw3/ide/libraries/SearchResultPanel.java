package org.uengine.codi.mw3.ide.libraries;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.Role;
import org.uengine.kernel.designer.web.ActivityView;

public class SearchResultPanel {
	
	int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	ArrayList<ProcessNode> processNodeList;
		public ArrayList<ProcessNode> getProcessNodeList() {
			return processNodeList;
		}
		public void setProcessNodeList(ArrayList<ProcessNode> processNodeList) {
			this.processNodeList = processNodeList;
		}
	ArrayList<HumanActivity> humanActivityList;
		public ArrayList<HumanActivity> getHumanActivityList() {
			return humanActivityList;
		}
		public void setHumanActivityList(ArrayList<HumanActivity> humanActivityList) {
			this.humanActivityList = humanActivityList;
		}
	ArrayList<Role> roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	ProcessViewPanel processViewPanel;
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
		
	@ServiceMethod
	public void load() throws Exception {
		
		// 임시로 휴먼 액티비티 생성
		humanActivityList = new ArrayList<HumanActivity>();
		TextContext textContext = new TextContext();
		textContext.setText("임시 액티비티 설명 및 이름");
		
		Documentation documentation = new Documentation();
		documentation.setAlias("D:/codi/codebase/codi/root/GG.process");
		documentation.setDefId("defId");
		documentation.setDocument("임시 문서");
		documentation.setTitle("임시 문서 제목");
		
		
		
		HumanActivity humanActivity = new HumanActivity();
		humanActivity.setId(Integer.toString(index));
		humanActivity.setName(textContext);
		humanActivity.setDescription(textContext);
		humanActivity.setDocumentation(documentation);
		
		humanActivityList.add(humanActivity);
		
		
		// 임시로 롤 생성
		roleList = new ArrayList<Role>();
		Role role = new Role();
		role.setName("임시 롤 이름");
		role.setDisplayName(textContext);
		role.setMetaworksContext(getMetaworksContext());
		
		roleList.add(role);
		
		
		String projectId = MetadataBundle.getProjectId();		
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		processViewPanel = new ProcessViewPanel();
		processNodeList = new ArrayList<ProcessNode>();
	
		
		File file = new File(mainPath);
		String[] childFilePaths = file.list();
		
		for(int i=0; i<childFilePaths.length; i++){
			
			// 여기부터 view
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
			
			if(!childFile.isDirectory()){
				String type = ResourceNode.findNodeType(childFile.getAbsolutePath());
			
				// 디렉토리가 아니고 프로세스면.
				if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
					ProcessNode processNode = new ProcessNode();
					processNode.setId("codi/root");
					processNode.setName(childFile.getName());
					processNode.setPath(childFile.getPath());
					processNode.setParentId(childFile.getParent());
					processNode.setType(TreeNode.TYPE_FILE_PROCESS);
					processNode.setMetaworksContext(getMetaworksContext());
					
					processNodeList.add(processNode);
				}
				
			}
		}
	}
	
	@ServiceMethod(callByContent = true)
	public Object showProcess() {
		processViewPanel.setAlias(processNodeList.get(index).getPath());
		processViewPanel.setDefId(processNodeList.get(index).getId());
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		return processViewPanel;
	}
	
	public Object showActivity() {
		return null;
	}
	
	public Object showRole() {
		return null;
	}
}
