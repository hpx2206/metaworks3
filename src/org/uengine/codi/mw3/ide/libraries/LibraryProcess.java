package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

public class LibraryProcess {

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
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	public void load() {
		this.setDefId("defId-01");
		this.setAlias("D:/codi/codebase/codi/root/GG.process");
		this.setName("임시 프로세스");
	}
	
	@ServiceMethod(callByContent = true)
	public Object showProperties() {
		ProcessViewPanel processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(this.getDefId());
		processViewPanel.setAlias(this.getAlias());
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		return processViewPanel;
	}
}
