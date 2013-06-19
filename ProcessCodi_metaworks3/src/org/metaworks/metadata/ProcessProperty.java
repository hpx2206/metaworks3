package org.metaworks.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext={
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/ProcessProperty.ejs'}"
})
@XStreamAlias("MetadataProperty")
public class ProcessProperty extends MetadataProperty{
	
	public ProcessProperty() {
		setType(MetadataProperty.PROCESS_PROP);
	}
	
	String value;
		@Hidden
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true)
	public Object modify() throws Exception {
		String sourceCodeBase = CodiClassLoader.mySourceCodeBase("jwtest01");
		
		ResourceNode node = new ResourceNode();
		node.setId(this.getValue());
		node.setName(this.getValue());
		node.setPath(sourceCodeBase + this.getValue());
		
		ProcessEditor processEditor = new ProcessEditor(node);
		processEditor.load();
		
		return processEditor;
	}		
}
