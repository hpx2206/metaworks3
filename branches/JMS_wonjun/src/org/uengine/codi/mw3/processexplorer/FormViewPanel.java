package org.uengine.codi.mw3.processexplorer;

import org.uengine.util.UEngineUtil;

public class FormViewPanel {
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	
	public void load() {
		// TODO Auto-generated method stub
		if(UEngineUtil.isNotEmpty(alias)  && UEngineUtil.isNotEmpty(defId)){
			
			
		}
	}

}
