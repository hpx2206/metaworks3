package org.uengine.webservice;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.uengine.sso.BaseAuthenticate;
import org.uengine.sso.CasAuthenticate;

@Path("sso")
public class JaxRsSSOWebService {

	@GET
	@Path("authorize")
	@Produces("application/json")
	public String _authorize(@Context HttpServletRequest req 
			, @Context HttpServletResponse res) throws IOException {
		
		String ssoService = req.getParameter("ssoService");
		String strTGT = (String)req.getSession().getAttribute("SSO-TGT");
				
		if(strTGT != null){
			BaseAuthenticate ssoAuth = new CasAuthenticate();
			String ticket = ssoAuth.serviceTicket(strTGT, ssoService);
			
			res.sendRedirect(ssoService+"?ticket=" + ticket);
		}else{
			try {
				ssoService = URLEncoder.encode(ssoService, "UTF-8");
				res.sendRedirect("/uengine-web/?type=SSOLogin&amp;service=" + ssoService);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	
	@GET
	@Path("accessToken")
	@Produces("application/json")
	public String _accessToken(@Context HttpServletRequest req 
			, @Context HttpServletResponse res) throws IOException {
		
		String redirect_uri = req.getParameter("redirect_uri");
		String client_id = req.getParameter("client_id");
		String client_secret = req.getParameter("client_secret");
		String code = req.getParameter("code");
		
		HashMap<String, String> params = new HashMap<String, String>(); 
		params.put("redirect_uri", redirect_uri);
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);
		params.put("code", code);
		
		BaseAuthenticate ssoAuth = new CasAuthenticate();
		return ssoAuth.accesstoken(params);
	}
	
	@GET
	@Path("/userInfo")
	@Produces("application/json")
	public String profile(@Context HttpServletRequest req 
			, @Context HttpServletResponse res
			, @PathParam("access_token") String accessToken) {
		String strTGT = (String)req.getSession().getAttribute("SSO-TGT");
		BaseAuthenticate ssoAuth = new CasAuthenticate();
		return ssoAuth.profile(strTGT);
	}
	
	@GET
	@Path("/destorytoken")
	@Produces("application/json")
	public String destorytoken(@Context HttpServletRequest req 
			, @Context HttpServletResponse res
			, @PathParam("access_token") String accessToken) throws Exception {
		String strTGT = (String)req.getSession().getAttribute("SSO-TGT");
		if(strTGT != null){
			BaseAuthenticate ssoAuth = new CasAuthenticate();
			ssoAuth.destorytoken(strTGT);
		}
		return "";
	}
	
}
