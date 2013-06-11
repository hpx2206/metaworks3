package org.metaworks.metadata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
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
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.ProcessMapList;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmanager.ProcessManagerRemote;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	  ejsPathMappingByContext={
		"{how: 'tree', face: 'dwr/metaworks/genericfaces/TreeFace.ejs'}",
		"{how: 'picker', face: 'dwr/metaworks/org/metaworks/metadata/MetadataPropertyPicker.ejs'}",
		"{how: 'appendProcessMap', face: 'dwr/metaworks/org/metaworks/metadata/MetadataPropertyProcessMap.ejs'}"
		
	  }, 
	  options={"fieldOrder"}, values={"name,type,value,file"})
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
	public Session session;

	public MetadataProperty() {
		setFile(new MetadataFile());
		setMetaworksContext(new MetaworksContext());
	}
	
	@XStreamOmitField	
	ArrayList<MetadataProperty> child;
		@Children
		public ArrayList<MetadataProperty> getChild() {
			return child;
		}
		public void setChild(ArrayList<MetadataProperty> child) {
			this.child = child;
		}

	@XStreamAsAttribute
	String type;
		@Range(
				options={"File",	"Image",	"Process",	"String",	"Form"}, 
				values ={"file",	"image",	"process",	"string",	"form"}
				)
		@TypeSelector(
				values = 		{ 
						"file",			
						"img",		
						"process", 
						"string",
						"form"
				}, 
				classes = 		{ 
						FileProperty.class,
						ImageProperty.class,
						ProcessProperty.class,
						StringProperty.class,
						FormProperty.class
				} 
				)
		@Icon
		@NonEditable(when={MetaworksContext.WHEN_EDIT})
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	@XStreamAsAttribute
	boolean isKeyEditable;
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
	
	@XStreamOmitField
	MetadataFile file;
		public MetadataFile getFile() {
			return file;
		}
		public void setFile(MetadataFile file) {
			this.file = file;
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
		
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public String toXmlXStream(){
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(this);
	}
		
	@Hidden
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object findResource(){
		
		// make workspace
		Workspace workspace = new Workspace();
		workspace.load();
		
		Navigator navigator = new Navigator();		
		ResourceNode workspaceNode = new ResourceNode();
		workspaceNode.setId(workspace.getId());
		workspaceNode.setRoot(true);
		workspaceNode.setHidden(true);
		
		for(Project project : workspace.getProjects()){
			ResourceNode node = new ResourceNode(project);
			node.getMetaworksContext().setWhere("resource");
			workspaceNode.add(node);
		}
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(workspace.getId());
		resourceTree.setNode(workspaceNode);
		
		navigator.setResourceTree(resourceTree);
		navigator.setId("popupTree");
		
		Popup popup = new Popup();
		popup.setPanel(navigator);
		return popup;
	}
	
	@Available(when=MetaworksContext.WHEN_NEW)
	@ServiceMethod(callByContent=true)
	public Object add() throws FileNotFoundException, IOException, Exception{
		
		MetadataProperty clone = null;
		
		try {
			clone = (MetadataProperty)this.clone();
			clone.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			clone.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			clone.setChange(true);
			
			//type별로 이미지/파일일때 value == path로
			if(FILE_PROP.equals(this.getType()) || IMAGE_PROP.equals(this.getType())){
				
				//file upload;
				this.getFile().setFilePath(this.metadataXML.getFilePath());
				this.getFile().upload();
				
				clone.setValue(this.getFile().getUploadedPath());
			}else if(STRING_PROP.equals(this.getType())){
				clone.setValue(this.getValue());
			}
			
			metadataXML.getProperties().add(clone);
			metadataXML.init();
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return metadataXML;
	}
	
	@Available(when=MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent=true)
	public Object remove(){
		
		metadataXML.getProperties().remove(this);
		
		return metadataXML;
	}
	
	@Available(when=MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent=true)
	public Object edit(){
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.setName(this.getName());
		this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.setFile(this.getFile());
		
		return this;
	}
	
	@Available(when=MetaworksContext.WHEN_EDIT)
	@ServiceMethod(callByContent=true)
	public Object save() throws FileNotFoundException, IOException, Exception{
		
		int index = metadataXML.properties.indexOf(this);
		
		MetadataProperty editProperty = metadataXML.properties.get(index);
		editProperty.setName(this.getName());
		editProperty.setChange(true);
		editProperty.setType(this.getType());
		
		
		//type별로 이미지/파일일때 value == path로0
		if(FILE_PROP.equals(this.getType()) || IMAGE_PROP.equals(this.getType())){
			
			//file upload;
			this.getFile().setFilePath(metadataXML.getFilePath());
			this.getFile().upload();
			
			String filePath = this.metadataXML.getFilePath();
			String value = filePath.substring(0,filePath.lastIndexOf("\\"));
			
			editProperty.setFile(this.getFile());
			editProperty.setValue(value + "/" + this.getFile().getFilename());
			editProperty.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		}else if(STRING_PROP.equals(this.getType())){
			editProperty.setValue(value);
		}
		
		
		metadataXML.properties.remove(this);
		metadataXML.properties.add(index,editProperty);
		
		editProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return metadataXML;
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW) 
	public MetadataXML up() {
		int index = metadataXML.properties.indexOf(this);
		
		if(index > 0) {
			metadataXML.properties.remove(this);
			metadataXML.properties.add(index-1,this);
		}
		
		return metadataXML;
		
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW) 
	public MetadataXML down() {
		int index = metadataXML.properties.indexOf(this);
		
		if(index < metadataXML.properties.size()-1) {
			metadataXML.properties.remove(this);
			metadataXML.properties.add(index+1,this);
		}
		
		return metadataXML;
	}
	
	@Hidden
	@ServiceMethod(payload="type", eventBinding="change", bindingFor="type", bindingHidden=true, target=ServiceMethodContext.TARGET_SELF)
	public Object selectType() throws Exception {
		
		Class dstClass = null;
		
		WebObjectType srcWOT = MetaworksRemoteService.getInstance().getMetaworksType(this.getClass().getName());
		ObjectInstance srcInstance = (ObjectInstance) srcWOT.metaworks2Type().createInstance();
		srcInstance.setObject(this);
		
		for(FieldDescriptor fd : srcWOT.metaworks2Type().getFieldDescriptors()){
			Map<String, String> typeSelector = (Map<String, String>) fd.getAttribute("typeSelector");
			if(typeSelector!=null){
				
				String typeName = (String) srcInstance.getFieldValue(fd.getName());
				String selectedTypeClassName = typeSelector.get(typeName);
				
				if(selectedTypeClassName==null)
					break;
				
				dstClass = Thread.currentThread().getContextClassLoader().loadClass(selectedTypeClassName);
				
				break;
			}
		}
		
		if(dstClass == null)
			throw new Exception("type selector exception");
		
		WebObjectType dstWOT = MetaworksRemoteService.getInstance().getMetaworksType(dstClass.getName());
		ObjectInstance dstInstance = (ObjectInstance) dstWOT.metaworks2Type().createInstance();
		
		for(FieldDescriptor fd : dstWOT.metaworks2Type().getFieldDescriptors()){
			if(fd.getAttribute("ormapping")==null)
				dstInstance.setFieldValue(fd.getName(), srcInstance.getFieldValue(fd.getName()));
		}

		MetadataProperty metadataProperty = (MetadataProperty)dstInstance.getObject();
		metadataProperty.setMetaworksContext(new MetaworksContext());
		metadataProperty.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		return metadataProperty;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MetadataProperty){
			MetadataProperty metadataProperty = (MetadataProperty)obj;
			if(metadataProperty != null && metadataProperty.getName() != null && metadataProperty.getName().equals(this.getName())){
				return true;
			}
		}
		
		return false;
	}

	@Available(how="tree")
	@ServiceMethod(callByContent=true, except="child", mouseBinding="left", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] pick(){		
		
		if("folder".equals(this.getType()))
			return null;
		else{
			this.getMetaworksContext().setHow("picker");
			
			return new Object[]{new ToOpener(this), new Remover(new Popup())};
		}
	}

	
	@Available(how="picker")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
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

		
		for(MetadataProperty metadataProperty : metadataXML.getProperties()){
			metadataProperty.setMetaworksContext(new MetaworksContext());
			metadataProperty.getMetaworksContext().setHow("tree");
			
			if(MetadataProperty.IMAGE_PROP.equals(metadataProperty.getType())){
				mpImage.getChild().add(metadataProperty);
			}else if(MetadataProperty.PROCESS_PROP.equals(metadataProperty.getType())){
				mpProcess.getChild().add(metadataProperty);
			}else if(MetadataProperty.FILE_PROP.equals(metadataProperty.getType())){
				mpFile.getChild().add(metadataProperty);
			}else if(MetadataProperty.FORM_PROP.equals(metadataProperty.getType())){
				mpForm.getChild().add(metadataProperty);
			}else if(MetadataProperty.STRING_PROP.equals(metadataProperty.getType())){
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
	
	@ServiceMethod(callByContent=true, except="child")
	public Object[] appendProcessMap() throws Exception {
		String alias = "@" + this.getName();
			
		ProcessMap processMap = new ProcessMap();

		org.uengine.kernel.ProcessDefinition procDef = processManager.getProcessDefinition(alias);
		String fullCommandPhrase = procDef.getDescription().getText();
		
		if(fullCommandPhrase!=null){
			int commandCotentStarts = fullCommandPhrase.indexOf(':');
			if(-1 < commandCotentStarts){
				
				processMap.setCmPhrase(fullCommandPhrase);
				processMap.setCmTrgr(fullCommandPhrase.substring(0, commandCotentStarts));
			}
		}
			
		processMap.setMapId(session.getCompany().getComCode() + "." + alias);
		processMap.setDefId(alias);
		processMap.setName(this.getName());
		processMap.setComCode(session.getCompany().getComCode());
		
		if(!processMap.confirmExist())
			throw new Exception("$AlreadyAddedApp");

		processMap.createMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load(session);
		
		return new Object[]{processMapList, new Remover(new ModalWindow())};		
	}
}
