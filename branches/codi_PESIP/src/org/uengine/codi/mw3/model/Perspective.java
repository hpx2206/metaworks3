package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.processexplorer.ProcessExploreWindow;

public class Perspective {
	
	public final static String TYPE_COMMINGTODO = "commingTodo";
	
	String label;
		@Id
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}

	boolean selected;
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
	@ServiceMethod(callByContent = true, payload = { "selected" })
	public Object[] select() throws Exception {
		setSelected(!isSelected()); // toggle
		if (isSelected()) {
			loadChildren();
			
		} else {
			unloadChildren();
		}
		return new Object[] { this };
	}


	protected void loadChildren() throws Exception {
		// TODO Override and load children when perspective selected
	}

	protected void unloadChildren() throws Exception {
		// TODO Override and unload children when perspective deselected
	}

	public Object[] loadInstanceListPanel(String perspectiveType,
			String selectedItem) throws Exception {
		return loadInstanceListPanel(session, perspectiveType, selectedItem);
	}
	
	public static Object[] loadInstanceListPanel(Session session, String perspectiveType,
			String selectedItem) throws Exception {
		return loadInstanceListPanel(session, perspectiveType, selectedItem, null);
	}
	
	public static Object[] loadDocumentListPanel(Session session, String perspectiveType,
			String selectedItem, String title) throws Exception{
		
		InstanceList instList = new InstanceList(session);
		instList.setFolderId(selectedItem);
		instList.setMetaworksContext(new MetaworksContext());
		savePerspectiveToSession(session, perspectiveType, selectedItem);
		if(perspectiveType.equals("document")){
			instList.getMetaworksContext().setHow("document");
			instList.loadDocument();
			
			InstanceListPanel instListPanel = new InstanceListPanel(session);
			instListPanel.setInstanceList(instList);
			instListPanel.session = session;
			instListPanel.documentFollowerLoad();
			
			 
			instListPanel.getSearchBox().setKeyword(session.getSearchKeyword());
			if( title == null && perspectiveType != null && perspectiveType.equals("document")){
				title = session.getWindowTitle();
			}else if( title == null ){
				title = "$perspective." + perspectiveType;
			}
			instListPanel.setTitle(title);
			session.setWindowTitle(title);
			
			SearchBox searchBox = new SearchBox();
			searchBox.setKeyword(session.getSearchKeyword());
			searchBox.setKeyUpSearch(true);
			searchBox.setKeyEntetSearch(true);
				
			final Object[] returnObject;
			
			returnObject = new Object[]{new Refresh(searchBox)};
			
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getEmployee().getEmpCode()), returnObject);
			
			
			return new Object[]{session, instListPanel};
		}else if(perspectiveType.equals("explorer")){
			
			
			
			instList.getMetaworksContext().setHow("explorer");
			instList.loadDocument();
			
			InstanceListPanel instListPanel = new InstanceListPanel(session);
			instListPanel.setInstanceList(instList);
			instListPanel.session = session;
			instListPanel.documentFollowerLoad();
			
			 
			instListPanel.getSearchBox().setKeyword(session.getSearchKeyword());
			if( title == null && perspectiveType != null && perspectiveType.equals("explorer")){
				title = session.getWindowTitle();
			}else if( title == null ){
				title = "$perspective." + perspectiveType;
			}
			instListPanel.setTitle(title);
			session.setWindowTitle(title);
			
			SearchBox searchBox = new SearchBox();
			searchBox.setKeyword(session.getSearchKeyword());
			searchBox.setKeyUpSearch(true);
			searchBox.setKeyEntetSearch(true);
				
			final Object[] returnObject;
			
			returnObject = new Object[]{new Refresh(searchBox)};
			
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getEmployee().getEmpCode()), returnObject);
			
			ProcessExploreWindow processExploreWindow = new ProcessExploreWindow();
			processExploreWindow.setPanel(instListPanel);
			return new Object[]{session, processExploreWindow};
			
