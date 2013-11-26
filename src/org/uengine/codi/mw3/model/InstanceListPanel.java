package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.calendar.ScheduleCalendar;
import org.uengine.codi.mw3.common.MainPanel;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.knowledge.ProjectManager;
import org.uengine.codi.mw3.knowledge.WfPanel;
import org.uengine.codi.mw3.marketplace.AppInformation;
import org.uengine.kernel.GlobalContext;
import org.uengine.oce.dashboard.DashboardWindow;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(
		ejsPathMappingByContext=
	{
		"{where: 'pinterest', face: 'dwr/metaworks/org/uengine/codi/mw3/model/InstanceListPanel_pinterest.ejs'}",
		"{where: 'sns', face: 'dwr/metaworks/org/uengine/codi/mw3/model/InstanceListPanel_sns.ejs'}",
		"{where: 'oce_app', face: 'dwr/metaworks/org/uengine/oce/InstanceListPanel_oce.ejs'}",
		"{where: 'oce_project', face: 'dwr/metaworks/org/uengine/oce/InstanceListPanel_oce.ejs'}"
		
	}		

)
public class InstanceListPanel implements ContextAware{
	
	@AutowiredFromClient
	public Session session;

	@Autowired
	public ProcessMap processMap;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	public InstanceListPanel(){
		this(null);
	}
	
	
	public InstanceListPanel(Session session){		
		setMetaworksContext(new MetaworksContext());
		
		InstanceSearchBox searchBox = new InstanceSearchBox();
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
			if("sns".equals(session.getEmployee().getPreferUX())){
				try{
					newInstancePanel = new NewInstancePanel();			
					newInstancePanel.load(session);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if("sns".equals(session.getEmployee().getPreferUX())){
				this.getMetaworksContext().setWhere("oce");
			}
			if("topic".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("topic");
				this.setPerspectiveInfo(new TopicInfo(session));
			}
			if("project".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("project");
				this.setPerspectiveInfo(new ProjectInfo(session));
			}
			if("document".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("document");
				this.setPerspectiveInfo(new DocumentInfo(session));
			}
			if("organization".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("organization");
				this.setPerspectiveInfo(new DeptInfo(session));
			}
			if("role".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("role");
				this.setPerspectiveInfo(new RoleInfo(session));
			}
			
			if("explorer".equals(session.getLastPerspecteType()))
				this.getMetaworksContext().setHow("explorer");		
			
			if("app".equals(session.getLastPerspecteType()))
				this.getMetaworksContext().setHow("app");
			
			if("oce_project".equals(session.getUx()))
				this.getMetaworksContext().setWhere("oce_project");
			
			if("process".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("process");
				this.setPerspectiveInfo(new ProcessInfo(session));
			}
			if("valuechain".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("valuechain");
				this.setPerspectiveInfo(new ProcessInfo(session));
			}
			
			if("activity".equals(session.getLastPerspecteType())){
				this.getMetaworksContext().setHow("activity");
				this.setPerspectiveInfo(new ActivityInfo(session));
			}
			instanceList = new InstanceList(session);
			
			this.setPreloaded(true);
		}
		
		if("1".equals(PageNavigator.USE_KNOWLEDGE)){
			this.setUseKnowledge(true);
		}
	}
	
	public void topicFollowersLoad() throws Exception{
		topicFollowers =  new TopicFollowers();
		topicFollowers.session = session;
		topicFollowers.load();
	}

	public void documentFollowerLoad() throws Exception{
		documentFollowers = new DocumentFollowers();
		documentFollowers.session = session;
		documentFollowers.load();
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
	PerspectiveInfo perspectiveInfo;
		public PerspectiveInfo getPerspectiveInfo() {
			return perspectiveInfo;
		}
		public void setPerspectiveInfo(PerspectiveInfo perspectiveInfo) {
			this.perspectiveInfo = perspectiveInfo;
		}

	WorkItem newInstantiator;
		public WorkItem getNewInstantiator() {
			return newInstantiator;
		}
		public void setNewInstantiator(WorkItem newInstantiator) {
			this.newInstantiator = newInstantiator;
		}

	DocumentFollowers documentFollowers;
		public DocumentFollowers getDocumentFollowers() {
			return documentFollowers;
		}
		public void setDocumentFollowers(DocumentFollowers documentFollowers) {
			this.documentFollowers = documentFollowers;
		}

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
		
	String definitionId;
		public String getDefinitionId() {
			return definitionId;
		}
		public void setDefinitionId(String definitionId) {
			this.definitionId = definitionId;
		}
		
	boolean useKnowledge;	
		public boolean isUseKnowledge() {
			return useKnowledge;
		}
		public void setUseKnowledge(boolean useKnowledge) {
			this.useKnowledge = useKnowledge;
		}


	@ServiceMethod
	public Object[] load() throws Exception {
		
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		//this.instanceListPanel = (InstanceListPanel) personalPerspective.loadAllICanSee()[1];
		
		return personalPerspective.loadAllICanSee();
	}
	
	@ServiceMethod
	public Object[] loadOnDashboard() throws Exception {
	
		session.getEmployee().setPreferUX("sns");
		
		Object[] returnObject = Perspective.loadInstanceListPanel(session , "allICanSee", "oce");
		
		String title = GlobalContext.getPropertyString("$All");
		
		DashboardWindow window = new DashboardWindow();
		window.setPanel(returnObject);
		window.setTitle(title);

		return new Object[]{window , session};
	}
	
	@ServiceMethod
	public Object[] loadByDefId() throws Exception {
		PersonalPerspective personalPerspective = new PersonalPerspective();
		personalPerspective.session = session;
		return personalPerspective.loadInstanceListPanel("topic", definitionId);
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
			
			if(session.getEmployee().isApproved()){
				this.knowledge.load(session.getCompany().getComCode());	
			}else{
				this.knowledge.load(session.getUser().getUserId());
			}	
			
			
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
	@ServiceMethod
	public ContentWindow newInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.load(session);
		
/*
 * 		jinwon refactoring
 * 
 		if(session.getEmployee().getIsAdmin())
			newInstancePanel.getMetaworksContext().setHow("admin");
*/		
		
		
		
		NewInstanceWindow window = new NewInstanceWindow(newInstancePanel);
		if( session.getWindowTitle() != null){
			window.setTitle(session.getWindowTitle());
		}
		
		return window;
	}
	
	
	@Face(displayName = "등록")
	@ServiceMethod(target="popup", callByContent=true)
	public Object loadProjectInfo() throws Exception {
		
		ProjectManager projectManager = new ProjectManager();
		projectManager.processManager = processManager; 
		projectManager.load(session.getLastSelectedItem());
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("$ProjectInfo");
		modalWindow.setWidth(800);
		modalWindow.setHeight(500);
		modalWindow.setPanel(projectManager);
		
		
		return modalWindow;
	}
	
	AppInformation appInformation;
		public AppInformation getAppInformation() {
			return appInformation;
		}
		public void setAppInformation(AppInformation appInformation) {
			this.appInformation = appInformation;
		}
	
	public void appInfoLoad() throws Exception {
		appInformation = new AppInformation(Integer.parseInt(session.getLastSelectedItem()));
		appInformation.load();
	}
	
	@ServiceMethod
	public MainPanel goSns() throws Exception {
		if(session != null){
			session.setLastPerspecteType("allICanSee");
			session.setUx("sns");
		}
		
		return new MainPanel(new Main(session));
	}
}
