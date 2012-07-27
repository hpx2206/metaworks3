package org.uengine.codi.cron;
        

import org.metaworks.dao.TransactionContext;
import org.metaworks.spring.SpringConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CodiCronJob extends QuartzJobBean{
	
	
	@Autowired
	SpringConnectionFactory connectionFactory;
	

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {

        TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
        
		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);
		
		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		

		try{
			
			//TODO: e-mail 읽어서 인스턴스 발행하기
			//TODO: worklist RESERVED 찾아서 실행하기
			//TODO: instance 완료시간 도래 된 것들 찾아서 알림 주기
			//
			
			tx.commit();
		}catch(Throwable e){
			try {
				tx.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
			//in DWR, we never stops the application
			//throw new ServletException(e);
			
		}finally{
			try {
				tx.releaseResources();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
