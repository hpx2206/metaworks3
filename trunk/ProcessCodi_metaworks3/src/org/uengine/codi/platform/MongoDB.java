package org.uengine.codi.platform;

import java.net.UnknownHostException;

import org.metaworks.dao.TransactionContext;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB {
	
	public DB getDB(String dbName) throws UnknownHostException, MongoException{
		
		//from this, it is allowed since the request is now managed
		TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(false); 
		
		Mongo m = new Mongo();

		DB db = m.getDB( dbName );

		return db;
	}

}
