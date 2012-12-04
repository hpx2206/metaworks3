package org.uengine.codi.mw3.admin;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.JavaSourceCode;
import org.uengine.codi.mw3.model.QuerySourceCode;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class EntityDefinition implements ContextAware, PropertyListable, NeedArrangementToSerialize{

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
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);		
	}
	
	String alias;
		@Hidden
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	int version;
		@Hidden
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		
	transient IUser author; 
		@Available(when={"edit"})
		public IUser getAuthor() {
			return author;
		}
		public void setAuthor(IUser author) {
			this.author = author;
		}		
		
	String defId;
		@NonEditable
		@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String defVerId;
		@NonEditable
		@Hidden
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}
		
	String parentFolder;
		@NonEditable
		@Hidden
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}
		
	String packageName;
		//@Available(when={"edit"})
		@Hidden
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
	
	
	String entityName;
		@Name
		@Available(when={"edit"})	
		public String getEntityName() {
			return entityName;
		}
		public void setEntityName(String entityName) {
			this.entityName = entityName;
		}
				
	EntityModeler entityModeler;
		@Available(when={"step1", "view"})
		public EntityModeler getEntityModeler() {
			return entityModeler;
		}
		public void setEntityModeler(EntityModeler entityModeler) {
			this.entityModeler = entityModeler;
		}

	transient QuerySourceCode querySourceCode;
		@Available(when="step2")
		public QuerySourceCode getQuerySourceCode() {
			return querySourceCode;
		}
		public void setQuerySourceCode(QuerySourceCode querySourceCode) {
			this.querySourceCode = querySourceCode;
		}		
		
	private String makeCreateQuery(){
		StringBuffer sb = new StringBuffer();
		StringBuffer sbPK = new StringBuffer();
		
		sb.append("CREATE TABLE ").append(this.entityName).append(" (").append("\n");
		
		ArrayList<EntityField> entityFields = getEntityModeler().getEntityFields();
		
		if(entityFields != null){
			for(int i=0; i<entityFields.size(); i++){
				EntityField entityField = entityFields.get(i);				
				
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
				
				if(i < entityFields.size()-1)
					sb.append(",").append("\n");		
				
				if(entityField.isKey()){
					if(sbPK.length()>0)
						sbPK.append(",");
					
					sbPK.append(entityField.getName());
				}
				
				
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
	
	@ServiceMethod(callByContent=true, when="edit")
	@Face(displayName="Next")
	public void next1() throws Exception{
		if(queryCreatedEntity())
			throw new Exception("이미 테이블이 존재합니다.");
		
		if(getEntityModeler() == null)
			setEntityModeler(new EntityModeler());		
				
		getEntityModeler().init();
		
		getMetaworksContext().setWhen("step1");	
	}
	
	@ServiceMethod(callByContent=true, when="step1")
	@Face(displayName="Next")
	public void next2(){
		if(getQuerySourceCode() == null)
			setQuerySourceCode(new QuerySourceCode());
		
		generateQueryCode();
		
		getMetaworksContext().setWhen("step2");		
	}	

	@ServiceMethod(callByContent=true, when="step2")
	public void createTable() throws Exception{
		save();
		
		run(getQuerySourceCode().getCode(), true);
		
		getMetaworksContext().setWhen("view");
	}
	
	@ServiceMethod(callByContent=true, when="view")
	public void dropTable() throws Exception{
		run(makeDropQuery(), true);
		
		getMetaworksContext().setWhen("edit");
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
			
			getQuerySourceCode().setCompileErrors(new CompileError[]{compileError});
		
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
	
	protected void generateQueryCode(){
		setQuerySourceCode(new QuerySourceCode());
		getQuerySourceCode().setCode(makeCreateQuery());		
	}
		
	@ServiceMethod(callByContent=true, when="view")
	public void generateDao() throws Exception {
		generateDaoInterface();
		//generateDaoImplementation();
	}
	
	private void generateDaoImplementation() throws Exception{
		
		if(getEntityName() == null)
			return;

		StringBuffer sb = new StringBuffer();
		
		String packageName = getPackageName();
		String entityName = getEntityName();
		String variableName = entityName.substring(0,1).toLowerCase() + entityName.substring(1);
		
		ClassDefinition classDefinition = new ClassDefinition();
		classDefinition.processManager = processManager; 
		classDefinition.setParentFolder(getParentFolder().toString());
		classDefinition.setPackageName(packageName);
		classDefinition.setClassName(entityName);		
		
		
		ArrayList<EntityField> entityFields = getEntityModeler().getEntityFields();
		
		if(packageName != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb	.append("import org.metaworks.dao.Database;\n")
			.append("import org.metaworks.widget.grid.DatabaseGrid;\n")
			.append("import org.uengine.codi.mw3.model.Popup;\n\n")
			
			.append("public class " + entityName).append(" extends Database").append("<I" +  entityName + ">").append(" implements ").append("I").append(entityName).append("{\n\n");
		
		for(int i=0; i<entityFields.size(); i++){
			EntityField entityField = entityFields.get(i);
			
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
				.append("	public Object find() throws Exception{\n\n")
				.append("		return databaseMe();\n")
				.append("	}\n\n")
				
//				.append("	@org.metaworks.annotations.ServiceMethod\n")
				.append("	public void delete() throws Exception{\n")
				.append("		deleteDatabaseMe();\n")
				.append("	}\n\n")
				
				.append("	public Popup showGrid() throws Exception{\n")
				.append("		I" + entityName + " " + variableName + " = (I" + entityName + ")sql(I" + entityName + ".class, \"SELECT * FROM " + variableName + "\");\n")
				.append("		" + variableName + ".select();\n")
				.append("		\n")
				.append("		DatabaseGrid grid = new DatabaseGrid();\n")
				.append("		grid.setObjectData(I" + entityName + ".class, " + variableName + ");\n")
				.append("		\n")
				.append("		Popup popup = new Popup(1000,300);\n")
				.append("		popup.setPanel(grid);\n")
				.append("		\n")
				.append("		return popup;\n")
				.append("	}\n\n")
				
		;
		
		sb.append("}");
		
		classDefinition.generateFaceHelperSourceCode();
		classDefinition.getSourceCodes().sourceCode = new JavaSourceCode();
		classDefinition.getSourceCodes().sourceCode.setCode(sb.toString());
		classDefinition.save();
	}
	
	private void generateDaoInterface() throws Exception{
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

		ArrayList<EntityField> entityFields = getEntityModeler().getEntityFields();
		
		if(packageName != null)
			sb.append("package ").append(getPackageName()).append(";\n\n");
		
		sb	.append("import javax.persistence.Id;\n\n")
			.append("import org.metaworks.ServiceMethodContext;\n")
			.append("import org.metaworks.dao.IDAO;\n")			
			.append("import org.metaworks.annotation.ServiceMethod;\n")			
			.append("import org.uengine.codi.mw3.model.Popup;\n\n")
			
			.append("public interface ").append(entityName).append(" extends IDAO{\n\n");
		
		for(int i=0; i<entityFields.size(); i++){
			EntityField entityField = entityFields.get(i);
			
			String fieldName = entityField.getName();
			String dataType = entityField.getDataType();
			
			String classType = "";
			
			if(dataType.equals("INT"))
				classType = "java.lang.Long";
			else if(dataType.equals("CHAR") || dataType.equals("VARCHAR"))
				classType = "java.lang.String";
			else if(dataType.equals("DATETIME"))
				classType = "java.util.Date";
			else if(dataType.equals("TIMESTAMP"))
				classType = "java.lang.Double";
			
			String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(fieldName);
			
			if(entityField.isKey()){
				sb.append("	@Id\n");
			}
			
			sb
				.append("	public ").append(classType).append(" get").append(fieldNameFirstCharUpper).append("();\n")
				.append("	public void set").append(fieldNameFirstCharUpper).append("(").append(classType).append(" ").append(fieldName).append(");\n\n")
			;
		}		
		
		/*
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
		
		.append("	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)\n")
		.append("	public Popup showGrid() throws Exception;\n\n");
		*/

		sb.append("}");
		
		classDefinition.generateFaceHelperSourceCode();
		classDefinition.getSourceCodes().sourceCode = new JavaSourceCode();
		classDefinition.getSourceCodes().sourceCode.setCode(sb.toString());
		classDefinition.save();
	}
	
	private void save() throws Exception{
		
		setParentFolder(getPackageName().replace('.', '/'));
		
		setAlias(getParentFolder() + "/" + getEntityName() + ".sql");

		/// generate source file
		String sourceCodeBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();
		File sourceCodeFile = new File(sourceCodeBase + "/" + getAlias());
		sourceCodeFile.getParentFile().mkdirs();
		//
		FileWriter writer = new FileWriter(sourceCodeFile);
		writer.write(getQuerySourceCode().getCode());
		writer.close();
		
		
		
		
		String strDef = GlobalContext.serialize(this, ClassDefinition.class);
		
		
		String fullDefId = processManager.addProcessDefinition(getEntityName(),getVersion(), "description", false, strDef, getParentFolder(),getDefId(), getAlias(), "entity");
		
//		String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
//		
//		processManager.setProcessDefinitionProductionVersion(definitionIdAndVersionId[1]);
		
//		setDefId(definitionIdAndVersionId[0]);
//		setDefVerId(definitionIdAndVersionId[1]);
	}		
	
	public boolean queryCreatedEntity() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{		
			String sql = "SELECT * FROM " + getEntityName() + " LIMIT 1";				
			
			conn = TransactionContext.getThreadLocalInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			if(rs != null)
				rs.close();			
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{				
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e)
			{
			}

			try{
				if(conn != null)
					conn.close();
			}catch(SQLException e)
			{
			}
		}
		
		return false;
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
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void beforeSerialization() {
	}
	
	
	@Override
	public void afterDeserialization() {
		/*
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias());
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			getQuerySourceCode().setCode(bao.toString());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
