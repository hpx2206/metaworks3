package org.uengine.codi.mw3.admin;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;


public class ClassNameInput{
	
	String className;
	
		public String getClassName() {
			return className;
		}
	
		public void setClassName(String className) {
			this.className = className;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist(){
		CodeAssist codeAssist = new CodeAssist();
		codeAssist.setAssistances(new ArrayList<String>());
//			codeAssist.getAssistances().add("");
		
		//codeAssist.setSrcCodeObjectId(clientObjectId);
		
		return codeAssist;
	}
}