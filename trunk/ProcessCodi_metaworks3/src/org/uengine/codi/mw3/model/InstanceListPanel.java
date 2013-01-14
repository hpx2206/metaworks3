package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.knowledge.WfPanel;
@Face(
		ejsPathMappingByContext=
	{
		"{where: 'pinterest', face: 'dwr/metaworks/org/uengine/codi/mw3/model/InstanceListPanel_pinterest.ejs'}",
		"{where: 'sns', face: 'dwr/metaworks/org/uengine/codi/mw3/model/InstanceListPanel_sns.ejs'}",
	}		

)
public class InstanceListPanel implements ContextAware{
	
	@AutowiredFromClient
	public Session session;

	public InstanceListPanel(){
		this(null);
	}
	
	
	public InstanceListPanel(Session session){		
		setMetaworksContext(new MetaworksContext());
		
		SearchBox searchBox = new SearchBox();
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);
		setSearchBox(searchBox);

		// TODO:2013-01-10
/*		if(session!=null){
			this.session = session;
			
			newInstantiator = new CommentWorkItem();
			newInstantiator.setWriter(session.getUser());
			newInstantiator.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			newInstantiator.setInstantiation(true);
			
			if("sns".equals(session.getEmployee().getPreferUX())){
				this.getMetaworksContext().setWhere("sns");
				newInstantiator.getMetaworksContext().setHow("sns");
			}
			if("topic".equals(session.getLastPerspecteType()))
				this.getMetaworksContext().setHow("topic");
			
		}*/
		
		if(session!=null){
			try{
				newInstancePanel = new NewInstancePanel();			
				newInstancePanel.load(session);
			}catch(Exception e){
				e.printStackTrace();
			}
				
			if("sns".equals(session.getEmployee().getPreferUX())){
				this.getMetaworksContext().setWhere("sns");
			}
			if("topic".equals(session.getLastPerspecteType()))
				this.getMetaworksContext().setHow("topic");
			
			instanceList = new InstanceList();
			instanceList.init();
		
			this.setPreloaded(true);
		}
	}
	
	public void topicFollowersLoad() throws Exception{
		topicFollowers =  new TopicFollowers();
		topicFollowers.session = session;
		topicFollowers.load();
	}

	MetaworksContext metaworksContext;
		
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
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
	
	NewInstancePanel newInstancePanel;
		public NewInstancePanel getNewInstancePanel() {
			return newInstancePanel;
		}
		public void setNewInstancePanel(NewInstancePanel newInstancePanel) {
			this.newInstancePanel = newInstancePanel;
		}
	
/*	WorkItem newInstantiator;
		public WorkItem getNewInstantiator() {
			return newInstantiator;
		}
		public void setNewInstantiator(WorkItem newInstantiator) {
			this.newInstantiator = newInstantiator;
		}	*/
	


	WfPanel knowledge;
		public WfPanel getKnowledge() {
			return knowledge;
		}
		public void setKnowledge(WfPanel knowledge) {
			this.knowledge = knowledge;
		}
		
	TopicFollowers topicFollowers;
		public TopicFollowers getTopicFollowers() {
			return topicFollowers;
		}
		public void setTopicFollowers(TopicFollowers topicFollowers) {
			this.topicFollowers = topicFollowers;
		}


	boolean preloaded;
		public boolean isPreloaded() {
			return preloaded;
		}
		public void setPreloaded(boolean preloaded) {
			this.preloaded = preloaded;
		}

		
	@ServiceMethod
	public Object[] load() throws Exception {
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		//this.instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		
		return personalPerspective.loadAllICanSee();
	}
	
	//@Hidden
	@Face(displayName="$Calendar")
	@ServiceMethod//(inContextMenu=true)
//	@Test(scenario="first", starter=true, instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.model.ContactListPanel.chat()")
	@Test(scenario="first", instruction="창을 닫으시고, '우측클릭 > Calendar' 를 클릭하시면 내가 참여하고 있는 목록을 달력으로 볼 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.admin.PageNavigator.goKnowledge()")	
	public void switchToScheduleCalendar() throws Exception{
		this.setInstanceList(null);
		this.setKnowledge(null);
		this.setTitle("$perspective.calendar");
		this.scheduleCalendar = new ScheduleCalendar();
		this.scheduleCalendar.session = session;
		this.scheduleCalendar.load();
		this.preloaded = true;
		if( this.getMetaworksContext() != null && "topic".equals(this.getMetaworksContext().getWhere()) ){
			this.topicFollowersLoad();
		}
	}
	
	//@Hidden
	@Face(displayName="$All")
	@ServiceMethod//(inContextMenu=true)
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

	@ServiceMethod//(inContextMenu=true)
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

	@ServiceMethod//(inContextMenu=true)
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
	@ServiceMethod//(inContextMenu=true)
	@Test(scenario="first", instruction="$first.SwitchToKnowledge", next="autowiredObject.org.uengine.codi.mw3.knowledge.WfPanel.newNode()")
	public void switchToKnowledge() throws Exception{
		this.setInstanceList(null);
		this.setScheduleCalendar(null);
		if( this.getMetaworksContext() != null && "topic".equals(this.getMetaworksContext().getWhere()) ){
			this.topicFollowersLoad();
			this.knowledge = new WfPanel();
			this.knowledge.session = session;
			this.knowledge.load(session.getLastSelectedItem());
		}else{
			this.setTitle("$perspective.strategic");
			this.knowledge = new WfPanel();
			this.knowledge.session = session;
			this.knowledge.load(session.getCompany().getComCode());
		}
		this.preloaded = true;
	}
	

	InstanceList instanceList;
		public InstanceList getInstanceList() {
			return instanceList;
		}
		public void setInstanceList(InstanceList instanceList) {
			this.instanceList = instanceList;
		}
	

	@Test(scenario="first", starter=true, instruction="$first.NewInstance", next="autowiredObject.org.uengine.codi.mw3.model.IProcessMap@examples/process/IssueManagement.process.initiate()")
	@ServiceMethod(inContextMenu=true)
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		
		if(session.getEmployee().getIsAdmin())
			newInstancePanel.getMetaworksContext().setHow("admin");
		
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		NewInstanceWindow window = new NewInstanceWindow(newInstancePanel);
		if( session.getWindowTitle() != null){
			window.setTitle(session.getWindowTitle());
		}
		return window;
	}
	
	
}
