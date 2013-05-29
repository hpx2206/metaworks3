package org.uengine.codi.mw3.ide.editor.metadata;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("metadata")
public class MatadataXML {
	
	@XStreamOmitField
	String filePath;
		@Id
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
	String company;
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	String typeName;
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
	@XStreamAlias("metadataProperties")
	ArrayList<MetadataProperty> properties;
		public ArrayList<MetadataProperty> getProperties() {
			return properties;
		}
		public void setProperties(ArrayList<MetadataProperty> properties) {
			this.properties = properties;
		}
	@XStreamAlias("metadataDefinitions")
	ArrayList<MetadataDefinition> definitions;
		public ArrayList<MetadataDefinition> getDefinitions() {
			return definitions;
		}
		public void setDefinitions(ArrayList<MetadataDefinition> definitions) {
			this.definitions = definitions;
		}
	@XStreamAlias("metadataRules")
	ArrayList<MetadataRule> rules;
		public ArrayList<MetadataRule> getRules() {
			return rules;
		}
		public void setRules(ArrayList<MetadataRule> rules) {
			this.rules = rules;
		}
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup addProperty(){
		Popup popup = new Popup();
		AddPropertyPopup addPropertyPopup = new AddPropertyPopup();
		addPropertyPopup.filePath = filePath;
		popup.setWidth(350);
		popup.setHeight(250);
		popup.setPanel(addPropertyPopup);
		return popup;
	}
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public void save(){
		
	}
		
	public static void main(String[] args) {
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		MatadataXML xml = new MatadataXML();
		xml.setCompany("uengine");
		xml.setType("project");
		xml.setTypeName("오키도키");
		
		ArrayList<MetadataProperty> properties = new ArrayList<MetadataProperty>();
		
		MetadataProperty type1 = new MetadataProperty();
		type1.setType("file");
		type1.setKeyEditable(false);
		type1.setName("filename");
		type1.setValue("file value");
		
		MetadataProperty type2 = new MetadataProperty();
		type2.setType("img");
		type2.setKeyEditable(false);
		type2.setName("imgname");
		type2.setValue("img value");
		
		properties.add(type1);
		properties.add(type2);
		
		xml.setProperties(properties);
		System.out.println(stream.toXML(xml));
	}
}
