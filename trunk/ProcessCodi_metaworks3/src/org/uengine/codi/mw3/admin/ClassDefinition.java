
package org.uengine.codi.mw3.admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.metaworks.ContextAware;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.alm.QualityOption;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.JavaSourceCode;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.User;
import org.uengine.codi.mw3.svn.CheckoutWindow;
import org.uengine.codi.mw3.svn.CommitWindow;
import org.uengine.codi.platform.Console;
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
	transient public ProcessManagerRemote processManager;

	public ClassDefinition(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	public void load(){
		//setClassName("클래스 명을 입력하십시오.");
		// 편집탭 설정
		ClassSourceCodes sourceCodes = new ClassSourceCodes();
		sourceCodes.setMetaworksContext(this.getMetaworksContext());
		sourceCodes.load();
		
		setSourceCodes(sourceCodes); 
		
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
	@Hidden
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		
	transient User author; 
		@Available(when={"edit", "step1"})
		@Face(displayName="$author")
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
		@Hidden
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
	
	String className;
		@Available(when={"edit", "step1"})
		@Face(displayName="$name", options={"mandatory"}, values={"true"})
		@Name
		@Test(
				scenario="ClassDefinition", 
				value="'HelloWorld'", 
				instruction="'HelloWorld'를 입력하세요.",
				next="next1()"
				)
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}

	transient ClassSourceCodes sourceCodes;
	@Available(when={"step1", "view"})
//	@TestSet({
		@Test(
				scenario="ClassDefinition", 
				instruction="소스코드를 작성하세요.",
				next="run()"
			)
//		@Test(next="run()", instruction="소스코드를 작성하세요.", testName="")
//	})
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
		

	public void generateSourceCode(){
		
		StringBuffer sb = new StringBuffer();
		sb
			.append("package ").append(getPackageName()).append(";\n\n")
			.append("public class " + getClassName() + "{\n\n");
		
		if(getSourceCodes().getClassModeler()!=null && getSourceCodes().getClassModeler().getClassFields()!=null)
		for(int i=0; i<getSourceCodes().getClassModeler().getClassFields().size(); i++){
			ClassField field = getSourceCodes().getClassModeler().getClassFields().get(i);
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getId());
			String fieldType = field.getType();
			
			if(fieldType.startsWith("java.lang"))
				fieldType = fieldType.substring("java.lang".length()+1);
			
			sb
				.append("	").append(fieldType).append(" ").append(field.getId()).append(";\n")
				.append("		public ").append(fieldType).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getId()).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(fieldType).append(" ").append(field.getId()).append("){ this.").append(field.getId()).append(" = ").append(field.getId()).append("; }\n\n")
				;
		}
		
		sb.append("}");
		
		if(sourceCodes==null){
			sourceCodes = new ClassSourceCodes();
			sourceCodes.load();
		}
		
		getSourceCodes().sourceCode = new JavaSourceCode();
		getSourceCodes().sourceCode.setCode(sb.toString());
		getSourceCodes().face = new FaceEditor();
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

