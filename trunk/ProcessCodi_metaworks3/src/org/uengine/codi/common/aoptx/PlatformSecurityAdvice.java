package org.uengine.codi.common.aoptx;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.metaworks.dao.TransactionContext;

@Aspect
public class PlatformSecurityAdvice {

    @Before("execution(* org.uengine.codi.platform.*.*(..))")
    public void beforeAPICall()  {
		TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(false); 

    }

    @After("execution(* org.uengine.codi.platform.*.*(..))")
    public void beforeRollback() throws java.rmi.RemoteException {
		TransactionContext.getThreadLocalInstance().setNeedSecurityCheck(true); 
    }

}
