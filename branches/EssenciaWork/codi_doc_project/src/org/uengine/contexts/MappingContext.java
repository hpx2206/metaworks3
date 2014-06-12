package org.uengine.contexts;

import java.io.Serializable;

import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.webProcessDesigner.MappingCanvas;
import org.uengine.codi.mw3.webProcessDesigner.MappingTree;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ParameterContext;

public class MappingContext implements Serializable{
	private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;

	ParameterContext[] mappingElements;
		public ParameterContext[] getMappingElements() {
			return mappingElements;
		}
		public void setMappingElements(ParameterContext[] mappingElements) {
			this.mappingElements = mappingElements;
		}
	
	// 추가된 로직들...
	String id;
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	transient MappingTree mappingTreeLeft;
		public MappingTree getMappingTreeLeft() {
			return mappingTreeLeft;
		}
		public void setMappingTreeLeft(MappingTree mappingTreeLeft) {
			this.mappingTreeLeft = mappingTreeLeft;
		}

	transient MappingTree mappingTreeRight;
		public MappingTree getMappingTreeRight() {
			return mappingTreeRight;
		}
		public void setMappingTreeRight(MappingTree mappingTreeRight) {
			this.mappingTreeRight = mappingTreeRight;
		}

	MappingCanvas mappingCanvas;
		public MappingCanvas getMappingCanvas() {
			return mappingCanvas;
		}
		public void setMappingCanvas(MappingCanvas mappingCanvas) {
			this.mappingCanvas = mappingCanvas;
		}
}
