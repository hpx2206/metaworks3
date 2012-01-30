package org.uengine.codi.mw3.model;

import javax.persistence.Id;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs", displayName="New Object...", options={"hideLabels"}, values={"true"})
public class NewChildContentPanel extends ContentWindow {
	
	Long parentFolder;
	@Id
	@Hidden
		public Long getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(Long parentFolder) {
			this.parentFolder = parentFolder;
		}

	@ServiceMethod
	public EntityDesignerWindow newEntity() throws Exception{
		EntityDesignerWindow entityDesignerWindow = new EntityDesignerWindow(getParentFolder().toString());
				
		return entityDesignerWindow;
	}

	/*
	public EntityDesignerContentPanel newEntity() throws Exception{
		EntityDesignerContentPanel entityDesigner = new EntityDesignerContentPanel();
		entityDesigner.newEntity(getParentFolder().toString());
		
		return entityDesigner;
	}
	*/

		
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
