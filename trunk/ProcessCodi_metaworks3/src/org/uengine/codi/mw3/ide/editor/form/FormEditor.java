package org.uengine.codi.mw3.ide.editor.form;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
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
	public Object save() {
		this.setContent(form.generateJavaCode());
		
		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(this.getForm().getFullClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.save();
	}
	
	@Face(displayName="$SaveAndPreview")
	@ServiceMethod(payload={"resourceNode", "form"}, target=ServiceMethodContext.TARGET_POPUP)
	@Hidden
	public Object preview() throws Exception {
		
		this.save();
		
		//CodiClassLoader.refreshSourcePath(project.getBuildPath().getSources().get(0).getPath());
		
		//Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
		
		Object o = Thread.currentThread().getContextClassLoader().loadClass(form.getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

		MetaworksRemoteService.getInstance().getMetaworksType(form.getFullClassName());
		
		//Window outputWindow = new Window();
		//outputWindow.setPanel(o);

		return new ModalWindow(new FormPreview(o), 0, 0, "$Preview");
		
	}
}
