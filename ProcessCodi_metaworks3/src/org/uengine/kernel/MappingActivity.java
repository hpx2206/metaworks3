package org.uengine.kernel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.metaworks.Type;
import org.uengine.codi.mw3.webProcessDesigner.MappingCanvas;
import org.uengine.codi.mw3.webProcessDesigner.MappingTree;
import org.uengine.contexts.HtmlFormContext;
import org.uengine.contexts.MappingContext;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.processdesigner.mapper.Transformer;
import org.uengine.util.UEngineUtil;

public class MappingActivity extends DefaultActivity implements IDrawDesigne {

	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
	public final static String FILE_SYSTEM_DIR = GlobalContext.getPropertyString("filesystem.path", ProcessDefinitionFactory.DEFINITION_ROOT);

	public MappingActivity(){
		setName("mapping");
	}
	
	MappingContext mappingContext;
		public MappingContext getMappingContext() {
			return mappingContext;
		}
		public void setMappingContext(MappingContext mappingContext) {
			this.mappingContext = mappingContext;
		}
	@Override
	public void drawInit() throws Exception {
		MappingTree leftTree = new MappingTree();
		leftTree.setId("left");
		leftTree.setAlign("left");
		MappingTree rightTree = new MappingTree();
		rightTree.setId("right");
		rightTree.setAlign("right");
		
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
	protected void executeActivity(ProcessInstance instance) throws Exception {
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
				
				instance.setBeanProperty(targetFieldName, (Serializable)value); //[instance].instanceId
	
			}	
		}
		fireComplete(instance);
	}
}
