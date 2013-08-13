package org.uengine.codi.mw3.processexplorer;

import org.uengine.codi.mw3.model.Perspective;

public class FavoritePerspective  extends Perspective {
	
	FavoritePanel favoritePanel;
		public FavoritePanel getFavoritePanel() {
			return favoritePanel;
		}
		public void setFavoritePanel(FavoritePanel favoritePanel) {
			this.favoritePanel = favoritePanel;
		}
	
	public FavoritePerspective(){
			setLabel("Favorite");
			
		}
	@Override
	protected void loadChildren() throws Exception {
		// TODO Auto-generated method stub
		
		favoritePanel = new FavoritePanel();
		favoritePanel.session = session;
		favoritePanel.load();
	}
	
	@Override
	protected void unloadChildren() throws Exception{
		setFavoritePanel(null);
	}
}
