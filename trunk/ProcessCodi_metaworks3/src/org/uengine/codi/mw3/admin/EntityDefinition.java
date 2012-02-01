
package org.uengine.codi.mw3.admin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ClassDesignerContentPanel;
import org.uengine.codi.mw3.model.EntitySourceCode;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;

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
	transient protected ProcessManagerRemote processManager;

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
		
		//if(this.getMetaworksContext().getWhen() == "newEntry"){
			sb.append("CREATE TABLE ").append(this.entityName).append("(").append("\n");
							
			for(int i=0; i<this.entityFields.size(); i++){
				EntityField entityField = this.entityFields.get(i);				
				
				sb
					.append(" ").append(entityField.getName())
					.append(" ").append(entityField.getDataType()).append("(").append(entityField.getLength()).append(")")
					.append(" ").append((entityField.isAllowNull()?"NULL":"NOT NULL"));
				
				String defaultValue = entityField.getDefaultValue();
				if(defaultValue != null && defaultValue.trim().length() > 0)
					sb.append(" ").append("DEFAULT '").append(defaultValue.trim()).append("'");
				
				String comment = entityField.getComment();
				if(comment != null && comment.trim().length() > 0)
					sb.append(" ").append("COMMENT '").append(comment.trim()).append("'");
				
				if(i < this.entityFields.size()-1)
					sb.append(",").append("\n");				
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
		entityQuery.getMetaworksContext().setHow("select");
		entityQuery.getQueryCode().setCode(makeCreateQuery());
		
		return entityQuery;
	}

	@ServiceMethod(callByContent=true)
	public EntityDefinition createTable() throws Exception{
		String query = makeCreateQuery();
		
		if(run(query)){
			setCreated(true);
		}
		
		//save();
		
		return this;
	}
	
	@ServiceMethod(callByContent=true)
	public EntityDefinition dropTable() throws Exception{
		String query = makeDropQuery();
		
		if(run(query)){
			setCreated(false);
		}
		
		return this;
	}
	
	
	private boolean run(String query){
		
		boolean result = false;
		
		if(query==null) return result;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = TransactionContext.getThreadLocalInstance().getConnection();				
			pstmt = conn.prepareStatement(query);
			pstmt.execute();
			
			result = true;
			
//			IDAO dao = Database.sql(IDAO.class, getCreateSQL().getCode());
//			dao.update();
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
			
			//getCreateSQL().setCompileErrors(new CompileError[]{compileError});
		
			e.printStackTrace();
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
		
		return result;
	}
	
	@ServiceMethod(callByContent=true)
	public Object generateDAO() throws Exception{
		save();
		
		ClassDefinition classDefinition = new ClassDefinition();		
		classDefinition.setParentFolder(getParentFolder().toString());
		
		classDefinition.setClassName(getEntityName());
		
		for(int i=0; i<this.entityFields.size(); i++){
			EntityField entityField = this.entityFields.get(i);
			
			String classType = "";
			
			if(entityField.getName().equals("INT"))
				classType = "java.lang.Long";
			else if(entityField.getName().equals("CHAR") || entityField.getName().equals("VARCHAR"))
				classType = "java.lang.String";
			else if(entityField.getName().equals("DATETIME"))
				classType = "java.util.Date";
			else if(entityField.getName().equals("TIMESTAMP"))
				classType = "java.lang.Double";
			
			classDefinition.newClassField.setFieldName(entityField.getName());
			classDefinition.newClassField.setType(classType);
			classDefinition.newClassField.add();
		}
		
		classDefinition.generateSourceCode();
		
		ClassDesignerContentPanel classDesigner = new ClassDesignerContentPanel();
		classDesigner.setClassDefinition(classDefinition);
		
		return classDesigner;
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
			writer.close();
			*/
			///
			
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
