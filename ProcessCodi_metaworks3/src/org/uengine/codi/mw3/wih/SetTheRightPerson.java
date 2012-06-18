package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.User;
@Face(displayName="$SRP_TITLE")
public class SetTheRightPerson implements ContextAware, ITool , Serializable{

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
        
    boolean continueYn;
    @Face(displayName="$SRP_APPROVAL")
	public boolean isContinueYn() {
		return continueYn;
	}
	public void setContinueYn(boolean continueYn) {
		this.continueYn = continueYn;
	}
	
	String continueYnVar;
	@Hidden
	public String getContinueYnVar() {
		return continueYnVar;
	}
	public void setContinueYnVar(String continueYnVar) {
		this.continueYnVar = continueYnVar;
	}
	
	IUser rightPerson;
	@Face(displayName="$SRP_CONTRACT")
	 public IUser getRightPerson() {
		 return rightPerson;
	 }

	 public void setRightPerson(IUser rightPerson) {
		 this.rightPerson = rightPerson;
	 }
	 
	 String selRightPerson;
	 @Hidden 
	public String getSelRightPerson() {
		return selRightPerson;
	}
	public void setSelRightPerson(String selRightPerson) {
		this.selRightPerson = selRightPerson;
	}
	@Override
	public void onLoad() {
        this.metaworksContext = new MetaworksContext();
        metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		// TODO Auto-generated method stub
        /*
		IEmployee emp2 = new Employee();
    	setEmpCode(emp2);
		getEmpCode().getMetaworksContext().setWhere("pickerCaller");
        */
        
        setRightPerson(new User());
        
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		setContinueYnVar(continueYn == true ? "Y" : "N");
		setSelRightPerson(rightPerson.getUserId());
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
	
}