package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class RegionPanel {

	IRegionNode regionNode;

	public IRegionNode getRegionNode() {
		return regionNode;
	}

	public void setRegionNode(IRegionNode regionNode) {
		this.regionNode = regionNode;
	}

	boolean isSelectedMore;

	public boolean isSelectedMore() {
		return isSelectedMore;
	}

	public void setSelectedMore(boolean isSelectedMore) {
		this.isSelectedMore = isSelectedMore;
	}

	public void load() throws Exception {
		IRegionNode regionNodeList = RegionNode.load(session);
		setRegionNode(regionNodeList);
	}

	@AutowiredFromClient
	transient public Session session;

	@ServiceMethod
	public void moreView() throws Exception {
		IRegionNode regionNodeList = RegionNode.moreView(session);
		setRegionNode(regionNodeList);
		setSelectedMore(true);
	}
}
