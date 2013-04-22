package org.uengine.codi.deltacloud;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.uengine.codi.deltacloud.dto.HardwareProfilesCDTO;
import org.uengine.codi.deltacloud.dto.HardwareProfilesDDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HardwareProfilesSF {
	public static ArrayList<HardwareProfilesDDTO> hardwareProfilesXmlParser(String url) throws Exception{
		
		// 리턴객체
		ArrayList<HardwareProfilesDDTO> list = new ArrayList<HardwareProfilesDDTO>();
		
		try {
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(url + "/hardware_profiles");
			doc.getDocumentElement().normalize();

			// hardwareProfile 엘리먼트 리스트
			NodeList hardwareProfilelist = doc.getElementsByTagName("hardware_profile");

			for (int i = 0; i < hardwareProfilelist.getLength(); i++) {
				HardwareProfilesDDTO hardwareProfilesDdto = new HardwareProfilesDDTO();
				Node hardwareProfileNode = hardwareProfilelist.item(i);

				if (hardwareProfileNode.getNodeType() == Node.ELEMENT_NODE) {
					// hardwareProfile 엘리먼트
					Element hardwareProfileElmnt = (Element) hardwareProfileNode;
					try{
						hardwareProfilesDdto.setId(hardwareProfileElmnt.getAttributeNode("id").toString());
					}catch(NullPointerException e){
						hardwareProfilesDdto.setId("");
					}
					
					// name 태그
					NodeList nameList = hardwareProfileElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					try{
						hardwareProfilesDdto.setName(name.getNodeValue());
					}catch(NullPointerException e){
						hardwareProfilesDdto.setName("");
					}
					
					// property 태그
					NodeList nodeList = hardwareProfileElmnt.getElementsByTagName("property");
			        for(int x=0,size= nodeList.getLength(); x<size; x++) {
			        	HardwareProfilesCDTO hardwareProfilesCdtos[] = new HardwareProfilesCDTO[size];
			            for(int j=0;j<size;j++){
				            hardwareProfilesCdtos[j] = new HardwareProfilesCDTO();
				            try{
				            	hardwareProfilesCdtos[j].setValue(nodeList.item(j).getAttributes().getNamedItem("value").getNodeValue());
				            }catch(NullPointerException e){
								hardwareProfilesCdtos[j].setValue("");
							}
				            try{
				            
				            	hardwareProfilesCdtos[j].setUnit(nodeList.item(j).getAttributes().getNamedItem("unit").getNodeValue());
				            }catch(NullPointerException e){
								hardwareProfilesCdtos[j].setUnit("");
							}
				            try{
				            	hardwareProfilesCdtos[j].setKind(nodeList.item(j).getAttributes().getNamedItem("kind").getNodeValue());
				            }catch(NullPointerException e){
								hardwareProfilesCdtos[j].setKind("");
							}
				            try{
				            	hardwareProfilesCdtos[j].setName(nodeList.item(j).getAttributes().getNamedItem("name").getNodeValue());
				            }catch(NullPointerException e){
								hardwareProfilesCdtos[j].setName("");
							}
			            }
			            hardwareProfilesDdto.setHardwareProfilesCDTOs(hardwareProfilesCdtos);
			        }
			        
			        list.add(hardwareProfilesDdto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