//	@ServiceMethod(callByContent=true, when="view", keyBinding="Ctrl+S")
	@ServiceMethod(callByContent=true, when="view")
	public Object compile() throws Exception{
		try{
			save();
		}catch(Exception e){
			return new CheckoutWindow(); //or confirmation dialog for launching CheckoutWindow
		}
	
		//TODO: please check the package name & static code analysis as much as possible here.
		
		if(getSourceCodes()==null || getSourceCodes().getSourceCode()==null) return this;
		
		CodiClassLoader.refreshClassLoader(getAlias());
		
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
			int index = 2;
			
			System.out.println("complie error");			
			try {
				parts = message.split(":");					
				if(parts[2].startsWith("/"))
					index++;
				
				lineNumber = Integer.parseInt(parts[index]);
				message = parts[index+1];
				
			} catch (Exception e1) {
				parts = null;
			}
			
			CompileError compileError = new CompileError();
			
			if(parts != null)
				compileError.setLine(lineNumber);
			
			compileError.setColumn(1);
			compileError.setMessage(e.getMessage());			
			compileErrors.add(compileError);
			
			Console.addError(e.getMessage());
			
			e.printStackTrace();
		} 
		
		
		//// try quality options /////
		
		if(getQualityOption()!=null && getQualityOption().getPmdRuleOption()!=null){
			
			
			
//			PMD.main(new String[]{
//					CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias(),
//					"org.uengine.codi.mw3.alm.MemoryRenderer",
//					getQualityOption().getPmdRuleOption().generatePMDOption()
//					
//			});
//			
//			for(Iterator<IRuleViolation> violations: MemoryRenderer.recentViolations){
//		        while (violations.hasNext()) {
//		            IRuleViolation rv = violations.next();
//
//		            System.out.println(rv.getFilename());
//		            System.out.println(rv.getBeginColumn());
//		            System.out.println(rv.getDescription());
//		            
//					CompileError compileError = new CompileError();
//					compileError.setLine(rv.getBeginLine());
//					compileError.setColumn(rv.getBeginColumn());
//					compileError.setMessage(rv.getDescription());
//					
//					compileErrors.add(compileError);
//
//		        }
//		    }
		}
		
		CompileError[] compileErrorInArray = new CompileError[compileErrors.size()];
		compileErrors.toArray(compileErrorInArray);
		
		getSourceCodes().getSourceCode().setCompileErrors(compileErrorInArray);		
		if(compileErrorInArray.length == 0)
			refreshClassInfo();
		
		
		return this;

	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP, when="view")
	@Face(displayName = "Run")
	@Test(
			scenario="ClassDefinition", 
			instruction="프로그램을 실행하기 위하여 클릭합니다.",
			next="autowiredObject.org.uengine.codi.mw3.admin.Runner.runMobile()" 
			)
	public Popup run() throws Exception{		

//		try{
		compile();
//		}catch(Exception e){}
		
		Runner runner = new Runner();
		runner.setFullClassName(getPackageName() + "." + getClassName());

		Popup runnerPopup = new Popup();
		runnerPopup.setName("Run...");
		runnerPopup.setPanel(runner);
	
		return runnerPopup;
	}
	
	
		
	/////// for wizard
	

//	transient ClassModeler wizardClassModeler;		
//	@Available(when={"step2"})
//		public ClassModeler getWizardClassModeler() {
//			return wizardClassModeler;
//		}
//		public void setWizardClassModeler(ClassModeler temporalClassModeler) {
//			this.wizardClassModeler = temporalClassModeler;
//		}
		
		
	@ServiceMethod(callByContent=true, when="edit")
	@Face(displayName="Next > ")
	@Test(
			scenario="ClassDefinition", 
			instruction="다음단계로 넘어가기 위해 클릭합니다.",
			next="sourceCodes"
			)
	public void next1(){
		
		getMetaworksContext().setWhen("view");
		generateSourceCode();
//		setWizardClassModeler(new ClassModeler());
	}

	@ServiceMethod(callByContent=true, when="step1")
	@Face(displayName="Finish")
//	@Test(target="next3")
	public void next2() throws Exception{
		save();
//		getSourceCodes().setClassModeler(getWizardClassModeler());
		getMetaworksContext().setWhen("view");
	}

	
//	@ServiceMethod(callByContent=true, when="step2")
//	public void next3(){
//		
//		getMetaworksContext().setWhen("view");
//	}
	
