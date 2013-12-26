package org.uengine.codi.mw3.model;

public class OrderInformationPanel {
	
	WorldMap worldMap;
		public WorldMap getWorldMap() {
			return worldMap;
		}
		public void setWorldMap(WorldMap worldMap) {
			this.worldMap = worldMap;
		}

	public void load() throws Exception {
		if( worldMap == null ) {
			worldMap = new WorldMap();
		}
		worldMap.loadMapInfo();
		
	}
	
}
