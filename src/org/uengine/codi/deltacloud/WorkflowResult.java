package org.uengine.codi.deltacloud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.uengine.kernel.GlobalContext;

public class WorkflowResult {

	String url;
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	
	String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	Instance instance;
		public Instance getInstance() {
			return instance;
		}
		public void setInstance(Instance instance) {
			this.instance = instance;
		}
	
	
	public WorkflowResult(){
		this(null);		
	}
	
	public WorkflowResult(String url){
		this.url = url;
	}
	
	public static WorkflowResult parser(Element wfResultEl){

		WorkflowResult wfResult = new WorkflowResult();
		wfResult.setId(wfResultEl.getAttributeValue("id"));
		
		List<Element> childElList = wfResultEl.getChildren();
		for(Element el : childElList){			
			if("state".equals(el.getName())){
				wfResult.setStatus(el.getValue());
			}else if("type".equals(el.getName())){
				wfResult.setType(el.getValue());
			}else if("messase".equals(el.getName())){
				wfResult.setMessage(el.getValue());
			}else if("instance".equals(el.getName())){
				wfResult.setInstance(Instance.parser(el));
			}
		}
		
		return wfResult;
	}
	
	public WorkflowResult requestStatus() throws Exception{
		HttpURLConnection conn = null;
		WorkflowResult wfResult = null;
		
		try {
			URL connectURI = new URL(url + "/workflow/" + this.getId());

			conn = (HttpURLConnection) connectURI.openConnection();			
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.connect();			

			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
				throw new RuntimeException("Faild / HTTP error code : "+conn.getResponseCode());
			}else{
				try {
					SAXBuilder saxBuilder = new SAXBuilder();
					Document outputDocument = saxBuilder.build(conn.getInputStream());
					Element rootElement = outputDocument.getRootElement();
					
					wfResult = WorkflowResult.parser(rootElement);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) try { conn.disconnect(); } catch (Exception e) { }
		}
		
		return wfResult;
	}
	
	public static void main(String[] args){

		WorkflowResult wfResult = new WorkflowResult("http://192.168.50.14/deltacloud/api");
		wfResult.setId("VM00000118");

		try {
			WorkflowResult result = wfResult.requestStatus();
			System.out.println("status : " + result.getStatus());
			if("CP".equals(result.getStatus())){
				System.out.println(result.getInstance().getPublicAddress());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
