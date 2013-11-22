package org.uengine.codi.mw3.selfservice;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.FileProperty;
import org.metaworks.metadata.FilePropertyPanel;
import org.metaworks.metadata.FormProperty;
import org.metaworks.metadata.FormPropertyPanel;
import org.metaworks.metadata.ImageProperty;
import org.metaworks.metadata.ImagePropertyPanel;
import org.metaworks.metadata.MetadataProperty;
import org.metaworks.metadata.MetadataXML;
import org.metaworks.metadata.ProcessProperty;
import org.metaworks.metadata.ProcessPropertyPanel;
import org.metaworks.metadata.StringProperty;
import org.metaworks.metadata.StringPropertyPanel;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.Workspace;
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
		
	FilePropertyPanel filePropertyPanel;	
		public FilePropertyPanel getFilePropertyPanel() {
			return filePropertyPanel;
		}
		public void setFilePropertyPanel(FilePropertyPanel filePropertyPanel) {
			this.filePropertyPanel = filePropertyPanel;
		}
		
	ImagePropertyPanel imagePropertyPanel;
		public ImagePropertyPanel getImagePropertyPanel() {
			return imagePropertyPanel;
		}
		public void setImagePropertyPanel(ImagePropertyPanel imagePropertyPanel) {
			this.imagePropertyPanel = imagePropertyPanel;
		}
		
	ProcessPropertyPanel processPropertyPanel;	
		public ProcessPropertyPanel getProcessPropertyPanel() {
			return processPropertyPanel;
		}
		public void setProcessPropertyPanel(ProcessPropertyPanel processPropertyPanel) {
			this.processPropertyPanel = processPropertyPanel;
		}

	StringPropertyPanel stringPropertyPanel;
		public StringPropertyPanel getStringPropertyPanel() {
			return stringPropertyPanel;
		}
		public void setStringPropertyPanel(StringPropertyPanel stringPropertyPanel) {
			this.stringPropertyPanel = stringPropertyPanel;
		}
		
	FormPropertyPanel formPropertyPanel;	
		public FormPropertyPanel getFormPropertyPanel() {
			return formPropertyPanel;
		}
		public void setFormPropertyPanel(FormPropertyPanel formPropertyPanel) {
			this.formPropertyPanel = formPropertyPanel;
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
		public MetadataXML getMetadataXml() {
			return metadataXml;
		}
		public void setMetadataXml(MetadataXML metadataXml) {
			this.metadataXml = metadataXml;
		}

		
	Workspace workspace;
		@AutowiredToClient
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}
		
	@AutowiredFromClient
	public Session session;
		
	public void load(Session session) throws Exception {

		AppMapping appMp = new AppMapping();
		appMp.session = session;
		appMp.setComCode(session.getCompany().getComCode());
		appMp.setIsDeleted(false);
		
		IAppMapping appList =appMp.findMyApps();
		appList.getMetaworksContext().setWhen("filter");
		appList.getMetaworksContext().setWhere("ssp");
		
		this.setAppMapping(appList);
		
		Workspace workspace = new Workspace();
		
		while(appList.next()) {			
			workspace.addProject(session.getCompany().getComCode(), appList.getProjectName(), appList.getAppName());
		}
		
		this.setWorkspace(workspace);
	}
	
	public void load(Session session, int appId) throws Exception {

		AppMapping appMp = new AppMapping();
		appMp.session = session;
		appMp.setAppId(appId);
		
		IAppMapping appList = appMp.findMyApp();
		
		appList.getMetaworksContext().setWhen("filter");
		appList.getMetaworksContext().setWhere("ssp");
		
		this.setAppMapping(appList);
		
		Workspace workspace = new Workspace();

		while(appList.next()) {			
			workspace.addProject(session.getCompany().getComCode(), appList.getProjectName(), appList.getAppName());
		}
		
		this.setWorkspace(workspace);
	}
	

	@ServiceMethod(callByContent=true)
	public void selectedApp() throws Exception {
		
		AppMapping appMapping = new AppMapping();
		appMapping.setAppId(this.getAppId());
		appMapping.setComCode(session.getCompany().getComCode());
		
		Project project = this.getWorkspace().findProject(appMapping.findMe().getProjectName());
		project.changeProject(session.getCompany().getComCode());
				
		MetadataXML metadataXML = new MetadataXML();
		try {
			metadataXML = metadataXML.loadWithInputstream(Thread.currentThread().getContextClassLoader().getResourceAsStream(Project.METADATA_FILENAME));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( metadataXML == null ){
			throw new MetaworksException("메타데이타 파일이 없습니다.");
		}

		metadataXML.setFilePath(project.getPath() + File.separatorChar + Project.METADATA_FILENAME);
		
		this.metadataProperties = new ArrayList<MetadataProperty>();	
		
		ArrayList<FileProperty> fileProperties = new ArrayList<FileProperty>();
		ArrayList<ImageProperty> imageProperties = new ArrayList<ImageProperty>();
		ArrayList<ProcessProperty> processProperties = new ArrayList<ProcessProperty>();
		ArrayList<StringProperty> stringProperties = new ArrayList<StringProperty>();
		ArrayList<FormProperty> formProperties = new ArrayList<FormProperty>();
		
		for(MetadataProperty metadataProperty : metadataXML.getProperties()){
			metadataProperty = (MetadataProperty) metadataProperty.selectType();
			metadataProperty.setProjectId(project.getId());
			metadataProperty.setMetaworksContext(new MetaworksContext());
			metadataProperty.getMetaworksContext().setWhere("ssp");
			
			if(MetadataProperty.FILE_PROP.equals(metadataProperty.getType())){
				fileProperties.add((FileProperty) metadataProperty);
			}else if(MetadataProperty.FORM_PROP.equals(metadataProperty.getType())){
				formProperties.add((FormProperty) metadataProperty);
			}else if(MetadataProperty.IMAGE_PROP.equals(metadataProperty.getType())){
				imageProperties.add((ImageProperty) metadataProperty);
			}else if(MetadataProperty.PROCESS_PROP.equals(metadataProperty.getType())){
				processProperties.add((ProcessProperty) metadataProperty);
			}else if(MetadataProperty.STRING_PROP.equals(metadataProperty.getType())) {
				stringProperties.add((StringProperty) metadataProperty);
			}
			
			this.metadataProperties.add(metadataProperty.getIndex(), metadataProperty);
		}
		
		metadataXML.setProperties(this.metadataProperties);
		this.getAppMapping().getMetaworksContext().setWhere("ssp");
		
		metadataXML.setMetaworksContext(new MetaworksContext());
		metadataXML.getMetaworksContext().setHow("selfservice");
	
		//add panel		
		FilePropertyPanel filePanel = new FilePropertyPanel(fileProperties);
		this.setFilePropertyPanel(filePanel);
		
		ImagePropertyPanel imagePanel = new ImagePropertyPanel(imageProperties);
		this.setImagePropertyPanel(imagePanel);

		ProcessPropertyPanel processPanel = new ProcessPropertyPanel(processProperties);
		this.setProcessPropertyPanel(processPanel);
		
		StringPropertyPanel stringPanel = new StringPropertyPanel(stringProperties);
		this.setStringPropertyPanel(stringPanel);
		
		FormPropertyPanel formPanel = new FormPropertyPanel(formProperties);
		this.setFormPropertyPanel(formPanel);		
		
		this.setMetadataXml(metadataXML);
	}
	
	@ServiceMethod(callByContent=true)
	public void saveProperty() {
		System.out.println("save property");
	}
}
