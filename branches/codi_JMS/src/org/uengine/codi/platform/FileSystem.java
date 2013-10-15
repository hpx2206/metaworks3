package org.uengine.codi.platform;

import java.io.File;
import java.io.IOException;

import org.metaworks.dao.TransactionContext;

public class FileSystem {
	
	public void createFile(String fileName){
		//from this, it is allowed since the request is now managed
//		TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(false); 

		File f = new File(fileName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(true); 
		
	}

}
