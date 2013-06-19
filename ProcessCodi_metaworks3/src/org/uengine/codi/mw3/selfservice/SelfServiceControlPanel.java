package org.uengine.codi.mw3.selfservice;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.FileProperty;
import org.metaworks.metadata.FormProperty;
import org.metaworks.metadata.ImageProperty;
import org.metaworks.metadata.MetadataProperty;
import org.metaworks.metadata.MetadataXML;
import org.metaworks.metadata.ProcessProperty;
import org.metaworks.metadata.StringProperty;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.marketplace.IAppMapping;
import org.uengine.codi.mw3.model.Session;


/**
 * Id: key
 * Name: Tab Name
 * 
 * AppNode:
 * 
 * load(): 
 * 
 * @author JISUN
 *
 */

@Face(displayName="$SelfServicePortal")
public class SelfServiceControlPanel {
	
	public SelfServiceControlPanel() {

	}
	
	IAppMapping appMapping;
		public IAppMapping getAppMapping() {
			return appMapping;
		}
		public void setAppMapping(IAppMapping appMapping) {
			this.appMapping = appMapping;
		}
		
	ArrayList<MetadataProperty> metadataProperties;
		public ArrayList<MetadataProperty> getMetadataProperties() {
			return metadataProperties;
		}
		public void setMetadataProperties(ArrayList<MetadataProperty> metadataProperties) {
			this.metadataProperties = metadataProperties;
		}
	
	ArrayList<FileProperty> fileProperties;
		public ArrayList<FileProperty> getFileProperties() {
			return fileProperties;
		}
		public void setFileProperties(ArrayList<FileProperty> fileProperties) {
			this.fileProperties = fileProperties;
		}
	
	ArrayList<FormProperty> formProperties;
		public ArrayList<FormProperty> getFormProperties() {
			return formProperties;
		}
		public void setFormProperties(ArrayList<FormProperty> formProperties) {
			this.formProperties = formProperties;
		}

	ArrayList<StringProperty> stringProperties;
		public ArrayList<StringProperty> getStringProperties() {
			return stringProperties;
		}
		public void setStringProperties(ArrayList<StringProperty> stringProperties) {
			this.stringProperties = stringProperties;
		}
	
	ArrayList<ProcessProperty> processProperties;
		public ArrayList<ProcessProperty> getProcessProperties() {
			return processProperties;
		}
		public void setProcessProperties(ArrayList<ProcessProperty> processProperties) {
			this.processProperties = processProperties;
		}

	ArrayList<ImageProperty> imageProperties;
		public ArrayList<ImageProperty> getImageProperties() {
			return imageProperties;
		}
		public void setImageProperties(ArrayList<ImageProperty> imageProperties) {
			this.imageProperties = imageProperties;
		}

	MetadataProperty metadataProperty;
		public MetadataProperty getMetadataProperty() {
			return metadataProperty;
		}
		public void setMetadataProperty(MetadataProperty metadataProperty) {
			this.metadataProperty = metadataProperty;
		}

	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}
		
	String appName;
		public String getAppName() {
			return appName;
		}
		public void setAppName(String appName) {
			this.appName = appName;
		}
	
	MetadataXML metadataXml;
		@AutowiredToClient
		public MetadataXML getMetadataXml() {
			return metadataXml;
		}
		public void setMetadataXml(MetadataXML metadataXml) {
			this.metadataXml = metadataXml;
		}

		
	public void load(Session session) throws Exception {

		AppMapping appMp = new AppMapping();
		appMp.setComCode(session.getCompany().getComCode());
		appMp.setIsDeleted(false);
		
		IAppMapping appList =appMp.findMyApps();
		appList.getMetaworksContext().setWhen("filter");
		
		
		this.setAppMapping(appList);
	}
	

	@ServiceMethod(callByContent=true)
	public void selectedApp() throws Exception {
		
		App app = new App();
		app.setAppId(this.getAppId());
		
		String projectId = app.databaseMe().getProject().getId();
		
		WfNode project = new WfNode();
		project.setId(projectId);
		
		String projectName = project.databaseMe().getName();
		
		// load metadata
		String sourceCodeBase = CodiClassLoader.mySourceCodeBase(projectName);
		String metadataFileName = "uengine.metadata";
		String metadataFilePath = sourceCodeBase + File.separatorChar + metadataFileName;
		
		MetadataXML metadataXML = new MetadataXML();
		metadataXML = metadataXML.loadWithPath(metadataFilePath);
		metadataXML.setFilePath(sourceCodeBase);
		
		this.metadataProperties = new ArrayList<MetadataProperty>();
		this.fileProperties = new ArrayList<FileProperty>();
		this.formProperties = new ArrayList<FormProperty>();
		this.imageProperties = new ArrayList<ImageProperty>();
		this.stringProperties = new ArrayList<StringProperty>();
		this.processProperties = new ArrayList<ProcessProperty>();
		
		for(MetadataProperty metadataProperty : metadataXML.getProperties()){
			metadataProperty = (MetadataProperty) metadataProperty.selectType();
			metadataProperty.setMetaworksContext(new MetaworksContext());
			metadataProperty.getMetaworksContext().setWhere("ssp");
			
			if(MetadataProperty.FILE_PROP.equals(metadataProperty.getType())){
				this.fileProperties.add((FileProperty) metadataProperty);
			}else if(MetadataProperty.FORM_PROP.equals(metadataProperty.getType())){
				this.formProperties.add((FormProperty) metadataProperty);
			}else if(MetadataProperty.IMAGE_PROP.equals(metadataProperty.getType())){
				this.imageProperties.add((ImageProperty) metadataProperty);
			}else if(MetadataProperty.PROCESS_PROP.equals(metadataProperty.getType())){
				this.processProperties.add((ProcessProperty) metadataProperty);
			}else if(MetadataProperty.STRING_PROP.equals(metadataProperty.getType())){
				this.stringProperties.add((StringProperty) metadataProperty);
			}
			
			this.metadataProperties.add(metadataProperty.getIndex(), metadataProperty);
		}
		
		metadataXML.setProperties(this.metadataProperties);
		this.setMetadataXml(metadataXML);
	}
	
	@ServiceMethod(callByContent=true)
	public void saveProperty() {
		System.out.println("save property");
	}
}
