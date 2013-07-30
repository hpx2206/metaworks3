package org.uengine.codi.mw3.webProcessDesigner.jms;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class HistoryItem implements ContextAware{
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String defName;
		public String getDefName() {
			return defName;
		}
		public void setDefName(String defName) {
			this.defName = defName;
		}
		
	public HistoryItem(){
		setMetaworksContext(new MetaworksContext());
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] save(){
		
		return new Object[]{new Remover(new ModalWindow() , true) };
	}
}
