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
		favoritePanel = new FavoritePanel();
	}
	@Override
	protected void loadChildren() throws Exception {
		favoritePanel.session = session;
		favoritePanel.load();
	}
	
	@Override
	protected void unloadChildren() throws Exception{
		setFavoritePanel(null);
	}
}
