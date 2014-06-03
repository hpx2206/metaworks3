package org.uengine.cloud.saasfier;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.util.CodiStringUtil;
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

		/*HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
		String url = request.getRequestURL().toString();
		String codebase = url.substring( 0, url.lastIndexOf( "/" ) );
		URL urlURL = new java.net.URL(codebase);
		String host = urlURL.getHost();
		int port = urlURL.getPort();
		
		String path = urlURL.getPath();
		String contextOnly = path.substring(0, path.substring(1).indexOf("/")+1);
		String protocol = urlURL.getProtocol();

		StringBuffer serverPath = new StringBuffer();
		
		serverPath.append(protocol+"://");
		
		
		if(host.length()>0)
			serverPath.append(host);
		
		if (port>-1)
			serverPath.append(":"+urlURL.getPort());
		
		if(contextOnly.length()>0 && !contextOnly.equals("/dwr")) 
			serverPath.append(contextOnly);
		
		System.out.println(CodiStringUtil.lastLastFileSeparatorChar(serverPath.toString()));*/
		String serverPath = GlobalContext.getPropertyString("domain","http://192.168.50.3:8080/uengine-web/");		
		
		return CodiStringUtil.lastLastFileSeparatorChar(serverPath.toString());
	}
	
	public static TenantContext getThreadLocalInstance(){
		TenantContext tc = local.get();
		
		if(tc != null)
			return tc;
		else
			return new TenantContext(null);
	}

	
}