package org.uengine.codi.mw3.project.oce;

public interface IaaSConnectionFectory {
	
	public final static String SERVER_STATUS_REQUEST 		= "Request";
	public final static String SERVER_STATUS_APPROVAL 		= "Approval";
	public final static String SERVER_STATUS_CREATING 		= "Creating";
	public final static String SERVER_STATUS_STOPPED 		= "Stopped";
	public final static String SERVER_STATUS_STARTED			= "Started";
	public final static String SERVER_STATUS_STARTING 		= "Starting";
	public final static String SERVER_STATUS_STOPPING		= "Stopping";
	public final static String SERVER_STATUS_RUNNING 		= "Running";
	public final static String SERVER_STATUS_DEPLOYING 		= "Deploying";
	public final static String SERVER_STATUS_DEPLOYFAIL		= "DeployFail";
	
	public final static String SERVER_STATUS_IP_REQUEST					= "IpRequest";
	public final static String SERVER_STATUS_IP_REQUESTFAIL			= "IpRequestFail";
	public final static String SERVER_STATUS_IPDELETE_REQUEST			= "IpDeleteRequest";
	public final static String SERVER_STATUS_IPDELETE_REQUESTFAIL	= "IpDeleteRequestFail";
	public final static String SERVER_STATUS_START_REQUEST			= "StartRequest";
	public final static String SERVER_STATUS_START_REQUESTFAIL		= "StartRequestFail";
	public final static String SERVER_STATUS_DELETE_REQUEST			= "DeleteRequest";
	public final static String SERVER_STATUS_DELETE_REQUESTFAIL		= "DeleteRequestFail";
	public final static String SERVER_STATUS_STOP_REQUEST				= "StopRequest";
	public final static String SERVER_STATUS_STOP_REQUESTFAIL		= "StopRequestFail";
	public final static String SERVER_STATUS_PORTFOWARDING_REQUEST				= "PortFowardingRequest";
	public final static String SERVER_STATUS_PORTFOWARDING_REQUESTFAIL		= "PortFowardingRequestFail";

	public ServerInfo getServerInfo();
	public void setServerInfo(ServerInfo serverInfo);
	/**
	 * 서버생성
	 */
	public void createServer() throws Exception;
	/**
	 * 서버시작
	 */
	public void startServer() throws Exception;
	/**
	 * 서버중지
	 */
	public void stopServer() throws Exception;
	/**
	 * 서버삭제
	 */
	public void deleteServer() throws Exception;
	/**
	 * 서버 정보확인(내부아이피, root 비밀번호 등)
	 */
	public void serverInfo() throws Exception;
	/**
	 * 서버 상태확인(생성중, 실행중, 삭제중, 실행하는중, 실행중, 중지하는중, 중지중 등)
	 */
	public String serverStatus() throws Exception;
	public String serverStatusWithThread() throws Exception;
	/**
	 * 외부 아이피 생성 
	 */
	public void createOutsideIP() throws Exception;
	/**
	 * 외부 아이피 삭제 
	 */
	public void deleteOutsideIP() throws Exception;
	
	/**
	 * ServerTypeList
	 */
	public Object getServerTypeList() throws Exception;
	/**
	 * 포트포워딩 목록
	 */
	public Object getPortForwardingList(String vmId) throws Exception;
	/**
	 * 포트포워딩 설정
	 */
	public void setPortForwarding() throws Exception;
	/**
	 * 포트포워딩 제거
	 */
	public void deletePortForwarding() throws Exception;
}
