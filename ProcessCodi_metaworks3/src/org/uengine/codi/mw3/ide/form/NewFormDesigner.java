package org.uengine.codi.mw3.ide.form;


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
			
	public void load() {
		
		setForm(new Form());
		form.load();
		
		setProperties(new FormFieldProperties());
//		properties.load();
		
		setMenu(new FormFieldMenu());
		menu.load();
		
	}
	
}
