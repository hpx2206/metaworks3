
package org.uengine.codi.mw3.admin;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.httpclient.methods.GetMethod;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.SourceCodeEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ClassDefinition implements ContextAware{

	public ClassDefinition(){
		init();
	}
	
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	int version;
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}

	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}

	ArrayList<ClassField> classFields;
		public ArrayList<ClassField> getClassFields() {
			return classFields;
		}
		public void setClassFields(ArrayList<ClassField> formFields) {
			this.classFields = formFields;
		}

	ArrayList<ClassMethod> classMethods;
		public ArrayList<ClassMethod> getClassMethods() {
			return classMethods;
		}
		public void setClassMethods(ArrayList<ClassMethod> classMethods) {
			this.classMethods = classMethods;
		}

	ClassField newClassField;
		public ClassField getNewClassField() {
			return newClassField;
		}
		public void setNewClassField(ClassField newClassField) {
			this.newClassField = newClassField;
		}

	@ServiceMethod(callByContent=true)
	public SourceCodeEditor generateJavaClass(){
		
		StringBuffer sb = new StringBuffer();
		sb
			.append("public class " + getClassName() + "{\n");
		
		for(int i=0; i<getClassFields().size(); i++){
			ClassField field = getClassFields().get(i);
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getFieldName());
			
			sb
				.append("	").append(field.getType()).append(" ").append(field.getFieldName()).append(";\n")
				.append("		public ").append(field.getType()).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getFieldName()).append("; }\n")
				.append("		public void set").append(field.getFieldName()).append("(").append(field.getType()).append("{ this.").append(field.getFieldName()).append(" = ").append(field.getFieldName()).append("; }\n")
				;
		}
		
		sb.append("}");
		
		
		SourceCodeEditor sourceCodeEditor = new SourceCodeEditor();
		sourceCodeEditor.setCode(sb.toString());
				
		return sourceCodeEditor;
	}
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		String strDef = GlobalContext.serialize(this, ClassDefinition.class);
		
		String defVerId = processManager.addProcessDefinition(getAlias(), getVersion(), "description", false, strDef, "-1", null, "form", getAlias(), null);
		processManager.setProcessDefinitionProductionVersion(defVerId);
		processManager.applyChanges();
	}


	public void init() {
		newClassField = new ClassField();
		newClassField.metaworksContext = new MetaworksContext();
		newClassField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newClassField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
		newClassField.classDefinition = this;

	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	protected ProcessManagerRemote processManager;
	
}
