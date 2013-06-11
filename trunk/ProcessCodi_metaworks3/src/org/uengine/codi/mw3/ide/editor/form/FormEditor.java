package org.uengine.codi.mw3.ide.editor.form;

import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.FormFieldMenu;
import org.uengine.codi.mw3.ide.form.FormFieldProperties;

public class FormEditor extends Editor {

	Form form;
		public Form getForm() {
			return form;
		}
		public void setForm(Form form) {
			this.form = form;
		}
		
	FormFieldProperties properties;
		public FormFieldProperties getProperties() {
			return properties;
		}
		public void setProperties(FormFieldProperties properties) {
			this.properties = properties;
		}
	
	FormFieldMenu menu;
		public FormFieldMenu getMenu() {
			return menu;
		}	
		public void setMenu(FormFieldMenu menu) {
			this.menu = menu;
		}
	
	public FormEditor() {
		
	}
	
	public FormEditor(ResourceNode resourceNode){
		super(resourceNode);	
	}
	@Override
	public String load() {
		
		Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
		
		String fullClassName = project.getBuildPath().makeFullClassName(this.getId());
		String packageName = null;
		String className = null;
		
		if(fullClassName.indexOf('.') > -1){
			packageName = fullClassName.substring(0, fullClassName.lastIndexOf('.'));
			className = fullClassName.substring(fullClassName.lastIndexOf('.')+1); 
		}else{
			className = fullClassName;
		}
		
		Form form = new Form();
		form.setPackageName(packageName);
		form.setId(className);
		form.load();
		
		this.setForm(form);
		
		setProperties(new FormFieldProperties());
		
		setMenu(new FormFieldMenu());
		menu.load();
		
		return null;
	}
	
	@Override
	public Object save() {
		// TODO: 만들어서 this.setContent() 넣어주면되
		this.setContent(form.generateJavaCode());
		
		// TODO Auto-generated method stub
		return super.save();
	}
}
