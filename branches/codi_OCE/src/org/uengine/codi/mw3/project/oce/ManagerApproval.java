package org.uengine.codi.mw3.project.oce;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.Range;
import org.metaworks.dao.ConnectionFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.JDBCConnectionFactory;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ICloudInfo;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.util.Base64;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
	}
	
	String projectId;
	@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	String projectName; 
	@Hidden
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	String instId;
	@Hidden
		public String getInstId() {
			return instId;
		}
		public void setInstId(String instId) {
			this.instId = instId;
		}
	String osType;
	@Hidden
		public String getOsType() {
			return osType;
		}
		public void setOsType(String osType) {
			this.osType = osType;
		}
	String wasType;
	@Hidden
		public String getWasType() {
			return wasType;
		}
		public void setWasType(String wasType) {
			this.wasType = wasType;
		}
	String dbType;
	@Hidden
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
		}
	String approval;
		@Face(displayName="요청된 개발기 생성을 승인합니다.")
		@Range(options={"승인", "거부"}, values={"approval", "reject"})
		public String getApproval() {
			return approval;
		}
		public void setApproval(String approval) {
			this.approval = approval;
		}
	String resultStr;
		@NonEditable
		@Face(displayName="진행여부.")
		public String getResultStr() {
			return resultStr;
		}
		public void setResultStr(String resultStr) {
			this.resultStr = resultStr;
		}
	
	String resultIp;
	@Available(when="view")
	@Face(displayName="생성된 IP 정보")
		public String getResultIp() {
			return resultIp;
		}
		public void setResultIp(String resultIp) {
			this.resultIp = resultIp;
		}
		
	@Autowired
	public ProcessManagerRemote processManager ; 	
		
	public static ConnectionFactory connectionFactory;
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		if( approval != null && approval.equals("approval") ){
			setResultStr("생성중 입니다.");
		}else{
			setResultStr("취소 되었습니다.");
		}
	}
	public void afterComplete() throws Exception {
		final String instIDDD = this.instId;
		final String projectName = this.getProjectName();
		ProcessManagerBean processManagerBean = new ProcessManagerBean();
		final ProcessInstance instance  = processManagerBean.getProcessInstance(instIDDD);
		this.processManager = processManagerBean;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					KtProjectCreateRequest ktProjectCreateRequest = new KtProjectCreateRequest();
					ktProjectCreateRequest.setProcessManager(processManager);
					CloudInfo cloudInfo = ktProjectCreateRequest.createRequset(projectName , instance);
					insertCloudInfo(cloudInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}
	
	public void insertCloudInfo(CloudInfo cloudInfo) throws Exception{
		
		TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
		try{
			tx.setManagedTransaction(false);
			tx.setAutoCloseConnection(true);
			
	//		tx.setRequest(request);
	//		tx.setResponse(response);
			
			String connectionString = GlobalContext.getPropertyString("jdbc.url", null);
			if(connectionString!=null){
				String driverClass = GlobalContext.getPropertyString("jdbc.driverClassName", null);
				String userId = GlobalContext.getPropertyString("jdbc.username", "root");
				String password = GlobalContext.getPropertyString("jdbc.password", "root");
				
				JDBCConnectionFactory cf = new JDBCConnectionFactory();
				cf.setConnectionString(connectionString);
				cf.setDriverClass(driverClass);
				cf.setUserId(userId);
				cf.setPassword(password);
				connectionFactory = cf;
			}
			if(connectionFactory!=null){
				tx.setConnectionFactory(connectionFactory);
			}
			
			WfNode node = new WfNode();
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from bpm_knol knol");
			sb.append(" where knol.type = ?type");	
			sb.append(" and knol.name = ?name");	
			
			IWfNode dao = (IWfNode)Database.sql(IWfNode.class,sb.toString());
			
			dao.set("type", "project");
			dao.set("name", cloudInfo.getServerName());
			dao.select();
			
			// 프로젝트를 처음 생성할때... 
			if(dao.size() > 0){
				if (dao.next()) {
					node.copyFrom(dao);
					
					cloudInfo.setProjectId(node.getId());
					cloudInfo.setServerInfo("KT Cloud");
					cloudInfo.setDbType(this.getDbType());
					cloudInfo.setOsType(this.getOsType());
					cloudInfo.setWasType(this.getWasType());
					cloudInfo.setServerGroup(KtProjectServers.SERVER_DEV);
					cloudInfo.setModdate(new Date());
					
					ICloudInfo iCInfo = cloudInfo.findServerByServerName(node.getId() , cloudInfo.getServerName() , KtProjectServers.SERVER_DEV);
					if( iCInfo.next() ){
						cloudInfo.setId(iCInfo.getId());
						cloudInfo.syncToDatabaseMe();
					}else{
						cloudInfo.setId(cloudInfo.createNewId());
						cloudInfo.createDatabaseMe();
					}
					tx.commit();
				}
			}else{
				// 개발기나 운영기를 추가할때
			}
		}catch(Exception e){
			tx.rollback();
			throw e;
		}finally{
			tx.releaseResources();
		}
	}
	
}