package org.uengine.codi.mw3.webProcessDesigner.jms;

import java.util.ArrayList;

public class ProcessAttributePanel {
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	ExtendAttribute extendAttribute;
		public ExtendAttribute getExtensionAttribute() {
			return extendAttribute;
		}
		public void setExtensionAttribute(ExtendAttribute extendAttribute) {
			this.extendAttribute = extendAttribute;
		}
		
	public void load() {
		// TODO 하위 document를 읽는다.
		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		extendAttribute = new ExtendAttribute();
	}
	

}
