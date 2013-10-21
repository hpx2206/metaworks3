package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Children;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("MetadataProperty")
public class MetadataPropertyBase implements ContextAware, Cloneable {

	public final static String FILE_PROP = "file";
	public final static String FORM_PROP = "form";
	public final static String IMAGE_PROP = "image";
	public final static String STRING_PROP = "string";
	public final static String PROCESS_PROP = "process";

	@AutowiredFromClient
	public MetadataXMLBase metadataXML;

	public MetadataPropertyBase() {
		setMetaworksContext(new MetaworksContext());
	}

	@XStreamOmitField
	ArrayList<MetadataPropertyBase> child;
	@Children
	@Hidden
	public ArrayList<MetadataPropertyBase> getChild() {
		return child;
	}
	public void setChild(ArrayList<MetadataPropertyBase> child) {
		this.child = child;
	}

	@XStreamAsAttribute
	String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

}
