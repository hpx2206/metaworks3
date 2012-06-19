package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class Sales_Contribution implements ITool , Serializable {
	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
    MetaworksContext metaworksContext;
        public MetaworksContext getMetaworksContext(){
            return this.metaworksContext;
        }
        public void setMetaworksContext(MetaworksContext metaworksContext){
            this.metaworksContext = metaworksContext;
        }
     
        
        
    @Override
	public void onLoad() {
        this.metaworksContext = new MetaworksContext();
        metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		// TODO Auto-generated method stub
        
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		//setCompYnVar(compYn == true ? "Y" : "N");
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
	
	String contribution_evaluation;
	
	String special_contents;
	
	@Face(displayName="$SALESCONTRIBUTION_CONTRIBUTION_EVALUATION")
	public String getContribution_evaluation() {
		return contribution_evaluation;
	}
	public void setContribution_evaluation(String contribution_evaluation) {
		this.contribution_evaluation = contribution_evaluation;
	}
	
	@Face(displayName="$SALESCONTRIBUTION_SPECIAL_CONTENTS")
	public String getSpecial_contents() {
		return special_contents;
	}
	public void setSpecial_contents(String special_contents) {
		this.special_contents = special_contents;
	}
	
	
	
	
}
