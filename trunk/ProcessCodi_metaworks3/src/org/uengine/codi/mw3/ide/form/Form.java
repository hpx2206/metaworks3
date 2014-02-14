package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.Session;

@Face(options={"hideEditBtn"}, values={"true"},
		ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/Form.ejs")
public class Form implements ContextAware {
	
	@AutowiredFromClient
	public Session session;
		
	public final static String FORM_FIELD_ID_PREFIX = "FORMFIELD_";
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String id;
		@Face(displayName="$form.id")
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String name;
		@Face(displayName="$form.name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String packageName;
		@Hidden   
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
	
	ArrayList<CommonFormField> formFields;
		@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
		public ArrayList<CommonFormField> getFormFields() {
			return formFields;
		}	
		public void setFormFields(ArrayList<CommonFormField> formFields) {
			this.formFields = formFields;
		}

	public String getFullClassName() {
		String fullClassName = "";

		if (this.getPackageName() != null)
			fullClassName += this.getPackageName() + ".";
		fullClassName += this.getId();

		return fullClassName;
	}
	
	public void init() {
		this.setFormFields(new ArrayList<CommonFormField>());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getMetaworksContext().setWhere("form");
	}
	
	public void load() {
		try {
			this.formLoad();
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	public Form() {
		this.setMetaworksContext(new MetaworksContext());
		this.setName("");
	}
	
	@Hidden
	@ServiceMethod(mouseBinding="drop", callByContent=true, bindingHidden=true) 
	public Object drop() {
		
		Object clipboard = session.getClipboard();
		if(clipboard instanceof CommonFormField){
			CommonFormField formField = (CommonFormField) clipboard;
			formField.setFieldId(makeFormFieldId());
			
			formField.init();
			formField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			formField.getMetaworksContext().setWhere("form");		
			
			formFields.add(formField);
			
		}else{		
			return null;
		}
			
		
		return this;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object modify() {
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.getMetaworksContext().setWhere("properties");
		
		FormProperties formProperties = new FormProperties();
		formProperties.setId(this.getId());
		formProperties.setName(this.getName());
		formProperties.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		return formProperties;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object[] apply() {


		
		return null;
	}
	
	public void formLoad() throws Exception {
		// TODO : process config file		
		ArrayList<CommonFormField> list = new ArrayList<CommonFormField>();
		list.add(new SingleLineTextField());
		list.add(new MultipleChoiceField());
		list.add(new DateField());
		list.add(new NumberField());
		list.add(new FileUploadField());
		list.add(new UserField());
		list.add(new DropDownField());
		list.add(new CheckBoxField());
		list.add(new ParagraphField());
		
		String alias = this.getFullClassName();
		
		ClassLoader prerCl = Thread.currentThread().getContextClassLoader();
		CodiClassLoader cl = CodiClassLoader.createClassLoader(this.getProjectId(), session.getEmployee().getGlobalCom(), false);
		
		Thread.currentThread().setContextClassLoader(cl);
		
		WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType(alias);
		
		Thread.currentThread().setContextClassLoader(prerCl);
		
		this.setName(wot.getDisplayName());
		
		for(int i=0; i < wot.getFieldDescriptors().length; i++){
			WebFieldDescriptor fd = wot.getFieldDescriptors()[i];
			
			for(int j=0; j< list.size(); j++){
				CommonFormField formField = list.get(j);
				
				if(formField.equalsType(fd)){
					formField.form = this;
					CommonFormField addFormField = formField.make(fd);
					
					this.getFormFields().add(addFormField);
					
					break;
				}
			}
		}
	}

	public String generateJavaCode() { 
	
		StringBuffer sb = new StringBuffer();
		StringBuffer importBuffer = new StringBuffer();
		StringBuffer methodBuffer = new StringBuffer();
		StringBuffer constructorBuffer = new StringBuffer();
		StringBuffer onLoadBuffer = new StringBuffer();
		StringBuffer beforeCompleteBuffer = new StringBuffer();
		
		ArrayList<String> importList = new ArrayList<String>();
		
		boolean isHidden = false;
		
		importBuffer.append("import org.uengine.codi.ITool; \n");
		importBuffer.append("import org.metaworks.annotation.Face;\n");
		importBuffer.append("import org.metaworks.annotation.Order;\n");
		
		if(formFields != null)
		for(int i = 0; i < formFields.size(); i++) {
			
			CommonFormField field = formFields.get(i);
			
			String importStr = "";
			String constructortStr = "";
			
			if(field.getHide())
				isHidden = true;
			
			importStr = field.generateImportCode();
			if(!importList.contains(importStr)){
				importList.add(importStr);
			}
			
			constructorBuffer.append(field.generateConstructorCode());
			
			methodBuffer.append(field.generateVariableCode());
			methodBuffer.append("		@Order(value=" + String.valueOf(i+1) + ")\n");
			methodBuffer.append(field.generateAnnotationCode());
			methodBuffer.append(field.generatePropertyCode());
			
			onLoadBuffer.append(field.generateOnLoadBuffer());
			beforeCompleteBuffer.append(field.generateBeforeComplete());
		}
		
		if(isHidden)
			importList.add("import org.metaworks.annotation.Hidden;\n");

		for(int i =0; i < importList.size(); i++){
			importBuffer.append(importList.get(i));
		}

		
		methodBuffer.append("	@Override\n");
		methodBuffer.append("	public void onLoad() throws Exception {\n");
		methodBuffer.append(onLoadBuffer.toString());
		methodBuffer.append("	}\n\n");
		
		methodBuffer.append("	@Override\n");
		methodBuffer.append("	public void beforeComplete() throws Exception {\n");
		methodBuffer.append(beforeCompleteBuffer.toString());
		methodBuffer.append("	}\n\n");
		
		methodBuffer.append("	@Override\n");
		methodBuffer.append("	public void afterComplete() throws Exception {\n");
		methodBuffer.append("	}\n\n");
		
		
		if(this.getPackageName() != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb.append(importBuffer.toString() + "\n");
		sb.append("@Face(displayName=\"" + this.getName() +"\", ejsPath=\"dwr/metaworks/genericfaces/FormFace.ejs\")\n");
		sb.append("public class " + this.getId() + "").append(" implements ITool").append( "{ \n\n");
		sb.append("	public " + this.getId() + "() {\n");
		sb.append(constructorBuffer.toString());
		sb.append("	}\n\n");
		sb.append(methodBuffer.toString());	
		sb.append("}");
		
//		System.out.println(sb.toString());		
		return sb.toString();		
	}
	
	public String makeFormFieldId() {	
		int id = 0;
		
		if(this.getFormFields() != null && this.getFormFields().size() > 0) {
			
			int max_id = 0;
			int cur_id = 0;
			
			for(int i = 0; i <this.getFormFields().size(); i++) {								
				CommonFormField cf =  this.getFormFields().get(i);
				cur_id = Integer.parseInt(cf.getFieldId().replace(FORM_FIELD_ID_PREFIX, ""));

				if (max_id < cur_id)
					max_id = cur_id;
								
			}
			id = max_id + 1;
		}
		
		return FORM_FIELD_ID_PREFIX + String.valueOf(id);
	}
	
	public void validate() throws Exception {
		for(CommonFormField formField : this.getFormFields()){
			if(!formField.isValidation())
				formField.validate();
		}
	}
}
