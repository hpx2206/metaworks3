package org.uengine.codi.mw3.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.processmanager.ProcessManagerRemote;

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
	
	private List<ClassDefinition> clsList;
	
	public List<ClassDefinition> getClsList() {
		return clsList;
	}
	public void setClsList(List<ClassDefinition> clsList) {
		this.clsList = clsList;
	}

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
		
		clsList = new ArrayList<ClassDefinition>();
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
	@Test(
			scenario="WebServiceDefinition", 
			starter=true,
			value="'test'", 
			instruction="'test'를 입력하세요.", 
			next="generateAdapter()"
			)
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
		
		String userId = null;
		try{
			userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
		}catch(Exception e){
			
		}
		
		String rootPath = CodiClassLoader.getMyClassLoader().sourceCodeBase();		
		
		/*
		WSDLToJava w2j = new WSDLToJava(new String[] {
				"-client",
				"-p",
				getTargetPackage(),
				"-d",
				rootPath,
				// "-compile",
				"-impl",
				getWsdlUrl()
			});
		
        try {

            w2j.run(new ToolContext());

        } catch (ToolException ex) {
            System.err.println();
            System.err.println("WSDLToJava Error: " + ex.getMessage());
            System.err.println();
            if (w2j.isVerbose()) {
                ex.printStackTrace();
            }
            if (w2j.isExitOnFinish()) {
                System.exit(1);
            }
            
        } catch (Exception ex) {
            System.err.println("WSDLToJava Error: " + ex.getMessage());
            System.err.println();
            if (w2j.isVerbose()) {
                ex.printStackTrace();
            }
            if (w2j.isExitOnFinish()) {
                System.exit(1);
            }
            
        }
        if (w2j.isExitOnFinish()) {
            System.exit(0);
        }
        */
        
		WSDLToJava.main(new String[] {
				"-client",
				"-p",
				getTargetPackage(),
				"-d",
				rootPath,
				// "-compile",
				"-impl",
				getWsdlUrl()
			});
		
		
		String inTargetPackage = getTargetPackage();
		String filePath = rootPath + "/" + inTargetPackage.replace(".", "/");
		
		File f = new File(filePath);
		
		
		File[] arrFile = f.listFiles();
		if(arrFile.length > 0) {
		    for(int i = 0; i < arrFile.length; i++){
		    	
		    	StringBuffer sb = new StringBuffer();
		    	
		    	ClassDefinition classDefinition = new ClassDefinition();
		    	classDefinition.processManager = processManager; 
		    	
				classDefinition.setParentFolder("1");
				
				classDefinition.setPackageName(getTargetPackage());
				classDefinition.setClassName(arrFile[i].getName().replace(".java", ""));	
				
				char[] c = new char[(int)arrFile[i].length()];
				BufferedReader br = new BufferedReader(new FileReader(arrFile[i]));
				br.read(c);
				
				sb.append(c);
				
				classDefinition.generateFaceHelperSourceCode();
				classDefinition.getSourceCodes().setSourceCode(new JavaSourceCode());
				classDefinition.getSourceCodes().getSourceCode().setCode(sb.toString());
				classDefinition.save();
				
				clsList.add(classDefinition);
				
		    }
		}
	    
		getMetaworksContext().setWhen("view");
		
	}
	
	@ServiceMethod(callByContent=true ,where="step1", when="edit") 
	public void next() throws Exception  {
		
		getMetaworksContext().setWhen("edit");
		getMetaworksContext().setWhere("step2");
		
		URL targetUrl = new URL(wsdlUrl);
		setTargetPackage(targetUrl.getHost());
		String serviceName = "";
		
		int slashIdx = targetUrl.getPath().lastIndexOf("/");
		int tokenIndex = 0;
		
		if(targetUrl.getPath().contains(".wsdl")) {
			tokenIndex = targetUrl.getPath().lastIndexOf(".wsdl");
			tokenIndex -= 1;
		} else if(targetUrl.getPath().contains("?wsdl")) {
			tokenIndex = targetUrl.getPath().lastIndexOf("?wsdl");
			tokenIndex -= 1;
		} else {
			tokenIndex = targetUrl.getPath().length();
		}
		
		serviceName = targetUrl.getPath().substring(slashIdx + 1, tokenIndex);
		setName(serviceName);
		
		
	}
	
	@ServiceMethod(callByContent=true ,where="step2", when="edit") 
	public void previous() throws Exception  {
		getMetaworksContext().setWhen("edit");
		getMetaworksContext().setWhere("step1");
	}

	
}
