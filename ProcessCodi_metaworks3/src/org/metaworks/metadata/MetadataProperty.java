package org.metaworks.metadata;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.TypeSelector;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.view.Navigator;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"name,type,value,file"})
@XStreamAlias("MetadataProperty")
public class MetadataProperty implements Cloneable {
	
	public final static String FILE_PROP = "file";
	public final static String FORM_PROP = "form";
	public final static String IMAGE_PROP = "img";
	public final static String STRING_PROP = "string";
	public final static String PROCESS_PROP = "process";
	
	@AutowiredFromClient
	public MetadataXML metadataXML;
	
	public MetadataProperty() {
		setFile(new MetadataFile()); 
	}
	
	@XStreamAsAttribute
	String type;
		@Range(
				options={"File",	"Image",	"Process",	"String",	"Form"}, 
				values ={"file",	"img",		"process",	"string",	"form"}
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

	String name;
		@Id
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	

	String value;
		@Available(where={STRING_PROP,"ide"})
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	
	@XStreamOmitField
	MetadataFile file;
		@Available(where={IMAGE_PROP,FILE_PROP})
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
			
			//file upload;
			this.getFile().setFilePath(metadataXML.getFilePath());
			this.getFile().upload();
			
			//type별로 이미지/파일일때 value == path로0
			if(FILE_PROP.equals(this.getType()) || IMAGE_PROP.equals(this.getType())){
				String filePath = this.metadataXML.getFilePath();
				String value = filePath.substring(0,filePath.lastIndexOf("\\"));
				clone.setValue(value + "/" + this.getFile().getFilename());
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
	
	@Hidden
	@ServiceMethod(payload="type", eventBinding="change", bindingFor="type", bindingHidden=true, target=ServiceMethodContext.TARGET_SELF)
	public Object selectType() {
		if(FILE_PROP.equals(this.getType()) || IMAGE_PROP.equals(this.getType())){
			this.getMetaworksContext().setWhere(FILE_PROP);
		}else if(PROCESS_PROP.equals(this.getType())){
			this.getMetaworksContext().setWhere(PROCESS_PROP);
		}else if(STRING_PROP.equals(this.getType())){
			this.getMetaworksContext().setWhere(STRING_PROP);
		}else if(FORM_PROP.equals(this.getType())){
			this.getMetaworksContext().setWhen(FORM_PROP);
		}else {
			this.getMetaworksContext().setWhere("");
		}
		return this;
		
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
	
	
}
