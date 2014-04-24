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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;

public class MetadataBundle {
	
	public static Properties projectBundle;
		public Properties getProjectBundle() {
			return projectBundle;
		}
		public void setProjectBundle(Properties projectBundle) {
			MetadataBundle.projectBundle = projectBundle;
		}
		
	// HashMap<프로젝트 아이디, Properties>
	public static HashMap<String, Properties> projectProperty = new HashMap<String, Properties>();
	
	public MetadataBundle(){
		this(null);
	}
	public MetadataBundle(String projectId){
		// 1. 앱이 처음 만들어 졌을때 xml 데이터를 쓰고, 파일등의 리소스를 모두 다운받는다...
		// 2. 앱이 구동될때, 리소스들을 모두 메모리에 담아 놓는다.. ( MetadataBundle 은 각 앱에서 1회 호출된다 )
		// 3. 로컬 경로에서 모두 리소스를 가져온다. - 
		// 4. TODO 추후에 - 변경로직 체크 ,  스케쥴링을 걸어서 변경된 부분을 담는 작업..
		
		// url 호출을 잘해야겠네..   
		// 프로젝트 id를 생성할때  http://회사명.processcodi.com/프로젝트명/img/log.jpg
		// 프로젝트 id를 생성할때  http://회사명.processcodi.com/img/log.jpg?appId=프로젝트명
		
		// 로컬에 있는 리소스를 보고 판단하기...
		// 프로퍼티의 type 이 local , remote 로 나누어진다... 
		/*
		 * local 은 해당 앱에서 프로퍼티를 생성하여서 로컬에 경로를 가지고 있을때
		 * remote 는 원격에서 파일을 가져와서 복사한걸... remote라 칭하고, 추후에 변경로직 체크하여 데이터를 새로 받아올때 사용한다.
		 */
	}

	
	@ServiceMethod
	public void loadProperty() throws Exception{
		
//		Thread.currentThread().getContextClassLoader().getResourceAsStream("image/logo.jpg");
//		Thread.currentThread().getContextClassLoader().getResourceAsStream("process/test.wpd");
//		Thread.currentThread().getContextClassLoader().loadClass("form.TestForm");
//		Thread.currentThread().getContextClassLoader().getResourceAsStream("uengine.metadata");
		
		
		String projectId = getProjectId();
		String sourceCodeBase = CodiClassLoader.getMyClassLoader().getCodebase();
		String tenantId = "root";
		if(TenantContext.getThreadLocalInstance()!=null && TenantContext.getThreadLocalInstance().getTenantId()!=null){
			tenantId = TenantContext.getThreadLocalInstance().getTenantId();
		}
		String projectBasePath = getProjectBasePath(projectId, tenantId);
		
		// 앱의 루트 경로 생성 
		// 1. 해당 앱의 메인 경로에 있는 uengine.metadata 파일을 찾는다.
		String metadataFileName = "uengine.metadata";
		
		// metadata file 확인 : codebase/appId/root
		String metadataFilePath = projectBasePath + File.separatorChar + metadataFileName;
		File metadataFile = new File(metadataFilePath);
		
		// 무조건 파일들을 내려받도록 설정 - if( !metadataFile.exists() ){  << 주석 처리
//		if( !metadataFile.exists() ){
			if( !metadataFile.getParentFile().exists() ){
				metadataFile.getParentFile().mkdirs();
			}
//			metadataFile.createNewFile();
			metadataFile = getPropertyRemote(projectId, tenantId ,metadataFile);
//		}
		MetadataXML metadataXML = new MetadataXML();
		Properties props = new Properties();
		
		if( metadataFile.exists() ){
			metadataXML = metadataXML.loadWithPath(metadataFile.getPath());
			
			props.put("sourceCodePath", projectBasePath);
			setMetadataProperties(props, metadataXML);
		}
		
		// tenant metadata file 확인 : codebase/appId/tenant
		String fullPath = sourceCodeBase + File.separatorChar + metadataFileName;
		File tenantMetadataFile = new File(fullPath);
		if( tenantMetadataFile.exists()){
			MetadataXML tenantMetadataXML = new MetadataXML();
			tenantMetadataXML = metadataXML.loadWithPath(tenantMetadataFile.getPath());
			setMetadataProperties(props, tenantMetadataXML);
		}
		
//		projectProperty.put(projectId, props);
		projectBundle = props;
	}
	
