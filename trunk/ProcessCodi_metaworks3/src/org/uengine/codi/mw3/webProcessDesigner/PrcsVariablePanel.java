package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class PrcsVariablePanel {

	public PrcsVariablePanel() throws Exception{
		prcsValiables = new ArrayList<PrcsVariable>();
		
		newPrcsValiable = new PrcsVariable();
		newPrcsValiable.load();
		newPrcsValiable.setMetaworksContext(new MetaworksContext());
		newPrcsValiable.getMetaworksContext().setWhen("edit");
	}
	
	public ArrayList<PrcsVariable> prcsValiables;
		public ArrayList<PrcsVariable> getPrcsValiables() {
			return prcsValiables;
		}
		public void setPrcsValiables(ArrayList<PrcsVariable> prcsValiables) {
			this.prcsValiables = prcsValiables;
		}
		
	PrcsVariable newPrcsValiable;
		public PrcsVariable getNewPrcsValiable() {
			return newPrcsValiable;
		}
		public void setNewPrcsValiable(PrcsVariable newPrcsValiable) {
			this.newPrcsValiable = newPrcsValiable;
		}
}
