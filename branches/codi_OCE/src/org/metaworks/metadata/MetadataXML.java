package org.metaworks.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.ResourceNode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
		@Hidden
		public String getCompany() {
			return company;
		}	
		public void setCompany(String company) {
			this.company = company;
		}
		
	String type;
		@Hidden
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	String typeName;
		@Hidden
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		
	ArrayList<MetadataProperty> properties;
		public ArrayList<MetadataProperty> getProperties() {
/*			int index;
			if( properties != null ){
				for(index=0; index < properties.size(); index++){
					properties.get(index).setIndex(index);
				}
			}else{
				properties = new ArrayList<MetadataProperty>();
			}*/
			return properties;
		}
		public void setProperties(ArrayList<MetadataProperty> properties) {
			this.properties = properties;
		}
	
	@XStreamOmitField
	MetadataPropertyInfo metadataPropertyInfo;
		public MetadataPropertyInfo getMetadataPropertyInfo() {
			return metadataPropertyInfo;
		}
		public void setMetadataPropertyInfo(MetadataPropertyInfo metadataPropertyInfo) {
			this.metadataPropertyInfo = metadataPropertyInfo;
		}
	
	public MetadataXML loadWithPath(String filePath){
		MetadataXML metadata = null;
		FileInputStream fin = null;
		File file = new File(filePath);
		if(file.exists()){
			try {
				fin = new FileInputStream(filePath);
				metadata = loadWithInputstream(fin);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				if( fin != null ){
					try { fin.close(); } catch (IOException e) { e.printStackTrace(); }
					fin = null;
				}
			}
		}
		return metadata;
	}
	
	public MetadataXML loadWithInputstream(InputStream stream){
		MetadataXML metadata = null;
		try{
			XStream xstream = new XStream();
			xstream.alias("metadata", MetadataXML.class);
			xstream.alias("MetadataProperty", MetadataProperty.class);
			xstream.autodetectAnnotations(true);
			metadata = (MetadataXML)xstream.fromXML( stream );
		}catch(Exception e){
			System.err.println(new MetaworksException("메타데이터 파일이 없거나 온전하지 않습니다."));
		}
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

		ResourceNode resourceNode = new ResourceNode(); 
		resourceNode.setId(this.getFilePath());

		MetadataProperty newMetadataProperty = new MetadataProperty();
		newMetadataProperty.metaworksContext = new MetaworksContext();
		newMetadataProperty.metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		newMetadataProperty.metaworksContext.setWhere("ide");
		newMetadataProperty.setType(MetadataProperty.STRING_PROP);
		newMetadataProperty = (MetadataProperty) newMetadataProperty.selectType();
		newMetadataProperty.setResourceNode(resourceNode);
		
		MetadataPropertyInfo metadataPropertyInfo = new MetadataPropertyInfo();
		metadataPropertyInfo.setNewMetadataProperty(newMetadataProperty);

		
		this.setMetadataPropertyInfo(metadataPropertyInfo);
	}
	
	public MetadataXML loadWithResourceNode(ResourceNode resourceNode) throws Exception{
		MetadataXML metadata = loadWithPath(resourceNode.getPath());
		if( metadata == null ){
			metadata = new MetadataXML();
		}
		metadata.setFilePath(resourceNode.getPath());
		
		
		for(MetadataProperty metadataProperty : metadata.getProperties()){
			if(MetadataProperty.FILE_PROP.equals(metadataProperty.getType()) || MetadataProperty.IMAGE_PROP.equals(metadataProperty.getType())){
//				MetadataFile file = new MetadataFile();
//				file.setUploadedPath(metadataProperty.getValue());
//				file.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
//				file.setMimeType(ResourceNode.findNodeType(metadataProperty.getValue()));
//				metadataProperty.setFile(file);
				String projectSourcePath = CodiClassLoader.mySourceCodeBase(resourceNode.getProjectId());
				MetadataFile file = new MetadataFile();
				file.setBaseDir(projectSourcePath);
				file.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				file.setUploadedPath(metadataProperty.getValue());
				file.setMimeType(ResourceNode.findNodeType(metadataProperty.getValue()));
				
				
				MetadataFile previewFile = new MetadataFile();
				previewFile.setBaseDir(projectSourcePath);
				previewFile.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				previewFile.setUploadedPath(metadataProperty.getValue());
				previewFile.setMimeType(ResourceNode.findNodeType(metadataProperty.getValue()));
				
				metadataProperty.setFile(file);
				metadataProperty.setFilePreview(previewFile);
			}
			metadataProperty.selectType();
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
