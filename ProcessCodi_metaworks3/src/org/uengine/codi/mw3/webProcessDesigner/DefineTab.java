package org.uengine.codi.mw3.webProcessDesigner;

public class DefineTab {
	
	public DefineTab() throws Exception{
		rolePanel = new RolePanel();
		prcsValiablePanel = new PrcsVariablePanel();
	}
	
	RolePanel rolePanel	;
		public RolePanel getRolePanel() {
			return rolePanel;
		}
		public void setRolePanel(RolePanel rolePanel) {
			this.rolePanel = rolePanel;
		}
		
	PrcsVariablePanel prcsValiablePanel;
		public PrcsVariablePanel getPrcsValiablePanel() {
			return prcsValiablePanel;
		}
		public void setPrcsValiablePanel(PrcsVariablePanel prcsValiablePanel) {
			this.prcsValiablePanel = prcsValiablePanel;
		}
}
