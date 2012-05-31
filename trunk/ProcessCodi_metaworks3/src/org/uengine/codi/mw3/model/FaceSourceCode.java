package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;

public class FaceSourceCode extends JavaSourceCode{

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist() {
		return super.requestAssist();
		
		/*
		if(getLineAssistRequested().endsWith("fields")){
			return super.requestAssist();
		}else{
			CodeAssist assist = new CodeAssist();
			assist.setAssistances(new ArrayList<String>());
			
			if(getLineAssistRequested().indexOf("fields.") > -1){
				assist.getAssistances().add("here()");
			}else
			if(getLineAssistRequested().indexOf("methods.") > -1){
				assist.getAssistances().add("here()");
				assist.getAssistances().add("caller()");
			}
			
			assist.setSrcCodeObjectId(getClientObjectId());
			
			return assist;
		}
		*/
	}
	
	
	
	
	
}
