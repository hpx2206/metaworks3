package org.uengine.codi.mw3.model;

public class Application {
	public ContentTopPanel loadContentTopPanel(Session session) throws Exception{
		ContentTopPanel contentTopPanel = new ContentTopPanel(ContentTopPanel.HOW_TRAY);
		contentTopPanel.session = session;
		contentTopPanel.load();
		
		return contentTopPanel;
	}
}
