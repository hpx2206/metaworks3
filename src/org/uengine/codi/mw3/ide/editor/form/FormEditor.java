package org.uengine.codi.mw3.ide.editor.form;

import java.io.File;
import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.FormFieldMenu;
import org.uengine.codi.mw3.ide.form.FormPreview;
import org.uengine.codi.mw3.ide.form.FormProperties;
import org.uengine.codi.mw3.ide.form.Properties;
import org.uengine.contexts.ComplexType;

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
		
		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(form.getFullClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		form.init();
		form.load();
		
		/*
		if(this.getContent() != null)
			form.load();
		else
			form.init();
		*/
		
		this.setForm(form);
		
//		this.setProperties(new FormFieldProperties());
		
		FormProperties formProperties = new FormProperties();
		formProperties.setId(form.getId());
		formProperties.setName(form.getName());
		formProperties.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.setProperties(formProperties);
		
		
		FormFieldMenu formFieldMenu = new FormFieldMenu();
		formFieldMenu.load();
		this.setMenu(formFieldMenu);
		
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
		super.save();
		
		// 이미지 스샷 뜨기
		
		return null;
	}
	
	@ServiceMethod(payload={"resourceNode", "form"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object formPreView() throws Exception {
		ModalWindow modalWindow = new ModalWindow();
		File file = new File(this.getResourceNode().getPath());
		
		Object processVariableValue = new Object();
//		Class variableType = Class.forName(this.getForm().getFullClassName());
		Class variableType = Thread.currentThread().getContextClassLoader().loadClass(this.getForm().getFullClassName());
		processVariableValue = (Serializable) variableType.newInstance();
		((ITool)processVariableValue).onLoad();
		TransactionContext.getThreadLocalInstance().setSharedContext(ITool.ITOOL_MAP_KEY, null);
		
		String thumbnailPath = this.getResourceNode().getPath().substring(0, this.getResourceNode().getPath().lastIndexOf(".")) + ".html";
		
		FormThumbnail formThumbnail = new FormThumbnail();
		formThumbnail.setFormObject(processVariableValue);
		formThumbnail.setThumbnailPath(thumbnailPath);
		
		
		modalWindow.setPanel(formThumbnail);
		modalWindow.setWidth(600);
		modalWindow.setHeight(600);
		modalWindow.setTitle(getName());
		
		return modalWindow;
	}
	
	@Face(displayName="$SaveAndPreview")
	@ServiceMethod(payload={"resourceNode", "form"}, target=ServiceMethodContext.TARGET_POPUP)
	@Hidden
	public Object preview() throws Exception {
		
		Project project = workspace.findProject(this.getResourceNode().getProjectId());
		
		this.save();
		
		TransactionContext.getThreadLocalInstance().getRequest().getSession().setAttribute("projectSourcePath", project.getBuildPath().getSources().get(0).getPath());
		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(form.getFullClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//CodiClassLoader.refreshSourcePath(project.getBuildPath().getSources().get(0).getPath());
		
		//Thread.currentThread().setContextClassLoader(CodiClassLoader.createClassLoader(project.getBuildPath().getSources().get(0).getPath()));
		
		Object o = Thread.currentThread().getContextClassLoader().loadClass(form.getFullClassName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();

		MetaworksRemoteService.getInstance().getMetaworksType(form.getFullClassName());
		
		//Window outputWindow = new Window();
		//outputWindow.setPanel(o);

		return new ModalWindow(new FormPreview(o), 0, 0, "$Preview");
		
	}
}
