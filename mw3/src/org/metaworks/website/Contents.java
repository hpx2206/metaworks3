package org.metaworks.website;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Contents extends Database<IContents> implements IContents{
	int contentId = -1;
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

	int orderId;
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
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
		IContents contents = (IContents) Database.sql(IContents.class, "select * from contents where menuId = ?menuId order by orderId");
		contents.setMenuId(menu.getMenuId());
		
		contents.select();
		
		return contents;
		
	}
	
	public ContentPanel add() throws Exception{ 
		return save();
	}
	
	public ContentPanel save() throws Exception {
		
		if(file!=null && file.fileTransfer!=null)
			file.upload();
		
		boolean isNew = (getContentId() == -1);
		
		if(isNew){ // -1 means new one. we have set -1 in ContentPanel.load()
			try{
				IDAO contentId = Database.sql(IDAO.class, "select max(contentId) 'contentId' from contents");
				contentId.select();
				contentId.next();
	
				setContentId(contentId.getInt("contentId") + 1);
			}catch(Exception e){
				
			}
			
			int orderId = 0;
			IMenu orderIdDAO = (IMenu) Database.sql(IMenu.class, "select max(orderId) 'orderId' from contents where menuId=?menuId");
			orderIdDAO.setMenuId(getMenuId());
			orderIdDAO.select();
			
			if(orderIdDAO.next())
				orderId = orderIdDAO.getInt("orderId") + 1;
			
			setOrderId(orderId);
	
			createDatabaseMe();
		}
		
		syncToDatabaseMe();
		flushDatabaseMe();
		
		
		return refreshContent();
	}
	
	public ContentPanel delete() throws Exception{
		deleteDatabaseMe();
		//flushDatabaseMe();
		
		return refreshContent();
	}

	@Override
	public ContentPanel moveUp() throws Exception {
		if(databaseMe().getOrderId() == 0)
			throw new Exception("It's top");
		
		IContents prevContents = (IContents) Database.sql(IContents.class, "update contents set orderId=orderId+1 where orderId=?orderId and menuId=?menuId");
		prevContents.setOrderId(databaseMe().getOrderId() - 1);
		prevContents.setMenuId(databaseMe().getMenuId());
		prevContents.update();
		
		databaseMe().setOrderId(databaseMe().getOrderId() - 1);
		flushDatabaseMe();
		
		return refreshContent();
	}
	
	@Override
	public ContentPanel moveDown() throws Exception {

		IContents nextContents = (IContents) Database.sql(IContents.class, "update contents set orderId=orderId-1 where orderId=?orderId and menuId=?menuId");
		nextContents.setOrderId(databaseMe().getOrderId() + 1); //points next one
		nextContents.setMenuId(databaseMe().getMenuId());
		int nextWasExisted = nextContents.update();
		
		if(nextWasExisted == 0)
			throw new Exception("It's bottom");
		
		databaseMe().setOrderId(databaseMe().getOrderId() + 1);
		flushDatabaseMe();
		
		return refreshContent();
	}
	
	
	private ContentPanel refreshContent() throws Exception {
		ContentPanel newContentPanel = new ContentPanel();
		newContentPanel.session = session; //TODO: need to be removed after 'Autowired Factory' of metaworks3 :  e.g. newContentPanel = MetaworksObject.create(ContentPanel.class) will return a auto-wired content for this
		newContentPanel.setMenu(contentPanel.getMenu());		
		newContentPanel.load();
		
		return newContentPanel;
	}
	
	@AutowiredFromClient
	public ContentPanel contentPanel;
	
	@AutowiredFromClient
	public Session session;
	

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
	
	@Override
	public IContents edit() throws Exception {
		type = databaseMe().getType();

		IContents content;
		
		if("p".equals(type)){
			content=new ParagraphContents();
		}else if("img".equals(type)){
			content=new ImageContents();
		}else if("file".equals(type)){
			content=new FileContents();
		}else if("src".equals(type)){
			content=new SourceCodeContents();
		}else 
			content=new Contents();

		content.setParagraph(databaseMe().getParagraph());
		content.setFile(databaseMe().getFile());
		content.setUrl(databaseMe().getUrl());
		content.setWidth(databaseMe().getWidth());
		content.setHeight(databaseMe().getHeight());
		
		content.setType(type);
		content.setOrderId(databaseMe().getOrderId());
		content.setMenuId(databaseMe().getMenuId());
		content.setContentId(databaseMe().getContentId());
		
		content.getMetaworksContext().setWhen(WHEN_EDIT);
		
		return content;
	}

	
}
