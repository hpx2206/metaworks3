package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.annotation.Id;
import org.uengine.kernel.ParameterContext;

public class MappingCanvas implements Serializable {
	
	String canvasId;
	@Id
		public String getCanvasId() {
			return canvasId;
		}
		public void setCanvasId(String canvasId) {
			this.canvasId = canvasId;
		}
	String leftTreeId;
		public String getLeftTreeId() {
			return leftTreeId;
		}
		public void setLeftTreeId(String leftTreeId) {
			this.leftTreeId = leftTreeId;
		}
	String rightTreeId;
		public String getRightTreeId() {
			return rightTreeId;
		}
		public void setRightTreeId(String rightTreeId) {
			this.rightTreeId = rightTreeId;
		}
		
	ParameterContext[] mappingElements;
		public ParameterContext[] getMappingElements() {
			return mappingElements;
		}

		public void setMappingElements(ParameterContext[] mappingElements) {
			this.mappingElements = mappingElements;
		}
}