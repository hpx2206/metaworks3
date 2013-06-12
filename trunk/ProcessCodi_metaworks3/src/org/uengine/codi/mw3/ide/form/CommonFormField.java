package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.Session;
import org.uengine.util.UEngineUtil;

@Face(options={"hideEditBtn"}, values={"true"})
public class CommonFormField implements ContextAware, Cloneable {
	
	@AutowiredFromClient
	public Session session;			// for drag
	
	@AutowiredFromClient
	public Form form;				// for action
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String fieldId;
		@Id
//		@Hidden
		public String getFieldId() {
			return fieldId;
		}
		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}
		
	String fieldType;
		@Hidden
		public String getFieldType() {
			return fieldType;
		}
		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}
	
	String id;
		@Face(displayName="$form.field.id")
		@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String displayName;
		@Face(displayName="$form.field.displayname")
		@Available(where={"form", "properties"})
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	
	Boolean hide;
		@Face(displayName="$form.field.hide")
		@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
		public Boolean getHide() {
			return hide;
		}
		public void setHide(Boolean hide) {
			this.hide = hide;
		}
		
	String ejsPath;
		@Hidden
		public String getEjsPath() {
			return ejsPath;
		}
		public void setEjsPath(String ejsPath) {
			this.ejsPath = ejsPath;
		}
		
	String options;
		@Hidden
		public String getOptions() {
			return options;
		}
		public void setOptions(String options) {
			this.options = options;
		}
	
	String values;
		@Hidden
		public String getValues() {
			return values;
		}
		public void setValues(String values) {
			this.values = values;
		}
		
	boolean define;
		@Hidden
		public boolean isDefine() {
			return define;
		}
		public void setDefine(boolean define) {
			this.define = define;
		}
		
	public void init(){
		this.setMetaworksContext(new MetaworksContext());
		this.setId("");
		this.setDisplayName("");
		this.setHide(false);
	}
		
	@ServiceMethod(mouseBinding="drag")
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"menu"})
	public Session drag() {				
		session.setClipboard(this);
		
		return session;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object modify() {
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.getMetaworksContext().setWhere("properties");
		
		return new FormFieldProperties(this);

	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object copy() {
		try {
			CommonFormField cloneFormField = (CommonFormField)this.clone();
			cloneFormField.setFieldId(form.makeFormFieldId());

			form.formFields.add(cloneFormField);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return form;
	}
		
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object remove() {
		
		form.formFields.remove(this);
		
		return form;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object up() {				
		int index = form.formFields.indexOf(this);
		if(index > 0) {
			form.formFields.remove(this);
			form.formFields.add(index-1,this);
		}
		
		return form;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object down() {
		int index = form.formFields.indexOf(this);
		if(index < form.formFields.size()-1) {
			form.formFields.remove(this);
			form.formFields.add(index+1,this);
		}
		
		return form;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object apply() {
		
		if(this.duplicationCheck()) {
		
			int pos = form.formFields.indexOf(this);
			if(pos > -1){
				this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				this.getMetaworksContext().setWhere("form");
				
				form.formFields.set(pos, this);		
			}
			
			return form;
		}
		else {
			System.out.println("====== duplication error error error ======");
			return null;
		}
	}	
	
	@Override	
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj instanceof CommonFormField){
			CommonFormField commonFormField = (CommonFormField)obj;
			
			result = this.getFieldId().equals(commonFormField.getFieldId());
		}
		
		return result;
	}	
		
	public String makeValueString(String value){
		return "\"" + value + "\"";
	}
	
	
	public String generateImportCode() {
		StringBuffer importBuffer = new StringBuffer();
		
		if(this.isDefine()){
			importBuffer.append("import " + this.getFieldType() + ";\n");
			
			try {
				WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType(this.getFieldType());
				Class iDAOClass = wot.iDAOClass();
				if(iDAOClass != null){
					importBuffer.append("import " + wot.iDAOClass().getName() + ";\n");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return importBuffer.toString();		
	}
	
	
	public String generateFace(){
		String separatorChar = "";
		
		StringBuffer face = new StringBuffer();
		face.append("	@Face(");
		if(this.getDisplayName() != null && !this.getDisplayName().equals("")){
			face.append(separatorChar);
			face.append("displayName=" + this.makeValueString(this.getDisplayName()));
			separatorChar  = ", ";
		}
	
		if(this.getEjsPath() != null && !this.getEjsPath().equals("")){
			face.append(separatorChar);
			face.append("ejsPath=" + this.makeValueString(this.getEjsPath()));
			separatorChar  = ", ";
		}
		
		if(this.getOptions() != null && !this.getOptions().equals("")){
			face.append(separatorChar);
			face.append("options={" + this.getOptions());
			separatorChar  = "}, ";
		}
		
		if(this.getValues() != null && !this.getValues().equals("")){
			face.append(separatorChar);
			face.append("values={" + this.getValues());
			face.append("}");
		}
				
		face.append(")\n");
		
		return face.toString();
	}
	
	public String generateAnnotationCode() {
		
		StringBuffer annotationdBuffer = new StringBuffer();
				
		if(this.getHide()){
			annotationdBuffer.append("	@Hidden\n");
		}
		
		annotationdBuffer.append(this.generateFace());
		
		return annotationdBuffer.toString();
	}
	
	public String generateConstructorCode() {
		StringBuffer constructordBuffer = new StringBuffer();				
		
		if(this.isDefine()){
			constructordBuffer
			.append("		").append("this.set").append(UEngineUtil.toOnlyFirstCharacterUpper(this.getId()))
			.append("(new ").append(this.getFieldType().substring(this.getFieldType().lastIndexOf(".")+1))
			.append("()); \n");
		}	
		
		return constructordBuffer.toString();
	}
	
	public String generateVariableCode() {		
		
		StringBuffer variableBuffer = new StringBuffer();
		String type = this.getFieldType().substring(this.getFieldType().lastIndexOf(".")+1);
		
		variableBuffer.append("	").append(type).append(" ").append(this.getId()).append(";\n");
		
		return variableBuffer.toString();
	}
	
	public String generatePropertyCode() {
		
		StringBuffer propertyBuffer = new StringBuffer();
		String fieldIdFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(this.getId());
		String type = this.getFieldType().substring(this.getFieldType().lastIndexOf(".")+1);
				
		propertyBuffer
		.append("		public ").append(type).append(" get").append(fieldIdFirstCharUpper).append("(){ return ").append(this.getId()).append("; }\n")
		.append("		public void set").append(fieldIdFirstCharUpper).append("(").append(type).append(" ").append(this.getId()).append("){ this.").append(this.getId()).append(" = ").append(this.getId()).append("; }\n\n")
		;		
		
		return propertyBuffer.toString();		
	}
	
	public boolean equalsType(WebFieldDescriptor fd){
		
		boolean equals = false;
		
		if(this.getEjsPath() == null){
			if(fd.getInputFace() == null || fd.getInputFace().equals(""))
				equals = true;
		}else if(this.getEjsPath().equals(fd.getInputFace())){
			equals = true;
		}
		
		if(equals){
			if(!this.getFieldType().equals(fd.getClassName()))
				equals = false;
		}
		
		return equals;

	}
	
	public CommonFormField make(WebFieldDescriptor fd)  {
		CommonFormField formField = null;
		
		try {
			formField = (CommonFormField)this.clone();
			formField.setFieldId(form.makeFormFieldId());
			formField.setId(fd.getName());
			formField.setDisplayName(fd.getDisplayName());
			formField.setHide((Boolean)fd.getAttributes().get("is key"));
			
			formField.setMetaworksContext(new MetaworksContext());
			formField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			formField.getMetaworksContext().setWhere("form");
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return formField;		
	}
	
//	validation check
	public boolean duplicationCheck() {		
		for(CommonFormField formField : form.formFields) {	
			if(this.getId().equals(formField.getId())) {
				return false;
			}
		}
		return true;
	}
}