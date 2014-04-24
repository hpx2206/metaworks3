package org.uengine.contexts;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.webProcessDesigner.MappingCanvas;
import org.uengine.codi.mw3.webProcessDesigner.MappingTree;
import org.uengine.codi.mw3.webProcessDesigner.SubProcessResourceTree;

public class SubProcessContext implements ContextAware ,Serializable {

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	transient MappingTree mappingTree;
		public MappingTree getMappingTree() {
			return mappingTree;
		}
		public void setMappingTree(MappingTree mappingTree) {
			this.mappingTree = mappingTree;
		}
		
	MappingCanvas mappingCanvas;
		public MappingCanvas getMappingCanvas() {
			return mappingCanvas;
		}
		public void setMappingCanvas(MappingCanvas mappingCanvas) {
			this.mappingCanvas = mappingCanvas;
		}
		
	transient	SubProcessResourceTree subProcessResourceTree;
		public SubProcessResourceTree getSubProcessResourceTree() {
			return subProcessResourceTree;
		}
		public void setSubProcessResourceTree(
				SubProcessResourceTree subProcessResourceTree) {
			this.subProcessResourceTree = subProcessResourceTree;
		}
}
