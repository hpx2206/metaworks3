package org.uengine.kernel;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
import org.uengine.webservice.WebServiceDefinition;

public class RestWebServiceActivity extends DefaultActivity implements IDrawDesigner{
	
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
		
	public void executeActivity(ProcessInstance instance) throws Exception{
		
		MethodProperty method = this.getMethod();
		
		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(method.getBasePath());
		
		Form f = new Form();
		f.param("first", "김");
		f.param("last", "형국");
		
		WebTarget myContext2 = myResource.path("/hello");
		WebTarget myContext3 = myContext2.path("/user");
		
		myContext3.request().post(Entity.entity(f, MediaType.APPLICATION_FORM_URLENCODED));
		
		
		
		
		
		
//		WebTarget myResource = client.target("http://192.168.56.101:8080");
		
		WebTarget myContext = myContext3.path("가가가가");
		
		Invocation.Builder invocationBuilder =	myContext.request(MediaType.TEXT_PLAIN_TYPE);
		Response response = invocationBuilder.get();
		
//		myResource.path(method.getCallPath());
//		myResource.queryParam("message", "그래요..");
		
//		method.getResponseMessages();
//		Response response = myContext.request(MediaType.APPLICATION_JSON).get();
//		Response response = myContext.request(MediaType.TEXT_PLAIN).get();
//		String response = myResource.request(MediaType.TEXT_PLAIN).get(String.class);
		
		
		System.out.println(response.readEntity(String.class));
		
		Response response2 = myContext3.request(MediaType.APPLICATION_JSON).get();
		System.out.println(response2.readEntity(String.class));
//		System.out.println( "response = " + response);
		
		MappingContext mc= getMappingContext();
		if(mc !=null){
			ParameterContext[] params = mc.getMappingElements();
			if( params == null && mc.getMappingCanvas() != null){
				params = mc.getMappingCanvas().getMappingElements();
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
				
//				instance.setBeanProperty(targetFieldName, (Serializable)value); //[instance].instanceId
	
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
		
	}

}
