package org.uengine.codi.mw3.ide;

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
import org.apache.commons.io.IOUtils;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataProperty;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataXML;
import org.uengine.kernel.GlobalContext;

import com.thoughtworks.xstream.XStream;

public class MetadataBundle {
	
	Properties projectBundle;
		public Properties getProjectBundle() {
			return projectBundle;
		}
		public void setProjectBundle(Properties projectBundle) {
			this.projectBundle = projectBundle;
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
		
//		try {
//			File mainFile = findMetadataFile( projectId );
//			if(!mainFile.exists()){
//				throw new Exception("can not find file");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

//	public Object getMetadataBundel(String projectId, String key) throws Exception{
//		// TODO 키값에 해당하는 번들을 찾고, key값에 맞는 값을 리턴해준다.
//		if( !projectProperty.containsKey(projectId) ){
//			loadProjectProperty();
//		}
//		projectBundle = projectProperty.get(projectId);
//		
//		if( projectBundle != null ){
//			return projectBundle.get(key);
//		}else{
//			return null;
//		}
//	}
	
//	private File findMetadataFile(String projectId) throws Exception{
//		
//		projectId = "uengine.project.uu";
//		
//		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
//		String mainPath = codebase + projectId;
//		String mainFileName = "uengine.metadata";	// TODO
//		
//		File mainFile = new File(mainPath + File.separatorChar + mainFileName);
//		return mainFile;
//	}
	
	@ServiceMethod
	public void loadProjectProperty() throws Exception{
		loadProjectProperty(null);
	}
	public void loadProjectProperty(String metadataPath) throws Exception{
		if( metadataPath == null ){
			
		}else{
			// metadataPath = 
		}
		// 프로젝트 및 앱이 실행이 될때 uengine.properties 에 metadataKey 의 값을 가져와서 url을 생성한다.
		// TODO value는 변경될수 있음
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String projectKey = GlobalContext.getPropertyString("metadataKey", "metadataKey");
		String projectId = null;
		String tanentId = null;
		if( projectKey != null ){	// uEngine.uu  (테넌트.프로젝트명)
			String [] wholeKey = projectKey.replace('.','@').split("@");
		}
		
		projectId = "uEngine\\uu\\" + "uengine.metadata";
		
		MetadataXML metadataXML = new MetadataXML();
//		metadataXML = metadataXML.loadWithProjectId(projectId);
		
		// 회사 및 프로젝트의 경로를 생성한다.
		
		// TODO metadataPath 의 xml 파일을 읽어서 
		// 프로퍼티에 상위 키값을 모두 찾아서 로딩시킨다.
		Properties props = new Properties();
		ArrayList<MetadataProperty> properties =  metadataXML.getProperties();
		if( properties != null ){
			for( MetadataProperty metadataProperty : properties){
				String key = metadataProperty.getName();
				String value = metadataProperty.getValue();
				if( "img".equals(metadataProperty.getType())){
					// TODO img 패스를 잡는 부분이 필요함
					
				}
				props.put(key, value);
			}
		}
		// url 호출을 잘해야겠네..   
		// 프로젝트 id를 생성할때  http://회사명.processcodi.com/프로젝트명/img/log.jpg
		// 프로젝트 id를 생성할때  http://회사명.processcodi.com/img/log.jpg?appId=프로젝트명
		
		// 로컬에 있는 리소스를 보고 판단하기...
		// 프로퍼티의 type 이 local , remote 로 나누어진다... 
		/*
		 * local 은 해당 앱에서 프로퍼티를 생성하여서 로컬에 경로를 가지고 있을때
		 * remote 는 원격에서 파일을 가져와서 복사한걸... remote라 칭하고, 추후에 변경로직 체크하여 데이터를 새로 받아올때 사용한다.
		 * xml파일에서 이미지를 새로 저장한다면 local로 저장을 하고, remote 가 있어도 그대로 놔둠 - 키값이 중복 될텐데???
		 */
		// 1. xml에서 데이터를 읽었을때, 로컬 경로를 찾을때..
		// 2. 또다른 메타데이터를 참조하여서 상위 프로젝트를 찾아야 할 경우..
		/*
		 * 상위 프로젝트 아이디나 어떤 값을 가지고 있어야한다.... 
		 * 상위 프로젝트는 어떻게 찾아가야하나???
		 */
		projectProperty.put(projectId, props);
		projectBundle = props;
	}
	
	@ServiceMethod
	public void loadProperty() throws Exception{
		// 1. 해당 앱의 메인 경로에 있는 uengine.metadata 파일을 찾는다.
		String projectKey = GlobalContext.getPropertyString("metadataKey", "metadataKey");
		String projectId = null;
		String tanentId = null;
		if( projectKey != null ){	// uEngine.uu  (테넌트.프로젝트명)
			String [] wholeKey = projectKey.replace('.','@').split("@");
			tanentId = wholeKey[0];
			projectId = wholeKey[1];
		}
		// 앱의 루트 경로 생성 
		// TODO 나중에 url 로 호출을 해야할지도 모르겠음 우선 메인경로에 있다고 생각하고 작업, 
		// 				metadataKey 키만 가지고 찾을수 있다고 가정함
		String metadataPath = getProjectBasePath(tanentId, projectId);
		String metadataFileName = "uengine.metadata";
		String fullPath = metadataPath + File.separatorChar + metadataFileName;
		File metadataFile = new File(fullPath);
		if( !metadataFile.exists() ){
			metadataFile.createNewFile();
			metadataFile = getPropertyRemote(tanentId, projectId, metadataFile);
		}
		MetadataXML metadataXML = new MetadataXML();
		metadataXML = metadataXML.loadWithPath(metadataFile.getPath());
		
		Properties props = new Properties();
		ArrayList<MetadataProperty> properties =  metadataXML.getProperties();
		if( properties != null ){
			for( MetadataProperty metadataProperty : properties){
				String key = metadataProperty.getName();
				String value = metadataProperty.getValue();
				if( "img".equals(metadataProperty.getType())){
					value = "metadata" + value + "?type=" + metadataProperty.getType();
				}
				// TODO others
				props.put(key, value);
			}
		}
//		projectProperty.put(projectId, props);
		projectBundle = props;
	}
	
	/**
	 * 앱에서 메인서버의 프로젝트로 메타데이터 파일 및 리소스를 요청
	 * 관점 : 앱에서 서블릿을 통하여 호출
	 */
	private File getPropertyRemote(String tanentId, String projectId, File metadataFile){
		// 코디 서버로 요청 '/metadata' 서블릿을 통하여 요청함
		String codiServerUrl = "http://localhost:8080/uengine-web/";
		String requestUrl = "metadata/getMetadataFile";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(codiServerUrl + requestUrl);
		getMethod.setQueryString(new NameValuePair[] { 
				new NameValuePair("tanentId", tanentId) ,
				new NameValuePair("projectId", projectId) ,
				new NameValuePair("metadataFileName", metadataFile.getName()) 
		});
		
		OutputStream out = null;
		try{
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {  
					InputStream in = new BufferedInputStream(getMethod.getResponseBodyAsStream());  
					MetadataXML metadataXML = new MetadataXML();
					metadataXML = metadataXML.loadWithInputstream(in);
					ArrayList<MetadataProperty> properties =  metadataXML.getProperties();
					if( properties != null ){
						String metadataPath = getProjectBasePath(tanentId, projectId);
						for( MetadataProperty metadataProperty : properties){
							// for 문을 돌면서 해당 경로에 데이터가 없다면 데이터를 요청하고, remote 를  true로 준다.
							String value = metadataProperty.getValue();
							File chekcFile = new File(metadataPath+value);
							if( !chekcFile.exists() ){
								// 
								// 원격에서 가져왔다는걸 명시해준다.
								metadataProperty.setRemote(true);
							}
						}
					}
					XStream stream = new XStream();
					stream.autodetectAnnotations(true);
					
					out = new BufferedOutputStream(new FileOutputStream(metadataFile));  
					stream.toXML(metadataXML, out);
					
			}  
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
	private void loadPropertyLocal() throws Exception{
		
	}
	
	/**
	 * 메인서버에서 요청받은 정보를 가지고, 메타데이터 파일을 찾아서 리턴해준다.
	 * 관점 : 메인서버의 서블릿
	 */
	
	
	
	/**
	 * 메타데이타파일이 변경되었을때, 이 메서드를 호출해서 프로퍼티값을 변경시켜놓는다.
	 * @param projectId
	 * @throws Exception
	 */
	public void changeProjectProperty(String projectId) throws Exception{
		// TODO
		// type : local 은 
	}
	
	public static String getProjectBasePath(String tanentId, String projectId){
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String projectBasePath = codebase + File.separatorChar + tanentId + File.separatorChar + projectId;
		
		return projectBasePath;
	}
}
