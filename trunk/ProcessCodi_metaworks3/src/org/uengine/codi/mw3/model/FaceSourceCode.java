package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;
import org.metaworks.example.ide.CodeAssister;
import org.metaworks.example.ide.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.mw3.admin.FormDefinition;
import org.uengine.codi.mw3.admin.FormInstance;
import org.uengine.codi.mw3.model.ContentPanel;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class FaceSourceCode extends JavaSourceCode{

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist() {
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
	}
	
	
	
	
	
}
