package org.uengine.codi.mw3;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.common.MainPanel;

public interface ILogin extends IDAO{

	@Id
	public String getUserId();
	public void setUserId(String userId);

	public String getName();
	public void setName(String name);

	public String getPortrait();
	public void setPortrait(String portrait);
	
	public boolean isAdmin();
	public void setAdmin(boolean isAdmin);

	public String getDefId();
	public void setDefId(String defId);
		
	@Test(scenario="first", starter=true, instruction="아이디/패스워스 = test/test 로 로그인을 합니다.", next="autowiredObject.org.uengine.codi.mw3.model.UnstructuredProcessInstanceStarter.start()")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public Object login() throws Exception;	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	public MainPanel loginSocialCoding() throws Exception;
}
