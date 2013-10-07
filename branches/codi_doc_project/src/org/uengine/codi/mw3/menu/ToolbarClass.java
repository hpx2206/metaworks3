package org.uengine.codi.mw3.menu;

import javax.servlet.http.HttpSession;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.menu.Toolbar;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.CrowdSourcerWindow;
import org.uengine.codi.mw3.model.Popup;

public class ToolbarClass extends Toolbar {

	@AutowiredFromClient
	public ClassDefinition classDefinition;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, callByContent=true)
	public Popup share(){
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)httpSession.getAttribute("userId");
		
		Login login = new Login();
		login.setUserId(userId);

		
		Popup popup = new Popup();
		popup.setName("공유하기...");
		popup.setPanel(new CrowdSourcerWindow(login, classDefinition.getAlias()));
		
		return popup;
	}
	
}
