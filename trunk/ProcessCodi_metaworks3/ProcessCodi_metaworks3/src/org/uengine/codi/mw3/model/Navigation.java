package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

public class Navigation {
	
	public Navigation(){
		personalPerspective = (new PersonalPerspective());
		personalPerspective.setSelected(true);

		strategyPerspective = (new StrategyPerspective());
		processPerspective = (new ProcessPerspective());

	}
	
	PersonalPerspective personalPerspective;
	
		public PersonalPerspective getPersonalPerspective() {
			return personalPerspective;
		}
	
		public void setPersonalPerspective(
				PersonalPerspective personalPerspectiveSelection) {
			this.personalPerspective = personalPerspectiveSelection;
		}
	
	StrategyPerspective strategyPerspective;

		public StrategyPerspective getStrategyPerspective() {
			return strategyPerspective;
		}
	
		public void setStrategyPerspective(StrategyPerspective strategyPerspective) {
			this.strategyPerspective = strategyPerspective;
		}
			
	ProcessPerspective processPerspective;

		public ProcessPerspective getProcessPerspective() {
			return processPerspective;
		}
	
		public void setProcessPerspective(ProcessPerspective processPerspective) {
			this.processPerspective = processPerspective;
		}	
}
