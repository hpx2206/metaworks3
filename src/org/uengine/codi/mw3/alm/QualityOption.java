package org.uengine.codi.mw3.alm;

public class QualityOption {
	
	public QualityOption(){
		setPmdRuleOption(new PMDRuleOption());
	}
	
	PMDRuleOption pmdRuleOption;

		public PMDRuleOption getPmdRuleOption() {
			return pmdRuleOption;
		}
	
		public void setPmdRuleOption(PMDRuleOption pmdRuleOption) {
			this.pmdRuleOption = pmdRuleOption;
		}
}
