package org.uengine.codi.cron;
        

import org.metaworks.dao.TransactionContext;
import org.metaworks.spring.SpringConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

@Component
public class CodiCronJob extends QuartzJobBean{
	
	
	@Autowired
	SpringConnectionFactory connectionFactory;
	

	@Autowired
	EMailReader emailReader;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {

        TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
        
		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);

		connectionFactory = (SpringConnectionFactory) arg0.getJobDetail().getJobDataMap().get("connectionFactory");
		emailReader = (EMailReader) arg0.getJobDetail().getJobDataMap().get("emailReader");
		
		MetaworksUEngineSpringConnectionAdapter connectionAdapter = new MetaworksUEngineSpringConnectionAdapter();

		CodiProcessManagerBean processManager = new CodiProcessManagerBean();
		processManager.setConnectionFactory(connectionAdapter);
		processManager.setAutoCloseConnection(false);
		processManager.setManagedTransaction(true);
		
		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		

		try{
			//TODO: e-mail 읽어서 인스턴스 발행하기
			
			emailReader.memoWorkItem.processManager = processManager;
			emailReader.memoWorkItem.instanceViewContent.instanceView.processManager = processManager;
			emailReader.read();
			
			//TODO: 대표 SNS 계정 접속하여 담벼락에 올라온 사항 인스턴스 발행하기
			//TODO: 대표 그룹 계정 (위의 SNS계정과 아마 API가 동일할 것)에 접속하여 담벼락 긁어오기
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
