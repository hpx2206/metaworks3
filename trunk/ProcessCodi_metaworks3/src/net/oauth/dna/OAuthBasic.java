package net.oauth.dna;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class OAuthBasic {
	
	static final String REQUEST_TOKEN_URL = "http://localhost:8080/uengine-web/request_token";
	static final String AUTHORIZE_URL = "http://localhost:8080/uengine-web/authorize";
	static final String ACCESS_TOKEN_URL = "http://localhost:8080/uengine-web/access_token";
	 
	static final String CONSUMER_KEY = "noCallbackConsumer";
	static final String CONSUMER_SECRET = "noCallbackSecret";
	 
	static final String API_URL = "http://localhost:8080/uengine-web/echo";
	 
	// Service Provider
	static OAuthProvider provider = new DefaultOAuthProvider(REQUEST_TOKEN_URL, ACCESS_TOKEN_URL, AUTHORIZE_URL);  

	// Consumer
	static OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	
	public void requestToken() throws Exception {
		
		String authUrl = provider.retrieveRequestToken(consumer, "http://localhost:8080/uengine-web/oauth/accessToken.jsp");	
		
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(authUrl);
		httpClient.executeMethod(getMethod);
		getMethod.releaseConnection();
		
//		System.out.println("아래 URL로 가서 사용자 인증을 하시면 인증코드(verifier)를 얻을 수 있습니다.");
//		System.out.println(authUrl);
	}
	
	public String retrieveAccessToken(String oauthVerifier) throws Exception {
		provider.retrieveAccessToken(consumer, oauthVerifier); //user id 들어갈것
		
		return consumer.getToken();
	}

}
