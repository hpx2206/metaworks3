package org.metaworks.website;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.MetaworksObject;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Contents extends MetaworksObject<IContents> implements IContents{
	int contentId;
		public int getContentId() {
			return contentId;
		}
		public void setContentId(int contentId) {
			this.contentId = contentId;
		}
		
	int menuId;
		public int getMenuId() {
			return menuId;
		}
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	String paragraph;
		public String getParagraph() {
			return paragraph;
		}
		public void setParagraph(String paragraph) {
			this.paragraph = paragraph;
		}
		
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	int width;
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		
	int height;
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
			
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}


	public static IContents loadHomeContents() throws Exception{
		Menu homeMenu = new Menu();
		homeMenu.setMenuId(-1);
		
		return loadContents(homeMenu);
	}
	
	public static IContents loadContents(IMenu menu) throws Exception{
		IContents contents = (IContents) Database.sql(IContents.class, "select * from contents where menuId = ?menuId");
		contents.setMenuId(menu.getMenuId());
		
		contents.select();
		
		return contents;
		
	}
	
	public ContentPanel save() throws Exception {
		
		//storing file
		
		
		try{
			IDAO contentId = Database.sql(IDAO.class, "select max(contentId) 'contentId' from contents");
			contentId.select();
			contentId.next();

			setContentId(contentId.getInt("contentId") + 1);
		}catch(Exception e){
			
		}
		
		createDatabaseMe();
		syncToDatabaseMe();
		flushDatabaseMe();
		
		ContentPanel cp = new ContentPanel();
		cp.setMenu(contentPanel.getMenu());
		
		cp.load();
		
		return cp;
	}
	
	public ContentPanel delete() throws Exception{
		deleteDatabaseMe();
		//flushDatabaseMe();
		
		ContentPanel newContentPanel = new ContentPanel();
		newContentPanel.setMenu(contentPanel.getMenu());
		
		newContentPanel.load();
		
		return newContentPanel;		
	}

	
	@AutowiredFromClient
	public ContentPanel contentPanel;
	

	private IContents newContents(IContents c) throws Exception{
		c.getMetaworksContext().setWhen(WHEN_NEW);
		c.setMenuId(contentPanel.getMenu().getMenuId());
		
		return c;
		
	}
	
	@Override
	public IContents newParagraph() throws Exception {
		return newContents(new ParagraphContents());
	}

	
	@Override
	public IContents newImage() throws Exception {
		return newContents(new ImageContents());
	}
	
	@Override
	public IContents newMovie() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IContents newFile() throws Exception {
		return newContents(new FileContents());
	}
	
}
