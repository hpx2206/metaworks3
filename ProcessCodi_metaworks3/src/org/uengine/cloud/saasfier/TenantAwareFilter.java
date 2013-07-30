package org.uengine.cloud.saasfier;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.uengine.kernel.GlobalContext;

public class TenantAwareFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if("1".equals(GlobalContext.getPropertyString("multitenancy.use", "1"))){
			//request 의 url 의 동적 변경...가능할런지..		
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String pathInfo = httpServletRequest.getPathInfo();
			
			if(pathInfo!=null){
				int tenantIdPos = pathInfo.indexOf("TID:");
				if(tenantIdPos > 0){
					String tenantId = pathInfo.substring(tenantIdPos + 5);
					tenantId = tenantId.substring(tenantId.indexOf("/"));
					RequestDispatcher dispatcher = request.getRequestDispatcher("");
			        dispatcher.forward(request, response);
				}
			}
			//
			String serverName = httpServletRequest.getServerName();
			if(serverName!=null){
				int tenantIdPos = serverName.indexOf(".");
				if(tenantIdPos > 0){
					String tenantId = serverName.substring(0, tenantIdPos);				
					new TenantContext(tenantId); //create unique tenant context for the requested thread.
				}else{
					new TenantContext(null);
				}
			}			
		}else{
			new TenantContext(null);
		}
		
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
							
//		String className = "com.mysql.jdbc.NonRegisteringDriver";
//		
//		
//		String sep = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0 ? ";" : ":";
//
//		ClassPool pool = ClassPool.getDefault();
//		
//		URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
//		URL urls[] = classLoader.getURLs();
//		StringBuffer sbClasspath = new StringBuffer();
//		for(URL url : urls){
//			String urlStr = url.getFile().toString();
//			sbClasspath.append(urlStr).append(sep);
//			try {
//				pool.appendClassPath(urlStr);
//			} catch (NotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//
//
//		CtClass cc;
//		try {
//			cc = pool.get(className);
//			CtMethod m = cc.getDeclaredMethod("connect");
//			m.insertBefore("{ System.out.println($1); System.out.println($2); $1=org.uengine.cloud.saasfier.JDBCDriverHack.changeConnectionString($1);}");
//			cc.writeFile();
//
//	    	HotSwapper hs = new HotSwapper(8000);
//	    	
//	    	Class.forName(className);
//
//	    	hs.reload(className, cc.toBytecode());
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	
	
	
	
}