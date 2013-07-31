package org.uengine.codi.deltacloud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class Image {
	
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
		
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

	String architecture;
		public String getArchitecture() {
			return architecture;
		}
		public void setArchitecture(String architecture) {
			this.architecture = architecture;
		}
	
	public Image(){
		this(null);
	}
	
	public Image(String url){
		this.url = url;
	}
	
	public static Image parser(Element imageEl){
		
		Image image = new Image();
		image.setId(imageEl.getAttributeValue("id"));
		
		List<Element> childElList = imageEl.getChildren();
		for(Element el : childElList){
			if("name".equals(el.getName()))
				image.setName(el.getValue());
			else if("architecture".equals(el.getName()))
				image.setArchitecture(el.getValue());
			else if("description".equals(el.getName()))
				image.setDescription(el.getValue());
			else
				System.out.println(el.getName() + ":" + el.getValue());
		}
		
		return image;
	}
	
	public ArrayList<Image> list() throws Exception{
		
		HttpURLConnection conn = null;
		ArrayList<Image> list = new ArrayList<Image>();
		
		try {
			URL connectURI = new URL(url + "/images");
			
			conn = (HttpURLConnection) connectURI.openConnection();			
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setRequestProperty("Content-Type", "application/xml");
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
						Image image = Image.parser(el);
						
						list.add(image);
					}
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
			Image image = new Image("http://192.168.212.52/deltacloud/api");
			ArrayList<Image> list = image.list();
			
			for(int i=0;i<list.size();i++){
				System.out.println("id : "+list.get(i).getId());
				System.out.println("name : "+list.get(i).getName());
				System.out.println("architecture : "+list.get(i).getArchitecture());
				System.out.println("description : "+list.get(i).getDescription());
				
				
				String desc[] = list.get(i).getDescription().split("_");
				System.out.println(desc[9]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
