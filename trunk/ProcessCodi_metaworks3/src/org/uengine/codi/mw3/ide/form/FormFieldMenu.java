package org.uengine.codi.mw3.ide.form;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.metaworks.MetaworksContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.processdesigner.RolePicker;

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
		
		
		SingleTextField singleTextField = new SingleTextField();
		singleTextField.setMetaworksContext(new MetaworksContext());
		singleTextField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		singleTextField.getMetaworksContext().setWhere("menu");
	
		MultipleChoiceField multipleChoiceField = new MultipleChoiceField();
		multipleChoiceField.setMetaworksContext(new MetaworksContext());
		multipleChoiceField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		multipleChoiceField.getMetaworksContext().setWhere("menu");
		
		formFields.add(singleTextField);
		formFields.add(multipleChoiceField);		
	}
}
