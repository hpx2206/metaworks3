package org.metaworks.metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.ObjectInstance;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Children;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Icon;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.TypeSelector;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.ProcessMapList;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.InstanceMonitor;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.processmanager.ProcessManagerRemote;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(ejsPath = "dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs",
ejsPathMappingByContext = {
		"{when: 'new', face: 'dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs'}",
		"{when: 'view', face: 'dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs'}",
		"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/MetadataPropertyDetail.ejs'}",
		"{how: 'tree', face: 'dwr/metaworks/genericfaces/TreeFace.ejs'}",
		"{how: 'picker', face: 'dwr/metaworks/org/metaworks/metadata/MetadataPropertyPicker.ejs'}",
		"{how: 'appendProcessMap', face: 'dwr/metaworks/org/metaworks/metadata/MetadataPropertyProcessMap.ejs'}" }, options = { "fieldOrder" }, values = { "name,type,value,file,description,resourceNode" })
@XStreamAlias("MetadataProperty")
public class MetadataProperty implements ContextAware, Cloneable {

	public final static String FILE_PROP = "file";
	public final static String FORM_PROP = "form";
	public final static String IMAGE_PROP = "image";
	public final static String STRING_PROP = "string";
	public final static String PROCESS_PROP = "process";

	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public MetadataXML metadataXML;

	@AutowiredFromClient
	public Workspace workspace;

	@AutowiredFromClient
	public Session session;

	public MetadataProperty() {
		setFile(new MetadataFile());
		setMetaworksContext(new MetaworksContext());
	}

	@XStreamOmitField
	ArrayList<MetadataProperty> child;
	@Children
	@Hidden
	public ArrayList<MetadataProperty> getChild() {
		return child;
	}
	public void setChild(ArrayList<MetadataProperty> child) {
		this.child = child;
	}

