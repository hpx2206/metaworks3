package org.uengine.codi.mw3.admin;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ILogin;

public class CrowdSourcerWindow implements ContextAware {

	public CrowdSourcerWindow(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
		
	public CrowdSourcerWindow(ILogin loginUser, String defId){
		this();
		
		setLoginUser(loginUser);
		setDefId(defId);
	}
	
	ILogin loginUser;
		public ILogin getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(ILogin loginUser) {
			this.loginUser = loginUser;
		}
	
	String defId;	 
		public String getDefId() {
			return defId;
		}	
		public void setDefId(String defId) {
			this.defId = defId;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
	
	String message;
		@Range(size=80)
		public String getMessage() {
			return message;			
		}
		public void setMessage(String message) {
			this.message = message;
		}
	
	@ServiceMethod(callByContent=true)
	public void send(){
	}
}

