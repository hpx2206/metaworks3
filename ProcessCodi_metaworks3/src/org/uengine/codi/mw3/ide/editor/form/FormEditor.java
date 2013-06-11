package org.uengine.codi.mw3.ide.editor.form;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
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
		
		String packageName = project.getBuildPath().makePackageName(this.getId());
		String className = project.getBuildPath().makeClassName(this.getId());
		
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
	
	@Face(displayName="$Preview")
	@ServiceMethod(payload={"resourceNode", "form"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object preview() throws Exception {
		Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
		
		Object o = Thread.currentThread().getContextClassLoader().loadClass(form.getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

		MetaworksRemoteService.getInstance().getMetaworksType(form.getFullClassName());
		
		Window outputWindow = new Window();
		outputWindow.setPanel(o);
		
		return new ModalWindow(outputWindow, 0, 0, "$Preview");
		
	}

}
