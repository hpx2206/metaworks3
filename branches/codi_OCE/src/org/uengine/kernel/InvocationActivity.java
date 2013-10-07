package org.uengine.kernel;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectInstance;
import org.metaworks.WebObjectType;
import org.metaworks.component.SelectBox;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.webProcessDesigner.ClassResourceMethod;
import org.uengine.codi.mw3.webProcessDesigner.MappingCanvas;
import org.uengine.contexts.InvocationContext;
import org.uengine.util.UEngineUtil;

public class InvocationActivity extends DefaultActivity implements IDrawDesigne {
	
	public InvocationActivity(){
		setName("invocation");
	}
	
	InvocationContext invocationContextIn;
		public InvocationContext getInvocationContextIn() {
			return invocationContextIn;
		}
		public void setInvocationContextIn(InvocationContext invocationContextIn) {
			this.invocationContextIn = invocationContextIn;
		}
		
	InvocationContext invocationContextOut;
		public InvocationContext getInvocationContextOut() {
			return invocationContextOut;
		}
		public void setInvocationContextOut(InvocationContext invocationContextOut) {
			this.invocationContextOut = invocationContextOut;
		}
	ClassResourceMethod classResourceMethod;
		public ClassResourceMethod getClassResourceMethod() {
			return classResourceMethod;
		}
		public void setClassResourceMethod(ClassResourceMethod classResourceMethod) {
			this.classResourceMethod = classResourceMethod;
		}

	String resourceClass;
		public String getResourceClass() {
			return resourceClass;
		}
		public void setResourceClass(String resourceClass) {
			this.resourceClass = resourceClass;
		}
		
	@Override
	public void drawInit() throws Exception {
//		System.out.println("resourceClass ======== " + resourceClass);
		// tree 는 새롭게 그린다.
		if( invocationContextIn != null && classResourceMethod != null && invocationContextOut != null ){
			drawTree();
		}else{
			invocationContextIn = new InvocationContext();
			invocationContextOut = new InvocationContext();
			drawTree();
			
			invocationContextIn.loadCanvas();
			invocationContextOut.loadCanvas();
			
			
			classResourceMethod = new ClassResourceMethod();
			classResourceMethod.setResourceClass(resourceClass);
			classResourceMethod.makeMethodChoice();
			
			MappingCanvas inCanvas = invocationContextIn.getMappingCanvas();
			inCanvas.setLeftTreeId(invocationContextIn.getMappingTree().getId());
			inCanvas.setRightTreeId(invocationContextIn.getClassResourceTree().getId());
			
			MappingCanvas outCanvas = invocationContextOut.getMappingCanvas();
			outCanvas.setLeftTreeId(invocationContextOut.getClassResourceTree().getId());
			
			outCanvas.setRightTreeId(invocationContextOut.getMappingTree().getId());
		}
		
	}
	
