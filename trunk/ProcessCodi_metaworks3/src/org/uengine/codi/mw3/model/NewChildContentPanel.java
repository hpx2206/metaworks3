package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import javax.persistence.Id;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.FormDefinition;
import org.uengine.codi.mw3.admin.FormInstance;
import org.uengine.codi.mw3.model.ContentPanel;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="New Object...", options={"hideLabels"}, values={"true"})
public class NewChildContentPanel extends ContentPanel{
	
	Long parentFolder;
	@Id
		public Long getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(Long parentFolder) {
			this.parentFolder = parentFolder;
		}

	@ServiceMethod
	public ClassDesignerContentPanel newJavaClass() throws Exception{
		ClassDesignerContentPanel classDesigner = new ClassDesignerContentPanel();
		classDesigner.newClass(getParentFolder().toString());
		
		return classDesigner;
	}

	@ServiceMethod
	public ProcessDesignerContentPanel newProcess() throws Exception{
		ProcessDesignerContentPanel processDesigner = new ProcessDesignerContentPanel();
		processDesigner.newProcessDefinition(getParentFolder().toString());
		
		return processDesigner;
	}

	@ServiceMethod
	public FormDesignerContentPanel newForm(){
		return new FormDesignerContentPanel();
	}

}
