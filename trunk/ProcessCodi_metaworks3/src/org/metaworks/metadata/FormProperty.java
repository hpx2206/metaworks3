package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.form.FormEditor;
import org.uengine.codi.util.CodiFileUtil;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext={
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
	public Object modify() throws Exception {
		String projectId = this.getProjectId();
		String sourceCodeBase = CodiClassLoader.mySourceCodeBase(projectId);
		
		ResourceNode node = new ResourceNode();
		node.setId(this.getValue());
		node.setName(this.getValue());
		node.setPath(sourceCodeBase + this.getValue());
		node.setProjectId(projectId);
		
		Workspace workspace = new Workspace();
		ArrayList<Project> projects = new ArrayList<Project>();
		
		String codeBasePath = CodiClassLoader.mySourceCodeBase();
		CodiFileUtil.mkdirs(codeBasePath);
		Project tenantMain = new Project();
		tenantMain.setId(projectId);
		tenantMain.setPath(codeBasePath);
		tenantMain.load();
		projects.add(tenantMain);
		workspace.setProjects(projects);
		
		FormEditor formEditor = new FormEditor(node);
		formEditor.workspace = workspace;
		formEditor.load();
		
		return formEditor;
	}

}
