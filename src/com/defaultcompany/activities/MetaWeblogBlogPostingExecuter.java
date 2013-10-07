package com.defaultcompany.activities;

import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

//import org.apache.xmlrpc.client.XmlRpcClient;
//import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.uengine.kernel.ProcessInstance;
import org.uengine.util.UEngineUtil;

public class MetaWeblogBlogPostingExecuter {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void executeActivity(ProcessInstance instance, MetaWeblogBlogPostingActivity activity) throws Exception {
		MetaWeblogAuth auth = activity.getAuth();
		MetaWeblogPost post = activity.getPost();
		String categories = activity.getCategories();
		
		try {
			
			/*XmlRpcClient client = new XmlRpcClient();
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(auth.getApiUrl()));
			client.setConfig(config);

			Vector params = new Vector();
			params.addElement(new String(auth.getBlogId()));
			params.addElement(new String(auth.getUserName()));
			params.addElement(new String(auth.getPassword()));

			Hashtable hashtable = new Hashtable();
			hashtable.put("title", activity.evaluateContent(instance, post.getTitle()).toString());
			hashtable.put("dateCreated", new Date());
			hashtable.put("description", activity.evaluateContent(instance, post.getDescription()).toString());
			
			String tags = activity.evaluateContent(instance, post.getTags()).toString();
			if (UEngineUtil.isNotEmpty(tags)) {
				hashtable.put("tags", tags); //쉼표로 태그 구분 된다.	
			}
			
			hashtable.put("categories", new String[] { categories });

			params.addElement(hashtable);
			params.add(new Boolean(true)); // true몇 공개 false면 비공개
			
			Object postId = client.execute(config, "metaWeblog.newPost", params);*/
			
		} catch (Exception e) {
			throw e;
		}
	}
}
