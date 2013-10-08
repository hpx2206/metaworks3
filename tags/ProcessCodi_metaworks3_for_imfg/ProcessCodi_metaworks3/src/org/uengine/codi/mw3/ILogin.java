package org.uengine.codi.mw3;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.common.MainPanel;

public interface ILogin extends IDAO{

	@Id
	@Validator(name = ValidatorContext.VALIDATE_NOTNULL)
	public String getUserId();
	public void setUserId(String userId);

	@Validator(name = ValidatorContext.VALIDATE_NOTNULL)
	@Hidden(when = MetaworksContext.WHEN_VIEW)
	@Face(options = "type", values = "password")	
	public String getPassword();
	public void setPassword(String password);
	
	public String getName();
	public void setName(String name);

	public String getPortrait();
	public void setPortrait(String portrait);
	
	public boolean isAdmin();
	public void setAdmin(boolean isAdmin);

	public String getDefId();
	public void setDefId(String defId);
		
	public boolean isFacebookSSO();
	public void setFacebookSSO(boolean isFacebookSSO);
		

	
	
	
	
	
	
	@Test(scenario="first", starter=true, instruction="Welcome! If you have account, sign in please... or sign up for your new account.", next="autowiredObject.org.uengine.codi.mw3.model.InstanceListPanel.newInstance()")
	@ServiceMethod(callByContent=true)//, validate=true)
	public Object login() throws Exception;	
	
	@ServiceMethod(callByContent=true)
	public MainPanel loginSocialCoding() throws Exception;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object subscribe() throws Exception;

	
}