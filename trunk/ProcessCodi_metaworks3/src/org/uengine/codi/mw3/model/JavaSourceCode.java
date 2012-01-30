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

public class JavaSourceCode extends SourceCode{

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist() {

		CodeAssist codeAssist = super.requestAssist();
				
		ArrayList<ClassField> classFields = classDefinition.getClassFields();
		
		for(ClassField field : classFields){
			codeAssist.getAssistances().add(field.getFieldName());
		}
		
		return codeAssist;
	}
	
	@AutowiredFromClient
	public ClassDefinition classDefinition;
	
}
