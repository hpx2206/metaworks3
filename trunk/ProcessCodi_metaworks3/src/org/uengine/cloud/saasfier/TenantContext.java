package org.uengine.cloud.saasfier;

import java.net.MalformedURLException;
import java.net.URL;

import org.metaworks.dao.TransactionContext;
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
	
	public static String getURL(String tenantId) throws MalformedURLException {

		String requestedURL = TransactionContext.getThreadLocalInstance().getRequest().getRequestURL().toString(); 
        String base = requestedURL.substring( 0, requestedURL.lastIndexOf( "/" ) );
        
        URL urlURL = new java.net.URL(base);
       	String host = urlURL.getHost();
       	int port = urlURL.getPort();
       	String protocol = urlURL.getProtocol();

       	StringBuffer url = new StringBuffer();
       	
       	url.append(protocol + "://");
       	if("1".equals(GlobalContext.getPropertyString("multitenancy.use", "0"))){
       		url.append((tenantId==null?"":tenantId+"."));
       	}
       	
       	url.append(host);
		url.append((port == 80 ? "" : ":"+port));
		url.append(TransactionContext.getThreadLocalInstance().getRequest().getContextPath());
		
		return url.toString();
	}
	
	public static TenantContext getThreadLocalInstance(){
		TenantContext tc = local.get();
		
		return tc;
	}

	
}