
package org.uengine.codi.mw3.admin;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiDwrServlet;
import org.uengine.codi.mw3.model.FaceSourceCode;
import org.uengine.codi.mw3.model.JavaSourceCode;

import org.uengine.codi.mw3.model.BrowserWindow;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.model.Window;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ClassDefinition implements ContextAware, PropertyListable{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	transient protected ProcessManagerRemote processManager;

	public ClassDefinition(){
		this.sourceCodes = new ClassSourceCodes();
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		init();
	}
	
	String alias;
	@Hidden
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	int version;
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		
	String defId;
	@NonEditable	
	@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String defVerId;
	@NonEditable
	@Hidden
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}
		
	String parentFolder;
	@NonEditable		
	@Hidden
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}

	String packageName;

		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		
	ArrayList<ClassField> classFields;
		public ArrayList<ClassField> getClassFields() {
			return classFields;
		}
		public void setClassFields(ArrayList<ClassField> formFields) {
			this.classFields = formFields;
		}

	ArrayList<ClassMethod> classMethods;
		public ArrayList<ClassMethod> getClassMethods() {
			return classMethods;
		}
		public void setClassMethods(ArrayList<ClassMethod> classMethods) {
			this.classMethods = classMethods;
		}

	transient ClassField newClassField;
		public ClassField getNewClassField() {
			return newClassField;
		}
		public void setNewClassField(ClassField newClassField) {
			this.newClassField = newClassField;
		}
		
		
	ClassSourceCodes sourceCodes;
		public ClassSourceCodes getSourceCodes() {
			return sourceCodes;
		}
		public void setSourceCodes(ClassSourceCodes sourceCodes) {
			this.sourceCodes = sourceCodes;
		}

		
		
	@ServiceMethod(callByContent=true)
	public void generateSourceCode(){
		
		StringBuffer sb = new StringBuffer();
		sb
			.append("package ").append(getPackageName()).append(";\n\n")
			.append("public class " + getClassName() + "{\n\n");
		
		if(getClassFields()!=null)
		for(int i=0; i<getClassFields().size(); i++){
			ClassField field = getClassFields().get(i);
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getFieldName());
			
			sb
				.append("	").append(field.getType()).append(" ").append(field.getFieldName()).append(";\n")
				.append("		public ").append(field.getType()).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getFieldName()).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(field.getType()).append(" ").append(field.getFieldName()).append("){ this.").append(field.getFieldName()).append(" = ").append(field.getFieldName()).append("; }\n\n")
				;
		}
		
		sb.append("}");
		
		if(sourceCodes==null)
			sourceCodes = new ClassSourceCodes();
		
		getSourceCodes().sourceCode = new JavaSourceCode();
		getSourceCodes().sourceCode.setCode(sb.toString());
	

		///create facehelper source
		
		sb = new StringBuffer();
		sb
			.append("var ").append((getPackageName() + "_" + getClassName()).replace('.','_'))
			.append(" = function(objectId, className){\n")
			.append("	this.objectId = objectId;\n")
			.append("	this.className = className;\n")
				
		.append("}");
		
		if(sourceCodes==null)
			sourceCodes = new ClassSourceCodes();
		
		getSourceCodes().faceHelper = new FaceHelperSourceCode();
		getSourceCodes().faceHelper.setCode(sb.toString());
	
	}
	
	@ServiceMethod(callByContent=true)
	public void compile() throws Exception{
		save();
	
		//TODO: please check the package name & static code analysis as much as possible here.
		
		if(getSourceCodes()==null || getSourceCodes().getSourceCode()==null) return;
		
		CodiDwrServlet.refreshClassLoader(getAlias());
		
//	    SimpleCompiler compiler = new SimpleCompiler();
//	    compiler.setParentClassLoader(Thread.currentThread().getContextClassLoader());
//	    //compiler.set
//	    
//	    try {
//			compiler.cook(getSourceCode().getCode());
//		} catch (CompileException e) {
//			
//			if(e.getMessage().indexOf("Line") > -1 && e.getMessage().indexOf("Column") > -1){
//
//				int lineNumber = Integer.parseInt(e.getMessage().split("Line ")[1].split(",")[0]);
//				
//				String[] positionAndErrorMessagePart = e.getMessage().split("Column ")[1].split(":");
//				int columnNumber = Integer.parseInt(positionAndErrorMessagePart[0]);
//				
//				CompileError compileError = new CompileError();
//				compileError.setLine(lineNumber);
//				compileError.setColumn(columnNumber);
//				compileError.setMessage(positionAndErrorMessagePart[1]);
//				
//				getSourceCode().setCompileErrors(new CompileError[]{compileError});
//					
//			}
//			
//			return;
//		} 
		
		try {
			String fullClsName = getPackageName()+"."+getClassName();
			
			Thread.currentThread().getContextClassLoader().loadClass(fullClsName);
			
			getSourceCodes().getSourceCode().setCompileErrors(null);
			
			MetaworksRemoteService.getInstance().clearMetaworksType(fullClsName);

			
		} catch(SecurityException e){
			
			throw e;
			
		} catch (Exception e) {
			// TODO we need to report the error properly
			String message = e.getMessage();
			String[] parts = null;
			int lineNumber = 0;
			
			try {
				lineNumber = Integer.parseInt((parts = message.split(":"))[2]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				parts = null;
				//e1.printStackTrace();
			}
			
			CompileError compileError = new CompileError();
			compileError.setLine(lineNumber);
			compileError.setColumn(1);
			compileError.setMessage(parts != null ? parts[3] : message);
			
			getSourceCodes().getSourceCode().setCompileErrors(new CompileError[]{compileError});
		
			e.printStackTrace();
		} 
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object run() throws Exception{
		

		Object o = Thread.currentThread().getContextClassLoader().loadClass(getPackageName() + "." + getClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
		
		Window outputWindow = new Window();
		outputWindow.setPanel(o);
//		outputWindow.
		
		return outputWindow;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object design() throws Exception{
		
		Window outputWindow = new Window();
		
		TemplateDesigner designer = new TemplateDesigner(getPackageName() + "." + getClassName());
		
		outputWindow.setPanel(designer);
		
		return outputWindow;
	}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object runMobile() throws Exception{
		

		Object o = Thread.currentThread().getContextClassLoader().loadClass(getPackageName() + "." + getClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
		
		MobileWindow outputWindow = new MobileWindow();
		outputWindow.setPanel(o);
//		outputWindow.
		
		return outputWindow;
	}	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object runFullWindow() throws Exception{
		
//		BrowserWindow window = new BrowserWindow(getPackageName() + "." + getClassName());
		
		Window window = new Window(new IFrame("tester.html?className=" + getPackageName() + "." + getClassName()));
		
		return window;
	}	
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		
		setAlias(getPackageName().replace('.', '/') + "/" + getClassName() + ".java");

		String strDef = GlobalContext.serialize(this, ClassDefinition.class);
		
		String fullDefId = processManager.addProcessDefinition(getClassName(), getVersion(), "description", false, strDef, getParentFolder(), getDefId(), getAlias(), "class");
		
		String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
		
		/// generate source file
		File sourceCodeFile = new File("/Users/jyjang/javasources/" + getAlias());
		sourceCodeFile.getParentFile().mkdirs();
		//sourceCodeFile.createNewFile();
		
		FileWriter writer = new FileWriter(sourceCodeFile);
		writer.write(getSourceCodes().getSourceCode().getCode());
		writer.close();
		
		//if there is face code, save it.
		String faceSource = "/Users/jyjang/javasources/" + getAlias();
		faceSource = faceSource.substring(0, faceSource.indexOf(".")) + ".ejs";
		
		File ejsFile = new File(faceSource);

		if(UEngineUtil.isNotEmpty(getSourceCodes().getFace().getCode())){
			
			writer = new FileWriter(ejsFile);
			writer.write(getSourceCodes().getFace().getCode());
			writer.close();

		}else{
			ejsFile.delete();
		}
		
		//if there is facehelper code, save it.
		String faceHelperSource = "/Users/jyjang/javasources/" + getAlias();
		faceHelperSource = faceHelperSource.substring(0, faceHelperSource.indexOf(".")) + ".ejs.js";
		
		File ejsJsFile = new File(faceHelperSource);

		if(UEngineUtil.isNotEmpty(getSourceCodes().getFaceHelper().getCode())){
			
			writer = new FileWriter(ejsJsFile);
			writer.write(getSourceCodes().getFaceHelper().getCode());
			writer.close();

		}else{
			ejsJsFile.delete();
		}
		
		///
		
		//TODO:   generate face file if exists
//		if(UEngineUtil.isNotEmpty(getFace().getCode())){
//			File faceFile = new File("/Users/jyjang/javasources/" + getAlias());
//			sourceCodeFile.getParentFile().mkdirs();
//			//sourceCodeFile.createNewFile();
//			
//			FileWriter writer = new FileWriter(sourceCodeFile);
//			writer.write(getSourceCode().getCode());
//			writer.close();
//		}
		///
		
		processManager.setProcessDefinitionProductionVersion(definitionIdAndVersionId[1]);
		processManager.applyChanges();
		
		setDefId(definitionIdAndVersionId[0]);
		setDefVerId(definitionIdAndVersionId[1]);
				
	}


	public void init() {
		newClassField = new ClassField();
		newClassField.metaworksContext = new MetaworksContext();
		newClassField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newClassField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
		newClassField.classDefinition = this;

	}
	
	@Override
	public ArrayList<String> listProperties() {
		try {
			
			
			WebObjectType type = MetaworksRemoteService.getInstance().getMetaworksType(getPackageName() + "." + getClassName());
			
			ArrayList array = new ArrayList();
			for (WebFieldDescriptor wfd: type.getFieldDescriptors()) {
				array.add(wfd.getName());
			}
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
