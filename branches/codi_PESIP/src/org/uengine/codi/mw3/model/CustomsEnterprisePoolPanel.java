package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.layout.Layout;


@Face(ejsPath="genericfaces/Tab.ejs")
public class CustomsEnterprisePoolPanel {
	public CustomsEnterprisePoolPanel() throws Exception{
		internalExportCompanyPanel = new InternalExportCompanyPanel();
		internalExportCompanyPanel.load();
		externalExportCompanyPanel = new ExternalExportCompanyPanel();
		externalExportCompanyPanel.load();
		exportAgentPanel = new ExportAgentPanel();
		exportAgentPanel.load();
	}
	
	InternalExportCompanyPanel internalExportCompanyPanel;
		@Face(displayName="수출기업(국내)")
		public InternalExportCompanyPanel getInternalExportCompanyPanel() {
			return internalExportCompanyPanel;
		}
	
		public void setInternalExportCompanyPanel(
				InternalExportCompanyPanel internalExportCompanyPanel) {
			this.internalExportCompanyPanel = internalExportCompanyPanel;
		}

	ExternalExportCompanyPanel externalExportCompanyPanel;
		@Face(displayName="수출기업(해외)")
		public ExternalExportCompanyPanel getExternalExportCompanyPanel() {
			return externalExportCompanyPanel;
		}
	
		public void setExternalExportCompanyPanel(
				ExternalExportCompanyPanel externalExportCompanyPanel) {
			this.externalExportCompanyPanel = externalExportCompanyPanel;
		}

	ExportAgentPanel exportAgentPanel;
		@Face(displayName="수출에이전트(개인)")
		public ExportAgentPanel getExportAgentPanel() {
			return exportAgentPanel;
		}
	
		public void setExportAgentPanel(ExportAgentPanel exportAgentPanel) {
			this.exportAgentPanel = exportAgentPanel;
		}
	

	
}
