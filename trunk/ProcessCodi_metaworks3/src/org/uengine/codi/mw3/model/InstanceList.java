package org.uengine.codi.mw3.model;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
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

	@AutowiredFromClient
	public Session session;
	

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
		
		IInstance instanceContents = Instance.load(navigation,	getPage()-1, PAGE_CNT);
		if(getMetaworksContext()==null){
			setMetaworksContext(new MetaworksContext());
		}
		String preferUX = session.getEmployee().getPreferUX();
		
		if("sns".equals(preferUX)){
			instanceContents.setMetaworksContext(new MetaworksContext());
			instanceContents.getMetaworksContext().setHow("sns");			
			instanceContents.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		}
		setInstances(instanceContents);

		// setting moreInstanceList
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
}
