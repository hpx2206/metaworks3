package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.RegionPanel;
import org.uengine.codi.mw3.knowledge.RegionTitle;

public class RegionPerspective extends Perspective {

	public RegionPerspective() throws Exception {
		setLabel("Region");
	}
	
	@Override
	protected void loadChildren() throws Exception {
		regionMap = new RegionPanel();
		regionMap.session = session;
		regionMap.load();
	}

	RegionPanel regionMap;
		public RegionPanel getRegionMap() {
			return regionMap;
		}
	
		public void setRegionMap(RegionPanel regionMap) {
			this.regionMap = regionMap;
		}

	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="$addRegion")
	public ModalWindow addTopic() throws Exception{
		RegionTitle regionTitle = new RegionTitle();
		regionTitle.setMetaworksContext(new MetaworksContext());
		regionTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		regionTitle.session = session;
		regionTitle.setRegionCode();
		return new ModalWindow(regionTitle , 500, 200,  "$addRegion");
	}
		
}
