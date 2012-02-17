
package org.uengine.codi.mw3.admin;

import java.io.File;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.EntityDesignerContentPanel;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.JavaSourceCode;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

@Face(options={"hideNewBtn", "hideAddBtn"},
	  values={"true", "true"})   
public class EntityDefinition implements ContextAware, PropertyListable{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	transient public ProcessManagerRemote processManager;
		
	public EntityDefinition(){						
	}
	
	String alias;
	@NonEditable
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
		
	String defId;
	@NonEditable
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String defVerId;
	@NonEditable
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}
		
	String parentFolder;
	@NonEditable		
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}

	String packageName;
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		
	String entityName;
		public String getEntityName() {
			return entityName;
		}
		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}

	ArrayList<EntityField> entityFields;
		@Face(options="{hideAddBtn}", values={"true"})
		public ArrayList<EntityField> getEntityFields() {
			return entityFields;
		}
		public void setEntityFields(ArrayList<EntityField> formFields) {
			this.entityFields = formFields;
		}

	EntityField newEntityField;
		public EntityField getNewEntityField() {
			return newEntityField;
		}
		public void setNewEntityField(EntityField newEntityField) {
			this.newEntityField = newEntityField;
		}
	
	boolean created;	
		public boolean isCreated() {
			return created;
		}
		public void setCreated(boolean created) {
			this.created = created;
		}
		
	private String makeCreateQuery(){
		StringBuffer sb = new StringBuffer();
		StringBuffer sbPK = new StringBuffer();
		
		//if(this.getMetaworksContext().getWhen() == "newEntry"){
			sb.append("CREATE TABLE ").append(this.entityName).append("(").append("\n");
							
			for(int i=0; i<this.entityFields.size(); i++){
				EntityField entityField = this.entityFields.get(i);				
				
				sb.append(" ").append(entityField.getName());
				
				if(entityField.getDataType().equals("TIMESTAMP") || entityField.getDataType().equals("DATETIME"))
					sb.append(" ").append(entityField.getDataType());
				else
					sb.append(" ").append(entityField.getDataType()).append("(").append(entityField.getLength()).append(")");
				
				sb.append(" ").append((entityField.isAllowNull()?"NULL":"NOT NULL"));
				
				String defaultValue = entityField.getDefaultValue();
				if(defaultValue != null && defaultValue.trim().length() > 0)
					sb.append(" ").append("DEFAULT '").append(defaultValue.trim()).append("'");
				
				String comment = entityField.getComment();
				if(comment != null && comment.trim().length() > 0)
					sb.append(" ").append("COMMENT '").append(comment.trim()).append("'");
				
				if(i < this.entityFields.size()-1)
					sb.append(",").append("\n");		
				
				if(entityField.isKey()){
					if(sbPK.length()>0)
						sbPK.append(",");
					
					sbPK.append(entityField.getName());
				}
				
				
			}
			
			if(sbPK.length()>0){
				sb.append(",").append("\n").append(" PRIMARY KEY ").append("(").append(sbPK.toString()).append(")");
			}
			sb.append(")");
			
			return sb.toString();
	}
	
	private String makeDropQuery(){
		StringBuffer sb = new StringBuffer();
		
		//if(this.getMetaworksContext().getWhen() == "newEntry"){
			sb.append("DROP TABLE ").append(this.entityName).append("\n");							
			sb.append(" ").append("CASCADE");
			
			return sb.toString();
	}
	
	@ServiceMethod(callByContent=true)
	public EntityQuery generateDDL() throws Exception{
		
		EntityQuery entityQuery = new EntityQuery();
		entityQuery.getQueryCode().setCode(makeCreateQuery());
		
		return entityQuery;
	}

	@ServiceMethod(callByContent=true)
	public void createTable() throws Exception{
		run(makeCreateQuery(), true);
	}
	
	@ServiceMethod(callByContent=true)
	public void dropTable() throws Exception{
		run(makeDropQuery(), true);
	}
	
	private void run(String query) throws Exception{
		run(query, false);
	}
	
	private void run(String query, boolean force) throws Exception{
		
		if(!force && (query.indexOf("insert") != -1 || query.indexOf("update") != -1 || query.indexOf("bpm_") != -1) || query.indexOf("drop") != -1 || query.indexOf("alter") != -1)
			throw new Exception("부적절한 SQL시도입니다.");
		
		if(query==null) return;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = TransactionContext.getThreadLocalInstance().getConnection();				
			pstmt = conn.prepareStatement(query);
			pstmt.execute();
		} catch (Exception e) {
			// TODO we need to report the error properly
			String message = e.getMessage();
			
			/*
			String[] parts = null;
			int lineNumber = 0;
			
			try {
				lineNumber = Integer.parseInt((parts = message.split("at line "))[1]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				parts = null;
				//e1.printStackTrace();
			}
			
			
			CompileError compileError = new CompileError();
			compileError.setLine(lineNumber);
			compileError.setColumn(1);
			compileError.setMessage(parts != null ? parts[0] : message);
			*/
			
			//getQuery().setCompileErrors(new CompileError[]{compileError});
		
			e.printStackTrace();
			
			throw new Exception(message);
		}finally{
			try{
				if(conn != null){
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle){			
			}
			
			try {
				if(pstmt != null){
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException sqle){						
			}				
		}	
	}
	
	@ServiceMethod(callByContent=true)
	public void generateDaoImplementation() throws Exception{
		
		if(getEntityName() == null)
			return;

		StringBuffer sb = new StringBuffer();
		
		String packageName = getPackageName();
		String entityName = getEntityName();

		
		ClassDefinition classDefinition = new ClassDefinition();
		classDefinition.processManager = processManager; 
		classDefinition.setParentFolder(getParentFolder().toString());
		classDefinition.setPackageName(packageName);
		classDefinition.setClassName(entityName);		

		if(packageName != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb	.append("import org.metaworks.dao.Database;\n\n")
			.append("public class " + entityName).append(" extends Database").append("<I" +  entityName + ">").append(" implements ").append("I").append(entityName).append("{\n\n");
		
		for(int i=0; i<this.entityFields.size(); i++){
			EntityField entityField = this.entityFields.get(i);
			
			String fieldName = entityField.getName();
			String dataType = entityField.getDataType();
			
			String classType = "";
			
			if(dataType.equals("INT"))
				classType = "Long";
			else if(dataType.equals("CHAR") || dataType.equals("VARCHAR"))
				classType = "String";
			else if(dataType.equals("DATETIME"))
				classType = "java.util.Date";
			else if(dataType.equals("TIMESTAMP"))
				classType = "Double";
					
			ClassField cf = new ClassField();
			cf.classModeler = classDefinition.getSourceCodes().getClassModeler();
			cf.classModeler.setNewClassField(new ClassField());
			cf.setFieldName(fieldName);
			cf.setType(classType);
			cf.add();	
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(fieldName);
			
			sb
				.append("	").append(classType).append(" ").append(fieldName).append(";\n")
				.append("		public ").append(classType).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(fieldName).append("; }\n")
				.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(classType).append(" ").append(fieldName).append("){ this.").append(fieldName).append(" = ").append(fieldName).append("; }\n\n")
			;
		}		
		
		sb
//				.append("	@org.metaworks.annotations.ServiceMethod(callByContent=true)\n")
				.append("	public void create() throws Exception{\n")
				.append("		createDatabaseMe();\n")
				.append("	}\n\n")
				
//				.append("	@org.metaworks.annotations.ServiceMethod(callByContent=true)\n")
				.append("	public void save() throws Exception{\n")
				.append("		syncToDatabaseMe();\n")
				.append("	}\n\n")

//				.append("	@org.metaworks.annotations.ServiceMethod\n")
//				.append("	public ").append(entityName).append(" find() throws Exception;\n\n")
				.append("	public Object find() throws Exception;\n\n")
				.append("		return databaseMe();\n")
				.append("	}\n\n")
				
//				.append("	@org.metaworks.annotations.ServiceMethod\n")
				.append("	public void delete() throws Exception{\n")
				.append("		deleteDatabaseMe();\n")
				.append("	}\n\n")
		;
		
		sb.append("}");
		
		classDefinition.generateFaceHelperSourceCode();
		classDefinition.getSourceCodes().sourceCode = new JavaSourceCode();
		classDefinition.getSourceCodes().sourceCode.setCode(sb.toString());
		classDefinition.save();
	}
	
	@ServiceMethod(callByContent=true)
	public void generateDaoInterface() throws Exception{
		if(getEntityName() == null)
			return;

		StringBuffer sb = new StringBuffer();
		
		String packageName = getPackageName();
		String entityName = "I" + getEntityName();

		
		ClassDefinition classDefinition = new ClassDefinition();
		classDefinition.processManager = processManager; 
		classDefinition.setParentFolder(getParentFolder().toString());
		classDefinition.setPackageName(packageName);
		classDefinition.setClassName(entityName);		

		if(packageName != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb	.append("import org.metaworks.dao.IDAO;\n")
			.append("import org.metaworks.annotation.ServiceMethod;\n")
			.append("import javax.persistence.Id;\n\n")
			
			.append("public interface ").append(entityName).append(" extends IDAO{\n\n");
		
		for(int i=0; i<this.entityFields.size(); i++){
			EntityField entityField = this.entityFields.get(i);
			
			String fieldName = entityField.getName();
			String dataType = entityField.getDataType();
			
			String classType = "";
			
			if(dataType.equals("INT"))
				classType = "Long";
			else if(dataType.equals("CHAR") || dataType.equals("VARCHAR"))
				classType = "String";
			else if(dataType.equals("DATETIME"))
				classType = "java.util.Date";
			else if(dataType.equals("TIMESTAMP"))
				classType = "Double";
			
			ClassField cf = new ClassField();
			cf.classModeler = classDefinition.getSourceCodes().getClassModeler();
			cf.classModeler.setNewClassField(new ClassField());
			cf.setFieldName(fieldName);
			cf.setType(classType);
			cf.add();	
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(fieldName);
			
			if(entityField.isKey()){
				sb.append("	@Id\n");
			}
			
			sb
				.append("	public ").append(classType).append(" get").append(fieldNameFirstCharUpper).append("();\n")
				.append("	public void set").append(fieldNameFirstCharUpper).append("(").append(classType).append(" ").append(fieldName).append(");\n\n")
			;
		}		
		
		sb
		.append("\n\n")
		.append("	@ServiceMethod(callByContent=true)\n")
		.append("	public void create() throws Exception;\n\n")
		
		.append("	@ServiceMethod(callByContent=true)\n")
		.append("	public void save() throws Exception;\n\n")

		.append("	@ServiceMethod\n")
//		.append("	public ").append(entityName).append(" find() throws Exception;\n\n")
		.append("	public Object find() throws Exception;\n\n")
		
		.append("	@ServiceMethod\n")
		.append("	public void delete() throws Exception;\n\n")
		;

		sb.append("}");
		
		classDefinition.generateFaceHelperSourceCode();
		classDefinition.getSourceCodes().sourceCode = new JavaSourceCode();
		classDefinition.getSourceCodes().sourceCode.setCode(sb.toString());
		classDefinition.save();
	}
			
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
			setAlias(getEntityName() + ".sql");

			String strDef = GlobalContext.serialize(this, ClassDefinition.class);
			
			String fullDefId = processManager.addProcessDefinition(getEntityName(),getVersion(), "description", false, strDef, getParentFolder(),getDefId(), getAlias(), "entity");
			
			String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
			
			/// generate source file
			File sourceCodeFile = new File("/Users/jyjang/javasources/" + getAlias());
			sourceCodeFile.getParentFile().mkdirs();
			//sourceCodeFile.createNewFile();
/*			
			FileWriter writer = new FileWriter(sourceCodeFile);
			writer.write(getSourceCode().getCode());
			writer.close();*/
			
			//TODO:   generate face file if exists
//			if(UEngineUtil.isNotEmpty(getFace().getCode())){
//				File faceFile = new File("/Users/jyjang/javasources/" + getAlias());
//				sourceCodeFile.getParentFile().mkdirs();
//				//sourceCodeFile.createNewFile();
//				
//				FileWriter writer = new FileWriter(sourceCodeFile);
//				writer.write(getSourceCode().getCode());
//				writer.close();
//			}
			///
			
			processManager.setProcessDefinitionProductionVersion(definitionIdAndVersionId[1]);
			processManager.applyChanges();
			
			setDefId(definitionIdAndVersionId[0]);
			setDefVerId(definitionIdAndVersionId[1]);

	}		

	public void init() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		if(entityFields != null){
			for(int i=0; i<entityFields.size();i++){
				EntityField entityField = entityFields.get(i);
				
				entityField.setMetaworksContext(new MetaworksContext());
				entityField.getMetaworksContext().setWhere("in-container");
				entityField.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			}
		}
		
		newEntityField = new EntityField();
		newEntityField.metaworksContext = new MetaworksContext();
		newEntityField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newEntityField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
		newEntityField.entityDefinition = this;
	}
	
	@Override
	public ArrayList<String> listProperties() {
		try {
			WebObjectType type = MetaworksRemoteService.getInstance().getMetaworksType(getEntityName());
			
			ArrayList array = new ArrayList();
			for (WebFieldDescriptor wfd: type.getFieldDescriptors()) {
				array.add(wfd.getName());
			}
			
			return array;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
