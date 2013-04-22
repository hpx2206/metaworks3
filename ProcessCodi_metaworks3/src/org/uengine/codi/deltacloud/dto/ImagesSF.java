package org.uengine.codi.deltacloud.dto;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImagesSF {
	public static ArrayList<ImagesDDTO> imagesXmlParser(String url) throws Exception{
		
		// 리턴객체
		ArrayList<ImagesDDTO> list = new ArrayList<ImagesDDTO>();
		
		try {
			
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(url + "/images");
			doc.getDocumentElement().normalize();

			// Images 엘리먼트 리스트
			NodeList imagelist = doc.getElementsByTagName("image");

			for (int i = 0; i < imagelist.getLength(); i++) {
				ImagesDDTO imagesDDTO = new ImagesDDTO();
				Node imageNode = imagelist.item(i);

				if (imageNode.getNodeType() == Node.ELEMENT_NODE) {
					// image 엘리먼트
					Element imageElmnt = (Element) imageNode;
					try{
						imagesDDTO.setId(imageElmnt.getAttributeNode("id").toString());
					}catch(NullPointerException e){
						imagesDDTO.setId("");
					}
					
					// name 태그
					NodeList nameList = imageElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					try{
						imagesDDTO.setName(name.getNodeValue());
					}catch(NullPointerException e){
						imagesDDTO.setName("");
					}
					
					// state 태그
					NodeList stateList = imageElmnt.getElementsByTagName("description");
					Element stateElmnt = (Element) stateList.item(0);
					Node state = stateElmnt.getFirstChild();
					try{
						imagesDDTO.setDescription(state.getNodeValue());
					}catch(NullPointerException e){
						imagesDDTO.setDescription("");
					}
					
					// limit 태그
					NodeList limitList = imageElmnt.getElementsByTagName("architecture");
					Element limitElmnt = (Element) limitList.item(0);
					Node limit = limitElmnt.getFirstChild();
					try{
						imagesDDTO.setArchitecture(limit.getNodeValue());
					}catch(NullPointerException e){
						imagesDDTO.setArchitecture("");
					}
					
					list.add(imagesDDTO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
