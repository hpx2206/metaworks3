
package org.uengine.codi.mw3.admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import net.sourceforge.pmd.IRuleViolation;
import net.sourceforge.pmd.PMD;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.CodiDwrServlet;
import org.uengine.codi.mw3.alm.MemoryRenderer;
import org.uengine.codi.mw3.alm.QualityOption;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.Feedback;
import org.uengine.codi.mw3.model.JavaSourceCode;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessDefinition;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.model.User;
import org.uengine.codi.mw3.model.Window;
import org.uengine.codi.mw3.svn.CheckoutWindow;
import org.uengine.codi.mw3.svn.CommitWindow;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;


public class ClassDefinition implements ContextAware, PropertyListable, NeedArrangementToSerialize{

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
		
		this.qualityOption = new QualityOption();

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
		
	transient User author; 
			
		public User getAuthor() {
			return author;
		}
		public void setAuthor(User author) {
			this.author = author;
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

	transient ClassSourceCodes sourceCodes;
		public ClassSourceCodes getSourceCodes() {
			return sourceCodes;
		}
		public void setSourceCodes(ClassSourceCodes sourceCodes) {
			this.sourceCodes = sourceCodes;
		}

	transient QualityOption qualityOption;
	@Hidden
		public QualityOption getQualityOption() {
			return qualityOption;
		}
		public void setQualityOption(QualityOption qualityOption) {
			this.qualityOption = qualityOption;
		}
	
	/*
	Feedback feedback;
		public Feedback getFeedback() {
			return feedback;
		}
		public void setFeedback(Feedback feedback) {
			this.feedback = feedback;
		}
	*/
		
	@ServiceMethod(callByContent=true)
	public void generateSourceCode(){
		
		StringBuffer sb = new StringBuffer();
		sb
			.append("package ").append(getPackageName()).append(";\n\n")
			.append("public class " + getClassName() + "{\n\n");
		
		if(getSourceCodes().getClassModeler()!=null && getSourceCodes().getClassModeler().getClassFields()!=null)
		for(int i=0; i<getSourceCodes().getClassModeler().getClassFields().size(); i++){
			ClassField field = getSourceCodes().getClassModeler().getClassFields().get(i);
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getFieldName());
			String fieldType = field.getType();
			
			if(fieldType.startsWith("java.lang"))
				fieldType = fieldType.substring("java.lang".length()+1);
			
			sb
				.append("	").append(fieldType).append(" ").append(field.getFieldName()).append(";\n")
				.append("		public ").append(fieldType).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getFieldName()).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(fieldType).append(" ").append(field.getFieldName()).append("){ this.").append(field.getFieldName()).append(" = ").append(field.getFieldName()).append("; }\n\n")
				;
		}
		
		sb.append("}");
		
		if(sourceCodes==null)
			sourceCodes = new ClassSourceCodes();
		
		getSourceCodes().sourceCode = new JavaSourceCode();
		getSourceCodes().sourceCode.setCode(sb.toString());
		