	@XStreamAsAttribute
	String type;
	@Range(options = { "File", "Image", "Process", "String", "Form" }, values = {
			"file", "image", "process", "string", "form" })
	@TypeSelector(values = { "file", "image", "process", "string", "form" }, classes = {
			FileProperty.class, ImageProperty.class, ProcessProperty.class,
			StringProperty.class, FormProperty.class })
	@Icon
	@NonEditable(when = { MetaworksContext.WHEN_EDIT })
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
		if( this.getFile() != null ){
			this.getFile().setTypeDir(type);
		}
	}

	@XStreamOmitField
	String projectId;
	@Hidden
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@XStreamAsAttribute
	boolean isKeyEditable;
	@Hidden
	public boolean isKeyEditable() {
		return isKeyEditable;
	}
	public void setKeyEditable(boolean isKeyEditable) {
		this.isKeyEditable = isKeyEditable;
	}

	@XStreamAsAttribute
	boolean isRemote;
	@Hidden
	public boolean isRemote() {
		return isRemote;
	}
	public void setRemote(boolean isRemote) {
		this.isRemote = isRemote;
	}

	String id;
	@Id
	@Hidden
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	String name;
	@Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@XStreamOmitField
	MetadataFile file;
	public MetadataFile getFile() {
		return file;
	}
	public void setFile(MetadataFile file) {
		this.file = file;
	}

	@XStreamOmitField
	Object filePreview;
	public Object getFilePreview() {
		return filePreview;
	}
	public void setFilePreview(Object filePreview) {
		this.filePreview = filePreview;
	}

	@XStreamOmitField
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	@XStreamOmitField
	int index;
	@Hidden
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	@XStreamOmitField
	boolean change;
	@Hidden
	public boolean isChange() {
		return change;
	}
	public void setChange(boolean change) {
		this.change = change;
	}

	@XStreamOmitField
	ResourceNode resourceNode;
	public ResourceNode getResourceNode() {
		return resourceNode;
	}
	public void setResourceNode(ResourceNode resourceNode) {
		this.resourceNode = resourceNode;
	}

	@XStreamOmitField
	boolean checkFile;
	@Hidden
	public boolean isCheckFile() {
		return checkFile;
	}
	public void setCheckFile(boolean checkFile) {
		this.checkFile = checkFile;
	}

	@XStreamOmitField
	boolean checkResource;
	@Hidden
	public boolean isCheckResource() {
		return checkResource;
	}
	public void setCheckResource(boolean checkResource) {
		this.checkResource = checkResource;
	}


	@Hidden
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_NONE)
	public String toXmlXStream() {
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);

		return stream.toXML(this);
	}

	@Available(when = MetaworksContext.WHEN_NEW)
	@ServiceMethod(callByContent = true)
	public Object add() throws FileNotFoundException, IOException, Exception {

		MetadataProperty clone = null;
		boolean isFile = false;
		boolean isResource = false;
		if( this.getName() == null || "".equals(this.getName()) ){
			throw new MetaworksException("name is empty!! please fill property name. ");
		}
		if( metadataXML.getProperties().contains(this) ){
			throw new MetaworksException("already exist name!! please change the property name");
		}
		try {

			if (this.getFile().getFileTransfer() != null
					&& this.getFile().getFileTransfer().getSize() > 0)
				isFile = true;

			if (this.getResourceNode().getId() != null
					&& this.getResourceNode().getPath() != null)
				isResource = true;

			//clone = (MetadataProperty) this.clone();
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			this.getMetaworksContext().setWhere("");
			this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

			this.setChange(true);

			// type별로 이미지/파일일때 value == path로
			if (FILE_PROP.equals(this.getType())
					|| IMAGE_PROP.equals(this.getType())
					|| PROCESS_PROP.equals(this.getType())
					|| FORM_PROP.equals(this.getType())) {

				if (isFile) {
					this.getFile().upload();
					this.setValue(this.getFile().getFilename());
				}else if(this.getFile()!=null && this.getFile().getFilename() != null){
					this.setValue(this.getFile().getUploadedPath());
				}

				if (isResource) {
					this.setValue(this.getResourceNode().getId().substring(this.getResourceNode().getId().indexOf(File.separatorChar)+1));
				}

			} else if (STRING_PROP.equals(this.getType())) {
				this.setValue(this.getValue());
			}

			this.getFile().setFileTransfer(null);
			this.setFilePreview(null);
			
			metadataXML.getProperties().add(this);
			metadataXML.init();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return metadataXML;
	}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true)
	public Object remove() {

		metadataXML.getProperties().remove(this);

		return metadataXML;
	}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true)
	public Object edit() throws Exception {

		Class dstClass = null;

		WebObjectType srcWOT = MetaworksRemoteService.getInstance().getMetaworksType(this.getClass().getName());
		ObjectInstance srcInstance = (ObjectInstance) srcWOT.metaworks2Type().createInstance();
		srcInstance.setObject(this);

		for (FieldDescriptor fd : srcWOT.metaworks2Type().getFieldDescriptors()) {
			Map<String, String> typeSelector = (Map<String, String>) fd.getAttribute("typeSelector");
			if (typeSelector != null) {

				String typeName = (String) srcInstance.getFieldValue(fd.getName());
				String selectedTypeClassName = typeSelector.get(typeName);

				if (selectedTypeClassName == null)
					break;

				dstClass = Thread.currentThread().getContextClassLoader().loadClass(selectedTypeClassName);

				break;
			}
		}

		if (dstClass == null)
			throw new Exception("type selector exception");

		
		WebObjectType dstWOT = MetaworksRemoteService.getInstance().getMetaworksType(dstClass.getName());
		ObjectInstance dstInstance = (ObjectInstance) dstWOT.metaworks2Type().createInstance();

		for (FieldDescriptor fd : dstWOT.metaworks2Type().getFieldDescriptors()) {
			if (fd.getAttribute("ormapping") == null){
				dstInstance.setFieldValue(fd.getName(), srcInstance.getFieldValue(fd.getName()));
			}
		}
		
		if( this.getMetaworksContext() == null ){
			this.setMetaworksContext( new MetaworksContext() );
		}

		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.setName(this.getName());
		this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);

		int index = metadataXML.properties.indexOf(this);
		
		//dstInstance.setFieldValue("Type", "image");
		//dstInstance.setFieldValue("File", this.getFile());
		
		MetadataProperty editProperty = (MetadataProperty) dstInstance.getObject();
		editProperty.getFile().setFileTransfer(null);
		
		boolean isFile = false;
		boolean isResource = false;

		if (this.getFile().getFileTransfer() != null
				&& this.getFile().getFileTransfer().getSize() > 0)
			isFile = true;

		if (this.getResourceNode().getId() != null
				&& this.getResourceNode().getPath() != null)
			isResource = true;
			
		
		if( this.getFile()!=null && this.getFile().getFilename() != null && !"".equals(this.getFile().getFilename()) ){
			MetadataFile resourceFile = new MetadataFile();
			resourceFile.setBaseDir(editProperty.getFile().getBaseDir());
			resourceFile.setFilename(editProperty.getFile().getFilename());
			resourceFile.setUploadedPath(this.getFile().getUploadedPath());
	//		resourceFile.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			resourceFile.setMimeType(ResourceNode.findNodeType(resourceFile.getFilename()));
			resourceFile.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			
			editProperty.setFile(resourceFile);
		}
		// type별로 이미지/파일일때 value == path로
		if (FILE_PROP.equals(this.getType())
				|| IMAGE_PROP.equals(this.getType())
				|| PROCESS_PROP.equals(this.getType())
				|| FORM_PROP.equals(this.getType())) {

			if (isFile) {
				editProperty.setValue(this.getFile().getFilename());
			}else if(this.getFile()!=null && this.getFile().getFilename() != null){
				editProperty.setValue(this.getFile().getUploadedPath());
			}

			if (isResource) {
				editProperty.setValue(this.getResourceNode().getId().substring(this.getResourceNode().getId().indexOf(File.separatorChar)+1));
			}

		} else if (STRING_PROP.equals(this.getType())) {
			editProperty.setValue(this.getValue());
		}
		
		editProperty.setFilePreview(null);
		editProperty.setMetaworksContext(metadataXML.properties.get(index).getMetaworksContext());
		
		metadataXML.properties.remove(index);
		metadataXML.properties.add(index, editProperty);

		metadataXML.init();
		
		return metadataXML;

	}

	@Available(when = MetaworksContext.WHEN_EDIT)
	@ServiceMethod(callByContent = true)
	public Object[] save() throws FileNotFoundException, IOException, Exception {

		int index = metadataXML.properties.indexOf(this);

		String metadataFilePath = metadataXML.getFilePath();

		MetadataProperty editProperty = metadataXML.properties.get(index);
		editProperty.setName(this.getName());
		editProperty.setChange(true);
		editProperty.setType(this.getType());
		editProperty.setValue(this.getValue());

		metadataXML.properties.remove(this);
		metadataXML.properties.add(index, editProperty);

		editProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

		MetadataEditor metadataEditor = new MetadataEditor();
		metadataEditor.setResourceNode(new ResourceNode());
		metadataEditor.getResourceNode().setPath(metadataFilePath);
		metadataEditor.setContent(metadataXML.toXmlXStream());
		metadataEditor.save();

		if(MetadataProperty.FILE_PROP.equals(this.getType())){
			
			ArrayList<FileProperty> fileProperties = new ArrayList<FileProperty>();
			
			for(MetadataProperty property : metadataXML.getProperties()){
				if(MetadataProperty.FILE_PROP.equals(property.getType())) {					
					fileProperties.add((FileProperty)property);
				}
			}
			
			FilePropertyPanel panel = new FilePropertyPanel(fileProperties);
			
			return new Object[]{panel};	
			
		}else if(MetadataProperty.IMAGE_PROP.equals(this.getType())) {
			
			ArrayList<ImageProperty> imageProperties = new ArrayList<ImageProperty>();
			
			for(MetadataProperty property : metadataXML.getProperties()){
				if(MetadataProperty.IMAGE_PROP.equals(property.getType())) {					
					imageProperties.add((ImageProperty)property);
				}
			}
			
			ImagePropertyPanel panel = new ImagePropertyPanel(imageProperties);
			
			return new Object[]{panel};	
			
		}else if(MetadataProperty.PROCESS_PROP.equals(this.getType())) {
			
			ArrayList<ProcessProperty> processProperties = new ArrayList<ProcessProperty>();
			
			for(MetadataProperty property : metadataXML.getProperties()){
				if(MetadataProperty.PROCESS_PROP.equals(property.getType())) {					
					processProperties.add((ProcessProperty)property);
				}
			}
			
			ProcessPropertyPanel panel = new ProcessPropertyPanel(processProperties);
			
			return new Object[]{panel};	
			
		}else if(MetadataProperty.STRING_PROP.equals(this.getType())) {
			
			ArrayList<StringProperty> stringProperties = new ArrayList<StringProperty>();
			
			for(MetadataProperty property : metadataXML.getProperties()){
				if(MetadataProperty.STRING_PROP.equals(property.getType())) {					
					stringProperties.add((StringProperty)property);
				}
			}
			
			StringPropertyPanel panel = new StringPropertyPanel(stringProperties);
			
			return new Object[]{panel};	
			
		}else if(MetadataProperty.FORM_PROP.equals(this.getType())) {
			
			ArrayList<FormProperty> formProperties = new ArrayList<FormProperty>();
			
			for(MetadataProperty property : metadataXML.getProperties()){
				if(MetadataProperty.FORM_PROP.equals(property.getType())) {					
					formProperties.add((FormProperty)property);
				}
			}
			
			FormPropertyPanel panel = new FormPropertyPanel(formProperties);
			
			return new Object[]{panel};
		}		
		
		return null;
//		return new Object[]{metadataXML};
	}

	@ServiceMethod(callByContent = true, when = MetaworksContext.WHEN_VIEW)
	public MetadataXML up() {
		int index = metadataXML.properties.indexOf(this);

		if (index > 0) {
			metadataXML.properties.remove(this);
			metadataXML.properties.add(index - 1, this);
		}

		return metadataXML;

	}

	@ServiceMethod(callByContent = true, when = MetaworksContext.WHEN_VIEW)
	public MetadataXML down() {
		int index = metadataXML.properties.indexOf(this);

		if (index < metadataXML.properties.size() - 1) {
			metadataXML.properties.remove(this);
			metadataXML.properties.add(index + 1, this);
		}

		return metadataXML;
	}

	@Hidden
	@ServiceMethod(payload = {"type", "metaworksContext"}, eventBinding = "change", bindingFor = "type", bindingHidden = true, target = ServiceMethodContext.TARGET_SELF)
	public Object selectType() throws Exception {

		Class dstClass = null;

		WebObjectType srcWOT = MetaworksRemoteService.getInstance().getMetaworksType(this.getClass().getName());
		ObjectInstance srcInstance = (ObjectInstance) srcWOT.metaworks2Type().createInstance();
		srcInstance.setObject(this);

		for (FieldDescriptor fd : srcWOT.metaworks2Type().getFieldDescriptors()) {
			Map<String, String> typeSelector = (Map<String, String>) fd.getAttribute("typeSelector");
			if (typeSelector != null) {

				String typeName = (String) srcInstance.getFieldValue(fd.getName());
				String selectedTypeClassName = typeSelector.get(typeName);

				if (selectedTypeClassName == null)
					break;

				dstClass = Thread.currentThread().getContextClassLoader()
						.loadClass(selectedTypeClassName);

				break;
			}
		}

		if (dstClass == null)
			throw new Exception("type selector exception");

		WebObjectType dstWOT = MetaworksRemoteService.getInstance().getMetaworksType(dstClass.getName());
		ObjectInstance dstInstance = (ObjectInstance) dstWOT.metaworks2Type().createInstance();

		for (FieldDescriptor fd : dstWOT.metaworks2Type().getFieldDescriptors()) {
			if (fd.getAttribute("ormapping") == null)
				dstInstance.setFieldValue(fd.getName(),
						srcInstance.getFieldValue(fd.getName()));
		}

		MetadataProperty metadataProperty = (MetadataProperty) dstInstance.getObject();
		metadataProperty.setMetaworksContext(new MetaworksContext());
		metadataProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		if( this.getMetaworksContext() != null ){
			metadataProperty.getMetaworksContext().setWhere(this.getMetaworksContext().getWhere());
		}

		if (!STRING_PROP.equals(this.getType())) {


			ResourceNode resourceNode = new ResourceNode();
			resourceNode.setMetaworksContext(new MetaworksContext());
			resourceNode.getMetaworksContext().setHow("resourcePicker");

			if( this.metadataXML != null && this.metadataXML.getFilePath() != null ){
				String metadataXMLPath = this.metadataXML.getFilePath();
				String FileUploadPath = metadataXMLPath.substring(0 , metadataXMLPath.indexOf("uengine.metadata"));
				metadataProperty.getFile().setBaseDir(FileUploadPath);
				
				resourceNode.setProjectId(this.metadataXML.getProjectId());
			}
			
			metadataProperty.setResourceNode(resourceNode);
		}

		return metadataProperty;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MetadataProperty) {
			MetadataProperty metadataProperty = (MetadataProperty) obj;
			if (metadataProperty != null && metadataProperty.getName() != null
					&& metadataProperty.getName().equals(this.getName())) {
				return true;
			}
		}

		return false;
	}

	@Available(how = "tree")
	@ServiceMethod(callByContent = true, except = "child", mouseBinding = "left", target = ServiceMethodContext.TARGET_APPEND)
	public Object[] pick() {

		if ("folder".equals(this.getType()))
			return null;
		else {
			this.getMetaworksContext().setHow("picker");

			return new Object[] { new ToOpener(this), new Remover(new Popup()) };
		}
	}

	@Available(how = "picker")
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_POPUP)
	public Object openPicker() {
		MetadataXML metadataXML = new MetadataXML();
		metadataXML = metadataXML.loadWithPath("d:/uengine.metadata");

		MetadataProperty metadataTree = new MetadataProperty();
		metadataTree.setChild(metadataXML.getProperties());
		metadataTree.setMetaworksContext(new MetaworksContext());
		metadataTree.getMetaworksContext().setHow("tree");
		metadataTree.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

		metadataTree.setId("__ROOT__");
		metadataTree.setName("Metadata");
		metadataTree.setType("folder");

		MetadataProperty mpImage = new MetadataProperty();
		mpImage.setName("image");
		mpImage.setId("image");
		mpImage.setType("folder");
		mpImage.setChild(new ArrayList<MetadataProperty>());

		MetadataProperty mpProcess = new MetadataProperty();
		mpProcess.setName("process");
		mpProcess.setId("process");
		mpProcess.setType("folder");
		mpProcess.setChild(new ArrayList<MetadataProperty>());

		MetadataProperty mpFile = new MetadataProperty();
		mpFile.setName("file");
		mpFile.setId("file");
		mpFile.setType("folder");
		mpFile.setChild(new ArrayList<MetadataProperty>());

		MetadataProperty mpForm = new MetadataProperty();
		mpForm.setName("form");
		mpForm.setId("form");
		mpForm.setType("folder");
		mpForm.setChild(new ArrayList<MetadataProperty>());

		MetadataProperty mpString = new MetadataProperty();
		mpString.setName("string");
		mpString.setId("string");
		mpString.setType("folder");
		mpString.setChild(new ArrayList<MetadataProperty>());

		for (MetadataProperty metadataProperty : metadataXML.getProperties()) {
			metadataProperty.setMetaworksContext(new MetaworksContext());
			metadataProperty.getMetaworksContext().setHow("tree");

			if (MetadataProperty.IMAGE_PROP.equals(metadataProperty.getType())) {
				mpImage.getChild().add(metadataProperty);
			} else if (MetadataProperty.PROCESS_PROP.equals(metadataProperty
					.getType())) {
				mpProcess.getChild().add(metadataProperty);
			} else if (MetadataProperty.FILE_PROP.equals(metadataProperty
					.getType())) {
				mpFile.getChild().add(metadataProperty);
			} else if (MetadataProperty.FORM_PROP.equals(metadataProperty
					.getType())) {
				mpForm.getChild().add(metadataProperty);
			} else if (MetadataProperty.STRING_PROP.equals(metadataProperty
					.getType())) {
				mpString.getChild().add(metadataProperty);
			}
		}

		metadataTree.setChild(new ArrayList<MetadataProperty>());
		metadataTree.getChild().add(mpImage);
		metadataTree.getChild().add(mpProcess);
		metadataTree.getChild().add(mpFile);
		metadataTree.getChild().add(mpForm);
		metadataTree.getChild().add(mpString);

		return new Popup(metadataTree);

	}

	@Hidden
	@ServiceMethod(callByContent = true, except = "child")
	public Object[] appendProcessMap() throws Exception {
		String alias = "@" + this.getName();

		ProcessMap processMap = new ProcessMap();

		org.uengine.kernel.ProcessDefinition procDef = processManager
				.getProcessDefinition(alias);
		String fullCommandPhrase = procDef.getDescription().getText();

		if (fullCommandPhrase != null) {
			int commandCotentStarts = fullCommandPhrase.indexOf(':');
			if (-1 < commandCotentStarts) {

				processMap.setCmPhrase(fullCommandPhrase);
				processMap.setCmTrgr(fullCommandPhrase.substring(0,
						commandCotentStarts));
			}
		}

		processMap.setMapId(session.getCompany().getComCode() + "." + alias);
		processMap.setDefId(alias);
		processMap.setName(this.getName());
		processMap.setComCode(session.getCompany().getComCode());

		if (!processMap.confirmExist())
			throw new Exception("$AlreadyAddedApp");

		processMap.createMe();

		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);

		return new Object[] { processMapList, new Remover(new ModalWindow()) };
	}

	@Hidden
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_AUTO)
	public MetadataProperty showPropertyDetail() throws Exception {

		String projectSourcePath = (String)TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("projectSourcePath");

		MetadataProperty detailProperty = new MetadataProperty();

		detailProperty.setName(this.getName());
		detailProperty.setType(this.getType());
		detailProperty.setDescription(this.getDescription());
		detailProperty.setValue(this.getValue());
		detailProperty.setProjectId(this.getProjectId());

		detailProperty = (MetadataProperty) detailProperty.selectType();

		detailProperty.setId("metaDetailView");
		detailProperty.getMetaworksContext().setWhen("show_detail");
		detailProperty.getMetaworksContext().setWhere("ssp");

		if(MetadataProperty.FILE_PROP.equals(this.getType())){
			MetadataFile file = new MetadataFile();
			file.setUploadedPath(this.getValue());
			file.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			file.setMimeType(ResourceNode.findNodeType(this.getValue()));
			file.setTypeDir(this.getType());

			detailProperty.setFile(file);
			detailProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);

		}else if(MetadataProperty.PROCESS_PROP.equals(this.getType())){

			ProcessViewPanel processViewPanel = new ProcessViewPanel();
			processViewPanel.setDefId(this.getValue());
			processViewPanel.setAlias(this.getValue());
			processViewPanel.setViewType("definitionView");
			processViewPanel.load();
			
			((ProcessProperty)detailProperty).setFilePreview(processViewPanel);
			detailProperty.getFile().setTypeDir(this.getType());

		}else if(MetadataProperty.IMAGE_PROP.equals(this.getType())){
			MetadataFile file = new MetadataFile();
			file.setBaseDir(projectSourcePath);
			file.setTypeDir(this.getType());
			file.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			file.setUploadedPath(this.getValue());
			file.setMimeType(ResourceNode.findNodeType(this.getValue()));


			MetadataFile previewFile = new MetadataFile();
			previewFile.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			previewFile.setUploadedPath(this.getValue());
			previewFile.setMimeType(ResourceNode.findNodeType(this.getValue()));
			previewFile.setUseClassLoader(true);

			/*
			ResourceNode resourceNode = new ResourceNode();
			resourceNode.setMetaworksContext(new MetaworksContext());
			resourceNode.getMetaworksContext().setHow("resourcePicker");
			resourceNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			 */

			detailProperty.setFile(file);
			detailProperty.setFilePreview(previewFile);
			//detailProperty.setResourceNode(resourceNode);
			//detailProperty.metadataXML = metadataXML;
		}else if(MetadataProperty.FORM_PROP.equals(this.getType())){
			MetadataFile file = new MetadataFile();
			file.setBaseDir(projectSourcePath);
			file.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			file.setUploadedPath(this.getValue());
			file.setMimeType(ResourceNode.findNodeType(this.getValue()));

			String path = this.getProjectId() + File.separatorChar + file.getUploadedPath();

			Project project = workspace.findProject(this.getProjectId());


			/*
			ResourceNode resourceNode = new ResourceNode();
			resourceNode.setMetaworksContext(new MetaworksContext());
			resourceNode.getMetaworksContext().setHow("resourcePicker");
			resourceNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			 */


			Object previewForm = Thread.currentThread().getContextClassLoader().loadClass(project.getBuildPath().makeFullClassName(path)).newInstance();

			detailProperty.setFile(file);
			detailProperty.setFilePreview(previewForm);

			//detailProperty.setResourceNode(resourceNode);
			//detailProperty.metadataXML = metadataXML;
		}else if(MetadataProperty.STRING_PROP.equals(this.getType())){
			detailProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}

		return detailProperty;
	}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true)
	public Object modify() throws Exception {
		return null;
	}

	@ServiceMethod(callByContent=true)
	@Available(when= MetaworksContext.WHEN_VIEW)
	public Object metadataModify() throws Exception {

		MetadataProperty metadataProperty = (MetadataProperty)this.clone();
		metadataProperty = (MetadataProperty) metadataProperty.selectType();
		metadataProperty.setMetaworksContext(new MetaworksContext());
		metadataProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		metadataProperty.getMetaworksContext().setWhere("ide");
		
		String type = metadataProperty.getType();
		if(IMAGE_PROP.equals(type) && metadataProperty.getFile() != null)
			metadataProperty.getFile().setTypeDir("image");
		
		if(FORM_PROP.equals(type) || PROCESS_PROP.equals(type)){
			metadataProperty.getResourceNode().setId(this.getProjectId() + "/" + this.getValue());
			metadataProperty.getResourceNode().setName(this.getValue());
		}
		
		MetadataPropertyInfo metadataPropertyInfo = new MetadataPropertyInfo();
		metadataPropertyInfo.setNewMetadataProperty(metadataProperty);
		
		return metadataPropertyInfo;

	}
}
