package org.uengine.sso;

import java.net.URLEncoder;
import java.util.HashMap;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.metaworks.dao.TransactionContext;
import org.springframework.web.util.CookieGenerator;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.util.CodiHttpClient;
import org.uengine.kernel.GlobalContext;


public class CasAuthenticate implements BaseAuthenticate {

	//	private final static String CAS_COOKIENAME = "CASTGC";
	public final static String CAS_RESTURL = GlobalContext.getPropertyString("cas.rest.url", "http://localhost:8080/cas/v1/tickets");
	public final static String CAS_OAUTHURL = GlobalContext.getPropertyString("cas.oauth.url", "http://127.0.0.1:8080/cas/oauth2.0");

	@Override
	public String authorize(HashMap<String, String> args) {
		try
		{
			String ssoService = args.get("ssoService");
			String username = args.get("username");
			String password = args.get("password");
			String strTGT   = args.get("tgt");

			CodiHttpClient codiHc = new CodiHttpClient();
			if(strTGT == null){
				if(ssoService == null || username == null || password == null)
					throw new Exception("parameter invalid. requirements ssoService, username, password ");

				// tgt 티켓 발행 
				HashMap<String, String> params = new HashMap<String,String>();
				params.put("username", username);
				params.put("password", password);
				HashMap mapResult = codiHc.sendMessageToEndPoint(CAS_RESTURL, params, "POST");
				if(mapResult != null){
					String strTgt = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
					strTgt = strTgt.substring( strTgt.lastIndexOf("/") +1);
					
					// 쿠키저장
					CookieGenerator cookieGenerator = new CookieGenerator();
					cookieGenerator.setCookieSecure(false);
					cookieGenerator.setCookieMaxAge(7889231);
					cookieGenerator.setCookieName("CASTGC");
					cookieGenerator.addCookie(TransactionContext.getThreadLocalInstance().getResponse(), strTgt);
					return strTgt;
				}
			}



			//			if(tgt == null){
			//				if(ssoService == null || username == null || password == null)
			//					throw new Exception("parameter invalid. requirements ssoService, username, password ");
			//				
			//				// tgt 티켓 발행 
			//				HashMap<String, String> params = new HashMap<String,String>();
			//				params.put("username", username);
			//				params.put("password", password);
			//				HashMap mapResult = codiHc.sendMessageToEndPoint(CAS_RESTURL, params, "POST");
			//				if(mapResult != null){
			//					String strTgt = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
			//					strTgt = strTgt.substring( strTgt.lastIndexOf("/") +1);
			//					System.out.println("Tgt is : " + strTgt);
			//					
			//					// st 티켓 발행 
			//					params = new HashMap<String,String>();
			//					params.put("service", ssoService);
			//					String myURL = CAS_RESTURL + "/"+ strTgt ;
			//					mapResult = codiHc.sendMessageToEndPoint(myURL, params, "POST");
			//					String strSt = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
			//					
			//					// 쿠키저장
			////					CookieGenerator cookieGenerator = new CookieGenerator();
			////					cookieGenerator.setCookieSecure(false);
			////					cookieGenerator.setCookieMaxAge(7889231);
			////					cookieGenerator.setCookieName(CAS_COOKIENAME);
			////					cookieGenerator.addCookie(TransactionContext.getThreadLocalInstance().getResponse(), strTgt);
			//					
			//					return strSt;
			//				}else{
			//					return null;
			//				}
			//				
			//			}else{
			//				String strTgt = tgt;
			//				if(ssoService == null )
			//					throw new Exception("parameter invalid. requirements ssoService ");
			//				
			//				// st 티켓 발행 
			//				HashMap<String,String> params = new HashMap<String,String>();
			//				params.put("service", ssoService);
			//				String myURL = CAS_RESTURL + "/"+ strTgt ;
			//				HashMap mapResult = codiHc.sendMessageToEndPoint(myURL, params, "POST");
			//				String strSt = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
			//				
			//				return strSt;
			//			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return null;
	}

	public String serviceTicket(String strTgt, String ssoService) {
		try
		{
			CodiHttpClient codiHc = new CodiHttpClient();

			HashMap<String,String> params = new HashMap<String,String>();
			params.put("service", ssoService);
			String myURL = CAS_RESTURL + "/"+ strTgt ;
			HashMap mapResult = codiHc.sendMessageToEndPoint(myURL, params, "POST");
			if(mapResult != null){
				String strSt = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
				return strSt;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return null;
	}


	@Override
	public String accesstoken(HashMap<String, String> args) {

		try{
			String redirect_uri = args.get("redirect_uri");
			String client_id = args.get("client_id");
			String client_secret = args.get("client_secret");
			String code = args.get("code");

			if(redirect_uri == null || client_id == null || client_secret == null || code == null)
				throw new Exception("parameter invalid. requirements redirect_uri, client_id, client_secret, code ");

			CodiHttpClient codiHc = new CodiHttpClient();

			StringBuffer sb = new StringBuffer();
			sb.append("redirect_uri=");
			sb.append(URLEncoder.encode(redirect_uri, "UTF-8"));
			sb.append("&client_id=");
			sb.append(URLEncoder.encode(client_id, "UTF-8"));
			sb.append("&client_secret=");
			sb.append(URLEncoder.encode(client_secret, "UTF-8"));
			sb.append("&code=");
			sb.append(URLEncoder.encode(code, "UTF-8"));

			String sendurl = CAS_OAUTHURL +  "/accessToken?" + sb.toString();
			HashMap mapResult = codiHc.sendMessageToEndPoint(sendurl, null, "GET");
			if(mapResult != null){
				String result = (String)mapResult.get(CodiHttpClient.RESULT_KEY);
				String[] ret  = result.split("&");
				String[] accessToken = ret[0].toString().split("=");
				return accessToken[1].toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean vaild() {
		String tgt = (String)TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("SSO-TGT");
		boolean result = tgt != null ? true : false;
		return result;
	}

	@Override
	public String profile(String access_token) {
		String sendurl = CAS_OAUTHURL + "/profile?access_token=" + access_token;
		CodiHttpClient codiHc = new CodiHttpClient();
		HashMap mapResult = codiHc.sendMessageToEndPoint(sendurl, null, "GET");
		if(mapResult != null){
			return (String)mapResult.get(CodiHttpClient.RESULT_KEY);
		}
		return null;
	}

	@Override
	public boolean destorytoken(String access_token) throws Exception {
		if(access_token != null){
			CodiHttpClient codiHc = new CodiHttpClient();
			String urls = CAS_RESTURL + "/" + access_token;
			HashMap mapResult = codiHc.sendMessageToEndPoint(urls, null, "DELETE");
			if(mapResult == null)
				return false;
			else
				return true;
		}else{
			return false;
		}
	}

}
