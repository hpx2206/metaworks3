
package org.uengine.codi.mw3.admin;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.uengine.util.UEngineUtil;

public class ClassField implements Cloneable, ContextAware{

	public ClassField(){
	}
	
	String id;	// 변수명
		@Id 
		@Face(displayName="$variableName")
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	boolean disable;				// Anotation Hidden 생성
		@Face(displayName="$disable")
		public boolean getDisable() {
			return disable;
		}
		public void setDisable(boolean disable) {
			this.disable = disable;
		}

	String displayname;;		// Anotation dispalyname 생성
		@Face(displayName="$displayName")
		public String getDisplayname() {
			return displayname;
		}
		public void setDisplayname(String displayname) {
			this.displayname = displayname;
		}
	
	String type;
	//@Hidden
	@Face(displayName="$typeName")
	@Range(
				options={
						"String", 
						"Long", 
						"Double", 
						"Date", 
						"File", 
						"User", 
						"Source Code",
						"WebEditor",
						"MetaworksContext"},
				values ={
						"java.lang.String", 
						"java.lang.Long", 
						"java.lang.Double", 
						"java.util.Date", 
						"org.metaworks.website.MetaworksFile", 
						"org.uengine.codi.mw3.model.IUser", 
						"org.metaworks.example.ide.SourceCode",
						"org.uengine.codi.mw3.admin.WebEditor", 
						"org.metaworks.MetaworksContext"

				}
		)
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		

	
//	JavaSourceCode typeInput;
//	@Face(options={"width", "height"}, values={"10", "20"})
//		public JavaSourceCode getTypeInput() {
//			return typeInput;
//		}
//		public void setTypeInput(JavaSourceCode typeInput) {
//			this.typeInput = typeInput;
//		}

	

	String valueString;
	@Hidden
		public String getValueString() {
			return valueString;
		}
		public void setValueString(String valueString) {
			this.valueString = valueString;
		}

	Long valueLong;
	@Hidden
		public Long getValueLong() {
			return valueLong;
		}
		public void setValueLong(Long valueLong) {
			this.valueLong = valueLong;
		}

	Date valueDate;
	@Hidden
		public Date getValueDate() {
			return valueDate;
		}
		public void setValueDate(Date valueDate) {
			this.valueDate = valueDate;
		}
		
	SourceCode valueSourceCode;
	@Hidden	
		public SourceCode getValueSourceCode() {
			return valueSourceCode;
		}
		public void setValueSourceCode(SourceCode valueSourceCode) {
			this.valueSourceCode = valueSourceCode;
		}
		
	MetaworksFile valueFile;
	@Hidden	
		public MetaworksFile getValueFile() {
			return valueFile;
		}
		public void setValueFile(MetaworksFile valueFile) {
			this.valueFile = valueFile;
		}
		
	boolean isInterface;
		@Hidden	
		public boolean isInterface() {
			return isInterface;
		}
		public void setInterface(boolean isInterface) {
			this.isInterface = isInterface;
		}

		
//	Object defaultValue;
//	@Face(displayName="$defaultValue")
//		public Object getDefaultValue() {
//			return defaultValue;
//		}
//		public void setDefaultValue(Object defaultValue) {
//			this.defaultValue = defaultValue;
//		}
		
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public Object[] add() throws Exception{

		
		if(classModeler.classFields==null)
			classModeler.classFields = new ArrayList<ClassField>();//new FormField[]{};
		
		//TODO: lesson 3 (validation with throwing exception)
		if(classModeler.classFields.contains(this))
			throw new Exception("There's already existing field named '" + getId() + "'.");
		ClassField clonedOne = (ClassField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)
		
		Class classType = Thread.currentThread().getContextClassLoader().loadClass(clonedOne.getType());
		if(classType.isInterface()){
			clonedOne.setInterface(true);
		}
		
//		if(clonedOne.getDefaultValue()==null){
//			
//			if(Number.class.isAssignableFrom(classType)){
//				clonedOne.setDefaultValue(
//					classType.getConstructor(new Class[]{String.class}).newInstance(new Object[]{"0"})
//				);
//				
//			}else if(!classType.isInterface()){
//				clonedOne.setDefaultValue(classType.newInstance());
//			}else if(classType.isInterface()){
//				clonedOne.setInterface(true);
//			}
//			
//		}

		clonedOne.setMetaworksContext(new MetaworksContext());  //TODO: lesson 4 (context injection)
		clonedOne.getMetaworksContext().setWhere("in-container");
		clonedOne.getMetaworksContext().setWhen("view");
		
		classModeler.classFields.add(clonedOne); 
		
		

		//clear the entries for newFormField	//TODO: lesson 6 (context clearing)
		classModeler.init();
		//
		
		if(classSourceCodes!=null && "class".equals(classSourceCodes.getMetaworksContext().getWhere())){
			StringBuffer javaCode = new StringBuffer(classSourceCodes.getSourceCode().getCode());
			int whereLastBraket = javaCode.lastIndexOf("}");
			
			
			ClassField field = this;
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getId());
			String fieldType = field.getType();
			
			StringBuffer sb = new StringBuffer();
			sb
				.append("\n")
				.append("	").append(fieldType).append(" ").append(field.getId()).append(";\n")
				.append("		public ").append(fieldType).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getId()).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(fieldType).append(" ").append(field.getId()).append("){ this.").append(field.getId()).append(" = ").append(field.getId()).append("; }\n\n")
				.append("\n");
	
			
			javaCode.insert(whereLastBraket, sb.toString());
			
			classSourceCodes.getSourceCode().setCode(javaCode.toString());
			
			return new Object[]{classSourceCodes.getSourceCode(), classModeler};
		}else{
			return new Object[]{classModeler};
		}
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="in-container", callByContent=true)
	public ClassModeler save() throws Exception{
		//TODO: lesson 3 (validation with throwing exception)
		
		int index = classModeler.classFields.indexOf(this);
		if(index==-1)
			throw new Exception("There's no existing field named '" + getId() + "'.");
					
		classModeler.classFields.remove(this);
		
		ClassField clonedOne = (ClassField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)
		
		classModeler.classFields.add(index, clonedOne); 
		
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return classModeler;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public ClassModeler remove(){
		classModeler.classFields.remove(this);
		
		return classModeler;
	}
	
	//TODO: quiz 2 (when the form field is first order, this button should be shown.
	//              Improve 'up' method and 'down' method not to be shown when it is in the first order and in the last order.
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container", callByContent=true)
	public ClassModeler up(){
		int index = classModeler.classFields.indexOf(this);
		
		if(index>0){
			classModeler.classFields.remove(this);
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classModeler.classFields.add(index-1, this);
		}

		return classModeler;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container", callByContent=true) 
	public ClassModeler down(){
		int index = classModeler.classFields.indexOf(this);
		
		if(index<classModeler.classFields.size()-1){
			classModeler.classFields.remove(this);      //TODO: lesson 1 (object addressing and correlation)
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classModeler.classFields.add(index+1, this);
		}

		return classModeler;
	}
	
	@ServiceMethod(callByContent=true)
	public void edit() throws Exception {
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	
	
	@Override //TODO: lesson 1 (object addressing and correlation)
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisFieldName = this.getId();
		String comparatorFieldName = ((ClassField)obj).getId();
		
		return thisFieldName.equals(comparatorFieldName);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public ClassSourceCodes classSourceCodes;
	
	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public ClassModeler classModeler;
	

	
	///// context //////  TODO: lesson 4 (context injection)
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
