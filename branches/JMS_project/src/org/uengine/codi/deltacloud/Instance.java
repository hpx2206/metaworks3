package org.uengine.codi.deltacloud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class Instance {

	String url;
	
	String workflowId;
		public String getWorkflowId() {
			return workflowId;
		}
		public void setWorkflowId(String workflowId) {
			this.workflowId = workflowId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String publicAddress;
		public String getPublicAddress() {
			return publicAddress;
		}
		public void setPublicAddress(String publicAddress) {
			this.publicAddress = publicAddress;
		}

	String imageId;
		public String getImageId() {
			return imageId;
		}
		public void setImageId(String imageId) {
			this.imageId = imageId;
		}
	
	String hardwareProfileId;
		public String getHardwareProfileId() {
			return hardwareProfileId;
		}
		public void setHardwareProfileId(String hardwareProfileId) {
			this.hardwareProfileId = hardwareProfileId;
		}
	
	String realmId;
		public String getRealmId() {
			return realmId;
		}
		public void setRealmId(String realmId) {
			this.realmId = realmId;
		}
		
	public Instance(){
		
	}
	
	public Instance(String url){
		this.url = url;
	}
	
	public static Instance parser(Element instanceEl){
		
		Instance instance = new Instance();
		instance.setWorkflowId(instanceEl.getAttributeValue("id"));
		
		List<Element> childElList = instanceEl.getChildren();
		for(Element el : childElList){			
			if("name".equals(el.getName())){
				instance.setName(el.getValue());
			}else if("public_addresses".equals(el.getName())){
				instance.setPublicAddress(el.getValue());
			}else{
				//System.out.println(el.getName() + ":" + el.getValue());
			}
		}
		
		return instance;
	}
		
	public Instance create() throws Exception{
		
		HttpURLConnection conn = null;
		Instance instance = null;
		
		try {
			URL connectURI = new URL(this.url + "/instances");

			Element element = new Element("instance");
			
			element.addContent(new Element("name").addContent(this.getName()));
			element.addContent(new Element("image").setAttribute("id", this.getImageId()));
			element.addContent(new Element("realm").setAttribute("id", this.getRealmId()));
			element.addContent(new Element("hardware_profile").setAttribute("id", this.getHardwareProfileId()));

			Document document = new Document(element);
			
			conn = (HttpURLConnection) connectURI.openConnection();			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.connect();			

			XMLOutputter xmlOutputter = new XMLOutputter();
			xmlOutputter.getFormat().setEncoding("utf-8");
			xmlOutputter.output(document, conn.getOutputStream());
					
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
				throw new RuntimeException("Faild / HTTP error code : "+conn.getResponseCode());
			}else{
				
				try {
					SAXBuilder saxBuilder = new SAXBuilder();
					Document outputDocument = saxBuilder.build(conn.getInputStream());
					Element rootElement = outputDocument.getRootElement();
					
					instance = Instance.parser(rootElement);
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
		
		return instance;
	}
	
	public static void main(String args[]){
		try {
			String realmId = "10044";
			String imageId = "130";
			String hardwareProfileId = "1";
			
			org.uengine.codi.deltacloud.Instance dcInstance = new org.uengine.codi.deltacloud.Instance("http://192.168.50.14/deltacloud/api");
			dcInstance.setRealmId(realmId);
			dcInstance.setImageId(imageId);
			dcInstance.setHardwareProfileId(hardwareProfileId);
			
			org.uengine.codi.deltacloud.Instance dcInstanceResult = dcInstance.create();
			System.out.println("workflowId : " + dcInstanceResult.getWorkflowId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

