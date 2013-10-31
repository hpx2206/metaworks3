package org.uengine.codi.mw3.model;

import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class WebServiceReference implements ContextAware {

	private String name;
	private String wsdlUrl;
	private String targetPackage;
	private String compileOption;
	
	MetaworksContext metaworksContext;


	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	public String getTargetPackage() {
		return targetPackage;
	}
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
	public String getCompileOption() {
		return compileOption;
	}
	public void setCompileOption(String compileOption) {
		this.compileOption = compileOption;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
	
	public WebServiceReference() {}
	
	@ServiceMethod(callByContent=true)
	public void generateAdapter() throws Exception {
		
//		"-client",
//		"-p",
//		"com.roomnine.userservice",
//		"-d",
//		"src",
//		"-compile",
//		"-impl",
//		"http://localhost:9000/UsersService?wsdl"
		
		WSDLToJava.main(new String[] {
				"-client",
				"-p",
				"org.uengine.codi.mw3"  + name,
				"-d",
				"/Users/roomnine/Development/workspace/uEngine/ProcessCodi_metaworks3/src",
				"-compile",
				"-impl",
				"http://localhost:9000/UsersService?wsdl"
			});
	}
	
	
	
	
}
