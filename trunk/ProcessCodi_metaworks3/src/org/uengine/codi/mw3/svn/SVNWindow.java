package org.uengine.codi.mw3.svn;

import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.CodiDwrServlet;
import org.uengine.codi.mw3.admin.ClassDefinition;

public class SVNWindow {
	
//	ClassDefinition classDefinition;
	
	
	
	public SVNWindow(){
		
	}
	
	public SVNWindow(ClassDefinition classDefinition){
		this.log = "";
	}

	String log;

		public String getLog() {
			return log;
		}
	
		public void setLog(String log) {
			this.log = log;
		}
		
	@ServiceMethod
	public void cancel(){
		
	}
		
	public void addLog(String log){
		WebContext wctx = WebContextFactory.get();
		String currentPage = wctx.getCurrentPage();

	   // For all the browsers on the current page:
	   ScriptSession session = wctx.getScriptSession();

	   //TODO: filter other topic's postings;
	   Util theScriptSessionUtil = new Util(session);
	   theScriptSessionUtil.addFunctionCall("mw3.getAutowiredObject('"+ getClass().getName() +"').__getFaceHelper().addLog('" + log + "')");

	}
	
	
	
	
	protected SVNClientManager getSVNClientManager(){
		//setup libraries
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();

        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        String name = "jyjang@uengine.org";
        String password = "Gx3XS6jA9hc7";
        SVNClientManager ourClientManager = SVNClientManager.newInstance(options, name, password);

        ourClientManager.getCommitClient().setEventHandler(new CommitEventHandler());
        ourClientManager.getUpdateClient().setEventHandler(new UpdateEventHandler(this));
        ourClientManager.getWCClient().setEventHandler(new WCEventHandler());

        return ourClientManager;
	}
	
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

}
