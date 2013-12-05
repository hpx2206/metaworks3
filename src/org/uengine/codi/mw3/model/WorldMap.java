package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.knowledge.IRegionNode;
import org.uengine.codi.mw3.knowledge.RegionNode;

public class WorldMap {
	
	IRegionNode regionNode;
		public IRegionNode getRegionNode() {
			return regionNode;
		}
		public void setRegionNode(IRegionNode regionNode) {
			this.regionNode = regionNode;
		}
		
	public void loadMapInfo() throws Exception {
		RegionNode findNode = new RegionNode();
		findNode.setType("region");
		this.setRegionNode(findNode.findRegion());
	}
	
}
