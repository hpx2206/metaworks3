package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class FormFieldMenu {

	ArrayList<CommonFormField> formFields;
		public ArrayList<CommonFormField> getFormFields() {
			return formFields;
		}	
		public void setFormFields(ArrayList<CommonFormField> formFields) {
			this.formFields = formFields;
		}
	
	public void load() {
		
		formFields = new ArrayList<CommonFormField>();
		
		/*
		 * TODO : process config file
		 * fomedesigner.xml
		  <org.uengine.codi.mw3.~~~>
		    <group>default</group>
		    <activityTypeClass>org.uengine.codi.mw3.ide.form.SingleTextField</activityTypeClass>
		  </org.uengine.codi.mw3.~~~>
		  <org.uengine.codi.mw3.~~~>
		    <group>default</group>
		    <activityTypeClass>org.uengine.codi.mw3.ide.form.MultipleChoiceField</activityTypeClass>
		  </org.uengine.codi.mw3.~~~>		  
		  
		  Class class = Thread.currentThread().getContextClassLoader().loadClass("org.uengine.codi.mw3.ide.form.MultipleChoiceField");
		  Constructor constructor = USE_CLASS.getConstructor();
	      CommonFormField formField = (CommonFormField)constructor.newInstance();
	      == equals ==
	      CommonFormField formField = new org.uengine.codi.mw3.ide.form.MultipleChoiceField();
		*/
		
		SingleLineTextField singleLineTextField = new SingleLineTextField();
		singleLineTextField.setMetaworksContext(new MetaworksContext());
		singleLineTextField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		singleLineTextField.getMetaworksContext().setWhere("menu");
	
		MultipleChoiceField multipleChoiceField = new MultipleChoiceField();
		multipleChoiceField.setMetaworksContext(new MetaworksContext());
		multipleChoiceField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		multipleChoiceField.getMetaworksContext().setWhere("menu");
		
		DateField dateField = new DateField();
		dateField.setMetaworksContext(new MetaworksContext());
		dateField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		dateField.getMetaworksContext().setWhere("menu");
		
		NumberField numberField = new NumberField();
		numberField.setMetaworksContext(new MetaworksContext());
		numberField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		numberField.getMetaworksContext().setWhere("menu");
		
		FileUploadField fileUploadField = new FileUploadField();
		fileUploadField.setMetaworksContext(new MetaworksContext());
		fileUploadField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		fileUploadField.getMetaworksContext().setWhere("menu");
		
		UserField userField = new UserField();
		userField.setMetaworksContext(new MetaworksContext());
		userField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		userField.getMetaworksContext().setWhere("menu");
		
		DropDownField dropDownField = new DropDownField();
		dropDownField.setMetaworksContext(new MetaworksContext());
		dropDownField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		dropDownField.getMetaworksContext().setWhere("menu");
		
		CheckBoxField checkBoxField = new CheckBoxField();
		checkBoxField.setMetaworksContext(new MetaworksContext());
		checkBoxField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		checkBoxField.getMetaworksContext().setWhere("menu");
		
		ParagraphField paragraphField = new ParagraphField();
		paragraphField.setMetaworksContext(new MetaworksContext());
		paragraphField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		paragraphField.getMetaworksContext().setWhere("menu");
		
		formFields.add(singleLineTextField);
		formFields.add(multipleChoiceField);
		formFields.add(dateField);
		formFields.add(numberField);
		formFields.add(fileUploadField);
		formFields.add(userField);
		formFields.add(dropDownField);
		formFields.add(checkBoxField);
		formFields.add(paragraphField);
	}
}
