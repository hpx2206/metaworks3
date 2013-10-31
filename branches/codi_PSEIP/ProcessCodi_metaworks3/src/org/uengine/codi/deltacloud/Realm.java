package org.uengine.codi.deltacloud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.uengine.kernel.GlobalContext;
public class Realm {
	
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
			
	String state;
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	String limit;
		public String getLimit() {
			return limit;
		}
		public void setLimit(String limit) {
			this.limit = limit;
		}
		
	public Realm(){
		this(null);
	}
	
	public Realm(String url){
		this.url = url;
	}

	public static Realm parser(Element realmEl){
		Realm realm = new Realm();
		
		realm.setId(realmEl.getAttributeValue("id"));
		
		List<Element> childElList = realmEl.getChildren();
		for(Element el : childElList){
			if("name".equals(el.getName()))
				realm.setName(el.getValue());
			else if("state".equals(el.getName()))
				realm.setState(el.getValue());
			else if("limit".equals(el.getName()))
				realm.setLimit(el.getValue());
			else
				System.out.println(el.getName() + ":" + el.getValue());
		}
		return realm;
	}
	
	public ArrayList<Realm> list() throws Exception{
		HttpURLConnection conn = null;
		ArrayList<Realm> list = new ArrayList<Realm>();
		
		try {
			URL connectURI = new URL(url + "/realms");

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
						Realm realm = Realm.parser(el);
						
						list.add(realm);
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
	
	public static void main(String args[]){
		Realm realm = new Realm("http://192.168.212.52/deltacloud/api");
		
		try {
			ArrayList<Realm> list =  realm.list();
			
			for(int i=0;i<list.size();i++){
				System.out.println("id : "+list.get(i).getId());
				System.out.println("name : "+list.get(i).getName());
				System.out.println("state : "+list.get(i).getState());
				System.out.println("limit : "+list.get(i).getLimit());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
