package org.uengine.codi.mw3.ide.libraries;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.Role;

public class SearchResultPanel {
	
	int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	ArrayList<LibraryActivity> activityList;
		public ArrayList<LibraryActivity> getActivityList() {
			return activityList;
		}
		public void setActivityList(ArrayList<LibraryActivity> activityList) {
			this.activityList = activityList;
		}
	ArrayList<LibraryRole> roleList;
		public ArrayList<LibraryRole> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<LibraryRole> roleList) {
			this.roleList = roleList;
		}
	ArrayList<LibraryProcess> processList;
		public ArrayList<LibraryProcess> getProcessList() {
			return processList;
		}
		public void setProcessList(ArrayList<LibraryProcess> processList) {
			this.processList = processList;
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
	ProcessAttributePanel processAttributePanel;
		public ProcessAttributePanel getProcessAttributePanel() {
			return processAttributePanel;
		}
		public void setProcessAttributePanel(ProcessAttributePanel processAttributePanel) {
			this.processAttributePanel = processAttributePanel;
		}
	//emberjs example member
//	EmberjsExample emberjsExample;
//		public EmberjsExample getEmberjsExample() {
//			return emberjsExample;
//		}
//		public void setEmberjsExample(EmberjsExample emberjsExample) {
//			this.emberjsExample = emberjsExample;
//		}
	@ServiceMethod
	public void load() throws Exception {
		
		// 임시로 휴먼 액티비티 생성
		LibraryActivity libraryActivity = new LibraryActivity();
		libraryActivity.load();
		
		activityList = new ArrayList<LibraryActivity>();
		activityList.add(libraryActivity);
		
		
		// 임시로 롤 생성
		LibraryRole libraryRole = new LibraryRole();
		libraryRole.load();
		
		roleList = new ArrayList<LibraryRole>();
		roleList.add(libraryRole);
		
		
		// 임시로 프로세스 생성
		LibraryProcess libraryProcess = new LibraryProcess();
		libraryProcess.load();
		
		processList = new ArrayList<LibraryProcess>();
		processList.add(libraryProcess);
		
		// 프로세스 어트리뷰트 생성
		processAttributePanel = new ProcessAttributePanel();
		processAttributePanel.setDocumentation(activityList.get(0).getHumanActivity().getDocumentation());
		
		
		// 프로세스 뷰 패널 생성
		processViewPanel = new ProcessViewPanel();
		
		//emberjsExample
//		emberjsExample = new EmberjsExample();
//		emberjsExample.load();
	}
	
}
