package org.uengine.codi.mw3.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNCommitItem;
import org.tmatesoft.svn.core.wc.SVNCommitPacket;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.CodiDwrServlet;
import org.uengine.codi.mw3.admin.ClassDefinition;

public class CommitWindow extends SVNWindow{
	
//	ClassDefinition classDefinition;
	
	
	
	public CommitWindow(){
		super();
	}
	
	public CommitWindow(ClassDefinition classDefinition){
		super();
		this.log = "Press 'sync' button to get commitable list";
	}

	List<Commitable> commitables;
	
		
		public List<Commitable> getCommitables() {
			return commitables;
		}
	
		public void setCommitables(List<Commitable> commitables) {
			this.commitables = commitables;
		}

	
	@ServiceMethod
	public void sync() throws SVNException, Exception{
		SVNClientManager ourClientManager = getSVNClientManager();

        String myWorkingCopyPath = CodiClassLoader.codiClassLoader.sourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (!wcDir.exists()) {
        	throw new Exception("You need check out first.");
        }
        
        SVNCommitClient commitClient = ourClientManager.getCommitClient();
        SVNCommitPacket packet = commitClient.doCollectCommitItems(new File[]{wcDir}, false, true, SVNDepth.INFINITY, new String[]{});
                
        SVNCommitItem[] commitItems = packet.getCommitItems();
        
        commitables = new ArrayList<Commitable>();
        
        for(SVNCommitItem item: commitItems){
        	Commitable commititem = new Commitable();
        	commititem.setMetaworksContext(new MetaworksContext());
        	commititem.getMetaworksContext().setWhen("edit");
        	commititem.setCheck(true);
        	commititem.setResourceName(item.getFile().getPath());
        	commitables.add(commititem);
        }
	}
	

	@ServiceMethod(callByContent=true)
	public void commit() throws SVNException, Exception{
		
		SVNClientManager ourClientManager = getSVNClientManager();
        
        String myWorkingCopyPath = CodiClassLoader.codiClassLoader.sourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

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
