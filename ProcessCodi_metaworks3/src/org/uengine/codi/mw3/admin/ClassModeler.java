package org.uengine.codi.mw3.admin;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class ClassModeler {
	public ArrayList<ClassField> classFields;
		public ArrayList<ClassField> getClassFields() {
			return classFields;
		}
	
		public void setClassFields(ArrayList<ClassField> classFields) {
			this.classFields = classFields;
		}

	public ArrayList<ClassMethod> classMethods;
		public ArrayList<ClassMethod> getClassMethods() {
			return classMethods;
		}
	
		public void setClassMethods(ArrayList<ClassMethod> classMethods) {
			this.classMethods = classMethods;
		}

	public ClassField newClassField;
	
		public ClassField getNewClassField() {
			return newClassField;
		}
	
		public void setNewClassField(ClassField newClassField) {
			this.newClassField = newClassField;
		}

	public ClassModeler() {
		init();

	}

	protected void init() {
		newClassField = new ClassField();
		newClassField.metaworksContext = new MetaworksContext();
		newClassField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newClassField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
	}
}