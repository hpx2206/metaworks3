package org.metaworks.metadata;

import java.io.File;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.form.FormEditor;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath = "dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs",
		ejsPathMappingByContext={
	"{where: 'ide', face: 'dwr/metaworks/org/metaworks/metadata/FormProperty.ejs'}",
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/FormProperty.ejs'}"
})
@XStreamAlias("MetadataProperty")
public class FormProperty extends MetadataProperty{
	
	public FormProperty() {
		setType(MetadataProperty.FORM_PROP);
	}
	
	String value;
		@Hidden
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

	@Override
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public Object modify() throws Exception {
		
		Project project = workspace.findProject(this.getProjectId());
		
		ResourceNode node = new ResourceNode();
		node.setId(this.getProjectId() + File.separatorChar + this.getValue());
		node.setName(this.getValue());
		node.setPath(project.getBuildPath().getSources().get(0).getPath() + File.separatorChar + this.getValue());
		node.setProjectId(this.getProjectId());
		
		FormEditor formEditor = new FormEditor(node);
		formEditor.workspace = workspace;
		formEditor.load();
		
		ModalWindow modalWindow = new ModalWindow(formEditor, 0, 0, "폼 편집");
		
		modalWindow.getButtons().put("$Save", "save");
		modalWindow.getButtons().put("$Cancel", null);
		modalWindow.getCallback().put("$Save", "changeFile");
		
		return modalWindow;
	}
	
	@ServiceMethod(callByContent=true)
	public void changeFile() throws Exception {

		String path = this.getProjectId() + File.separatorChar + file.getUploadedPath();
		
		Project project = workspace.findProject(this.getProjectId());
		
		Object previewForm = Thread.currentThread().getContextClassLoader().loadClass(project.getBuildPath().makeFullClassName(path)).newInstance();
				
		
		setFilePreview(previewForm);
		
	}

}
