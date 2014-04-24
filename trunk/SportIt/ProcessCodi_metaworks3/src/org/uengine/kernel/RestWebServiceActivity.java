package org.uengine.kernel;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Order;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.webProcessDesigner.MappingCanvas;
import org.uengine.codi.mw3.webProcessDesigner.MappingTree;
import org.uengine.codi.mw3.webProcessDesigner.PoolMappingTree;
import org.uengine.contexts.MappingContext;
import org.uengine.util.UEngineUtil;
import org.uengine.webservice.MethodProperty;
import org.uengine.webservice.ParameterProperty;
import org.uengine.webservice.WebServiceDefinition;

public class RestWebServiceActivity extends DefaultActivity implements IDrawDesigner{
	
	public static final String METHOD_TYPE_GET = "GET";
	public static final String METHOD_TYPE_POST = "POST";
	public static final String METHOD_TYPE_DELETE = "DELETE";
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public RestWebServiceActivity(){
		super("restWebService");
		method = new MethodProperty();
	}

	MethodProperty method;
	@Hidden
		public MethodProperty getMethod() {
			return method;
		}
		public void setMethod(MethodProperty method) {
			this.method = method;
		}

	transient String parentEditorId;
	@Hidden
		public String getParentEditorId() {
			return parentEditorId;
		}
		public void setParentEditorId(String parentEditorId) {
			this.parentEditorId = parentEditorId;
		}	
	transient WebServiceDefinition webServiceDefinition;
	@Order(3)
	@Face(displayName="연결 선택")
		public WebServiceDefinition getWebServiceDefinition() {
			return webServiceDefinition;
		}
		public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition) {
			this.webServiceDefinition = webServiceDefinition;
		}
	MappingContext mappingContext;
	@Order(4)
	@Face(displayName="$dataMapping")
		public MappingContext getMappingContext() {
			return mappingContext;
		}
		public void setMappingContext(MappingContext mappingContext) {
			this.mappingContext = mappingContext;
		}
		
	MappingContext mappingContextOut;
	@Order(5)
	@Face(displayName="결과값 매핑")
	@Available(condition="mappingContextOut")
		public MappingContext getMappingContextOut() {
			return mappingContextOut;
		}
		public void setMappingContextOut(MappingContext mappingContextOut) {
			this.mappingContextOut = mappingContextOut;
		}
		
	public void executeActivity(ProcessInstance instance) throws Exception{
		
		MethodProperty method = this.getMethod();
		String callPath = method.getCallPath();
		String queryString = null;
		if( callPath.indexOf("{") > 0 && callPath.indexOf("}") > 0){
			queryString = callPath.substring(callPath.indexOf("{") + 1, callPath.indexOf("}"));
			callPath = callPath.substring(0, callPath.indexOf("{"));
		}
		
		Client client = ClientBuilder.newClient();
		WebTarget baseResource = client.target(method.getBasePath());
		
		WebTarget callTarget = baseResource.path(callPath);
		
		Object entryObject = null;	// form Type 변수
		Invocation.Builder invocationBuilder = null;
		Response response = null;
		
		HashMap<String, Object> dataMap = requestDataMapping(instance);
		
		if( method.getRequest() != null && method.getRequest().length > 0){
			ParameterProperty[] propertyArray = method.getRequest();
			for(int j=0; j < propertyArray.length; j++){
				ParameterProperty pp = propertyArray[j];
				String paramType = pp.getParamType();
				if( "path".equals(paramType) ){
					if( queryString != null && queryString.equals(pp.getName()) ){
						if( dataMap != null && dataMap.containsKey(pp.getName())){
							// {message} 형식으로 호출된 path 가 있는 경우 path를 넣어줌
							String targetPath = dataMap.get(pp.getName()).toString();
							callTarget = callTarget.path(targetPath.trim()); 
						}else{
							callTarget = callTarget.path(pp.getName());
						}
					}
				}else if( "form".equals(paramType) ){
					if( entryObject == null ){
						entryObject = new Form();
					}
					if( dataMap != null && dataMap.containsKey(pp.getName())){
						((Form)entryObject).param(pp.getName(), dataMap.get(pp.getName()).toString());
					}else{
						((Form)entryObject).param(pp.getName(), "");
					}
				}
			}
			
		}
		// Consumes : 어떤 형식의 데이터를 넘기겠다. 
		JSONArray consumes = method.getConsumes();
		if( consumes != null ){
			String[] argType = new String[consumes.size()];
			for( int i = 0; i < consumes.size(); i++){
				argType[i] = consumes.getString(i);
			}
			invocationBuilder = callTarget.request(argType);
			
		}
		JSONArray produces = method.getProduces();
		if( produces != null ){
			String[] argType = new String[produces.size()];
			for( int i = 0; i < produces.size(); i++){
				argType[i] = produces.getString(i);
			}
			invocationBuilder = callTarget.request(argType);
		}
		if( invocationBuilder == null ){
			invocationBuilder = callTarget.request();
		}
		
		String methodCallType = method.getName();
		if( METHOD_TYPE_GET.equalsIgnoreCase(methodCallType) ){
			response = invocationBuilder.get();
		}else if( METHOD_TYPE_POST.equalsIgnoreCase(methodCallType) ){
			if( entryObject instanceof Form ){
				response = invocationBuilder.post(Entity.entity(entryObject,MediaType.APPLICATION_FORM_URLENCODED ));
			}else if( entryObject instanceof JSONObject ){
				response = invocationBuilder.post(Entity.entity(entryObject,MediaType.APPLICATION_JSON ));
			}
		}
		
		String responseClass = method.getResponseClass();
		if( responseClass != null && responseClass.equalsIgnoreCase(String.class.getSimpleName())){
//			System.out.println(response.readEntity(String.class));
			this.responseDataMapping(instance, response.readEntity(String.class));
		}
		
		fireComplete(instance);
		
	}
	
	public HashMap<String, Object> requestDataMapping(ProcessInstance instance) throws Exception{
		
		HashMap<String, Object> dataMap = null; 
		MappingContext mc= getMappingContext();
		if(mc !=null){
			ParameterContext[] params = mc.getMappingElements();
			if( params == null && mc.getMappingCanvas() != null){
				params = mc.getMappingCanvas().getMappingElements();
			}
			if( params.length > 0 ){
				dataMap = new HashMap<String, Object>();
			}
			for (int i = 0; i < params.length; i++) {
				ParameterContext param = params[i];
				
				String srcVariableName = null;
				String targetFieldName = param.getArgument().getText();
				Object value = null;
				
				if(param.getVariable() == null && param.getTransformerMapping() != null){
					value = param.getTransformerMapping().getTransformer().letTransform(instance, param.getTransformerMapping().getLinkedArgumentName());
				}else{
					srcVariableName = param.getVariable().getName();		
					if( srcVariableName.startsWith("[activities]") || srcVariableName.startsWith("[instance]")  || srcVariableName.startsWith("[roles]") ){
						value = instance.getBeanProperty(srcVariableName); // varA
					}else{
						String [] wholePartPath = srcVariableName.replace('.','@').split("@");
						// wholePartPath.length >= 1 이 되는 이유는 안쪽에 객체의 값을 참조하려고 하는 부분이기때문에 따로 값을 가져와야함
						if( wholePartPath.length >= 2 ){
							String rootObjectName = wholePartPath[1] ;
							if( wholePartPath.length > 2 ){
								for(int j = 2 ; j < wholePartPath.length; j++){
									rootObjectName += "."+ wholePartPath[j];
								}
							}
							// 이걸 바로 호출
							Object rootObject = instance.getBeanProperty(wholePartPath[0]);
							if( rootObject != null ){
								value = UEngineUtil.getBeanProperty(rootObject, rootObjectName);
							}
						}else{
							value = instance.getBeanProperty(srcVariableName); // varA
						}
					}
				}
				dataMap.put(targetFieldName, value);
			}	
		}
		return dataMap;
	}
	
	public void responseDataMapping(ProcessInstance instance, Object returnData) throws Exception{
		
		MappingContext mc= getMappingContextOut();
		if(mc !=null){
			ParameterContext[] params = mc.getMappingElements();
			for (int i = 0; i < params.length; i++) {
				ParameterContext param = params[i];
				
				// TODO 현재 리턴 데이터는 무조건 하나이기때문에, 바로 셋팅함 
//				String srcVariableName = null;
				String targetFieldName = param.getArgument().getText();
				
//				srcVariableName = param.getVariable().getName();
//				
//				Object value = null;
				instance.setBeanProperty(targetFieldName, (Serializable)returnData); //[instance].instanceId
			}
		}
	}
	
	public ValidationContext validate(Map options) {
		ValidationContext vc = super.validate(options);
		if( method == null || (method != null && method.getId() == null )){
			vc.add(getActivityLabel() + " method is empty ");
		}
		return vc;
	}
	
	@Override
	public void drawInit() throws Exception {
		Tree leftTree;
		Tree rightTree;
		
		leftTree = new MappingTree();
		((MappingTree) leftTree).setParentEditorId(this.getParentEditorId());
		leftTree.setId(TreeNode.ALIGN_LEFT);
		leftTree.setAlign(TreeNode.ALIGN_LEFT);
		
		rightTree = new PoolMappingTree();
		((PoolMappingTree) rightTree).setActivity(this);
		((PoolMappingTree) rightTree).setInOut(MappingTree.MAPPING_IN);
		rightTree.setId(TreeNode.ALIGN_RIGHT);
		rightTree.setAlign(TreeNode.ALIGN_RIGHT);
		
		if( mappingContext == null ){
			mappingContext = new MappingContext();
			MappingCanvas canvas = new MappingCanvas();
			canvas.setCanvasId("mappingCanvas");
			canvas.setLeftTreeId(leftTree.getId());
			canvas.setRightTreeId(rightTree.getId());
			mappingContext.setMappingCanvas(canvas);
		}
		mappingContext.setMappingTreeLeft(leftTree);
		mappingContext.setMappingTreeRight(rightTree);
		
		if( mappingContextOut != null ){
			
			Tree leftTreeOut = new PoolMappingTree();
			((PoolMappingTree) leftTreeOut).setActivity(this);
			((PoolMappingTree) leftTreeOut).setInOut(MappingTree.MAPPING_OUT);
			leftTreeOut.setId(TreeNode.ALIGN_LEFT+"Out");
			leftTreeOut.setAlign(TreeNode.ALIGN_LEFT);
			
			Tree rightTreeOut = new MappingTree();
			((MappingTree) rightTreeOut).setParentEditorId(this.getParentEditorId());
			rightTreeOut.setId(TreeNode.ALIGN_RIGHT+"Out");
			rightTreeOut.setAlign(TreeNode.ALIGN_RIGHT);
			
			if( mappingContextOut.getMappingCanvas() == null ){
				MappingCanvas canvas = new MappingCanvas();
				canvas.setCanvasId("mappingCanvas"+"Out");
				canvas.setLeftTreeId(leftTreeOut.getId());
				canvas.setRightTreeId(rightTreeOut.getId());
				mappingContextOut.setMappingCanvas(canvas);
			}
			mappingContextOut.setMappingTreeLeft(leftTreeOut);
			mappingContextOut.setMappingTreeRight(rightTreeOut);
			
		}
		
	}

}
