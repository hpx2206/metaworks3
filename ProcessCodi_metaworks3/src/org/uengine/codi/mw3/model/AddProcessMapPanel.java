package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.metadata.MetadataProperty;
import org.metaworks.metadata.MetadataXML;

@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
public class AddProcessMapPanel {

	ResourceFile processDefinitions;
		@Face(displayName="$FileResource")
		public ResourceFile getProcessDefinitions() {
			return processDefinitions;
		}
		public void setProcessDefinitions(ResourceFile processDefinitions) {
			this.processDefinitions = processDefinitions;
		}

	MetadataProperty metadataTree;
		@Face(displayName="$MetadataResource")
		public MetadataProperty getMetadataTree() {
			return metadataTree;
		}
		public void setMetadataTree(MetadataProperty metadataTree) {
			this.metadataTree = metadataTree;
		}
		
	public void load(){
		ResourceFile processDefinitions = new ResourceFile();
		
		processDefinitions.setMetaworksContext(new MetaworksContext());	
		processDefinitions.getMetaworksContext().setWhen("appendProcessMap");

		processDefinitions.setFolder(true);
		processDefinitions.setAlias("");
		processDefinitions.setName("/");
		processDefinitions.drillDown();
		
		this.setProcessDefinitions(processDefinitions);
		
		
		
		
		
		MetadataProperty metadataTree = new MetadataProperty();
		metadataTree.setMetaworksContext(new MetaworksContext());
		metadataTree.getMetaworksContext().setHow("appendProcessMap");
		metadataTree.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		metadataTree.setName("process");
		metadataTree.setId("process");
		metadataTree.setType("folder");
		metadataTree.setChild(new ArrayList<MetadataProperty>());
		

		// load metadata
		MetadataXML metadataXML = new MetadataXML();
		metadataXML = metadataXML.loadWithPath("d:/uengine.metadata");

		
		for(MetadataProperty metadataProperty : metadataXML.getProperties()){
			if(MetadataProperty.PROCESS_PROP.equals(metadataProperty.getType())){
				metadataProperty.setMetaworksContext(new MetaworksContext());
				metadataProperty.getMetaworksContext().setHow("appendProcessMap");
				
				metadataTree.getChild().add(metadataProperty);
			}
		}
		
		
		this.setMetadataTree(metadataTree);
	}
	
}
