package org.uengine.codi.deltacloud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class HardwareProfile {
	
	String url;
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String cpu;
		public String getCpu() {
			return cpu;
		}
		public void setCpu(String cpu) {
			this.cpu = cpu;
		}
	
	String memory;
		public String getMemory() {
			return memory;
		}
		public void setMemory(String memory) {
			this.memory = memory;
		}
	
	String architecture;
		public String getArchitecture() {
			return architecture;
		}
		public void setArchitecture(String architecture) {
			this.architecture = architecture;
		}
	
	String storage;
		public String getStorage() {
			return storage;
		}
		public void setStorage(String storage) {
			this.storage = storage;
		}

	public HardwareProfile(){
		this(null);
	}
	
	public HardwareProfile(String url){
		this.url = url;
	}
	
	public static HardwareProfile parser(Element hardwareProfileEl){
		HardwareProfile hardwareProfile = new HardwareProfile();
		
		hardwareProfile.setId(hardwareProfileEl.getAttributeValue("id"));
		
		List<Element> childElList = hardwareProfileEl.getChildren();
		for(Element el : childElList){
			if("name".equals(el.getName()))
				hardwareProfile.setName(el.getValue());
			else if("property".equals(el.getName())){
				if("cpu".equals(el.getAttributeValue("name")))					
					hardwareProfile.setCpu(el.getAttributeValue("value"));
				else if("memory".equals(el.getAttributeValue("name")))					
					hardwareProfile.setMemory(el.getAttributeValue("value"));
				else if("storage".equals(el.getAttributeValue("name")))					
					hardwareProfile.setStorage(el.getAttributeValue("value"));
				else if("architecture".equals(el.getAttributeValue("name")))					
					hardwareProfile.setArchitecture(el.getAttributeValue("value"));
				else
					System.out.println(el.getAttributeValue("name") + ":" + el.getAttributeValue("value"));
			}else
				System.out.println(el.getName() + ":" + el.getValue());
		}
		
		return hardwareProfile;
		
	}
	
	public ArrayList<HardwareProfile> list() throws Exception{
		
		HttpURLConnection conn = null;
		ArrayList<HardwareProfile> list = new ArrayList<HardwareProfile>();
		
		try {
			URL connectURI = new URL(this.url + "/hardware_profiles");

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
					
					List<Element> childElList = rootElement.getChildren();
					for(Element el : childElList){
						HardwareProfile hardwareProfile = HardwareProfile.parser(el);
						
						list.add(hardwareProfile);
					}
					//
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) try { conn.disconnect(); } catch (Exception e) { }
		}
		return list;
	}
	
	public static void main(String[] args){
		try {
			HardwareProfile hardwareProfile = new HardwareProfile("http://192.168.50.14/deltacloud/api");
			ArrayList<HardwareProfile> list = hardwareProfile.list();
			
			for(int i=0;i<list.size();i++){
				System.out.println("id : "+list.get(i).getId());
				System.out.println("name : "+list.get(i).getName());
				System.out.println("cpu : "+list.get(i).getCpu());
				System.out.println("memory : "+list.get(i).getMemory());
				System.out.println("storage : "+list.get(i).getStorage());
				System.out.println("name : "+list.get(i).getName());
				System.out.println("architecture : "+list.get(i).getArchitecture());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
