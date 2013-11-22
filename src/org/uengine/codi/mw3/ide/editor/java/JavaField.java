package org.uengine.codi.mw3.ide.editor.java;


public class JavaField {
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	boolean statics;
		public boolean isStatics() {
			return statics;
		}
		public void setStatics(boolean statics) {
			this.statics = statics;
		}
	
	@Override
	public boolean equals(Object name){
		return this.getName().equals(name);
	}
	
}
