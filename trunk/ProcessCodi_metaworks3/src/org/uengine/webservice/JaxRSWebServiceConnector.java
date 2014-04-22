package org.uengine.webservice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.kernel.Activity;
import org.uengine.kernel.RestWebServiceActivity;
import org.uengine.kernel.designer.web.ActivityView;
import org.uengine.kernel.designer.web.DynamicDrawGeom;
import org.uengine.kernel.designer.web.DynamicDrawWebService;

import com.thoughtworks.xstream.XStream;

@Face(ejsPath="genericfaces/ActivityFace.ejs", options={"fieldOrder"},values={"linkedId,webServiceName"})
public class JaxRSWebServiceConnector implements WebServiceConnector ,Serializable {

	public JaxRSWebServiceConnector(){
		setApiType(WEBSERVICE_API_JAXRS);
		webServiceDefinition = new WebServiceDefinition();
	}
	
	String linkedId;
	@Face(displayName="연결된 앱 ID")
	@Id
		public String getLinkedId() {
			return linkedId;
		}
		public void setLinkedId(String linkedId) {
			this.linkedId = linkedId;
		}

	String apiType;
	@Hidden
		public String getApiType() {
			return apiType;
		}
		public void setApiType(String apiType) {
			this.apiType = apiType;
		}
		
	String webServiceName;
	@Face(displayName="연결된 앱 이름")
		public String getWebServiceName() {
			return webServiceName;
		}
		public void setWebServiceName(String webServiceName) {
			this.webServiceName = webServiceName;
		}

	transient WebServiceDefinition webServiceDefinition;
	@Hidden
		public WebServiceDefinition getWebServiceDefinition() {
			return webServiceDefinition;
		}
		public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition) {
			this.webServiceDefinition = webServiceDefinition;
		}
		
	public DynamicDrawGeom drawActivitysOnDesigner() throws Exception{
		DynamicDrawGeom ddg = new DynamicDrawWebService();
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		WebServiceDefinition wsd = this.getWebServiceDefinition();
		ArrayList<ResourceProperty> list = wsd.getResourceList();
		
		// 같은 패스별로 엑티비티를 만들기 위하여 HashMap을 사용
		HashMap<String, ArrayList<ResourceProperty>> map = new HashMap<String, ArrayList<ResourceProperty>>(); 
		for( ResourceProperty resourceProperty : list){
			String key = resourceProperty.getPath();
			ArrayList<ResourceProperty> rp;
			if( map.containsKey(key) ){
				 rp = map.get(key);
			}else{
				rp = new ArrayList<ResourceProperty>();
			}
			rp.add(resourceProperty);
			map.put(key, rp);
		}
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String)itr.next();
			ArrayList<ResourceProperty> rpList = map.get(key);
			RestWebServiceActivity activity = new RestWebServiceActivity();
			
			ActivityView activityView = new ActivityView();
			activityView.setWidth("100");	
			activityView.setHeight("80");
			activityView.setClassType("Activity");
			activityView.setShapeType("GEOM");
			activityView.setShapeId("OG.shape.bpmn.A_WebServiceTask");
			activityView.setActivityClass(activity.getClass().getName());
			activityView.setActivity(activity);
			
			activity.setActivityView(activityView);
			
			ArrayList<MethodProperty> methodList 	= new ArrayList<MethodProperty>();
			
			for( ResourceProperty resourceProperty : rpList){
				methodList.addAll(resourceProperty.getMethods());
			}
			// TODO 스펙이 변경됨
