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
    	
    	Mongo m;
		try {
			
			m = new Mongo();
			DB db = m.getDB( "platform" );
			
			DBCollection coll = db.getCollection("apiCalls");
			
			long time = System.currentTimeMillis();

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
