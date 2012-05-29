package org.uengine.codi.mw3.menu;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.metaworks.widget.menu.SubMenu;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.platform.Console;


public class SubMenuRun extends SubMenu {
	@AutowiredFromClient
	public ClassDefinition classDefinition;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run (Normal)")
	public Object[] run() throws Exception{
				
		try{
			try{
				classDefinition.compile();
			}catch(Exception e){
				//compile error will report the error by itself 
				
				return new Object[]{new Remover(this)};
			}

			String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();

			Console.addLog("Run (Normal) -> " + fullClassName);
			
			Object o = Thread.currentThread().getContextClassLoader().loadClass(fullClassName).newInstance();

			ModalWindow outputWindow = new ModalWindow();
			outputWindow.setTitle("Output");
			outputWindow.setPanel(o);
			
			return new Object[]{outputWindow, new Remover(this)};

		}catch(Exception e){
			/*
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			
			e.printStackTrace(pw);
			String stackDump = sw.toString();
			
			sw.close();
			pw.close();
			
			Console.addLog(stackDump);
			*/
			
			Console.addError(e.getMessage());
		}		
		
		return new Object[]{new Remover(this)};
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run for UI Design")
	public Object[] design() throws Exception{
		
		classDefinition.compile();

		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();
		
		Console.addLog("Run for UI Design -> " + fullClassName);
		
		ModalWindow outputWindow = new ModalWindow();
		outputWindow.setTitle("Output");
		
		try{
			TemplateDesigner designer = new TemplateDesigner(fullClassName);
			
			outputWindow.setPanel(designer);
			
			return new Object[]{outputWindow, new Remover(this)};
		}catch(Exception e){
			Console.addError(e.getMessage());
		}
		
		return new Object[]{new Remover(this)};
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in Mobile")
	public Object[] runMobile() throws Exception{
		
		classDefinition.compile();
		
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
			Console.addError(e.getMessage());
		}
		
		return new Object[]{new Remover(this)};
	}	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in New Window")
	public Object[] runFullWindow() throws Exception{
		
		classDefinition.compile();

		String fullClassName = classDefinition.getPackageName() + "." + classDefinition.getClassName();
		
		Console.addLog("Run in Mobile -> " + fullClassName);
		
		try{
			String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
			
			ModalWindow outputWindow = new ModalWindow();
			outputWindow.setTitle("Output");

			outputWindow.setPanel(new IFrame("runner.html?className=" + fullClassName + "&classOwner=" + userId));
			
			return new Object[]{outputWindow, new Remover(this)};
		}catch(Exception e){
			Console.addError(e.getMessage());
		}			
		
		return new Object[]{new Remover(this)};
	}	
}
