package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Hidden;


public class NewFormDesigner {
	
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
	
	String alias;
	@Hidden
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	
	public void load() {
		setForm(new Form());
		form.setPackageName(getAlias().substring(0, getAlias().lastIndexOf("/")));
		form.setId(getAlias().substring(getAlias().lastIndexOf("/") + 1, getAlias().lastIndexOf(".")));
		form.load();
		
		setProperties(new FormFieldProperties());
		
		setMenu(new FormFieldMenu());
		menu.load();
		
	}
	
}
