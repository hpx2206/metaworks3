package org.metaworks.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("metadata")
public class MetadataXMLBase {
	
	public MetadataXMLBase() {
	}
	
	@XStreamOmitField
	String filePath;
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
		
	ArrayList<MetadataPropertyBase> properties;
		public ArrayList<MetadataPropertyBase> getProperties() {
			return properties;
		}
		public void setProperties(ArrayList<MetadataPropertyBase> properties) {
			this.properties = properties;
		}
	
	public MetadataXMLBase loadWithPath(String filePath){
		MetadataXMLBase metadata = null;
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
	
	public MetadataXMLBase loadWithInputstream(InputStream stream){
		MetadataXMLBase metadata = null;
		try{
			XStream xstream = new XStream();
			xstream.alias("metadata", MetadataXMLBase.class);
			xstream.alias("MetadataProperty", MetadataPropertyBase.class);
			xstream.autodetectAnnotations(true);
			metadata = (MetadataXMLBase)xstream.fromXML( stream );
		}catch(Exception e){
//			e.printStackTrace();
			System.err.println(new Exception("메타데이터 파일이 없거나 온전하지 않습니다."));
		}
		return metadata;
	}
		
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
	
}
