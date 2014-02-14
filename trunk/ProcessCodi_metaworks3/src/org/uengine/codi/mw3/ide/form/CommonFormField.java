package org.uengine.codi.mw3.ide.form;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.Order;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.codi.mw3.model.Session;
import org.uengine.util.UEngineUtil;

@Face(options={"fieldOrder"}, values={"displayName,id,hide"},
ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/CommonFormField.ejs",
ejsPathMappingByContext= {
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FormFieldModify.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class CommonFormField implements ContextAware, Cloneable {

	@AutowiredFromClient
	public Session session;			// for drag

	@AutowiredFromClient
	public Form form;				// for action

	@AutowiredFromClient
	public Properties formFieldProperty; 


	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}	
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	String fieldId;
	@Id
	@Hidden
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	String name;
	@Hidden
	@Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	String fieldType;
	@Hidden
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	String fieldSize;
	@Hidden
	@Face(displayName="$form.field.size", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"Small","Medium","Large"}, values={"small","medium","large"})
		public String getFieldSize() {
			return fieldSize;
		}
		public void setFieldSize(String fieldSize) {
			this.fieldSize = fieldSize;
		}

	String id;
	@Order(value=1)
	@NotNull(message="이름을 입력하세요.")
	@Face(displayName="이름")
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	String displayName;
	@Order(value=2)
	@Face(displayName="설명")
	@Available(where={"form", "properties"})
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	Boolean hide;
	@Order(value=3)
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

	boolean selected;
	@Hidden
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	boolean validation;
	@Hidden
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	
	public void init(){
		this.setMetaworksContext(new MetaworksContext());
		this.setHide(false);
		this.setFieldSize("medium");
	}

	@ServiceMethod(mouseBinding="drag")
	@Available(where={"menu"})
	public Session drag() {
	
		session.setClipboard(this);

		return session;
	}
	
	@ServiceMethod(eventBinding="click")
	@Available(where={"menu"})
	public Object click() {
		CommonFormField formField = this;		
		formField.setFieldId(form.makeFormFieldId());
		
		formField.init();
		formField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		formField.getMetaworksContext().setWhere("form");
		
		form.getFormFields().add(formField);			
		
		return form;	
	}
	
	//, mouseBinding="left"
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
		CommonFormField cloneFormField = (CommonFormField)this.clone();
		cloneFormField.setFieldId(form.makeFormFieldId());
		cloneFormField.setValidation(false);
		cloneFormField.setId(null);
		
		form.formFields.add(cloneFormField);

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
		
		for(CommonFormField formField : form.formFields)
			formField.setSelected(false);
		
		int index = form.formFields.indexOf(this);
		if(index > 0) {
			form.formFields.remove(this);
			form.formFields.add(index-1,this);
			
			this.setSelected(true);
		}

		return form;
	}

	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_VIEW}, where={"form"})
	public Object down() {
		
		for(CommonFormField formField : form.formFields)
			formField.setSelected(false);

		int index = form.formFields.indexOf(this);
		if(index < form.formFields.size()-1) {
			form.formFields.remove(this);
			form.formFields.add(index+1,this);
			
			this.setSelected(true);
		}

		return form;
	}

	@ServiceMethod(callByContent=true, validate=true)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object[] apply() throws Exception {

		int pos = form.formFields.indexOf(this);
		if(pos > -1){
			for(int i=0; i<form.formFields.size(); i++){
				if(pos == i) continue;
				
				CommonFormField formField = form.formFields.get(i);
				
				if(this.getId().equals(formField.getId()))
					throw new Exception("이미 사용 중인 이름입니다.");
			}
			
			CommonFormField applyFormField = (CommonFormField)this.clone();
			
			applyFormField.init();
			applyFormField.setHide(this.getHide());
			applyFormField.setFieldSize(this.getFieldSize());
			applyFormField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			applyFormField.getMetaworksContext().setWhere("form");
			applyFormField.setValidation(true);

			form.formFields.set(pos, applyFormField);
		}
		
		FormFieldProperties formFieldProperty = (FormFieldProperties)this.formFieldProperty;
		formFieldProperty.setFormField(this);

		return new Object[]{form, formFieldProperty};
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
		face.append("		@Face(");
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
		.append("		public ").append(type).append(" get").append(fieldIdFirstCharUpper).append("(){\n")
		.append("			return ").append(this.getId()).append(";\n")
		.append("		}\n")
		.append("		public void set").append(fieldIdFirstCharUpper).append("(").append(type).append(" ").append(this.getId()).append("){\n")
		.append("			this.").append(this.getId()).append(" = ").append(this.getId()).append(";\n")
		.append("		}\n\n");		

		return propertyBuffer.toString();		
	}

	public String generateBeforeComplete() {
		return "";
	}
	
	public String generateOnLoadBuffer(){
		return "";
	}
	
	public String setter(){
		return "this.set" + UEngineUtil.toOnlyFirstCharacterUpper(this.getId());
	}
	
	public String getter(){
		return "this.get" + UEngineUtil.toOnlyFirstCharacterUpper(this.getId());
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
			formField.init();
			formField.setFieldId(form.makeFormFieldId());
			formField.setId(fd.getName());
			formField.setDisplayName(fd.getDisplayName());
			
			if(fd.getAttributes().get("hidden") != null)
				formField.setHide((Boolean)fd.getAttributes().get("hidden"));
			
			formField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			formField.getMetaworksContext().setWhere("form");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formField;		
	}
	
	public void validate() throws Exception {
		if(this.getId() == null)
			throw new Exception("이름이 입력되지 않았습니다.");
	}
	
	@Override
	public Object clone() {
		try
		{
			return super.clone();
		}
		catch(Exception e){ return null; }
	}	

	//	validation check
	//	public boolean duplicationCheck() {		
	//		for(CommonFormField formField : form.formFields) {	
	//			if(!this.getFieldId().equals(formField.getFieldId()) && this.getId().equals(formField.getId())) {
	//				return false;
	//			}
	//		}
	//		return true;
	//	}
}