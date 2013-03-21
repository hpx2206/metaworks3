package org.uengine.codi.mw3.ide.editor.java;

import java.util.List;

public class JavaMethod {

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String returnType;
		public String getReturnType() {
			return returnType;
		}
		public void setReturnType(String returnType) {
			this.returnType = returnType;
		}
	
	List<JavaField> parameters;
		public List<JavaField> getParameters() {
			return parameters;
		}
		public void setParameters(List<JavaField> parameters) {
			this.parameters = parameters;
		}
		
	String ownerClassName;
		public String getOwnerClassName() {
			return ownerClassName;
		}
		public void setOwnerClassName(String ownerClassName) {
			this.ownerClassName = ownerClassName;
		}
	
}
