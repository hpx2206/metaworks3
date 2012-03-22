package org.uengine.codi.mw3.menu;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.menu.SubMenu;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.model.Window;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.platform.Console;
import org.metaworks.Remover;


public class SubMenuRun extends SubMenu {
	@AutowiredFromClient
	public ClassDefinition classDefinition;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run (Normal)")
	public Object[] run() throws Exception{
		System.out.println("run");
		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();
		
		Console.addLog("Run (Normal) -> " + fullClassName);
		
		try{
			Object o = Thread.currentThread().getContextClassLoader().loadClass(fullClassName).newInstance();

			Window outputWindow = new Window();
			outputWindow.setPanel(o);
			
			return new Object[]{outputWindow, new Remover(this)};

		}catch(Exception e){
			Console.addLog(e.getMessage());

			throw e;
		}		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run for UI Design")
	public Object[] design() throws Exception{
		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();
		
		Console.addLog("Run for UI Design -> " + fullClassName);
		
		Window outputWindow = new Window();
		
		try{
			TemplateDesigner designer = new TemplateDesigner(fullClassName);
			
			outputWindow.setPanel(designer);
			
			return new Object[]{outputWindow, new Remover(this)};
		}catch(Exception e){
			Console.addLog(e.getMessage());

			throw e;			
		}
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in Mobile")
	public Object[] runMobile() throws Exception{
		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();

		Console.addLog("Run in Mobile -> " + fullClassName);
		
		try{
			Object o = Thread.currentThread().getContextClassLoader().loadClass(fullClassName).newInstance();
			
			MobileWindow outputWindow = new MobileWindow();
			outputWindow.setPanel(o);
			
			HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
			
			outputWindow.setUrl(request.getServerName() + request.getProtocol()+request.getPathInfo());
			
			return new Object[]{outputWindow, new Remover(this)};
		}catch(Exception e){
			Console.addLog(e.getMessage());

			throw e;			
		}
	}	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in New Window")
	public Object[] runFullWindow() throws Exception{
		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();
		
		Console.addLog("Run in Mobile -> " + fullClassName);
		
		try{
			String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
			
			Window outputWindow = new Window(new IFrame("runner.html?className=" + fullClassName + "&classOwner=" + userId));
			
			return new Object[]{outputWindow, new Remover(this)};
		}catch(Exception e){
			Console.addLog(e.getMessage());

			throw e;			
		}			
	}	
}
