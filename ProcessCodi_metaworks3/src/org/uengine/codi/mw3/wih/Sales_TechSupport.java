package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class Sales_TechSupport  implements ITool , Serializable {
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
	
	String techSupport_contents;
	
	String special_contents;
	
	@Face(displayName="기술지원 내역")
	public String getTechSupport_contents() {
		return techSupport_contents;
	}
	public void setTechSupport_contents(String techSupport_contents) {
		this.techSupport_contents = techSupport_contents;
	}
	
	@Face(displayName="기타 특이사항")
	public String getSpecial_contents() {
		return special_contents;
	}
	public void setSpecial_contents(String special_contents) {
		this.special_contents = special_contents;
	}
	
	
}
