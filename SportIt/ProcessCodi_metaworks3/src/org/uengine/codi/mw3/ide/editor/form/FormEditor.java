package org.uengine.codi.mw3.ide.editor.form;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.FormFieldMenu;
import org.uengine.codi.mw3.ide.form.FormPreview;
import org.uengine.codi.mw3.ide.form.FormProperties;
import org.uengine.codi.mw3.ide.form.Properties;

public class FormEditor extends Editor {

	Form form;
		public Form getForm() {
			return form;
		}
		public void setForm(Form form) {
			this.form = form;
		}
		
	Properties properties;
		public Properties getProperties() {
			return properties;
		}
		public void setProperties(Properties properties) {
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
		
		String packageName = ResourceNode.makePackageName(this.getId());
		String className = ResourceNode.makeClassName(this.getId());
		
		Form form = new Form();
		form.session = resourceNode.session;
		form.setProjectId(this.getResourceNode().getProjectId());
		form.setPackageName(packageName);
		form.setId(className);
		form.init();

		this.setForm(form);
		
		FormProperties formProperties = new FormProperties();
		formProperties.setId(form.getId());
		formProperties.setName(form.getName());
		formProperties.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.setProperties(formProperties);
		
		FormFieldMenu formFieldMenu = new FormFieldMenu();
		formFieldMenu.load();
		this.setMenu(formFieldMenu);

	}
	
	@Override
	public String load() {
		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(form.getFullClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		form.load();
		this.setForm(form);
		
		return null;
	}
	
	@Override
	@ServiceMethod(callByContent=true)
	public Object save() throws Exception{
		form.validate();
		
		this.setContent(form.generateJavaCode());
		
		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(this.getForm().getFullClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.save();
	}
	
	public Object run() throws Exception{
		
		this.save();
		
		ClassLoader prerCl = Thread.currentThread().getContextClassLoader();
		CodiClassLoader cl = CodiClassLoader.createClassLoader(this.getResourceNode().getProjectId(), session.getEmployee().getGlobalCom(), false);
		
		Thread.currentThread().setContextClassLoader(cl);
		
		Object o = Thread.currentThread().getContextClassLoader().loadClass(form.getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
		if(o instanceof ITool)
			((ITool)o).onLoad();
		
		Thread.currentThread().setContextClassLoader(prerCl);

		return new FormPreview(o);
		
	}
}