	public void drawTree() throws Exception{
		String inId = "in";
		invocationContextIn.setId(inId);
		invocationContextIn.loadTree();
		invocationContextIn.treeSetId(inId);
		invocationContextIn.getClassResourceTree().setResourceClass(resourceClass);
		invocationContextIn.getClassResourceTree().setAlign("right");
		
		String outId = "out";
		invocationContextOut.setId(outId);
		invocationContextOut.loadTree();
		invocationContextOut.treeSetId(outId);
		invocationContextOut.getClassResourceTree().setResourceClass(resourceClass);
		invocationContextOut.getMappingTree().setAlign("right");
	}
	protected void executeActivity(ProcessInstance instance) throws Exception {
		/**
		 * in 에서 설정한 정보를 셋팅하는 경우는  target 이 프로세스 상에는 들어가 있지 않기때문에 instance쪽과는 상관없이 돌아간다.
		 * 그리하여 바로 메서드에 set 을 하여 데이터를 저장함
		 * out 은 in과 반대로 source 쪽에서 get을 하여 데이터를 가져온 후에 최종적으로  instance쪽에 넣어준다.
		 */
		
		String resourceClassName = getResourceClass();
		String className = resourceClassName.substring(0, resourceClassName.lastIndexOf(".")).replaceAll("/", ".");
		Class resourceClass = Thread.currentThread().getContextClassLoader().loadClass(className);
		Object object = resourceClass.newInstance();
		
		// in 으로 셋팅한 정보
		InvocationContext in = getInvocationContextIn();
		if( in != null ){
			ParameterContext[] params = in.getMappingCanvas().getMappingElements();
			for (int i = 0; i < params.length; i++) {
				ParameterContext param = params[i];
				
				String srcVariableName = null;
				String targetFieldName = param.getArgument().getText();
				Object value = null;
				
				if(param.getVariable() == null && param.getTransformerMapping() != null){
					value = param.getTransformerMapping().getTransformer().letTransform(instance, param.getTransformerMapping().getLinkedArgumentName());
				}else{
					srcVariableName = param.getVariable().getName();				
					
					String [] wholePartPath = srcVariableName.replace('.','@').split("@");
					// wholePartPath.length >= 3 이 되는 이유는 안쪽에 객체의 값을 참조하려고 하는 부분이기때문에 따로 값을 가져와야함
					if( wholePartPath.length >= 3 ){
						String rootObjectName = wholePartPath[0] + "." + wholePartPath[1];
						Object rootObject = instance.getBeanProperty(rootObjectName);
						if( rootObject != null ){
							value = UEngineUtil.getBeanProperty(rootObject, wholePartPath[2]);
						}
						for(int j = 3; j < wholePartPath.length ; j++){
							value = UEngineUtil.getBeanProperty(value, wholePartPath[j]);
						}
					}else{
						value = instance.getBeanProperty(srcVariableName); // varA
					}
				}			
				// targetFieldName 은 className.fieldName 이런식으로 들어오기 때문에 약간의 조작이 필요함.
				String fieldName = targetFieldName.substring(targetFieldName.lastIndexOf(".") + 1);
				// 첫글자는 대문자로
				String output = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); 
				
				System.out.println("111 =========>> " + srcVariableName);
				System.out.println("111 =========>> " + targetFieldName);
				System.out.println("111 =========>> " + output);
				System.out.println("111 =========>> " + value);
				
				Method m = resourceClass.getMethod("set"+output, new Class[]{String.class});
				m.invoke(object, new Object[]{value});
			}
		}
		
		// call 메서드 실행
		ClassResourceMethod method = getClassResourceMethod();
		SelectBox selectedMethod = method.getMethodSelectBox();
		if( selectedMethod != null ){
			String methodName = selectedMethod.getSelected();
			try {
				MetaworksRemoteService metaworksRemoteService = MetaworksRemoteService.getInstance();
				org.metaworks.dwr.InvocationContext invocationContext = metaworksRemoteService.prepareToCall(className, object, methodName, null);
				object = invocationContext.getObject();
				Method m = resourceClass.getMethod(methodName, new Class[]{});
				m.invoke(object, new Object[]{});
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		// out 으로 셋팅한 정보
		InvocationContext out = getInvocationContextOut();
		if( out != null ){
			ParameterContext[] params = out.getMappingCanvas().getMappingElements();
			for (int i = 0; i < params.length; i++) {
				ParameterContext param = params[i];
				
				String srcVariableName = null;
				String targetFieldName = param.getArgument().getText();
				
				srcVariableName = param.getVariable().getName();
				
				// srcVariableName 은 className.fieldName 이런식으로 들어오기 때문에 약간의 조작이 필요함.
				String fieldName = srcVariableName.substring(srcVariableName.lastIndexOf(".") + 1);
				// 첫글자는 대문자로
				String output = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); 
				Method m = resourceClass.getMethod("get"+output, new Class[]{});
				Object value = m.invoke(object, new Object[]{});
				instance.setBeanProperty(targetFieldName, (Serializable)value); //[instance].instanceId
			}
		}
		fireComplete(instance);
	}
	
}
