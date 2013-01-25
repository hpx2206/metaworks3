package org.uengine.codi.mw3.webProcessDesigner;

public class DefineTab {
	
	public DefineTab() throws Exception{
		rolePanel = new RolePanel();
		prcsValiablePanel = new PrcsValiablePanel();
	}
	
	RolePanel rolePanel	;
		public RolePanel getRolePanel() {
			return rolePanel;
		}
		public void setRolePanel(RolePanel rolePanel) {
			this.rolePanel = rolePanel;
		}
		
	PrcsValiablePanel prcsValiablePanel;
		public PrcsValiablePanel getPrcsValiablePanel() {
			return prcsValiablePanel;
		}
		public void setPrcsValiablePanel(PrcsValiablePanel prcsValiablePanel) {
			this.prcsValiablePanel = prcsValiablePanel;
		}
}