//			activity.setMethods(methodList);
			activityList.add(activity);
		}
		
		ddg.setActivityList(activityList);
		
		return ddg;
	}
		
	@Override
	public void load() throws Exception{
		if( this.getLinkedId() != null && !"".equals(this.getLinkedId())){
			AppMapping app = new AppMapping();
			app.setAppId(Integer.parseInt(this.getLinkedId())); 
			String appName = app.databaseMe().getAppName();
			this.setWebServiceName(appName);
			
			// 1. appId에 해당하는 파일이 있는지 확인한다.
			String appBasePath = MetadataBundle.getProjectBasePath(appName);
			String webServiceFileName = appName + ".WADL";
			
			String fullPath = appBasePath + File.separatorChar + webServiceFileName;
			File webServiceFile = new File(fullPath);
			if( !webServiceFile.exists()){
	//			String url = "http://192.168.56.101:8080/hello/";
				String url = app.databaseMe().getUrl();
				makeWebServiceFile(url, webServiceFile);
			}
			// load webService
			webServiceDefinition = webServiceDefinition.loadWithPath(fullPath);
			for(ResourceProperty rp : webServiceDefinition.getResourceList()){
				webServiceDefinition.injectionPathInfo(rp, webServiceDefinition.getBase());
			}
		}
	}
	
	public void makeWebServiceFile(String url, File webServiceFile) throws Exception{
		URL iUrl = new URL(url);
		String urlPath = url.substring(0, url.indexOf(iUrl.getPath()));
//		System.out.println(" urlPath = " + urlPath);
		String docPath = urlPath +  "/docs" + iUrl.getPath();
//		System.out.println(" docPath = " + docPath);
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request;
		HttpResponse response;
	    HttpContext localContext = new BasicHttpContext();
	    String content = "";
	    String returnContent = null;
	    try{
	        request = new HttpGet(docPath);
	        response = client.execute(request,localContext);
	        content = EntityUtils.toString(response.getEntity());
	        returnContent = contentsAnalyize(content);
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	    	client.getConnectionManager().shutdown();
	    }
	    
//	    System.out.println(returnContent);
	    
	    if( returnContent != null ){
		    FileWriter writer = null;
	
			try {
				if(!webServiceFile.exists()){
					webServiceFile.getParentFile().mkdirs();
					webServiceFile.createNewFile();
				}
	
				writer = new FileWriter(webServiceFile);
				writer.write(returnContent);
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(writer != null)
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	    }
	}
	
	public String contentsAnalyize(String jsonContents) throws Exception{
		JSONObject jsonObject = JSONObject.fromObject(jsonContents);
//		String resourcePath = jsonObject.getString("resourcePath");
		
		WebServiceDefinition wsd = new WebServiceDefinition();
		wsd.setBase(jsonObject.getString("basePath"));
		wsd.setName(webServiceName);
		ArrayList<ResourceProperty> resourceArray = new ArrayList<ResourceProperty>();
		
		JSONArray apiArray = JSONArray.fromObject(jsonObject.get("apis"));
		if( apiArray != null ){
			for(int i=0; i < apiArray.size(); i++){
				
				JSONObject methodsApi = JSONObject.fromObject(apiArray.get(i));
				ResourceProperty rootResource = new ResourceProperty();
				if( methodsApi.containsKey("path") ){
					rootResource.setPath(methodsApi.getString("path"));						// call path
				}
				if( methodsApi.containsKey("description") ){
					rootResource.setDescription(methodsApi.getString("description"))	;	// description
				}
				
				MethodProperty method = new MethodProperty();
				
				if( methodsApi.containsKey("operations") && methodsApi.get("operations") != null ){
					JSONArray operationArray = JSONArray.fromObject(methodsApi.get("operations"));
					for(int j=0; j < operationArray.size(); j++){
						JSONObject operationApi = JSONObject.fromObject(operationArray.get(j));
						if( operationApi.containsKey("httpMethod") ){
							method.setName(operationApi.getString("httpMethod"));
						}
						if( operationApi.containsKey("nickname") ){
							method.setId(operationApi.getString("nickname"));
						}
						if( operationApi.containsKey("responseClass") ){
							method.setResponseClass(operationApi.getString("responseClass"));
						}
						if( operationApi.containsKey("responseMessages") ){
							method.setResponseMessages(operationApi.getJSONArray("responseMessages"));
						}
						if( operationApi.containsKey("consumes") ){
							method.setConsumes(operationApi.getJSONArray("consumes"));
						}
						if( operationApi.containsKey("produces") ){
							method.setProduces(operationApi.getJSONArray("produces"));
						}
						if( operationApi.containsKey("summary") ){
							method.setSummary(operationApi.getString("summary"));
						}
						if( operationApi.containsKey("parameters") && operationApi.get("parameters") != null ){
							JSONArray parameterArray = JSONArray.fromObject(operationApi.get("parameters"));
							ParameterProperty[] pps = new ParameterProperty[parameterArray.size()];
							for(int k=0; k < parameterArray.size(); k++){
								JSONObject parameterApi = JSONObject.fromObject(parameterArray.get(k));
								ParameterProperty parameterProperty = new ParameterProperty();
								if( parameterApi.containsKey("name") ){
									parameterProperty.setName(parameterApi.getString("name"));
								}
								if( parameterApi.containsKey("paramType") ){
									parameterProperty.setParamType(parameterApi.getString("paramType"));
								}
								if( parameterApi.containsKey("dataType") ){
									parameterProperty.setDataType(parameterApi.getString("dataType"));
								}
								if( parameterApi.containsKey("defaultValue") ){
									parameterProperty.setDefaultValue(parameterApi.getString("defaultValue"));
								}
								pps[k] = parameterProperty;
							}
							method.setRequest(pps);
						}
					}
				}
				
				rootResource.getMethods().add(method);
				resourceArray.add(rootResource);
				
			}
		}
		JSONArray modelsArray = JSONArray.fromObject(jsonObject.get("models"));
		if( modelsArray != null ){
			
		}
		// setting webServiceDefinition
		wsd.setResourceList(resourceArray);
		
		XStream stream = new XStream();
		stream.autodetectAnnotations(true);
		
		return stream.toXML(wsd);
	}
	
	
}