	public void setMetadataProperties(Properties props, MetadataXML metadataXML){
		ArrayList<MetadataProperty> properties =  metadataXML.getProperties();
		if( properties != null ){
			for( MetadataProperty metadataProperty : properties){
				String key = metadataProperty.getName();
				String value = metadataProperty.getValue();
//				if( !"String".equals(metadataProperty.getType()) ){
//					value = metadataProperty.getType()+ File.separatorChar +value;
//				}
				if( "form".equals(metadataProperty.getType())){
//					value = metadataProperty.getType()+ "." +value;
				}
				if( "image".equals(metadataProperty.getType())){
					value = "metadata" + File.separatorChar + value + "?type=" + metadataProperty.getType();
				}
				// TODO others
				props.put(key, value);
			}
		}
	}
	/**
	 * 앱에서 메인서버의 프로젝트로 메타데이터 파일 및 리소스를 요청
	 * 관점 : 앱에서 서블릿을 통하여 호출
	 */
	private File getPropertyRemote(String projectId, String tenantId, File metadataFile){
		// 코디 서버로 요청 '/metadata' 서블릿을 통하여 요청함
		String codiServerUrl = GlobalContext.getPropertyString("metadataUrl", "http://localhost:8080/uengine-web/");
		String requestUrl = "metadata/getMetadataFile";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(codiServerUrl + requestUrl);
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
					MetadataXML metadataXML = new MetadataXML();
					metadataXML = metadataXML.loadWithInputstream(in);
					if( metadataXML != null ){
						// metadataXML 안쪽에 isFirst 같은 변수를 두어서.. 자기 프로젝트의 모든 리소스를 내려받는 작업이 필요하다.
						// TODO 우선 모두 가져오는 방식으로..
						
						ArrayList<MetadataProperty> properties =  metadataXML.getProperties();
						if( properties != null ){
							String metadataPath;
							if( tenantId != null){
								metadataPath = MetadataBundle.getProjectBasePath(projectId , tenantId);
							}else{
								metadataPath = MetadataBundle.getProjectBasePath(projectId);
							}
							for( MetadataProperty metadataProperty : properties){
								// for 문을 돌면서 해당 경로에 데이터가 없다면 데이터를 요청하고, remote 를  true로 준다.
								String value = metadataProperty.getValue();
								if( !"string".equalsIgnoreCase(metadataProperty.getType())){
									String filePath = null;
//									if( "image".equalsIgnoreCase(metadataProperty.getType()) ){
//										filePath = metadataPath+ File.separatorChar + metadataProperty.getType() + File.separatorChar + value;
//									}else{
										filePath = metadataPath + File.separatorChar + value;
//									}
									
									File checkFile = new File(filePath);
//									if( !checkFile.exists() ){
										if( !checkFile.getParentFile().exists() ){
											checkFile.getParentFile().mkdirs();
										}
										// 파일이 없다면 메인서버에서 리소스를 가져온다.
//										String requsetPath =  metadataProperty.getType() + File.separatorChar + value;
//										String fileFullPath =  metadataPath+ File.separatorChar + requsetPath;
										makeFileFromRemote(projectId , tenantId, value , filePath);
										// 원격에서 가져왔다는걸 명시해준다.
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
	/**
	 * 로컬의 메인 바로 아래에 있는 메타데이터 파일을 읽어서 프로퍼티로 생성을 해 놓는다.
	 * 관점 : 앱에서 호출
	 */
	private void makeFileFromRemote( String projectId, String tenantId, String requsetPath, String fileFullPath) throws Exception{
		String codiServerUrl = GlobalContext.getPropertyString("metadataUrl", "http://localhost:8080/uengine-web/");
		String requestUrl = "metadata/getMetadataFile";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(codiServerUrl + requestUrl);
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
	
	/**
	 * 메타데이타파일이 변경되었을때, 이 메서드를 호출해서 프로퍼티값을 변경시켜놓는다.
	 * @param projectId
	 * @throws Exception
	 */
	public void changeProjectProperty(String projectId) throws Exception{
		// TODO
	}
	
	public static String getProjectBasePath(String projectId){
		return getProjectBasePath(projectId, "root");
	}
	
	public static String getProjectBasePath(String projectId, String tenantId){
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String projectBasePath = null;
		if( projectId == null ){
			projectBasePath = codebase + File.separatorChar + tenantId;
		}else{
			projectBasePath = codebase + File.separatorChar + projectId+ File.separatorChar + tenantId;
		}
		
		return projectBasePath;
	}
	
	public static String getProjectId(){
		String projectKey = GlobalContext.getPropertyString("metadataKey", "codi" );
		// TODO projectKey 가 암호화 키로 온다는 가정하에 구하는 로직 필요함
		String projectId = projectKey;
		
		return projectId;
	}
	
	public static String getBundleData(String key){
		return MetadataBundle.projectBundle.getProperty(key);
	}
}