//			return new Object[]{session, instListPanel};
			
			
			
			
		}
		return null;
	}
	
	
	
	public static Object[] loadInstanceListPanel(Session session, String perspectiveType,
			String selectedItem, String title) throws Exception {
		
		try {
			if(perspectiveType.equals(session.getLastPerspecteType()) != true) {
				System.out.println("clear text in the search box because perspectiveType is not equal");
				session.setSearchKeyword(null);
	
			} else if(selectedItem.equals(session.getLastSelectedItem()) != true){
				System.out.println("clear text in the search box because selectItem is not equal");
				session.setSearchKeyword(null);
			}
			else {
				System.out.println("remain text in the search box");
			}
		} catch (Exception e) {
			System.out.println("Do nothing");
		}
		
		if(perspectiveType.equals("inbox")){
			/* �닿� �좎씪 移댁슫���ㅼ떆 怨꾩궛 */
			TodoBadge todoBadge = new TodoBadge();
			todoBadge.session = session;
			todoBadge.refresh();

			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()),
					new Object[]{new Refresh(todoBadge, true)});			
		}
			
		
		savePerspectiveToSession(session, perspectiveType, selectedItem);
		
		
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		InstanceList instList = new InstanceList(session);
		instList.session = session;
		instList.setMetaworksContext(new MetaworksContext());
		instList.getMetaworksContext().setHow(perspectiveType);
		if(selectedItem == null){
			instList.getMetaworksContext().setWhere(session.getLastPerspecteType());
		}else {
			instList.getMetaworksContext().setWhere(selectedItem);
		}
		instList.load();
		
		InstanceListPanel instListPanel = new InstanceListPanel(session);
		instListPanel.session = session;
		instListPanel.setNewInstancePanel(newInstancePanel);
		instListPanel.setInstanceList(instList);

		// set search Keyword to searchBox
		instListPanel.getSearchBox().setKeyword(session.getSearchKeyword());
		if( title == null && perspectiveType != null && perspectiveType.equals("topic")){
			title = session.getWindowTitle();
		}else if( title == null ){
			title = "$perspective." + perspectiveType;
		}
		if("topic".equals(perspectiveType) ||"region".equals(perspectiveType) || "market".equals(perspectiveType) ||
				"project".equals(perspectiveType) || "app".equals(perspectiveType)){
	
			instListPanel.topicFollowersLoad();
		}
//		if("project".equals(perspectiveType)){
//			instListPanel.projectInfoLoad();
//		}
//		if("app".equals(perspectiveType)){
//			instListPanel.appInfoLoad();
//		}
		instListPanel.setTitle(title);
		session.setWindowTitle(title);

		
		InstanceSearchBox searchBox = new InstanceSearchBox();
		searchBox.setKeyword(session.getSearchKeyword());
		searchBox.setKeyUpSearch(true);
		searchBox.setKeyEntetSearch(true);
			
		//final Object[] returnObject;
		
//		if("sns".equals(session.getEmployee().getPreferUX())){
//			WfPanel wfPanel = new WfPanel();
//			
//			if("topic".equals(perspectiveType)){
//				wfPanel.session = session;
//				wfPanel.load(selectedItem);
//			}else{			
//				wfPanel.session = session;
//				wfPanel.load(session.getCompany().getComCode());
//			}
//			
//			returnObject = new Object[]{new Refresh(searchBox), new Refresh(wfPanel), new Refresh(new FollowerPanel("instance"))};
//		}else
//			returnObject = new Object[]{new Refresh(searchBox)};
//		
//		MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getEmployee().getEmpCode()), returnObject);
		
		if("oce".equals(selectedItem)){
			return new Object[] {instListPanel};
		}
		return new Object[] {session, instListPanel};
	}

	private static void savePerspectiveToSession(Session session,
			String perspectiveType, String selectedItem) {
		session.setLastPerspecteType(perspectiveType);
		session.setLastSelectedItem(selectedItem);
		//session.setSearchKeywordBox(null);
	}

	@AutowiredFromClient
	public static Session session;
}
