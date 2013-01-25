package org.uengine.codi.platform;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.annotation.ServiceMethod;

public class Console {
	
	public Console(){
		setLog("");
	}

	String log;
		public String getLog() {
			return log;
		}
	
		public void setLog(String log) {
			this.log = log;
		}
		
	public static void addLog(String log){
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();
	
	   // For the session user only 
	   ScriptSession session = wctx.getScriptSession();
	
	   //TODO: filter other topic's postings;
	   Util theScriptSessionUtil = new Util(session);
	   theScriptSessionUtil.addFunctionCall("mw3.getAutowiredObject('"+ Console.class.getName() +"').__getFaceHelper().addLog('" + log + "')");
	
	}
	
	public static void addError(String log){
		
		log = log.replaceAll("\\n", "<br>");
		
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();
	
	   // For the session user only 
	   ScriptSession session = wctx.getScriptSession();
	
	   //TODO: filter other topic's postings;
	   Util theScriptSessionUtil = new Util(session);
	   theScriptSessionUtil.addFunctionCall("mw3.getAutowiredObject('"+ Console.class.getName() +"').__getFaceHelper().addError('" + log + "')");
	
	}	

	@ServiceMethod
	public void clear(){
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();
	
	   // For the session user only 
	   ScriptSession session = wctx.getScriptSession();
	
	   //TODO: filter other topic's postings;
	   Util theScriptSessionUtil = new Util(session);
	   theScriptSessionUtil.addFunctionCall("mw3.getAutowiredObject('"+ Console.class.getName() +"').__getFaceHelper().clear()");
	
	}

}
