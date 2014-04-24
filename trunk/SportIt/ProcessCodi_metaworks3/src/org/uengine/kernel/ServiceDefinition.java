/*
 * Created on 2004-05-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.uengine.kernel;

import com.ibm.wsdl.factory.*;
import javax.wsdl.xml.*;
import javax.wsdl.*;
import java.util.*;
import java.io.*;

import org.uengine.processdesigner.AntToolDialog;
import org.uengine.util.UEngineUtil;

/**
 * @author Jinyoung Jang
 */
public class ServiceDefinition implements java.io.Serializable{
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	static Hashtable serviceDefinitions;

	String name;
		public String getName() {
			return name;
		}
		public void setName(String string) {
			name = string;
		}

	String wsdlLocation;
		public String getWsdlLocation() {
			return wsdlLocation;
		}
		public void setWsdlLocation(String string) {
			wsdlLocation = string;
		}
	
	String stubPackage;
		public String getStubPackage() {
			return stubPackage;
		}
		public void setStubPackage(String string) {
			stubPackage = string;
		}

		
	public Definition getDefinition() throws Exception{
/*		synchronized(serviceDefinitions){
			if(serviceDefinitions==null)
				serviceDefinitions = new Hashtable();
			
			if(!serviceDefinitions.containsKey(getName())){
				String wsdlUrl = getWsdlLocation();
				WSDLReader reader = (new WSDLFactoryImpl()).newWSDLReader();
				serviceDefinitions.put(getName(), reader.readWSDL(wsdlUrl));
			}
		
			return (Definition)serviceDefinitions.get(getName());		
		}*/
		
		//only when build time, it is needed to load wsdl file
		if(!GlobalContext.isDesignTime())
			return null;
		
		String wsdlUrl = getWsdlLocation();
		WSDLReader reader = (new WSDLFactoryImpl()).newWSDLReader();
		return reader.readWSDL(wsdlUrl);
	}
	
	public void generateStub() throws Exception{
		generateStub(System.out);
	}		
	
	public void generateStub(final PrintStream out) throws Exception{		
		String stubDir = System.getProperty(GlobalContext.PROPERTY_STUB_DIR, "./stubs");
		String antTool = System.getProperty(GlobalContext.PROPERTY_ANT_PATH, "ant");
		
		stubDir = stubDir + System.getProperty("file.separator") + getName();
		
		Definition def = getDefinition();
		String targetNS = def.getTargetNamespace();
		
/*		List list = def.getTypes().getExtensibilityElements();
		
		for(Iterator iter = list.iterator(); iter.hasNext();){
			Object data = iter.next();
		}*/
		
		String stubPkgName = UEngineUtil.QName2PackageName(def.getTargetNamespace());		
		setStubPackage(stubPkgName);
		 
		String[] args = 
		new String[]{
					"wsdl2java",
					"-Dparam.url=\"" + getWsdlLocation() +"\"",
					"-Dparam.output=\"" + new File(stubDir).getAbsolutePath() +"\"",
					"-Dparam.name=\"" + getName() +"\"",
				};
				
		String argLine = "";
		for(int i=0; i<args.length; i++)
			argLine += (args[i] + " "); 

		AntToolDialog ant = new AntToolDialog();
		ant.setCommand(argLine);
		ant.run();
	}

	public String toString(){
		return getName();
	} 
	
	public boolean equals(Object obj){
		if(obj !=null)
			return getName().equals(((ServiceDefinition)obj).getName());
			
		return false;
	}

}
