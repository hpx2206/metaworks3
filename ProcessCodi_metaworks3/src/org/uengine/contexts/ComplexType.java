package org.uengine.contexts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.codehaus.janino.Java;
import org.codehaus.janino.Java.ClassDeclaration;
import org.codehaus.janino.Parser;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.SimpleCompiler;
import org.codehaus.janino.util.Traverser;
import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectType;
import org.metaworks.Type;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.PropertyListable;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.ui.XMLValueInput;
import org.uengine.util.UEngineUtil;

public class ComplexType implements Serializable, PropertyListable{
	
	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;

	public static void metaworksCallback_changeMetadata(Type type){
		FieldDescriptor fd;
		
		fd = type.getFieldDescriptor("TypeId");
		XMLValueInput inputter = new XMLValueInput("/dwr/xstr-rpc?className=org.uengine.codi.mw3.model.ResourceFile&methodName=drillDownDeeply&object=" + URLEncoder.encode("<org.uengine.codi.mw3.model.ResourceFile><alias></alias><filter>java</filter></org.uengine.codi.mw3.model.ResourceFile>"))/* {
			public void onValueChanged() { 
				changeBindingArguments((String) getValue());
			}
		}*/;
		
		inputter.setEditable(true);

		fd.setInputter(inputter);
	}

	String typeId;
	Object value;
	transient Class typeClass;
	ClassLoader classLoader;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	public Class getTypeClass() throws Exception{
		return getTypeClass(null);
	}

	public Class getTypeClass(ProcessManagerRemote pm) throws Exception{
		if(typeClass!=null) return typeClass;
		
//		String clsTypeId = ProcessDefinition.splitDefinitionAndVersionId(getTypeId())[1];
		String typeId = getTypeId();
		String className = null;
		if( typeId != null  && typeId.startsWith("[@") && MetadataBundle.projectBundle != null){
			String key = typeId.substring(2, typeId.lastIndexOf("]"));
			String value = MetadataBundle.projectBundle.getProperty(key);
//			firstSourcePath = MetadataBundle.projectBundle.getProperty("sourceCodePath");
//			System.out.println("key = " + key);
//			System.out.println("value = " + value);
			className = value.substring(1, value.lastIndexOf(".")).replace('/', '.');
		}else{
			//className = typeId.substring(1, typeId.lastIndexOf(".")).replace('/', '.');
			className = typeId.substring(1, typeId.length() -1);
		}
		
		
		typeClass = Thread.currentThread().getContextClassLoader().loadClass(className);
		
		return typeClass;
		

//		InputStream is = null;
//		String javaSource = null;
//		
//		if(GlobalContext.isDesignTime()){
//			is = ProcessDesigner.getClientProxy().showObjectDefinitionWithDefinitionId(clsTypeId);
//			ByteArrayOutputStream bao = new ByteArrayOutputStream();
//			UEngineUtil.copyStream(is, bao);
//			javaSource = bao.toString();
//		}else{
//			javaSource = pm.getResource(clsTypeId);
//		}
//		
//        Java.CompilationUnit cu;
//        
//        cu = new Parser(new Scanner("", new ByteArrayInputStream(javaSource.getBytes()))).parseCompilationUnit();
//        final ArrayList clsNames = new ArrayList();
//
//        // Traverse it and count declarations.
//        new Traverser(){
//			public void traverseClassDeclaration(ClassDeclaration arg0) {
//				clsNames.add(arg0.getClassName());
//				super.traverseClassDeclaration(arg0);
//			}
//        }.traverseCompilationUnit(cu);
//
//        String clsName = (String)clsNames.get(0);
//        
//        SimpleCompiler compiler = new SimpleCompiler();
//		compiler.setParentClassLoader(GlobalContext.class.getClassLoader());
//		compiler.cook(new ByteArrayInputStream(javaSource.getBytes()));
//		//compiler.getClassLoader().
//		
//		classLoader = compiler.getClassLoader();
//		typeClass = classLoader.loadClass(clsName);
		
//		return typeClass;
	}
	
	
	@Override
	public ArrayList<String> listProperties() {
		try {
			Class clazz = getTypeClass();
			
			ObjectType type = new ObjectType(clazz);
			ArrayList<String> fieldNames = new ArrayList<String>();
			for(FieldDescriptor fd: type.getFieldDescriptors()){
				fieldNames.add(fd.getName());
			}
			
			return fieldNames;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);//.printStackTrace();
		}
		
	}

}
