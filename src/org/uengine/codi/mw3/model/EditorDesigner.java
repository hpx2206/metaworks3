package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.ClassDefinition;

public class EditorDesigner extends ResourceDesigner{
	
	ClassDefinition classDefinition;
		@Name
		public ClassDefinition getClassDefinition() {
			return classDefinition;
		}
	
		public void setClassDefinition(ClassDefinition classDefinition) {
			this.classDefinition = classDefinition;
		}

	@ServiceMethod
	@Hidden
	@Override
	public void load() {
		classDefinition = new ClassDefinition();
		classDefinition.setAlias(getAlias().substring(0, getAlias().lastIndexOf(".")) + ".clsdef");
		classDefinition.getMetaworksContext().setWhere("form");
		classDefinition.getMetaworksContext().setWhen("view");
		classDefinition.afterDeserialization();
	}
}
