package org.uengine.codi.mw3.ide.editor.form;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.form.CommonFormField;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.FormFieldMenu;
import org.uengine.codi.mw3.ide.form.FormFieldProperties;
import org.uengine.codi.mw3.ide.form.FormPreview;

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
		Project project = workspace.findProject(this.getResourceNode().getProjectId());
		
		//CodiClassLoader.refreshSourcePath(project.getBuildPath().getSources().get(0).getPath());
		
		String packageName = project.getBuildPath().makePackageName(this.getId());
		String className = project.getBuildPath().makeClassName(this.getId());
		
		Form form = new Form();
		form.setPackageName(packageName);
		form.setId(className);
		
		TransactionContext.getThreadLocalInstance().getRequest().getSession().setAttribute("projectSourcePath", project.getBuildPath().getSources().get(0).getPath());
		CodiClassLoader.refreshClassLoader(form.getFullClassName());

		form.load();
		
		this.setForm(form);
		
		setProperties(new FormFieldProperties());
		
		setMenu(new FormFieldMenu());
		menu.load();
		
		return null;
	}
	
	@Override
	public Object save() {		
		if(this.blankCheck()) {
			this.setContent(form.generateJavaCode());
			return super.save();
		}
		else {
			System.out.println("====== blank error error error ======");
			return null;
		}
	}
	
	@Face(displayName="$Preview")
	@ServiceMethod(payload={"resourceNode", "form"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object preview() throws Exception {
		Project project = workspace.findProject(this.getResourceNode().getProjectId());
		
		TransactionContext.getThreadLocalInstance().getRequest().getSession().setAttribute("projectSourcePath", project.getBuildPath().getSources().get(0).getPath());
		
		//CodiClassLoader.refreshSourcePath(project.getBuildPath().getSources().get(0).getPath());
		
		//Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
		
		Object o = Thread.currentThread().getContextClassLoader().loadClass(form.getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

		MetaworksRemoteService.getInstance().getMetaworksType(form.getFullClassName());
		
		//Window outputWindow = new Window();
		//outputWindow.setPanel(o);

		return new ModalWindow(new FormPreview(o), 0, 0, "$Preview");
		
	}
	
	
//	validation check
	
	public boolean blankCheck() {
		
		for(CommonFormField formField : form.getFormFields()) {
			
			if(
					(formField.getId() == null || formField.getId().trim().length() == 0)
					|| (formField.getDisplayName() == null || formField.getDisplayName().trim().length() == 0)) 
			{
				return false;
			}
		}
		return true;
	}
	
	

}
