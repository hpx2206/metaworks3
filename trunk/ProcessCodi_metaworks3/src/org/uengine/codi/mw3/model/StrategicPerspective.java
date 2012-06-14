package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.knowledge.WfPanel;

public class StrategicPerspective extends Perspective {

	public StrategicPerspective() throws Exception {
		setLabel("Strategic");
	}
	
	
	



	@Override
	protected void loadChildren() throws Exception {
		strategyMap = new WfPanel();
		strategyMap.load("-1");
	}






	WfPanel strategyMap;

		public WfPanel getStrategyMap() {
			return strategyMap;
		}
	
		public void setStrategyMap(WfPanel strategyMap) {
			this.strategyMap = strategyMap;
		}

}
