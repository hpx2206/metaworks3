
package org.uengine.codi.mw3.admin;


import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.model.Window;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.codi.platform.Console;


public class Runner implements ContextAware{

	public Runner(){
	}

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
	
	String fullClassName;
	@Id
		public String getFullClassName() {
			return fullClassName;
		}
		public void setFullClassName(String className) {
			this.fullClassName = className;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run (Normal)")
	public Object run() throws Exception{
		Console.addLog("Run (Normal) -> " + getFullClassName());
		
		try{
			Object o = Thread.currentThread().getContextClassLoader().loadClass(getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

			Window outputWindow = new Window();
			outputWindow.setPanel(o);
			
			return outputWindow;

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			
			e.printStackTrace(pw);
			String stackDump = sw.toString();
			
			sw.close();
			pw.close();
			
			Console.addError(stackDump);

			throw e;
		}		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run for UI Design")
	public Object design() throws Exception{
		
		Window outputWindow = new Window();
		
		TemplateDesigner designer = new TemplateDesigner(getFullClassName());
		
		outputWindow.setPanel(designer);
		
		return outputWindow;
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in Mobile")
	@Test(
			scenario="ClassDefinition" 
			) 
	public Object runMobile() throws Exception{
		

		Object o = Thread.currentThread().getContextClassLoader().loadClass(getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
		
		MobileWindow outputWindow = new MobileWindow();
		outputWindow.setPanel(o);
		
		HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
		
		outputWindow.setUrl(request.getServerName() + request.getProtocol()+request.getPathInfo());
		
		return outputWindow;
	}	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="Run in New Window")
	public Object runFullWindow() throws Exception{
		
//		BrowserWindow window = new BrowserWindow(getPackageName() + "." + getClassName());
		
		String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
		
		Window window = new Window(new IFrame("runner.html?className=" + getFullClassName() + "&classOwner=" + userId));
		
		return window;
	}	
	
	
}
