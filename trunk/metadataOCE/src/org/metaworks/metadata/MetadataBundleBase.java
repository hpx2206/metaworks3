package org.metaworks.metadata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;

public class MetadataBundleBase {
	
	static String codiServerUrl = "http://uenginecloud.org:7070/uengine-web/";
//	static String codiServerUrl = "http://localhost:8080/uengine-web/";
	
	public static Properties projectBundle;
		public Properties getProjectBundle() {
			return projectBundle;
		}
		public void setProjectBundle(Properties projectBundle) {
			MetadataBundleBase.projectBundle = projectBundle;
		}
		
	public static HashMap<String, Properties> projectProperty = new HashMap<String, Properties>();
	
	public MetadataBundleBase(){
	}

	public void loadProperty() throws Exception{
		String projectId = getProjectId();
		String metadataUrl = GlobalContext.getPropertyString("metadataUrl", codiServerUrl);
		this.loadProperty(projectId , metadataUrl);
	}
	public void loadProperty(String projectId) throws Exception{
		String metadataUrl = GlobalContext.getPropertyString("metadataUrl", codiServerUrl);
		this.loadProperty(projectId , metadataUrl);
	}
		
	public void loadProperty(String projectId , String metadataUrl) throws Exception{
		GlobalContext.getProperties();
		GlobalContext.setProperty("metadataKey", projectId);
		GlobalContext.setProperty("metadataUrl", metadataUrl);
		String sourceCodeBase = GlobalContext.getCodeBaseRoot();
		String tenantId = "root";
		if(TenantContext.getThreadLocalInstance()!=null && TenantContext.getThreadLocalInstance().getTenantId()!=null){
			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		}
		String projectBasePath = getProjectBasePath(projectId, tenantId);
		
		String metadataFileName = "uengine.metadata";
		
		String metadataFilePath = projectBasePath + File.separatorChar + metadataFileName;
		File metadataFile = new File(metadataFilePath);
		
//		if( !metadataFile.exists() ){
			if( !metadataFile.getParentFile().exists() ){
				metadataFile.getParentFile().mkdirs();
			}
//			metadataFile.createNewFile();
			metadataFile = getPropertyRemote(projectId, tenantId ,metadataFile);
//		}
		MetadataXMLBase metadataXML = new MetadataXMLBase();
		Properties props = new Properties();
		
		if( metadataFile.exists() ){
			metadataXML = metadataXML.loadWithPath(metadataFile.getPath());
			
			props.put("sourceCodePath", projectBasePath);
			setMetadataProperties(props, metadataXML);
		}
		
		String fullPath = sourceCodeBase + File.separatorChar + metadataFileName;
		File tenantMetadataFile = new File(fullPath);
		if( tenantMetadataFile.exists()){
			MetadataXMLBase tenantMetadataXML = new MetadataXMLBase();
			tenantMetadataXML = metadataXML.loadWithPath(tenantMetadataFile.getPath());
			setMetadataProperties(props, tenantMetadataXML);
		}
		
//		projectProperty.put(projectId, props);
		projectBundle = props;
	}
	
