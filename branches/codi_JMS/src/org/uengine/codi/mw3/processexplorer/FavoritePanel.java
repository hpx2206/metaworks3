package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;

public class FavoritePanel {

	@AutowiredFromClient
	transient public Session session;
	
	IFavoriteFile favoriteFile;
		public IFavoriteFile getFavoriteFile() {
			return favoriteFile;
		}
	
		public void setFavoriteFile(IFavoriteFile favoriteFile) {
			this.favoriteFile = favoriteFile;
		}
	
	public void load() throws Exception {
//		IFavoriteFile favoriteFile = FavoriteFile.loadList(session);
//		setFavoriteFile(favoriteFile);
//		
		
		FavoriteFile fFile = new FavoriteFile();
		fFile.session = session;
		
		favoriteFile = fFile.loadList();
		setFavoriteFile(favoriteFile);
		
		
	}
	
	
	
}
