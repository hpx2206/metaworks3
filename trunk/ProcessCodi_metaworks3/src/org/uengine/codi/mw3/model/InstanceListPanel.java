package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.knowledge.WfPanel;

public class InstanceListPanel {
	
	@AutowiredFromClient
	public Session session;
	
	public InstanceListPanel(){		
		setSearchBox(new SearchBox());
		
		instanceList = new InstanceList();
		instanceList.init();
	}

	String title;
		@Hidden
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	SearchBox searchBox;		
		@Face(options={"keyupSearch"}, values={"true"})
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}

	ScheduleCalendar scheduleCalendar;
		public ScheduleCalendar getScheduleCalendar() {
			return scheduleCalendar;
		}
		public void setScheduleCalendar(ScheduleCalendar scheduleCalendar) {
			this.scheduleCalendar = scheduleCalendar;
		}
	
	WfPanel knowledge;
		
		public WfPanel getKnowledge() {
			return knowledge;
		}
		public void setKnowledge(WfPanel knowledge) {
			this.knowledge = knowledge;
		}
		
		
	//@Hidden
	@Face(displayName="$Calendar")
	@ServiceMethod(inContextMenu=true)
//	@Test(scenario="first", starter=true, instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.model.ContactListPanel.chat()")
	@Test(scenario="first", instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.admin.PageNavigator.goKnowledge()")	
	public void switchToScheduleCalendar() throws Exception{
		this.setInstanceList(null);
		this.setKnowledge(null);
		this.scheduleCalendar = new ScheduleCalendar();
		this.scheduleCalendar.session = session;
		this.scheduleCalendar.load();
	}
	
	//@Hidden
	@Face(displayName="$All")
	@ServiceMethod(inContextMenu=true)
	public Object[] switchToInstanceList() throws Exception{
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		return personalPerspective.loadInstanceListPanel("allICanSee", null);
		
/*		this.setScheduleCalendar(null);
		this.setKnowledge(null);
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		InstanceListPanel instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		
		setInstanceList(instanceListPanel.getInstanceList());
*/	}

	@ServiceMethod(inContextMenu=true)
	@Face(displayName="$Following")
	public Object[] switchToInstanceListFollowing() throws Exception{
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		return personalPerspective.loadInstanceListPanel("all", null);		
/*		this.setScheduleCalendar(null);
		this.setKnowledge(null);
	
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		InstanceListPanel instanceListPanel = (InstanceListPanel) personalPerspective.loadAll()[1];
		
		setInstanceList(instanceListPanel.getInstanceList());*/
	}

	@ServiceMethod(inContextMenu=true)
	@Face(displayName="$MyToDo")
	public Object[] switchToInstanceListMyToDo() throws Exception{
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		
		return personalPerspective.loadInstanceListPanel("inbox", null);		
		
/*		this.setScheduleCalendar(null);
		this.setKnowledge(null);
	
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		InstanceListPanel instanceListPanel = (InstanceListPanel) personalPerspective.loadInbox()[1];
		
		setInstanceList(instanceListPanel.getInstanceList());*/
	}

	@Face(displayName="$Strategic")
	@ServiceMethod(inContextMenu=true)
	@Test(scenario="first", instruction="$first.SwitchToKnowledge", next="autowiredObject.org.uengine.codi.mw3.knowledge.WfPanel.newNode()")
	public void switchToKnowledge() throws Exception{
		this.setInstanceList(null);
		this.setScheduleCalendar(null);
		
		this.knowledge = new WfPanel();
		this.knowledge.load("-1");
	}
	

	InstanceList instanceList;
		public InstanceList getInstanceList() {
			return instanceList;
		}
		public void setInstanceList(InstanceList instanceList) {
			this.instanceList = instanceList;
		}
	

	@Test(scenario="first", starter=true, instruction="$first.NewInstance", next="autowiredObject.org.uengine.codi.mw3.model.IProcessMap@IssueManagement.process.initiate()")
	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		
		if(session.getEmployee().getIsAdmin())
			newInstancePanel.getMetaworksContext().setHow("admin");
		
		newInstancePanel.session = session;
		newInstancePanel.load();
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
	
}