	public void setMetadataProperties(Properties props, MetadataXMLBase metadataXML){
		ArrayList<MetadataPropertyBase> properties =  metadataXML.getProperties();
		if( properties != null ){
			for( MetadataPropertyBase metadataProperty : properties){
				String key = metadataProperty.getName();
				String value = metadataProperty.getValue();
//				if( !"String".equals(metadataProperty.getType()) ){
//					value = metadataProperty.getType()+ File.separatorChar +value;
//				}
//				if( "form".equals(metadataProperty.getType())){
//					value = metadataProperty.getType()+ "." +value;
//				}
				if( "image".equals(metadataProperty.getType())){
					value = "metadata" + File.separatorChar + value + "?type=" + metadataProperty.getType();
				}
				// TODO others
				props.put(key, value);
			}
		}
	}
	private File getPropertyRemote(String projectId, String tenantId, File metadataFile){
		String requestUrl = "metadata/getMetadataFile";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(GlobalContext.getPropertyString("metadataUrl", codiServerUrl) + requestUrl);
		getMethod.setQueryString(new NameValuePair[] { 
				new NameValuePair("projectId", projectId) ,
				new NameValuePair("tenantId", tenantId) ,
				new NameValuePair("metadataFileName", metadataFile.getName()) 
		});
		
		OutputStream out = null;
		InputStream in = null;
		try{
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {  
					in = new BufferedInputStream(getMethod.getResponseBodyAsStream());  
					MetadataXMLBase metadataXML = new MetadataXMLBase();
					metadataXML = metadataXML.loadWithInputstream(in);
					if( metadataXML != null ){
						
						ArrayList<MetadataPropertyBase> properties =  metadataXML.getProperties();
						if( properties != null ){
							String metadataPath;
							if( tenantId != null){
								metadataPath = MetadataBundleBase.getProjectBasePath(projectId , tenantId);
							}else{
								metadataPath = MetadataBundleBase.getProjectBasePath(projectId);
							}
							for( MetadataPropertyBase metadataProperty : properties){
								String value = metadataProperty.getValue();
								if( !"string".equalsIgnoreCase(metadataProperty.getType())){
									String filePath = null;
//									if( "image".equalsIgnoreCase(metadataProperty.getType()) ){
//										filePath = metadataPath+ File.separatorChar + metadataProperty.getType() + File.separatorChar + value;
//									}else{
										value = value.replaceAll("\\", File.separator);
										filePath = metadataPath + File.separatorChar + value;
//									}
									
									File checkFile = new File(filePath);
//									if( !checkFile.exists() ){
										if( !checkFile.getParentFile().exists() ){
											checkFile.getParentFile().mkdirs();
										}
//										String requsetPath =  metadataProperty.getType() + File.separatorChar + value;
//										String fileFullPath =  metadataPath+ File.separatorChar + requsetPath;
										makeFileFromRemote(projectId , tenantId, value , filePath);
	//									metadataProperty.setRemote(true);
//									}
								}
							}
						}
						XStream stream = new XStream();
						stream.autodetectAnnotations(true);
						
						out = new BufferedOutputStream(new FileOutputStream(metadataFile));  
						stream.toXML(metadataXML, out);
					}
			}  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in != null){
				try { in.close(); in = null; } catch (IOException e) {		e.printStackTrace();}
			}
			if(out != null){
				try { out.close(); out = null; } catch (IOException e) {		e.printStackTrace();}
			}
			getMethod.releaseConnection();
		}
		return metadataFile;
	}
	
	private void makeFileFromRemote( String projectId, String tenantId, String requsetPath, String fileFullPath) throws Exception{
		String requestUrl = "metadata/getMetadataFile";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(GlobalContext.getPropertyString("metadataUrl", codiServerUrl) + requestUrl);
		getMethod.setQueryString(new NameValuePair[] { 
				new NameValuePair("projectId", projectId) ,
				new NameValuePair("tenantId", tenantId) ,
				new NameValuePair("metadataFileName", requsetPath) 
		});
		
		OutputStream out = null;
		InputStream inputStream = null;
		
		try{
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {  
				inputStream = new BufferedInputStream(getMethod.getResponseBodyAsStream()); 
				out = new FileOutputStream(fileFullPath);
				
				int read = 0;
				byte[] bytes = new byte[1024];
				
				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(inputStream != null){
				try { inputStream.close(); inputStream = null; } catch (IOException e) {		e.printStackTrace();}
			}
			if(out != null){
				try { out.close(); out = null; } catch (IOException e) {		e.printStackTrace();}
			}
			getMethod.releaseConnection();
		}
		
	}
	public void changeProjectProperty(String projectId) throws Exception{
		// TODO
	}
	
	public static String getProjectBasePath(String projectId){
		return getProjectBasePath(projectId, "root");
	}
	
	public static String getProjectBasePath(String projectId, String tenantId){
		String codebase = GlobalContext.getCodeBaseRoot();
		String projectBasePath = null;
		if( projectId == null ){
			projectBasePath = codebase + tenantId;
		}else{
			projectBasePath = codebase + projectId+ File.separatorChar + tenantId;
		}
		
		return projectBasePath;
	}
	
	public static String getProjectId(){
		String projectKey = GlobalContext.getPropertyString("metadataKey", null );
		String projectId = projectKey;
		
		return projectId;
	}
	
	public static String getBundleData(String key){
		return MetadataBundleBase.projectBundle.getProperty(key);
	}
	
	public static String getBundleForm(String key, JSONObject jsonObject){
		return null;
	}
}
