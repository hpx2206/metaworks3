package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.Session;

@Face(options={"hideEditBtn"}, values={"true"},
		ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/Form.ejs")
public class Form {
	
	@AutowiredFromClient
	public Session session;
		
	public final static String FORM_FIELD_ID_PREFIX = "FORMFIELD_";

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
	
	public void load() {
		setFormFields(new ArrayList<CommonFormField>());
		
		try {
			this.formLoad();
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
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
		
		WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType(alias);

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
		StringBuffer constructorBuffer 	= new StringBuffer();
		ArrayList<String> importList = new ArrayList<String>();
		ArrayList<String> constructorList = new ArrayList<String>();
		String classFaceOrderStr = "";
		
		importBuffer.append("import org.uengine.codi.ITool; \n");
		importBuffer.append("import org.metaworks.annotation.Face;\n");
		
		constructorBuffer.append("	public " + this.getId() + "() { \n");
		
		for(int i = 0; i < formFields.size(); i++) {
			
			CommonFormField field = formFields.get(i);
		
			String importStr = "";
			String constructortStr = "";
			
			if( i == 0){
				classFaceOrderStr = field.getId();
			}else{
				classFaceOrderStr += "," + field.getId();
			}
			
			//여기 hidden 부분 어떻게 처리 할까? 암튼 이거 아니야 -________________- 어케해방
			if(field.getHide()) {
				importStr = "import org.metaworks.annotation.Hidden;\n";
				if(!importList.contains(importStr)){
					importList.add(importStr);
				}
			}
			
			importStr = field.generateImportCode();
			if(!importList.contains(importStr)){
				importList.add(importStr);
			}
			
			constructortStr = field.generateConstructorCode();
			if(!constructorList.contains(constructortStr)){
				constructorList.add(constructortStr);
			}
			
			
			methodBuffer.append(field.generateVariableCode());
			methodBuffer.append(field.generateAnnotationCode());
			methodBuffer.append(field.generatePropertyCode());
		}
		
		for(int i =0; i < constructorList.size(); i++){
			constructorBuffer.append(constructorList.get(i));
		}
		constructorBuffer.append("	}\n\n");
		
		methodBuffer
		.append("	@Override\n")
		.append("	public void onLoad() throws Exception {\n")
		.append("	}\n\n");
		
		methodBuffer
		.append("	@Override\n")
		.append("	public void beforeComplete() throws Exception {\n")
		.append("	}\n\n");
		
		methodBuffer
		.append("	@Override\n")
		.append("	public void afterComplete() throws Exception {\n")
		.append("	}\n\n");
		
		for(int i =0; i < importList.size(); i++){
			importBuffer.append(importList.get(i));
		}
		
		if(this.getPackageName() != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb.append(importBuffer.toString() + "\n");
		sb.append("@Face(ejsPath=\"genericfaces/FormFace.ejs\", options={\"fieldOrder\"},values={\""+ classFaceOrderStr +"\"})\n");
		sb.append("public class " + this.getId() + "").append(" implements ITool").append( "{\n\n");
		sb.append(constructorBuffer.toString());
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
}
