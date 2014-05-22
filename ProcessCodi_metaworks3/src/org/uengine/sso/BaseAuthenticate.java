package org.uengine.sso;

import java.util.HashMap;

import net.sf.json.JSONObject;

public interface BaseAuthenticate {
	public String authorize(HashMap<String, String> params);
	public String serviceTicket(String strTgt, String ssoService);
	public String accesstoken(HashMap<String, String> args);
	public boolean vaild();
	public String profile(String access_token);
	public boolean destorytoken(String access_token) throws Exception;
}
