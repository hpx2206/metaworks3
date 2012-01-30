
package org.uengine.codi.mw3.admin;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.PropertyListable;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

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
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
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

		public String getTableName() {
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
		

	SourceCode createSQL;
			
		public SourceCode getCreateSQL() {
			return createSQL;
		}
		public void setCreateSQL(SourceCode sourceCode) {
			this.createSQL = sourceCode;
		}
				

		
		@ServiceMethod(callByContent=true)
		public void generateCreateSQL(){
			
			StringBuffer sb = new StringBuffer();
			sb
				.append("package ").append(getTableName()).append(";\n\n")
				.append("public class " + getEntityName() + "{\n\n");
			
			for(int i=0; i<getEntityFields().size(); i++){
				EntityField field = getEntityFields().get(i);
				
				String fieldNameFirstCharUpper = UEngineUtil.toOnlyFirstCharacterUpper(field.getFieldName());
				
				sb
					.append("	").append(field.getType()).append(" ").append(field.getFieldName()).append(";\n")
					.append("		public ").append(field.getType()).append(" get").append(fieldNameFirstCharUpper).append("(){ return ").append(field.getFieldName()).append("; }\n")
					.append("		public void set").append(fieldNameFirstCharUpper).append("(").append(field.getType()).append(" ").append(field.getFieldName()).append("){ this.").append(field.getFieldName()).append(" = ").append(field.getFieldName()).append("; }\n\n")
					;
			}
			
			sb.append("}");
			
			
			createSQL = new SourceCode();
			createSQL.setCode(sb.toString());
					
		}
		
		@ServiceMethod(callByContent=true)
		public void createTable() throws Exception{
			save();
		
			if(getCreateSQL()==null) return;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = TransactionContext.getThreadLocalInstance().getConnection();				
				pstmt = conn.prepareStatement(getCreateSQL().getCode());
				pstmt.execute();
				
//				IDAO dao = Database.sql(IDAO.class, getCreateSQL().getCode());
//				dao.update();
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
				
				getCreateSQL().setCompileErrors(new CompileError[]{compileError});
			
				e.printStackTrace();
			}finally{
				if(conn != null){
					conn.close();
					conn = null;
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
		public Object generateDAO() throws Exception{
			
			ClassDefinition classDefinition = new ClassDefinition();
			
			classDefinition.compile();
			
			ResourcePanel resource = new ResourcePanel();
			
			return resource; // let the resource panel refreshed 
		}
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public Object run() throws Exception{
			

			Object o = Thread.currentThread().getContextClassLoader().loadClass(getTableName() + "." + getEntityName()).newInstance();//cl.loadClass(getPackageName() + "." + getClassName()).newInstance();
			
			return o;
		}

		
		@ServiceMethod(callByContent=true)
		public void save() throws Exception{
			
			setAlias(getEntityName() + ".sql");

			String strDef = GlobalContext.serialize(this, ClassDefinition.class);
			
			String fullDefId = processManager.addProcessDefinition(getEntityName(), getVersion(), "description", false, strDef, getParentFolder(), getDefId(), getAlias(), "class");
			
			String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
			
			/// generate source file
			File sourceCodeFile = new File("/Users/jyjang/javasources/" + getAlias());
			sourceCodeFile.getParentFile().mkdirs();
			//sourceCodeFile.createNewFile();
			
			FileWriter writer = new FileWriter(sourceCodeFile);
			writer.write(getCreateSQL().getCode());
			writer.close();
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
		newEntityField = new EntityField();
		newEntityField.metaworksContext = new MetaworksContext();
		newEntityField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newEntityField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
		newEntityField.entityDefinition = this;

	}
	
	@Override
	public ArrayList<String> listProperties() {
		try {
			WebObjectType type = MetaworksRemoteService.getInstance().getMetaworksType(getTableName() + "." + getEntityName());
			
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
