package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.FormDefinition;
import org.uengine.codi.mw3.admin.FormInstance;
import org.uengine.codi.mw3.model.ContentPanel;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Class Designer", options={"hideLabels"}, values={"true"})
public class ClassDesignerContentPanel extends ContentPanel{
	

	ClassDefinition classDefinition;
		public ClassDefinition getClassDefinition() {
			return classDefinition;
		}
		public void setClassDefinition(ClassDefinition classDefinition) {
			this.classDefinition = classDefinition;
		}

	public void newClass(String parentFoler) throws Exception{
		classDefinition = new ClassDefinition();
		classDefinition.setParentFolder(parentFoler);
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		classDefinition = (ClassDefinition) GlobalContext.deserialize(resource, ClassDefinition.class);
	}

	
	@Autowired
	ProcessManagerRemote processManager;

}
