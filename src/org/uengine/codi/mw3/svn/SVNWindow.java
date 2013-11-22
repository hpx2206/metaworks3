package org.uengine.codi.mw3.svn;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.uengine.codi.platform.Console;

public class SVNWindow {
	
	public SVNWindow(){
		this.log = "";
	}

	String log;
		public String getLog() {
			return log;
		}
	
		public void setLog(String log) {
			this.log = log;
		}
				
	public void addLog(String log){
		
		Console.addLog(log);
		
//		WebContext wctx = WebContextFactory.get();
//		String currentPage = wctx.getCurrentPage();
//
//	   // For all the browsers on the current page:
//	   ScriptSession session = wctx.getScriptSession();
//
//	   //TODO: filter other topic's postings;
//	   Util theScriptSessionUtil = new Util(session);
//	   theScriptSessionUtil.addFunctionCall("mw3.getAutowiredObject('"+ getClass().getName() +"').__getFaceHelper().addLog('" + log + "')");

	}

	protected SVNClientManager getSVNClientManager(){
		//setup libraries
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();

        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        
        String name = "mw3.tester1@gmail.com";
        String password = "XW3ej3kq4xc7";
        SVNClientManager ourClientManager = SVNClientManager.newInstance(options, name, password);        
        ourClientManager.getCommitClient().setEventHandler(new CommitEventHandler());
        ourClientManager.getUpdateClient().setEventHandler(new UpdateEventHandler(this));
        ourClientManager.getWCClient().setEventHandler(new WCEventHandler());

        return ourClientManager;
	}
	
	/*
	@ServiceMethod
	public void update() throws SVNException, Exception{
		
		SVNClientManager ourClientManager = getSVNClientManager();
        
        String myWorkingCopyPath = CodiDwrServlet.codiClassLoader.sourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (!wcDir.exists()) {
        	throw new Exception("You need check out first.");
        }
        
        SVNDiffClient diffClient = ourClientManager.getDiffClient();
        //diffClient.doDiff(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7)

		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		updateClient.doUpdate(wcDir, SVNRevision.HEAD, true); //since the directory inherently have connection info, url is not required.

		//let the session knows the source code is checked out that means classloader should use this code base 
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		session.setAttribute("sourceCodeBase", CodiClassLoader.mySourceCodeBase());

	}

	@ServiceMethod
	public void commit() throws SVNException, Exception{
		
		SVNClientManager ourClientManager = getSVNClientManager();
        
        String myWorkingCopyPath = CodiDwrServlet.codiClassLoader.sourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (!wcDir.exists()) {
        	throw new Exception("You need check out first.");
        }
        
        SVNCommitClient commitClient = ourClientManager.getCommitClient();

        commitClient.doCommit( 
    		   new File[] { wcDir }, false,
    		   "commitMessage", false, true
        );
        
	}
	*/

}