//////// end for wizard

	
//	@ServiceMethod(callByContent=true)
//	@Face(displayName = "Save")
	public void save() throws Exception{
		
        CodiClassLoader contextClassLoader = CodiClassLoader.getMyClassLoader();
		String myWorkingCopyPath = ((CodiClassLoader)contextClassLoader).mySourceCodeBase();//"/Users/jyjang/MyWorkingCopy";
		
        if(myWorkingCopyPath==null)
        	throw new Exception("소셜코딩을 환영합니다! 소스코드를 수정하려면 먼저 페이스북 로그인을 하신후 체크아웃(checkout)을 받으셔야 합니다.");
        	
        File wcDir = new File(myWorkingCopyPath).getParentFile(); //project folder is one level parent folder than 'src'
        
        if (!wcDir.exists()) {
        	throw new Exception("소셜코딩을 환영합니다! 소스코드를 수정하려면 체크아웃(checkout)을 먼저 하십시오.");
        }

		
		setAlias(getPackageName().replace('.', '/') + "/" + getClassName() + ".java");

		
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

//		if(UEngineUtil.isNotEmpty(getSourceCodes().getFace().getEditor().getContents())){
		if(UEngineUtil.isNotEmpty(getSourceCodes().getFace().getCode())){
			
			writer = new FileWriter(ejsFile);
			writer.write(new String(getSourceCodes().getFace().getEditor().getContents().getBytes("UTF-8"),"UTF-8"));
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
		
		if( this.getMetaworksContext().getWhere().equalsIgnoreCase("form")){
			String formSource = sourceCodeBase + "/" + getAlias();
			formSource = faceHelperSource.substring(0, faceHelperSource.indexOf(".")) + ".editor";
			File formFile = new File(formSource);
			writer = new FileWriter(formFile);
			writer.write(getSourceCodes().getSourceCode().getCode());
			writer.close();
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
		
//		String strDef = GlobalContext.serialize(this, ClassDefinition.class);
//		
//		String fullDefId = processManager.addProcessDefinition(getClassName(), getVersion(), "description", false, strDef, getParentFolder(), getDefId(), getAlias(), "class");
//		
//		String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
//
//		
//		processManager.setProcessDefinitionProductionVersion(definitionIdAndVersionId[1]);
//		//processManager.applyChanges();
//		
//		setDefId(definitionIdAndVersionId[0]);
//		setDefVerId(definitionIdAndVersionId[1]);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, when="view")
	@Face(displayName="Sync")
	
	public Popup sync(){
		Popup popup = new Popup();
		popup.setName("SVN Client");
		popup.setPanel(new CheckoutWindow(this));
		
		return popup;
	}

//	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, when="view")
	public Popup commit(){
		Popup popup = new Popup();
		popup.setName("SVN Client");
		popup.setPanel(new CommitWindow(this));
		
		return popup;
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_STICK, callByContent=true, when=MetaworksContext.WHEN_VIEW)
	@Face(displayName = "Post to my wall")
	public Popup share(){
		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)httpSession.getAttribute("userId");
		
		Login login = new Login();
		login.setUserId(userId);

		Popup popup = new Popup();
		popup.setName("공유하기...");
		popup.setPanel(new CrowdSourcerWindow(login, getDefId()));
		
		return popup;		
	}


	public static ArrayList<String> listProperties(String className) {
		try {
			
			if(className.startsWith("[")){
				className = className.substring(1, className.length()-6);
				className = className.replace("/", ".");
			}
			
			WebObjectType type = MetaworksRemoteService.getInstance().getMetaworksType(className);
			
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
	public ArrayList<String> listProperties() {
			
		return listProperties(getPackageName() + "." + getClassName());
			
	}
	
	
	@Override
	public void beforeSerialization() {
	}
	
	
	@Override
	public void afterDeserialization() {
	
		String fullClassName = getAlias().substring(0, getAlias().indexOf("."));
		
		int whereLastSlash = fullClassName.lastIndexOf("/");
		
		if(whereLastSlash > -1){
			packageName = fullClassName.substring(0, whereLastSlash).replace('/', '.');
			className = fullClassName.substring(whereLastSlash+1);
		}else{
			packageName = "";
			className = fullClassName;
		}
		
		ClassSourceCodes newSourceCodes = new ClassSourceCodes();
		newSourceCodes.setMetaworksContext(this.getMetaworksContext());
		newSourceCodes.load();
		setSourceCodes(newSourceCodes);

		
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias().substring(0, getAlias().indexOf(".")) + ".java");
		
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
				getSourceCodes().getFace().getEditor().setContents(bao.toString());
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
		
		try {
			compile();
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
			cf.setId(wfd.getName());
			cf.setType(fd.getClassType().getName());
			cf.setMetaworksContext(new MetaworksContext());
			cf.getMetaworksContext().setWhere("in-container");
			cf.setDisplayname(fd.displayName);
			cf.setDisable(fd.getAttribute("hidden") != null ? fd.getAttribute("hidden").toString().equals("true") : false);
			cf.setInterface(fd.getClassType().isInterface());
			classFields.add(cf);
		}
		
		getSourceCodes().getClassModeler().setClassFields(classFields);
	}
	
	@ServiceMethod(callByContent=true ,when="edit", keyBinding="Ctrl+S")
	public void saveAsForm() throws Exception{
		StringBuffer sb = new StringBuffer();								
		StringBuffer importBuffer 			= new StringBuffer();		// import 생성 버퍼
		StringBuffer methodBuffer 		= new StringBuffer();		// 메서드 생성 버퍼
		StringBuffer constructorBuffer 	= new StringBuffer();		// 생성자 생성 버퍼
		StringBuffer overridingBuffer 	= new StringBuffer();		// overriding 생성 버퍼
		ArrayList<String> importList = new ArrayList<String>(); 
		
		constructorBuffer.append("	public " + getClassName() + "() { \n");
		
		importBuffer.append("import org.uengine.codi.ITool; \n");
		importBuffer.append("import org.metaworks.annotation.Face;\n");
		String classFaceOrderStr = "";
		if(getSourceCodes().getClassModeler()!=null && getSourceCodes().getClassModeler().getClassFields()!=null)
		for(int i=0; i<getSourceCodes().getClassModeler().getClassFields().size(); i++){
			ClassField field = getSourceCodes().getClassModeler().getClassFields().get(i);
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getId());
			String fieldType = field.getType();
			String importStr = "";
			if( i == 0){
				classFaceOrderStr = field.getId();
			}else{
				classFaceOrderStr += "," + field.getId();
			}
			
			if(fieldType.startsWith("java.lang"))
				fieldType = fieldType.substring("java.lang".length()+1);
			
			if(fieldType.startsWith("org.")){
				String className = fieldType.substring(fieldType.lastIndexOf(".")+1);
				importStr = "import " + fieldType + " ;\n";
				if(!importList.contains(importStr)){
					importList.add(importStr);
				}
//				importBuffer.append("import ").append(fieldType).append(" ;\n");
				
				if(field.isInterface()){
					// 인터페이스인 경우, 객체 생성을 해주기 위하여 인터페이스가 아닌 클레스도 import 한다
					importStr = "import " + fieldType.substring(0, fieldType.lastIndexOf(".")+1) + className.substring(1) + " ;\n";
					if(!importList.contains(importStr)){
						importList.add(importStr);
					}
					constructorBuffer	
									.append("		").append("set").append(fieldNameFirstCharUpper)
									.append("( new ").append(className.substring(1)).append("() ); \n");
				}else{
					constructorBuffer	
									.append("		").append("set").append(fieldNameFirstCharUpper)
									.append("( new ").append(className).append("() ); \n");
				}
				fieldType = className;
			}
			methodBuffer.append("	").append(fieldType).append(" ").append(field.getId()).append(";\n");
			
			if( field.getDisable()){
				importStr = "import org.metaworks.annotation.Hidden;\n";
				if(!importList.contains(importStr)){
					importList.add(importStr);
				}
				methodBuffer.append("	@Hidden\n");
			}
			if( field.getDisplayname() != null && !field.getDisplayname().equals("")){
				methodBuffer.append("	@Face(displayName=\""+ field.getDisplayname() +"\")\n");
			}
				
			methodBuffer
				.append("		public ").append(fieldType).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getId()).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(fieldType).append(" ").append(field.getId()).append("){ this.").append(field.getId()).append(" = ").append(field.getId()).append("; }\n\n")
				;
		}
		
		constructorBuffer.append("	}\n");
		
		overridingBuffer.append("	@Override \n");
		overridingBuffer.append("	public void onLoad() {}	\n");
		overridingBuffer.append("	@Override \n");
		overridingBuffer.append("	public void beforeComplete() {}	\n");
		overridingBuffer.append("	@Override \n");
		overridingBuffer.append("	public void afterComplete() {}	\n");
		
		for(int i =0; i < importList.size(); i++){
			importBuffer.append(importList.get(i));
		}
		
		// 만들어진 소스들을 구성
		sb.append("package ").append(getPackageName()).append(";\n\n");
		sb.append(importBuffer.toString());
		sb.append("@Face(ejsPath=\"genericfaces/FormFace.ejs\" options={\"fieldOrder\"},values={\""+ classFaceOrderStr +"\"})\n");
		sb.append("public class " + getClassName() + "").append(" implements ITool ").append( "{\n\n");
		
		sb.append(constructorBuffer.toString());
		sb.append(methodBuffer.toString());
		sb.append(overridingBuffer.toString());
		
		sb.append("}");
		
		
		
		
		if(sourceCodes==null){
			sourceCodes = new ClassSourceCodes();
			sourceCodes.load();
		}
		
		getSourceCodes().sourceCode = new JavaSourceCode();
		getSourceCodes().sourceCode.setCode(sb.toString());
		
		compile();
	}
}
