package org.metaworks.metadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"type,typeName,filePath,company,properties,newMetadataProperty"})
@XStreamAlias("metadata")
public class MetadataXML implements ContextAware {
	
	public MetadataXML() {
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
	
	@XStreamOmitField
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
			e.printStackTrace();
		}
		
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(this);
	}
	
	protected void init() throws Exception {
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		newMetadataProperty = new MetadataProperty();
		newMetadataProperty.metaworksContext = new MetaworksContext();
		newMetadataProperty.metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		newMetadataProperty.metaworksContext.setWhere("ide");
		newMetadataProperty.setType(MetadataProperty.STRING_PROP);
		newMetadataProperty = (MetadataProperty) newMetadataProperty.selectType();
		
	}
	
	public MetadataXML loadWithResourceNode(ResourceNode resourceNode) throws Exception{
		MetadataXML metadata = loadWithPath(resourceNode.getPath());
		metadata.setFilePath(resourceNode.getId());
		
		
		for(MetadataProperty metadataProperty : metadata.getProperties()){
			if(MetadataProperty.FILE_PROP.equals(metadataProperty.getType()) || metadataProperty.IMAGE_PROP.equals(metadataProperty.getType())){
				MetadataFile file = new MetadataFile();
				file.setFilePath(resourceNode.getId());
				file.setUploadedPath(metadataProperty.getValue());
				file.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				metadataProperty.setFile(file);
			}
		}
			
		metadata.init();
		
		return metadata;
	}
	
	public boolean save(){
		
		for(int i=0; i<this.getProperties().size(); i++){
			MetadataProperty metadataProperty = this.getProperties().get(i);
			
			if(metadataProperty.isChange() && metadataProperty.getType().equals("file")){
				
			}
		}
		
		return true;
	}
	
}
