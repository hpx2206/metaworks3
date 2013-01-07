package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class Perspective {
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

	protected Object[] loadInstanceListPanel(String perspectiveType,
			String selectedItem) throws Exception {
		return loadInstanceListPanel(session, perspectiveType, selectedItem);
	}
	
	public static Object[] loadInstanceListPanel(Session session, String perspectiveType,
			String selectedItem) throws Exception {
		return loadInstanceListPanel(session, perspectiveType, selectedItem, null);
	}
	public static Object[] loadInstanceListPanel(Session session, String perspectiveType,
			String selectedItem, String title) throws Exception {

		if (session != null) {
			if(!perspectiveType.equals(session.getLastPerspecteType())) {
				System.out.println("clear text in the search box because perspectiveType is not equal");
				session.setSearchKeyword("");

			} else if(selectedItem != null && !selectedItem.equals(session.getLastSelectedItem())){
				System.out.println("clear text in the search box because selectItem is not equal");
				session.setSearchKeyword("");
			}
			else {
				System.out.println("remain text in the search box");
			}
		}

		InstanceList instList = new InstanceList();
		instList.init();
		savePerspectiveToSession(session, perspectiveType, selectedItem);
		instList.load(session);
		
		InstanceListPanel instListPanel = new InstanceListPanel(session);
		instListPanel.setInstanceList(instList);
		
		// set search Keyword to searchBox
		instListPanel.getSearchBox().setKeyword(session.getSearchKeyword());
		if( title == null && perspectiveType != null && perspectiveType.equals("topic")){
			title = session.getWindowTitle();
		}else if( title == null ){
			title = "$perspective." + perspectiveType;
		}
		if( "topic".equals(perspectiveType)){
			instListPanel.topicFollowersLoad();
		}
		instListPanel.setTitle(title);
		session.setWindowTitle(title);
		return new Object[] {session, instListPanel};
	}

	private static void savePerspectiveToSession(Session session,
			String perspectiveType, String selectedItem) {
		session.setLastPerspecteType(perspectiveType);
		session.setLastSelectedItem(selectedItem);
		//session.setSearchKeywordBox(null);
	}

	@AutowiredFromClient
	public Session session;

}
