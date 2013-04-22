package org.uengine.codi.deltacloud;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.uengine.codi.deltacloud.dto.RealmsDDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RealmsSF {
	public static ArrayList<RealmsDDTO> realmsXmlParser(String url) throws Exception{
		
		// 리턴객체
		ArrayList<RealmsDDTO> list = new ArrayList<RealmsDDTO>();
		
		try {
			
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(url + "/realms");
			doc.getDocumentElement().normalize();

			// realm 엘리먼트 리스트
			NodeList realmlist = doc.getElementsByTagName("realm");

			for (int i = 0; i < realmlist.getLength(); i++) {
				RealmsDDTO realmsDdto = new RealmsDDTO();
				Node realmNode = realmlist.item(i);

				if (realmNode.getNodeType() == Node.ELEMENT_NODE) {
					// realm 엘리먼트
					Element realmElmnt = (Element) realmNode;
					try{
						realmsDdto.setId(realmElmnt.getAttributeNode("id").toString());
					}catch(NullPointerException e){
						realmsDdto.setId("");
					}
					
					// name 태그
					NodeList nameList = realmElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					try{
						realmsDdto.setName(name.getNodeValue());
					}catch(NullPointerException e){
						realmsDdto.setName("");
					}
					
					// state 태그
					NodeList stateList = realmElmnt.getElementsByTagName("state");
					Element stateElmnt = (Element) stateList.item(0);
					Node state = stateElmnt.getFirstChild();
					try{
						realmsDdto.setState(state.getNodeValue());
					}catch(NullPointerException e){
						realmsDdto.setState("");
					}
					
					// limit 태그
					NodeList limitList = realmElmnt.getElementsByTagName("limit");
					Element limitElmnt = (Element) limitList.item(0);
					Node limit = limitElmnt.getFirstChild();
					try{
						realmsDdto.setLimit(limit.getNodeValue());
					}catch(NullPointerException e){
						realmsDdto.setLimit("");
					}
					
					list.add(realmsDdto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
