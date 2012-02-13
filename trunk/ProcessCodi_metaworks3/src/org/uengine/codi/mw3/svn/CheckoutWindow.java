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

public class CheckoutWindow extends SVNWindow{
	
//	ClassDefinition classDefinition;
	
	
	
	public CheckoutWindow(){
		super();
	}
	
	public CheckoutWindow(ClassDefinition classDefinition){
//		this.classDefinition = classDefinition;
		super();
		this.log = "Press checkout button to start";
	}

	
	@ServiceMethod
	public void checkout() throws SVNException, Exception{
		SVNClientManager ourClientManager = getSVNClientManager();

        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded("https://metaworks3.googlecode.com/svn/trunk");
        } catch (SVNException e) {
            //
        }
        
        final SVNURL url = repositoryURL.appendPath("MyRepos", false);

        
        String myWorkingCopyPath = CodiDwrServlet.codiClassLoader.sourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (wcDir.exists()) {
        	throw new Exception("Already there's checkout working copy exists.");
        }

        
        wcDir.mkdirs();
        
		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		updateClient.doCheckout(url, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, true);

		//let the session knows the source code is checked out that means classloader should use this code base 
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		session.setAttribute("sourceCodeBase", CodiClassLoader.sourceCodeBase());

		setLog("check out done !");
	}

}
