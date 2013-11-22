package org.uengine.codi.mw3.model;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.Login;


@Face(
		ejsPathMappingByContext=
	{
		"{where: 'pinterest', face: 'dwr/metaworks/org/uengine/codi/mw3/model/InstanceList_pinterest.ejs'}",
	}		

)
public class InstanceList implements ContextAware{

	final static int PAGE_CNT = 15;
	final static int PAGE_CNT_MOBILE = 5;
	final static int PAGE_CNT_DASHBOARD = 3;
	
	public InstanceList(){
		this(null);
	}
	
	public InstanceList(Session session){
		this.session = session;
		
		this.setNavigation(new Navigation(session));
		this.setPage(1);
	}

	int page;
		@Id
		@Hidden		
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	IInstance instances;		
		public IInstance getInstances() {
			return instances;
		}
	
		public void setInstances(IInstance instances) {
			this.instances = instances;
		}

	InstanceList moreInstanceList;
		public InstanceList getMoreInstanceList() {
			return moreInstanceList;
		}
	
		public void setMoreInstanceList(InstanceList moreInstanceList) {
			this.moreInstanceList = moreInstanceList;
		}		

	Navigation navigation;
		public Navigation getNavigation() {
			return navigation;
		}
		public void setNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

	@AutowiredToClient
	public Session session;
	

	String folderId;
		public String getFolderId() {
			return folderId;
		}
		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}
	String folderName;
		public String getFolderName() {
			return folderName;
		}
	
		public void setFolderName(String folderName) {
			this.folderName = folderName;
		}

	IWorkItem workItem;
		public IWorkItem getWorkItem() {
			return workItem;
		}
		public void setWorkItem(IWorkItem workItem) {
			this.workItem = workItem;
		}
//	@ServiceMethod(callByContent = true, except = { "instances",
//			"moreInstanceList" })
//	public void search() throws Exception {
//		setPage(0);
//		load(session);
//	}

	@ServiceMethod(callByContent = true, except = { "instances" })
	public void more() throws Exception {
		load(this.getNavigation());
	}
	
	@ServiceMethod(callByContent = true, except = { "workitem" })
	public void moreDocument() throws Exception{
		loadDocument(this.getNavigation());
	}
	
	public InstanceList load() throws Exception {
		return load(this.getNavigation());
	}
	
	public InstanceList load(Navigation navigation) throws Exception {
		if(navigation.getPerspectiveType() != null && navigation.getPerspectiveType().equals("inbox")){			
			//NEW WAY IS GOOD
			Browser.withSession(Login.getSessionIdWithUserId(navigation.getEmployee().getEmpCode()), new Runnable(){
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("mw3.getAutowiredObject('" + TodoBadge.class.getName() + "').refresh", new Object[]{});					
				}
				
			});
		}
		int count;
		Instance tempInstanceContent = new Instance();
		tempInstanceContent.setMetaworksContext(new MetaworksContext());
		if(getMetaworksContext()!=null && getMetaworksContext().getHow()!=null && "dashboard".equals(getMetaworksContext().getHow())){
			count = ("phone".equals(navigation.getMedia())?InstanceList.PAGE_CNT_MOBILE:InstanceList.PAGE_CNT_DASHBOARD);
			tempInstanceContent.getMetaworksContext().setHow("dashboard");
			
		}else{
			count = ("phone".equals(navigation.getMedia())?InstanceList.PAGE_CNT_MOBILE:InstanceList.PAGE_CNT);
		}
		IInstance instanceContents = tempInstanceContent.loadOnDashboard(navigation,	getPage()-1, count);
		instanceContents.setMetaworksContext(new MetaworksContext());
		
		if(this.getMetaworksContext() == null) {
			this.setMetaworksContext(new MetaworksContext());
		}
		if(!("TodoBadge".equals(this.getMetaworksContext().getWhen()))){
			instanceContents.getMetaworksContext().setHow("instance");
		}
		if(getMetaworksContext()==null){
			setMetaworksContext(new MetaworksContext());
			
		}
		if( session != null ){
			String preferUX = session.getEmployee().getPreferUX();
			
			if("oce".equals(session.getUx()) ){
				if("dashboard".equals(session.getLastPerspecteType())){
					instanceContents.getMetaworksContext().setWhere("dashboard");
				}
			}
		}
		
//		if("sns".equals(preferUX)){
//			if("oce".equals(session.getUx())){
//				instanceContents.setMetaworksContext(new MetaworksContext());
//				if("dashboard".equals(this.getMetaworksContext().getHow()))
//					instanceContents.getMetaworksContext().setWhere("dashboard");
//				instanceContents.getMetaworksContext().setHow("sns");			
//				instanceContents.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
//			}
//		}
		setInstances(instanceContents);
			// setting moreInstanceList
		if( instanceContents.size() >= PAGE_CNT){
			setMoreInstanceList(new InstanceList());
			getMoreInstanceList().setNavigation(navigation);
			getMoreInstanceList().setPage(getPage()+1);
		}
		return this;
	}
	
	public InstanceList loadDocument() throws Exception{
		return loadDocument(this.getNavigation());
	}
	public InstanceList loadDocument(Navigation navigation) throws Exception{
		IInstance instance = null;
		
		if("UnlabeledDocument".equals(this.getMetaworksContext().getHow())){
			instance = Instance.loadDocument();
		}else{
			instance = Instance.loadDocument(getFolderId());
		}
		
		instance.getMetaworksContext().setHow("document");
		setInstances(instance);
//		setWorkItem(workitem);
		setMoreInstanceList(new InstanceList());
		getMoreInstanceList().setNavigation(navigation);
		getMoreInstanceList().setPage(getPage()+1);
		return this;
	}

//	private int findPerspectiveTypeCode(String typeString) {
//		if(typeString == null) {
//			return -1;
//		} else if(typeString.equals("inbox")) {
//			return 0;
//		} else if(typeString.equals("all")) {
//			return 1;
//		} else if(typeString.equals("request")) {
//			return 2;
//		} else if(typeString.equals("stopped")) {
//			return 3;
//		} else if(typeString.equals("organization")) {
//			return 4;
//		} else if(typeString.equals("process")) {
//			return 5;
//		} else if(typeString.equals("strategy")) {
//			return 6;
//		} else if(typeString.equals("taskExt1")) {
//			return 7;
//		} else if(typeString.equals("status")) {
//			return 8;
//		} else if("allICanSee".equals(typeString)){
//			return 9;
//		}
//		return -1;
//	}
	
	@ServiceMethod(target="instances")
	public void drillDown() throws Exception{
		
		System.out.println("SDfsdkf sldkfjs ldkfj sdlkfj seldkfj sdlkfj sldkjfsldkfj sdkl");
		
	}
}
