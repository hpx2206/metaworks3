package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class PrcsValiablePanel {

	public PrcsValiablePanel() throws Exception{
		prcsValiables = new ArrayList<PrcsValiable>();
		
		newPrcsValiable = new PrcsValiable();
		newPrcsValiable.setMetaworksContext(new MetaworksContext());
		newPrcsValiable.getMetaworksContext().setWhen("edit");
	}
	
	public ArrayList<PrcsValiable> prcsValiables;
		public ArrayList<PrcsValiable> getPrcsValiables() {
			return prcsValiables;
		}
		public void setPrcsValiables(ArrayList<PrcsValiable> prcsValiables) {
			this.prcsValiables = prcsValiables;
		}
		
	PrcsValiable newPrcsValiable;
		public PrcsValiable getNewPrcsValiable() {
			return newPrcsValiable;
		}
		public void setNewPrcsValiable(PrcsValiable newPrcsValiable) {
			this.newPrcsValiable = newPrcsValiable;
		}
}
