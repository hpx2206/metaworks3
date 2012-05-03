package org.uengine.codi.mw3.menu;

import javax.servlet.http.HttpSession;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.menu.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.NewClass;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.svn.CheckoutWindow;
import org.uengine.processmanager.ProcessManagerRemote;

public class SubMenuFile extends SubMenu {
	
	@AutowiredFromClient
	public ClassDefinition classDefinition;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@ServiceMethod(keyBinding="Alt+Shift+N", target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="New")
	public Object[] newObject() throws Exception {
		//setting the facebook user Id into session attribute;
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession();
		
		long defId = 1;

		if(session.getAttribute("defId") != null)
			defId = (Long)session.getAttribute("defId");		
		
		NewClass newClass = new NewClass(); 
		newClass.setSourceFolder(String.valueOf(defId));
			
		return new Object[]{newClass, new Remover(this)};			
	}
	
	@ServiceMethod(target="popup")
	@Face(displayName="Check Out", options={"separator"}, values={"true"})	
	public Popup checkOut() throws Exception {
		Popup popup = new Popup();
		popup.setName("SVN Client");
		popup.setPanel(new CheckoutWindow(classDefinition));
		
		return popup;

	}

	@ServiceMethod
	@Face(displayName="Close")
	public Object[] close() throws Exception {
		return new Object[]{new ContentWindow(), new Remover(this)};
	}
	
	@ServiceMethod
	@Face(displayName="Close All", options={"separator"}, values={"true"})
	public Object[] closeAll() throws Exception {
		return new Object[]{new ContentWindow(), new Remover(this)};
	}

	@ServiceMethod
	@Face(displayName="Save")
	public Object save() throws Exception {
		classDefinition.processManager = processManager;
		classDefinition.save();
		
		return new Remover(this);
		
	}

	@ServiceMethod
	@Face(displayName="Save As...")
	public Object saveAs() throws Exception {
		classDefinition.processManager = processManager;
		classDefinition.save();
		
		return new Remover(this);		
	}
	
	@ServiceMethod
	@Face(displayName="Save All", options={"separator"}, values={"true"})
	public Object saveAll() throws Exception {
		classDefinition.processManager = processManager;
		classDefinition.save();
		
		return new Remover(this);		
	}

	
}
