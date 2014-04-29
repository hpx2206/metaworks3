package org.uengine.codi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

//import org.oce.webservices.sso.CasServerService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class CodiHttpClient {
	
	private static Logger log = Logger.getLogger(CodiHttpClient.class);
	
	public static String call(String methodType, String url)  throws Exception {
		StringBuffer sb = new StringBuffer();
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("Attempting to access " + url);
            }
            final URL sendUrl = new URL(url);
//            final String output = "logoutRequest=" + URLEncoder.encode(message, "UTF-8");

            connection = (HttpURLConnection) sendUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(methodType);
            connection.setRequestProperty("Accept", "application/json");
//            connection.setReadTimeout(this.readTimeout);
//            connection.setConnectTimeout(this.connectionTimeout);
//            connection.setInstanceFollowRedirects(this.followRedirects);

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line; 
			while ((line = in.readLine()) != null) {
				sb.append(line);			
			}
            
            if (log.isDebugEnabled()) {
                log.debug("Response message " + sb.toString());
            }
            
            return sb.toString();
        } catch (final SocketTimeoutException e) {
            log.warn("Socket Timeout Detected while attempting to send message to [" + url + "].");
            return null;
        } catch (final Exception e) {
            log.warn("Error Sending message to url endpoint [" + url + "].  Error is [" + e.getMessage() + "]");
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (final IOException e) {
                    // can't do anything
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
