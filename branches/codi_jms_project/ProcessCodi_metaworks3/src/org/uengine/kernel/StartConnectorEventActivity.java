package org.uengine.kernel;

public class StartConnectorEventActivity extends EventActivity{

	String previousProcessDefinitionAlias;
		public String getPreviousProcessDefinitionAlias() {
			return previousProcessDefinitionAlias;
		}
		public void setPreviousProcessDefinitionAlias(
				String previousProcessDefinitionAlias) {
			this.previousProcessDefinitionAlias = previousProcessDefinitionAlias;
		}
}
