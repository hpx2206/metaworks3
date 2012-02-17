package org.uengine.codi.platform;

import java.net.UnknownHostException;
import java.util.Set;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.metaworks.dao.TransactionContext;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Aspect
public class PlatformSecurityAdvice {

    @Before("execution(* org.uengine.codi.platform.*.*(..))")
    public void beforeAPICall()  {
		SecurityContext.getThreadLocalInstance().setNeedSecurityCheck(false); 

    }

    @After("execution(* org.uengine.codi.platform.*.*(..))")
    public void afterAPICall() throws java.rmi.RemoteException {
    	SecurityContext.getThreadLocalInstance().setNeedSecurityCheck(true);
    	
//    	TransactionContext.getThreadLocalInstance().getSharedContext(contextKey)
    	Mongo m;
		try {
			
			m = new Mongo();
			
			DB db = m.getDB( "platform" );
			
			DBCollection coll = db.getCollection("apiCalls");

			
			long time = System.currentTimeMillis();// % 3600000;

//			BasicDBObject query = new BasicDBObject();

//	        query.put("time", new BasicDBObject("$eq", time));  // e.g. find all where i > 50

//	        DBObject info = coll.findOne(query);
//
//	        long count = 0;
//	        if(info!=null){
//	        	count = (Long) info.get("count");
//	        }else{
//	        	info = new BasicDBObject();
//	        }
			
	        DBObject info = new BasicDBObject();
	        
	        info.put("time", time);
			info.put("count", 1);
			
			
			coll.insert(info);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
    	

    }

}
