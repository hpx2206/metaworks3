package org.metaworks.metadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("metadata")
public class MetadataXML {
	
	public MetadataXML() {
		init();
	}
	
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
	ArrayList<MetadataProperty> properties;
		public ArrayList<MetadataProperty> getProperties() {
			int index;
			
			for(index=0; index < properties.size(); index++){
				properties.get(index).setIndex(index);
			}
			return properties;
		}
		public void setProperties(ArrayList<MetadataProperty> properties) {
			this.properties = properties;
		}
//	ArrayList<MetadataDefinition> definitions;
//		public ArrayList<MetadataDefinition> getDefinitions() {
//			return definitions;
//		}
//		public void setDefinitions(ArrayList<MetadataDefinition> definitions) {
//			this.definitions = definitions;
//		}
//	ArrayList<MetadataRule> rules;
//		public ArrayList<MetadataRule> getRules() {
//			return rules;
//		}
//		public void setRules(ArrayList<MetadataRule> rules) {
//			this.rules = rules;
//		}
	MetadataProperty newMetadataProperty;
		public MetadataProperty getNewMetadataProperty() {
			return newMetadataProperty;
		}
		public void setNewMetadataProperty(MetadataProperty newMetadataProperty) {
			this.newMetadataProperty = newMetadataProperty;
		}

	
	public MetadataXML loadWithPath(String filePath){
		MetadataXML metadata = null;
		FileInputStream fin;
		try {
			fin = new FileInputStream(filePath);
			metadata = loadWithInputstream(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return metadata;
	}
	public MetadataXML loadWithInputstream(InputStream stream){
		MetadataXML metadata = null;
		XStream xstream = new XStream();
		xstream.alias("metadata", MetadataXML.class);
		xstream.alias("MetadataProperty", MetadataProperty.class);
		xstream.autodetectAnnotations(true);
		metadata = (MetadataXML)xstream.fromXML( stream );
		return metadata;
	}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public String toXmlXStream(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(this);
	}
	
	public static void main(String[] args) {
//		XStream stream = new XStream();
//		stream.autodetectAnnotations(true);
//		
//		MetadataXML xml = new MetadataXML();
//		xml.setCompany("uengine");
//		xml.setType("project");
//		xml.setTypeName("오키도키");
//		
//		ArrayList<MetadataProperty> properties = new ArrayList<MetadataProperty>();
//		
//		MetadataProperty type1 = new MetadataProperty();
//		type1.setType("file");
//		type1.setKeyEditable(false);
//		type1.setName("filename");
//		type1.setValue("file value");
//		
//		MetadataProperty type2 = new MetadataProperty();
//		type2.setType("img");
//		type2.setKeyEditable(false);
//		type2.setName("imgname");
//		type2.setValue("img value");
//		
//		MetadataProperty type3 = new MetadataProperty();
//		type2.setType("string");
//		type2.setKeyEditable(false);
//		type2.setName("ss");
//		type2.setValue("ss");
//		
//		properties.add(type1);
//		properties.add(type2);
//		properties.add(type3);
//		
//		xml.setProperties(properties);
//		System.out.println(stream.toXML(xml));
//		
//		
//		MetadataXML xml1 = new MetadataXML();
//		
//		ArrayList<MetadataProperty> properties1 = new ArrayList<MetadataProperty>();
//		properties1.add(type3);
//		xml.setProperties(properties1);
//		System.out.println(stream.toXML(xml));
	}
	
	protected void init() {
		newMetadataProperty = new MetadataProperty();
		newMetadataProperty.metaworksContext = new MetaworksContext();
		newMetadataProperty.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newMetadataProperty.metaworksContext.setWhere("ide");
		
		
		newMetadataProperty.selectedType = new SelectBox();
		newMetadataProperty.selectedType.add("텍스트", "string");
		newMetadataProperty.selectedType.add("파일", "file");
		newMetadataProperty.selectedType.add("이미지", "img");
		newMetadataProperty.selectedType.add("프로세스", "process");
		newMetadataProperty.selectedType.setSelected(newMetadataProperty.selectedType.getOptionValues().get(0));
	}
	
}
