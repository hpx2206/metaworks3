package org.uengine.codi.scheduler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dao.TransactionListener;
import org.metaworks.spring.SpringConnectionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.StatefulJob;
import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.TimerEventActivity;
import org.uengine.kernel.WaitActivity;
import org.uengine.scheduler.SchedulerItem;


public class WaitJob implements StatefulJob {
	
	public static ConnectionFactory connectionFactory;
	
	public void execute(JobExecutionContext context) {

		TransactionContext tx = null;
		
		try{
			Thread.currentThread().setContextClassLoader(CodiClassLoader.codiClassLoader);
			
			connectionFactory = (SpringConnectionFactory) context.getJobDetail().getJobDataMap().get("connectionFactory");
			
			tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing.
			tx.setManagedTransaction(true);
			tx.setAutoCloseConnection(true);
			
			if(connectionFactory!=null)
				tx.setConnectionFactory(connectionFactory);

			MetaworksUEngineSpringConnectionAdapter connectionAdapter = new MetaworksUEngineSpringConnectionAdapter();
	
			CodiProcessManagerBean pm = new CodiProcessManagerBean();
			pm.setConnectionFactory(connectionAdapter);
			pm.setAutoCloseConnection(false);
			pm.setManagedTransaction(true);
			
			CodiClassLoader clForSession = CodiClassLoader.createClassLoader(null, null, false);
			Thread.currentThread().setContextClassLoader(clForSession);
			
			Calendar now = Calendar.getInstance();
			
			List<SchedulerItem> schedulerItems = this.getAllSchedule();
			
			for (final SchedulerItem item : schedulerItems) {
				if (!(item.getStartDate().getTime() <= now.getTimeInMillis())) {
					continue;
				}
	
				ProcessInstance instance = null;
				
				try {
					instance = pm.getProcessInstance(item.getInstanceId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				boolean isError = true;
				if (instance != null) {
					Activity act = instance.getProcessDefinition().getActivity(item.getTracingTag());
					/*
					 *WaitActivity에서 인스턴스 종료 옵션 선택시
					 * 					
					 */
					if (act != null && act instanceof WaitActivity) {
						WaitActivity wa = (WaitActivity)act;
						
						String status = wa.getStatus(instance);
						if (Activity.STATUS_RUNNING.equals(status) || Activity.STATUS_TIMEOUT.equals(status)) {
							tx.addTransactionListener(new TransactionListener() {

								public void beforeRollback(TransactionContext tx) throws Exception {
								}

								public void beforeCommit(TransactionContext tx) throws Exception {
									deleteSchedule(item.getIdx());
								}

								public void afterRollback(TransactionContext tx) throws Exception {
								}

								public void afterCommit(TransactionContext tx) throws Exception {
								}
							});
							
							isError = false;
							
							if(wa.getInstanceStop() != null && wa.getInstanceStop().equals("STOP_INSTANCE")){
								wa.stopInstance(instance);
							}
							
							wa.fireComplete(instance);
						} else if (Activity.STATUS_FAULT.equals(status)
								|| Activity.STATUS_READY.equals(status) 
								|| Activity.STATUS_STOPPED.equals(status) || Activity.STATUS_CANCELLED.equals(status) ) {
							
							continue;
							
						}
					}else if( act != null && act instanceof TimerEventActivity ){
						TimerEventActivity wa = (TimerEventActivity)act;
						
						String status = wa.getStatus(instance);
						if (Activity.STATUS_RUNNING.equals(status) || Activity.STATUS_TIMEOUT.equals(status)) {
							tx.addTransactionListener(new TransactionListener() {

								public void beforeRollback(TransactionContext tx) throws Exception {
								}

								public void beforeCommit(TransactionContext tx) throws Exception {
									deleteSchedule(item.getIdx());
								}

								public void afterRollback(TransactionContext tx) throws Exception {
								}

								public void afterCommit(TransactionContext tx) throws Exception {
								}
							});
							
							isError = false;
							
							if(wa.getInstanceStop() != null && wa.getInstanceStop().equals("STOP_INSTANCE")){
								WaitActivity waitActivity = new WaitActivity();
								waitActivity.stopInstance(instance);
							}
							
							wa.fireComplete(instance);
						}else if (Activity.STATUS_FAULT.equals(status)
								|| Activity.STATUS_READY.equals(status) 
								|| Activity.STATUS_STOPPED.equals(status) || Activity.STATUS_CANCELLED.equals(status) ) {
							continue;
						}
					}
				}
				
				if (isError) {
					deleteSchedule(item.getIdx());
				}
			}
			
			pm.applyChanges();
			tx.commit();

		}catch(Exception e){
			e.printStackTrace();
			
			if(tx != null){
				try {
					tx.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}			
			}
		}finally{
			if(tx != null){
				try {
					tx.releaseResources();
				} catch (Exception e) {
					e.printStackTrace();
				}							
			}
		}

	}
	
	public List<SchedulerItem> getAllSchedule() {
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<SchedulerItem> schedulerItems = new ArrayList<SchedulerItem>();
        
        try {
            conn = connectionFactory.getConnection();
            stmt = conn.createStatement(); 
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT "); 
            sql.append("	schedule_table.SCHE_IDX, "); 
            sql.append("	schedule_table.INSTID, "); 
            sql.append("	schedule_table.TRCTAG, "); 
            sql.append("	schedule_table.STARTDATE, "); 
            sql.append("	bpm_procinst.STATUS, "); 
            sql.append("	bpm_procinst.ISDELETED "); 
            sql.append("FROM schedule_table JOIN bpm_procinst ON schedule_table.INSTID = bpm_procinst.INSTID "); 
            sql.append("WHERE "); 
            sql.append("	bpm_procinst.ISDELETED = 0 "); 
            sql.append("	AND bpm_procinst.STATUS = 'Running' ");
            
            rs = stmt.executeQuery(sql.toString());
            
            while (rs.next()) {
            	SchedulerItem item = new SchedulerItem();
            	
            	item.setIdx(rs.getInt("SCHE_IDX"));
            	item.setInstanceId(rs.getString("INSTID"));
            	item.setTracingTag(rs.getString("TRCTAG"));
            	item.setStartDate(rs.getTimestamp("STARTDATE"));
            	
            	schedulerItems.add(item);
            }

        } catch (Exception e){
        	e.printStackTrace();
        } finally {
        	if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) { }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) { }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) { }
            }
        }
     
        return schedulerItems;
	}
	
	/**
	 * Delete schedule in DB.
	 * 
	 * @param instance the instance
	 * @param tracintTag the tracing tag
	 */
	public void deleteSchedule(int idx) throws Exception {
		// delete schedule information from DB. 
		Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = connectionFactory.getConnection();
            
            stmt = conn.createStatement();
            
            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM schedule_table WHERE SCHE_IDX=").append(idx);
            
            stmt.executeUpdate(sql.toString());
            
//            conn.commit();
        } catch (Exception e) {
			if (conn != null) try { conn.rollback(); } catch (Exception e1) { }
			throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) { }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) { }
            }
        }
	}
	
}

