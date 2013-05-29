package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("MetadataProperty")
public class MetadataProperty {
	@XStreamAsAttribute
	String type;
	@XStreamAsAttribute
	boolean isKeyEditable;
	
	String name;
	String value;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isKeyEditable() {
		return isKeyEditable;
	}
	public void setKeyEditable(boolean isKeyEditable) {
		this.isKeyEditable = isKeyEditable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public String toXmlXStream(){
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(this);
	}
	
}