		generateFaceHelperSourceCode();	
	}
	
	public void generateFaceHelperSourceCode(){
		StringBuffer sb = new StringBuffer();
		
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
		
		ArrayList<CompileError> compileErrors = new ArrayList<CompileError>();
		
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
			
			compileErrors.add(compileError);
			
			e.printStackTrace();
		} 
		
		
		//// try quality options /////
		
		if(getQualityOption()!=null && getQualityOption().getPmdRuleOption()!=null){
			
			
			
			PMD.main(new String[]{
					"/Users/jyjang/javasources/" + getAlias(),
					"org.uengine.codi.mw3.alm.MemoryRenderer",
					getQualityOption().getPmdRuleOption().generatePMDOption()
					
			});
			
			for(Iterator<IRuleViolation> violations: MemoryRenderer.recentViolations){
		        while (violations.hasNext()) {
		            IRuleViolation rv = violations.next();

		            System.out.println(rv.getFilename());
		            System.out.println(rv.getBeginColumn());
		            System.out.println(rv.getDescription());
		            
					CompileError compileError = new CompileError();
					compileError.setLine(rv.getBeginLine());
					compileError.setColumn(rv.getBeginColumn());
					compileError.setMessage(rv.getDescription());
					
					compileErrors.add(compileError);

		        }
		    }
		}
		
		CompileError[] compileErrorInArray = new CompileError[compileErrors.size()];
		compileErrors.toArray(compileErrorInArray);
		
		getSourceCodes().getSourceCode().setCompileErrors(compileErrorInArray);
		
		if(compileErrorInArray.length == 0)
			refreshClassInfo();

	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup run() throws Exception{		

		Runner runner = new Runner();
		runner.setFullClassName(getPackageName() + "." + getClassName());

		Popup runnerPopup = new Popup();
		runnerPopup.setName("Run...");
		runnerPopup.setPanel(runner);
	
		return runnerPopup;
	}

	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		
        CodiClassLoader contextClassLoader = CodiClassLoader.getMyClassLoader();
		String myWorkingCopyPath = ((CodiClassLoader)contextClassLoader).mySourceCodeBase();//"/Users/jyjang/MyWorkingCopy";

        if(myWorkingCopyPath==null)
        	throw new Exception("소셜코딩을 환영합니다! 소스코드를 수정하려면 먼저 페이스북 로그인을 하신후 체크아웃(checkout)을 받으셔야 합니.");
        	
        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (!wcDir.exists()) {
        	throw new Exception("소셜코딩을 환영합니다! 소스코드를 수정하려면 체크아웃(checkout)을 먼저 하십시오.");
        }

		
		setAlias(getPackageName().replace('.', '/') + "/" + getClassName() + ".java");

		String strDef = GlobalContext.serialize(this, ClassDefinition.class);
		
		String fullDefId = processManager.addProcessDefinition(getClassName(), getVersion(), "description", false, strDef, getParentFolder(), getDefId(), getAlias(), "class");
		
		String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
		
		/// generate source file
		String sourceCodeBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();
		File sourceCodeFile = new File(sourceCodeBase + "/" + getAlias());
		sourceCodeFile.getParentFile().mkdirs();
		//sourceCodeFile.createNewFile();
		
		FileWriter writer = new FileWriter(sourceCodeFile);
		writer.write(getSourceCodes().getSourceCode().getCode());
		writer.close();
		
		//if there is face code, save it.
		String faceSource = sourceCodeBase + "/" + getAlias();
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
		String faceHelperSource = sourceCodeBase + "/" + getAlias();
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
		//processManager.applyChanges();
		
		setDefId(definitionIdAndVersionId[0]);
		setDefVerId(definitionIdAndVersionId[1]);
				
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup checkout(){
		Popup popup = new Popup();
		popup.setName("SVN Client");
		popup.setPanel(new CheckoutWindow(this));
		
		return popup;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup commit(){
		Popup popup = new Popup();
		popup.setName("SVN Client");
		popup.setPanel(new CommitWindow(this));
		
		return popup;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK, callByContent=true)
	public Popup crowdSourcer(){
		Popup popup = new Popup();
		popup.setName("crowd Sourcer");
		popup.setPanel(new CrowdSourcerWindow(getDefId()));
		
		return popup;		
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
	
	
	@Override
	public void beforeSerialization() {
	}
	
	
	@Override
	public void afterDeserialization() {
		
		try {
			ProcessDefinition def = new ProcessDefinition();
			def.setDefId(new Long(getDefId()));
			String authorId = def.databaseMe().getAuthor();
			
			User author = new User();
			author.setUserId(authorId);
			setAuthor(author);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setSourceCodes(new ClassSourceCodes());
		
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias());
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			getSourceCodes().getSourceCode().setCode(bao.toString());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if there is face code, read it.
		String faceSource = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias();
		faceSource = faceSource.substring(0, faceSource.indexOf(".")) + ".ejs";
		
		File ejsFile = new File(faceSource);

		if(ejsFile.exists()){
			
			bao = new ByteArrayOutputStream();
			try {
				is = new FileInputStream(ejsFile);
				UEngineUtil.copyStream(is, bao);
				getSourceCodes().getFace().setCode(bao.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{getSourceCodes().getFace().setCode("");}
		
		//if there is facehelper code, read it.
		String faceHelperSource = CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias();
		faceHelperSource = faceHelperSource.substring(0, faceHelperSource.indexOf(".")) + ".ejs.js";
		
		File ejsJsFile = new File(faceHelperSource);

		if(ejsJsFile.exists()){
			
			bao = new ByteArrayOutputStream();
			try {
				is = new FileInputStream(ejsJsFile);
				UEngineUtil.copyStream(is, bao);
				getSourceCodes().getFaceHelper().setCode(bao.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		try {
			refreshClassInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void refreshClassInfo() throws Exception{
		//compile();
		
		WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType(getPackageName() + "." + getClassName()); 
	
		
		WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
		FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();

		ArrayList<ClassField> classFields = new ArrayList<ClassField>();
		for(int i=0; i<fields.length; i++){
			WebFieldDescriptor wfd = wfields[i];
			FieldDescriptor fd = fields[i];
			ClassField cf = new ClassField();
			cf.setFieldName(wfd.getName());
			cf.setType(fd.getClassType().getName());
			classFields.add(cf);
		}
		
		getSourceCodes().getClassModeler().setClassFields(classFields);
	}
}
