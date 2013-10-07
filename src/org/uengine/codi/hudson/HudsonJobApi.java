package org.uengine.codi.hudson;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HudsonJobApi {
public HudsonJobDDTO hudsonJobApiXmlParser(String hudsonJobIP, String jobId) throws Exception{
		
		HudsonJobDDTO hudsonJobDDTO = new HudsonJobDDTO();
		
		String hudsonJobUrl = hudsonJobIP + "/job/" + jobId + "/api/xml";
		try {
			
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(hudsonJobUrl);
			doc.getDocumentElement().normalize();

			NodeList hudsonJobApilist = doc.getElementsByTagName("freeStyleProject");

			for (int i = 0; i < hudsonJobApilist.getLength(); i++) {
				Node hudsonJobApiNode = hudsonJobApilist.item(i);

				if (hudsonJobApiNode.getNodeType() == Node.ELEMENT_NODE) {
					
					// action �섎━癒쇳듃
					Element hudsonJobElmnt = (Element) hudsonJobApiNode;
					try{
						hudsonJobDDTO.setAction(hudsonJobElmnt.getAttributeNode("action").toString());
					}catch(NullPointerException e){
						hudsonJobDDTO.setAction("");
					}
					
					// description �섎━癒쇳듃
					NodeList descriptionList = hudsonJobElmnt.getElementsByTagName("description");
					Element descriptionElmnt = (Element) descriptionList.item(0);
					Node description = descriptionElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setDescription(description.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setDescription("");
					}
					
					// displayName �섎━癒쇳듃
					NodeList displayNameList = hudsonJobElmnt.getElementsByTagName("displayName");
					Element displayNameElmnt = (Element) displayNameList.item(0);
					Node displayName = displayNameElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setDisplayName(displayName.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setDisplayName("");
					}
					
					// name �섎━癒쇳듃
					NodeList nameList = hudsonJobElmnt.getElementsByTagName("name");
					Element nameElmnt = (Element) nameList.item(0);
					Node name = nameElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setName(name.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setName("");
					}
					
					// url �섎━癒쇳듃
					NodeList urlList = hudsonJobElmnt.getElementsByTagName("url");
					Element urlElmnt = (Element) urlList.item(0);
					Node url = urlElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setUrl(url.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setUrl("");
					}
					
					// buildable �섎━癒쇳듃
					NodeList buildableList = hudsonJobElmnt.getElementsByTagName("buildable");
					Element buildableElmnt = (Element) buildableList.item(0);
					Node buildable = buildableElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setBuildable(buildable.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setBuildable("");
					}
					
					// build �쒓렇
					// 1�곸뒪 �댁긽
					NodeList buildList = hudsonJobElmnt.getElementsByTagName("build");
					int buildSize= buildList.getLength();
					BuildDDTO BuildDdtos[] = new BuildDDTO[buildSize];
			        for(int x=0; x<buildSize; x++) {
			        	BuildDDTO tempDDTO = new BuildDDTO();
			        	BuildDdtos[x] = new BuildDDTO();
			            try{
			            	NodeList buildLists = buildList.item(x).getChildNodes();
			            	
			            	tempDDTO.setNumber(buildLists.item(0).getTextContent());
			            	tempDDTO.setUrl(buildLists.item(1).getTextContent());
			            }catch(NullPointerException e){
			            	tempDDTO.setNumber("null");
			            	tempDDTO.setUrl("null");
						}
			            BuildDdtos[x]=tempDDTO;
			        }
			        hudsonJobDDTO.setBuildDDTOs(BuildDdtos);
			        
			        //firstBuild
					// 1�곸뒪 寃쎌슦
					NodeList firstBuildList = hudsonJobElmnt.getElementsByTagName("firstBuild");
					BuildDDTO firstBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList firstBuildLists = firstBuildList.item(0).getChildNodes();
		            	
		            	firstBuildDDTO.setNumber(firstBuildLists.item(0).getTextContent());
		            	firstBuildDDTO.setUrl(firstBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	firstBuildDDTO.setNumber("null");
		            	firstBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setFirstBuildDDTO(firstBuildDDTO);
			        
					// healthReport �쒓렇

		            
		            
					// inQueue �쒓렇
					NodeList inQueueList = hudsonJobElmnt.getElementsByTagName("inQueue");
					Element inQueueElmnt = (Element) inQueueList.item(0);
					Node inQueue = inQueueElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setInQueue(inQueue.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setInQueue("");
					}
					
					// keepDependencies �쒓렇
					NodeList keepDependenciesList = hudsonJobElmnt.getElementsByTagName("keepDependencies");
					Element keepDependenciesElmnt = (Element) keepDependenciesList.item(0);
					Node keepDependencies = keepDependenciesElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setKeepDependencies(keepDependencies.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setKeepDependencies("");
					}
					
					// lastBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastBuildList = hudsonJobElmnt.getElementsByTagName("lastBuild");
					BuildDDTO lastBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastBuildLists = lastBuildList.item(0).getChildNodes();
		            	
		            	lastBuildDDTO.setNumber(lastBuildLists.item(0).getTextContent());
		            	lastBuildDDTO.setUrl(lastBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastBuildDDTO.setNumber("null");
		            	lastBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastBuildDDTO(lastBuildDDTO);
					
					// lastCompletedBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastCompletedBuildList = hudsonJobElmnt.getElementsByTagName("lastCompletedBuild");
					BuildDDTO lastCompletedBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastCompletedBuildLists = lastCompletedBuildList.item(0).getChildNodes();
		            	
		            	lastCompletedBuildDDTO.setNumber(lastCompletedBuildLists.item(0).getTextContent());
		            	lastCompletedBuildDDTO.setUrl(lastCompletedBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastCompletedBuildDDTO.setNumber("null");
		            	lastCompletedBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastCompletedBuild(lastBuildDDTO);
		            
					// lastFailedBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastFailedBuildList = hudsonJobElmnt.getElementsByTagName("lastFailedBuild");
					BuildDDTO lastFailedBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastFailedBuildLists = lastFailedBuildList.item(0).getChildNodes();
		            	
		            	lastFailedBuildDDTO.setNumber(lastFailedBuildLists.item(0).getTextContent());
		            	lastFailedBuildDDTO.setUrl(lastFailedBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastFailedBuildDDTO.setNumber("null");
		            	lastFailedBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastFailedBuild(lastFailedBuildDDTO);
		            
					// lastStableBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastStableBuildList = hudsonJobElmnt.getElementsByTagName("lastStableBuild");
					BuildDDTO lastStableBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastStableBuildLists = lastStableBuildList.item(0).getChildNodes();
		            	
		            	lastStableBuildDDTO.setNumber(lastStableBuildLists.item(0).getTextContent());
		            	lastStableBuildDDTO.setUrl(lastStableBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastStableBuildDDTO.setNumber("null");
		            	lastStableBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastStableBuild(lastStableBuildDDTO);
		            
					// lastSuccessfulBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastSuccessfulBuildList = hudsonJobElmnt.getElementsByTagName("lastSuccessfulBuild");
					BuildDDTO lastSuccessfulBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastSuccessfulBuildLists = lastSuccessfulBuildList.item(0).getChildNodes();
		            	
		            	lastSuccessfulBuildDDTO.setNumber(lastSuccessfulBuildLists.item(0).getTextContent());
		            	lastSuccessfulBuildDDTO.setUrl(lastSuccessfulBuildLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastSuccessfulBuildDDTO.setNumber("null");
		            	lastSuccessfulBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastSuccessfulBuild(lastSuccessfulBuildDDTO);
		            
					// lastUnsuccessfulBuild �쒓렇
					// 1�곸뒪 寃쎌슦
					NodeList lastUnsuccessfulBuildList = hudsonJobElmnt.getElementsByTagName("lastUnsuccessfulBuild");
					BuildDDTO lastUnsuccessfulBuildDDTO = new BuildDDTO();
		            try{
		            	NodeList lastUnsuccessfulBuildDDTOLists = lastUnsuccessfulBuildList.item(0).getChildNodes();
		            	
		            	lastUnsuccessfulBuildDDTO.setNumber(lastUnsuccessfulBuildDDTOLists.item(0).getTextContent());
		            	lastUnsuccessfulBuildDDTO.setUrl(lastUnsuccessfulBuildDDTOLists.item(1).getTextContent());
		            }catch(NullPointerException e){
		            	lastUnsuccessfulBuildDDTO.setNumber("null");
		            	lastUnsuccessfulBuildDDTO.setUrl("null");
					}
		            hudsonJobDDTO.setLastUnSuccessfulBuild(lastUnsuccessfulBuildDDTO);				
					
					// nextBuildNumber �쒓렇
					NodeList nextBuildNumberList = hudsonJobElmnt.getElementsByTagName("nextBuildNumber");
					Element nextBuildNumberElmnt = (Element) nextBuildNumberList.item(0);
					Node nextBuildNumber = nextBuildNumberElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setNextBuilderNumber(nextBuildNumber.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setNextBuilderNumber("");
					}
					
					// property �쒓렇
					NodeList propertyList = hudsonJobElmnt.getElementsByTagName("property");
					Element propertyElmnt = (Element) propertyList.item(0);
					Node property = propertyElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setProperty(property.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setProperty("");
					}
			        
					// concurrentBuild �쒓렇
					NodeList concurrentBuildList = hudsonJobElmnt.getElementsByTagName("concurrentBuild");
					Element concurrentBuildElmnt = (Element) concurrentBuildList.item(0);
					Node concurrentBuild = concurrentBuildElmnt.getFirstChild();
					try{
						hudsonJobDDTO.setConcurrentBuild(concurrentBuild.getNodeValue());
					}catch(NullPointerException e){
						hudsonJobDDTO.setConcurrentBuild("");
					}
				}
			}
		} catch (Exception e) {
		}
		return hudsonJobDDTO;
	}
}
