package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(options="hideEditBtn", values="true")   
public class WebServiceDefinition  {
	
	transient MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	String parentFolder;
	
	private String name;
	private String wsdlUrl;
	private String targetPackage;
	private String compileOption;
	
	@Autowired
	transient public ProcessManagerRemote processManager;
	
	@NonEditable		
	@Hidden
	public String getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}
	
	public WebServiceDefinition() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhere("step1");
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}

	@Available(where="step2")
	@Test(value="'Name'", next="package", instruction="'HelloWorld'를 입력하세요.")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Available(where="step1")
	@Test(value="'test'", next="name", testName="Service Name", instruction="'Web Service 명을 입력하세요.")
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
	
	@Available(where="step2")
	@Test(value="'Package'", next="compileOpeion", instruction="'HelloWorld'를 입력하세요.")
	public String getTargetPackage() {
		return targetPackage;
	}
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
	
	@Available(where="step2")
	@Test(value="'generateAdapter'", next="generateAdapter()", instruction="'소스코드를 작성하세요.")
	public String getCompileOption() {
		return compileOption;
	}
	public void setCompileOption(String compileOption) {
		this.compileOption = compileOption;
	}
	
	@ServiceMethod(callByContent=true, where="step1")
	public void generateAdapter() throws Exception {
		
		
//		"-client",
//		"-p",
//		"com.roomnine.userservice",
//		"-d",
//		"src",
//		"-compile",
//		"-impl",
//		"http://localhost:9000/UsersService?wsdl"
		
		String userId = null;
		try{
			userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
		}catch(Exception e){
			
		}
		
		String rootPath = "/Users/roomnine/codebase/"+ userId + "/src/";
		
		
		WSDLToJava.main(new String[] {
				"-client",
				"-p",
				getTargetPackage()  + "." + name,
				"-d",
				rootPath,
				// "-compile",
				"-impl",
				"http://localhost:9000/UsersService?wsdl"
			});
		
		String inTargetPackage = getTargetPackage() + "." + name;
		String filePath = rootPath + inTargetPackage.replace(".", "/");
		
		File f = new File(filePath);
		StringBuffer sb = new StringBuffer();
		
		
		File[] arrFile = f.listFiles();
		
	    for(int i = 0; i < arrFile.length; i++){
	    	ClassDefinition classDefinition = new ClassDefinition();
	    	classDefinition.processManager = processManager; 
			classDefinition.setParentFolder(getParentFolder().toString());
			classDefinition.setPackageName(getTargetPackage() + "." + name);
			classDefinition.setClassName(arrFile[i].getName().replace(".java", ""));	
			
			char[] c = new char[(int)arrFile[i].length()];
			BufferedReader br = new BufferedReader(new FileReader(arrFile[i]));
			br.read(c);
			
			sb.append(c);
			
			classDefinition.generateFaceHelperSourceCode();
			classDefinition.getSourceCodes().setSourceCode(new JavaSourceCode());
			classDefinition.getSourceCodes().getSourceCode().setCode(sb.toString());
			classDefinition.save();
	    }
	    
		getMetaworksContext().setWhen("view");
		
	}
	
	@ServiceMethod(callByContent=true ,where="step1", when="edit") 
	public void next() throws Exception  {
		getMetaworksContext().setWhen("edit");
		getMetaworksContext().setWhere("step2");
	}

	
}
