package org.uengine.cloud.saasfier;

import java.net.MalformedURLException;
import java.net.URL;

import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.kernel.GlobalContext;



public class TenantContext{
	
    static ThreadLocal<TenantContext> local = new ThreadLocal<TenantContext>();

    String tenantId;
		public String getTenantId() {
			return tenantId;
		}
		public void setTenantId(String tenantId) {
			this.tenantId = tenantId;
		}


	public TenantContext(String tenantId){
//		if(getThreadLocalInstance()!=null)
//			throw new RuntimeException("There's uncommitted transactionContext remains.");
		
		this.tenantId = tenantId;
		
//new Throwable("TenantId is recognized: " + tenantId).printStackTrace();
		
		local.set(this);

	}
	
	public static String getURL() throws MalformedURLException {
		return TenantContext.getURL(null);
	}
	
	public static String getURL(String tenantId) throws MalformedURLException {

		/*
		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
        URL urlURL = new java.net.URL(base);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	String protocol = urlURL.getProtocol();

       	StringBuffer url = new StringBuffer();
       	
       	url.append(protocol + "://");
       	if("1".equals(StartCodi.USE_MULTITENANCY)){
       		url.append((tenantId==null?"":tenantId+"."));
       	}
       	
       	url.append(host);
		url.append((port == 80 ? "" : ":"+port));
		url.append(TransactionContext.getThreadLocalInstance().getRequest().getContextPath());
		*/
		
		String ip = GlobalContext.getPropertyString("web.server.ip", "uenginecloud.com");
		String port = GlobalContext.getPropertyString("web.server.port", "7070");
		String contextRoot = GlobalContext.getPropertyString("web.context.root", "");

		StringBuffer url = new StringBuffer();
		url.append("http://");

       	if("1".equals(StartCodi.USE_MULTITENANCY))
       		url.append((tenantId==null?"":tenantId+"."));

		url.append(ip);
		
		if(port.length() > 0)
			url.append(":" + port);
		if(contextRoot.length() > 0)
			url.append("/" + contextRoot);
		
		return url.toString();
	}
	
	public static TenantContext getThreadLocalInstance(){
		TenantContext tc = local.get();
		
		if(tc != null)
			return tc;
		else
			return new TenantContext(null);
	}

	
}