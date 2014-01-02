package org.uengine.codi.platform;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PlatformSecurityAdvice {

	/*
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
    */
}
