package org.uengine.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.metaworks.MetaworksException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("resources")
public class WebServiceDefinition implements Cloneable {
	
	@XStreamAsAttribute
	String base;
		public String getBase() {
			return base;
		}
		public void setBase(String base) {
			this.base = base;
		}
	
	ArrayList<ResourceProperty> resourceList;
		public ArrayList<ResourceProperty> getResourceList() {
			return resourceList;
		}
		public void setResourceList(ArrayList<ResourceProperty> resourceList) {
			this.resourceList = resourceList;
		}

	public WebServiceDefinition(){
		resourceList = new ArrayList<ResourceProperty>();
	}
	
	public WebServiceDefinition loadWithPath(String filePath){
		WebServiceDefinition webDefinition = null;
		FileInputStream fin = null;
		File file = new File(filePath);
		if(file.exists()){
			try {
				fin = new FileInputStream(filePath);
				webDefinition = loadWithInputstream(fin);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				if( fin != null ){
					try { fin.close(); } catch (IOException e) { e.printStackTrace(); }
					fin = null;
				}
			}
		}
		return webDefinition;
	}
	
	public WebServiceDefinition loadWithInputstream(InputStream stream){
		WebServiceDefinition webDefinition = null;
		try{
			XStream xstream = new XStream();
			xstream.alias("resources", WebServiceDefinition.class);
			xstream.alias("resource", ResourceProperty.class);
			xstream.alias("method", MethodProperty.class);
			xstream.alias("param", ParameterProperty.class);
			xstream.autodetectAnnotations(true);
			webDefinition = (WebServiceDefinition)xstream.fromXML( stream );
		}catch(Exception e){
			System.err.println(new MetaworksException("파일이 없거나 온전하지 않습니다."));
		}
		return webDefinition;
	}
}
