package org.uengine.codi.mw3.ide.editor.role;

import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.model.RuleDesignerContentPanel;

public class RoleEditor extends Editor {

	RuleDesignerContentPanel ruleDesigner;
		public RuleDesignerContentPanel getRuleDesigner() {
			return ruleDesigner;
		}
	
		public void setRuleDesigner(RuleDesignerContentPanel ruleDesigner) {
			this.ruleDesigner = ruleDesigner;
		}

	public RoleEditor(){
		super();
	}
		
	public RoleEditor(String filename){
		super(filename);
		
		this.setType("process");
		
		try {
			RuleDesignerContentPanel ruleDesigner = new RuleDesignerContentPanel();			
			ruleDesigner.setAlias(filename);
			
			this.setRuleDesigner(ruleDesigner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object save(){
		try {
			//this.getProcessDesigner().saveMe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
