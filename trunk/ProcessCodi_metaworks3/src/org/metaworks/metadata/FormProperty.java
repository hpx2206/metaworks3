package org.metaworks.metadata;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.form.FormEditor;

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
		String sourceCodeBase = CodiClassLoader.mySourceCodeBase("jwtest01");
		
		ResourceNode node = new ResourceNode();
		node.setId(this.getValue());
		node.setName(this.getValue());
		node.setPath(sourceCodeBase + this.getValue());
		
		FormEditor formEditor = new FormEditor(node);
		formEditor.load();
		
		return formEditor;
	}

}
