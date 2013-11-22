package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.ide.form.NewFormDesigner;
import org.uengine.codi.mw3.ide.form.NewFormDesignerLayout;

public class JavaDesigner extends ResourceDesigner{
	
	ClassDefinition classDefinition;
		@Name
		public ClassDefinition getClassDefinition() {
			return classDefinition;
		}
	
		public void setClassDefinition(ClassDefinition classDefinition) {
			this.classDefinition = classDefinition;
		}
	
	NewFormDesignerLayout newFormDesignerLayout;
	public NewFormDesignerLayout getNewFormDesignerLayout() {
		return newFormDesignerLayout;
	}

	public void setNewFormDesignerLayout(NewFormDesignerLayout newFormDesignerLayout) {
		this.newFormDesignerLayout = newFormDesignerLayout;
	}

	@ServiceMethod
	@Hidden
	@Override
	public void load() {
//		classDefinition = new ClassDefinition();
//		classDefinition.setAlias(getAlias().substring(0, getAlias().lastIndexOf(".")) + ".clsdef");
//		classDefinition.getMetaworksContext().setWhere("class");
//		classDefinition.afterDeserialization();
//		classDefinition.getMetaworksContext().setWhen("view");
		
		NewFormDesignerLayout formDesigner = new NewFormDesignerLayout();	
		formDesigner.load(getAlias());
		setNewFormDesignerLayout(formDesigner);
	}

}
