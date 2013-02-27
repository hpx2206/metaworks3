package org.uengine.codi.cron;


import javax.sql.RowSet;

import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.spring.SpringConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.codi.mw3.model.CommentWorkItem;
import org.uengine.codi.mw3.model.Company;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IEmployee;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.User;

public class ScheduleReader extends QuartzJobBean{
	
	public ScheduleReader() {}
	
	
	@Autowired
	SpringConnectionFactory connectionFactory;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
 			throws JobExecutionException{
		
		
		connectionFactory = (SpringConnectionFactory) arg0.getJobDetail().getJobDataMap().get("connectionFactory");
		
		TransactionContext tx = new TransactionContext();

		tx.setManagedTransaction(false);
		tx.setAutoCloseConnection(true);
		
		MetaworksUEngineSpringConnectionAdapter connectionAdapter = new MetaworksUEngineSpringConnectionAdapter();

		CodiProcessManagerBean processManager = new CodiProcessManagerBean();
		processManager.setConnectionFactory(connectionAdapter);
		processManager.setAutoCloseConnection(false);
		processManager.setManagedTransaction(true);
		
		if(connectionFactory!=null)
			tx.setConnectionFactory(connectionFactory);
		
		try {
			
			//workitem 생성
			CommentWorkItem workItem = new CommentWorkItem();
			
			IInstance instList = (IInstance) Database.sql(IInstance.class, "select * from bpm_procinst where DATE_FORMAT(duedate,'%Y-%m-%d') = CURRENT_DATE");
			instList.select();
			
			RowSet instRow = instList.getImplementationObject().getRowSet();
			
			Session codiSession = new Session();
			Locale localeManager = new Locale();
			
			if(instList.size() > 0 ){
				
				while(instRow.next()){

					IUser writer = new User();
					writer.setUserId(instRow.getString("INITEP"));
					writer.setName(instRow.getString("INITRSNM"));
					
					ICompany company = new Company();
					company.setComCode(instRow.getString("INITCOMCD"));
					
					IEmployee emp = new Employee();
					emp.setEmpCode(instRow.getString("INITEP"));
					emp.setGlobalCom(instRow.getString("INITCOMCD"));
					
					
					codiSession.setUser(writer);
					codiSession.setCompany(company);
					codiSession.setEmployee(emp);
					
					localeManager.setLanguage(emp.findMe().getLocale());
					localeManager.load();
					
					workItem.setInstId(instRow.getLong("INSTID"));
					workItem.setEndpoint(instRow.getString("INITEP"));
					workItem.setDueDate(instRow.getDate("DUEDATE"));
					workItem.setRootInstId(instRow.getLong("INSTID"));
					workItem.setWriter(writer);
					workItem.setIsDeleted(instRow.getBoolean("ISDELETED"));
					workItem.setTitle(localeManager.getResourceBundle().getProperty("TimeToComplete"));
					
					workItem.processManager = processManager;
					workItem.session = codiSession;
					workItem.add();
							
				}
			}
			
			tx.commit();
		}catch(Throwable e){
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		}finally{
			try {
				tx.releaseResources();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
