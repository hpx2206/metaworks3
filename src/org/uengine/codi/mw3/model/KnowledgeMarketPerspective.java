package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.KnowledgeMarketPanel;
import org.uengine.codi.mw3.knowledge.KnowledgeMarketTitle;
import org.uengine.codi.mw3.knowledge.RegionTitle;


public class KnowledgeMarketPerspective extends Perspective{
	public KnowledgeMarketPerspective() throws Exception {
		setLabel("Knowledge");
	}
	
	@Override
	protected void loadChildren() throws Exception {
		knowledgeMarketMap = new KnowledgeMarketPanel();
		knowledgeMarketMap.session = session;
		knowledgeMarketMap.load();
	}

	KnowledgeMarketPanel knowledgeMarketMap;
		public KnowledgeMarketPanel getKnowledgeMarketMap() {
			return knowledgeMarketMap;
		}
	
		public void setKnowledgeMarketMap(KnowledgeMarketPanel knowledgeMarketMap) {
			this.knowledgeMarketMap = knowledgeMarketMap;
		}
		
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="$addKnowledgeMarket")
	public ModalWindow addTopic() throws Exception{
		KnowledgeMarketTitle knowledgeMarketTitle = new KnowledgeMarketTitle();
		knowledgeMarketTitle.setMetaworksContext(new MetaworksContext());
		knowledgeMarketTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		knowledgeMarketTitle.session = session;
		return new ModalWindow(knowledgeMarketTitle , 500, 200,  "$addKnowledgeMarket");
	}
			
}
