package org.metaworks.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.RowSet;

import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ObjectInstance;
import org.metaworks.ObjectType;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.ORMapping;
import org.metaworks.dwr.MetaworksRemoteService;

import sun.jdbc.rowset.CachedRowSet;


/**
 * Generic DAO
 *
 * @author <a href="mailto:ghbpark@hanwha.co.kr">Sungsoo Park</a>, Jinyoung Jang
 * @version $Id: AbstractGenericDAO.java,v 1.33 2010/04/08 01:27:21 allbegray Exp $
 */
public abstract class AbstractGenericDAO implements InvocationHandler, IDAO {
//	protected boolean isAutoCommit = true;
	
	protected boolean isConnective = false;
		public boolean isConnective() {
			return isConnective;
		}
		public void setConnective(boolean isConnective) {
			this.isConnective = isConnective;
		}
		
	private boolean isDirty = false;
	
	private boolean isUpdateOnlyWhenDirty = true;
		protected boolean updateOnlyWhenDirty() {
			return isUpdateOnlyWhenDirty;
		}
		public void setUpdateOnlyWhenDirty(boolean isUpdateOnlyWhenDirty) {
			this.isUpdateOnlyWhenDirty = isUpdateOnlyWhenDirty;
		}
		
    protected CachedRowSet rowSet = null;
    
    private ConnectionFactory connectionFactory;
		public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}
		public void setConnectionFactory(ConnectionFactory factory) {
			//if(factory==null) throw new RuntimeException("ConnectionFactory should not be null.");
			
			connectionFactory = factory;
		}
    
    //private HashMap propertyMap;
	protected HashMap cache;
    protected HashMap outFieldMap;
    protected HashMap modifiedFieldMap;
    
    protected boolean isBatchUpdate = false;
    
    String sqlStmt;
		public String getSqlStmt() {
			return sqlStmt;
		}
		public void setSqlStmt(String sqlStmt) {
			this.sqlStmt = sqlStmt;
		}

	String tableName;
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		
	String keyField;
		public String getKeyField() {
			return keyField;
		}
		public void setKeyField(String keyField) {
			this.keyField = keyField;
		}

    
	Class daoClass;
		public Class getDaoClass() {
			return daoClass;
		}

	HashMap<String, Class> propertyTypes;
		
	ArrayList<String> propertyNames;
		public ArrayList<String> getPropertyNames() {
			return propertyNames;
		}
		
	Object synchronizedObject;
		public Object getSynchronizedObject() {
			return synchronizedObject;
		}
		public void setSynchronizedObject(Object synchronizedObject) {
			this.synchronizedObject = synchronizedObject;
		}
//	HashMap isOptional;
	




	boolean autoSQLGeneration;
		public boolean isAutoSQLGeneration() {
			return autoSQLGeneration;
		}
		public void setAutoSQLGeneration(boolean autoSQLGeneration) {
			this.autoSQLGeneration = autoSQLGeneration;
		}	

	protected AbstractGenericDAO(Class daoClass, boolean isConnective) throws Exception{
		initialize(daoClass, isConnective);
	}	


  
	protected AbstractGenericDAO(final String jndiName, boolean isConnective, String sqlStmt, Class daoClass) throws Exception {
		this(
			new ConnectionFactory(){
				public Connection getConnection() throws Exception{
					InitialContext ctx = null;
					ctx = new InitialContext();
					DataSource ds = (javax.sql.DataSource) ctx.lookup(jndiName);
					return ds.getConnection();
				}
			}, 
			isConnective,
			sqlStmt, daoClass, null
		);		
	}
	
//	protected AbstractGenericDAO(TransactionContext tc, String sqlStmt, Class daoClass)	throws Exception {
//		this(
//			new TransactionContextConnectionFactoryAdapter(tc),
//			false,
//			sqlStmt, daoClass
//		);
//		this.tc = tc;
//	}		

//	protected AbstractGenericDAO(final Connection conn, boolean isConnective, String sqlStmt, Class daoClass)	throws Exception {
//		this(
//			new ConnectionFactory(){
//				public Connection getConnection() throws Exception{
//					return conn;
//				}
//			},
//			isConnective,
//			sqlStmt, daoClass
//		);
//	}		

	protected AbstractGenericDAO(ConnectionFactory con, boolean isConnective, String sqlStmt, Class daoClass, Object synchronizedObject) throws Exception {
		initialize(con, isConnective, sqlStmt, daoClass, synchronizedObject);
	}
	
	protected void initialize(ConnectionFactory cf, boolean isConnective, String sqlStmt, Class daoClass, Object synchronizedObject)
		throws Exception {
		
		setSynchronizedObject(synchronizedObject);
		
		setConnectionFactory(cf);

/*		propertyMap = new HashMap();{
			String[] splits = statement.split("?");
			for(int i=0; i<splits.length; i++){
				String token = splits[i];
    		
				String splits2[] = token.split(" ");        		
				String propertyName = splits2[0];
    		
				propertyMap.put(propertyName.toUpperCase(), new Integer(i));
			}
		}*/
//		this.outFieldMap = new HashMap();    
//		this.cache = new HashMap();
		
//		this.selectSqlStmt = selectSqlStmt;
//		this.insertSqlStmt = insertSqlStmt;
//		this.updateSqlStmt = updateSqlStmt;
		
//		this.actualSelectSqlStmt = getActualSqlStmt(selectSqlStmt); 
//		this.actualInsertSqlStmt = getActualSqlStmt(insertSqlStmt);
//		this.actualUpdateSqlStmt = getActualSqlStmt(updateSqlStmt);
		
//		this.daoClass = daoClass;
//		Method[] methods = daoClass.getMethods();
//		propertyTypes = new HashMap(methods.length);
//		for(int i=0; i<methods.length; i++){
//			String propName = methods[i].getName();
//			
//			if(propName.startsWith("get")){
//				propName = propName.substring(3).toUpperCase();
//				propertyTypes.put(propName, methods[i].getReturnType());
//			}
//		}
		setStatement(sqlStmt);
		initialize(daoClass, isConnective);
	}

	
	protected void initialize(Class daoClass, boolean isConnective) throws Exception {
//		this.isAutoCommit = isAutoCommit;
		this.isConnective = isConnective;
		
//		if ( isAutoCommit ) isConnective = false;
		
		this.outFieldMap = new HashMap();    
		
		this.cache = new HashMap(){

			@Override //this will guarantee the cachedRows and cache access will synchronized exactly. 
			public Object get(Object arg0) {
				if(cachedRows==null)
					return super.get(arg0);
				else{
					HashMap currRowCache = cachedRows.get(cursor);
					return currRowCache.get(arg0);
				}
			}

			@Override //this will guarantee the cachedRows and cache access will synchronized exactly. 
			public Object put(Object arg0, Object arg1) {
				if(cachedRows==null)
					return super.put(arg0, arg1);
				else{
					HashMap currRowCache = cachedRows.get(cursor);
					return currRowCache.put(arg0, arg1);
				}
			}
			
		};
		
		this.daoClass = daoClass;

		initializePropertyTypes();
	}
	
	protected void initializePropertyTypes(){
		Method[] methods = daoClass.getMethods();
		propertyTypes = new HashMap<String, Class>(methods.length);
		propertyNames = new ArrayList<String>();
		for(int i=0; i<methods.length; i++){
			String propName = methods[i].getName();
			
			boolean getterStartsGet = propName.startsWith("get");
			boolean getterStartsIs = (getterStartsGet ? false : propName.startsWith("is"));
			
			if((getterStartsGet || getterStartsIs) && methods[i].getParameterTypes().length == 0){
				
				try{
					propName = propName.substring((getterStartsGet ? 3 : 2));

					propertyNames.add(propName.substring(0, 1).toLowerCase() + propName.substring(1));
					
					propName = propName.toUpperCase();
					propertyTypes.put(propName, methods[i].getReturnType());
				}catch(Exception e){
					//ignore if there's no setter having same property naming convention.
					e.printStackTrace();
				}
				
			}
		}		
	}
	
	public Class getPropertyType(String propertyName){
		if(propertyTypes==null){
			initializePropertyTypes();
		}
		
		return propertyTypes.get(propertyName);
	}
	
	public void select() throws Exception {
		
		if(sqlStmt == null && isAutoSQLGeneration()){
			createSelectSql();
		}
		
//		rowSet = null;
		//TODO: need to be cached
		releaseResource();
		
		PreparedStatement pstmt = null;
		ConnectionFactory cf = getConnectionFactory();
		Connection con = cf.getConnection();
		try{
			if(con == null) throw new Exception("Connection is null");
			
			pstmt = con.prepareStatement(getActualSqlStmt(getStatement()));
		
			lateBindProperties(getStatement(), pstmt);
			rowSet = new CachedRowSet();
//			ResultSet rs = pstmt.executeQuery();
//			rs.next();
			rowSet.populate(pstmt.executeQuery());
			rowSet.beforeFirst();
			
			isDirty = false;
			
		}catch(Exception e){
			throw new Exception("Error when to try sql [" + getStatement() + "] ", e);
		}finally{
			if (rowSet!= null && cf instanceof TransactionContext) {
				TransactionContext tc = (TransactionContext) cf;
				tc.addReleaseResourceListeners(new ReleaseResourceListener() {
					
					@Override
					public void beforeReleaseResource(TransactionContext tx) throws Exception {
						releaseResource();
					}
				});
			}
			
			try{pstmt.close();}catch(Exception e){}
			if ( !isConnective ) {
				checkOkToCloseConnection();
				try{con.close();}catch(Exception e){}
			}
			
		}		
	}
	
	private void checkOkToCloseConnection() throws Exception{
	}
	
	public void createInsertSql() throws Exception{
		final StringBuffer sql_KeyNames = new StringBuffer();
		final StringBuffer sql_ValuePlaceHolders = new StringBuffer();
		
		if(rowSet==null){	
			ForLoop loopForCacheKeys = new ForLoop(){
				String sep = "";

				public void logic(Object target) {
					String propertyName = (String)target;

					//ignores metaworks signals
					if("METAWORKSCONTEXT".equals(propertyName)) return;
					
					sql_KeyNames.append(sep + propertyName);
					sql_ValuePlaceHolders.append(sep + "?" + propertyName);
					
					sep =", ";
				}
				
			};
			
			loopForCacheKeys.run(cache.keySet());
			sqlStmt = "insert into " + getTableName() + "("+ sql_KeyNames +") values (" + sql_ValuePlaceHolders + ")";

		}else{				
			String sep = "";
			ResultSetMetaData rsMetaData = rowSet.getMetaData();
			for(int i=1; i<=rsMetaData.getColumnCount(); i++){
				String propertyName = rsMetaData.getColumnName(i);
				
				sql_KeyNames.append(sep + propertyName);
				sql_ValuePlaceHolders.append(sep + "?" + propertyName);
				
				sep =", ";
			}

			sqlStmt = "insert into " + getTableName() + "("+ sql_KeyNames +") values (" + sql_ValuePlaceHolders + ")";

		   	adjustMetaDataIfFetched();
		}
		
	}
	
	public int insert() throws Exception {
		if(sqlStmt == null && isAutoSQLGeneration()){
			createInsertSql();
		}
		
		return update();
	}

	private static Hashtable typeMappingHT = new Hashtable();
	static{
		typeMappingHT.put(Integer.valueOf(Types.VARCHAR), 	String.class);
		typeMappingHT.put(Integer.valueOf(Types.INTEGER), 	Number.class);
		typeMappingHT.put(Integer.valueOf(2), 				Number.class);
		typeMappingHT.put(Integer.valueOf(Types.DATE),		Date.class);
		typeMappingHT.put(Integer.valueOf(Types.BOOLEAN),	Boolean.class);
	}
	protected void adjustMetaDataIfFetched() throws Exception{
		if(rowSet==null) return;
		
		ResultSetMetaData rsMetaData = rowSet.getMetaData();
		for(int i=1; i<=rsMetaData.getColumnCount(); i++){
			String propertyName = rsMetaData.getColumnName(i);
			int type = rsMetaData.getColumnType(i);

			Class propertyCls = (Class)typeMappingHT.get(Integer.valueOf(type));
			if(propertyCls!=null)
				propertyTypes.put(propertyName, propertyCls);
		}
	}
	
	public int update(String sqlStmt) throws Exception {	
		setStatement(sqlStmt);
		return update();
	}
	
	
	private int batchCount = 0;
		public int getBatchCount() {
			return batchCount;
		}

	private PreparedStatement pstmtForBatch = null;
	private Connection connForBatch = null;
	
	public void addBatch() throws Exception {
		isBatchUpdate = true;
		if ( batchCount == 0 ) {
//			if ( getConnectionFactory() == null ) 
//				setConnectionFactory(new ConnectionFactory());
			connForBatch = getConnectionFactory().getConnection();
			pstmtForBatch = connForBatch.prepareStatement(getActualSqlStmt(getStatement()));
		} 
		lateBindProperties(getStatement(), pstmtForBatch);
		pstmtForBatch.addBatch();
		batchCount++;
	}
	
	public int[] updateBatch() throws Exception {
		try{
			if ( batchCount > 0 ) {
				return pstmtForBatch.executeBatch();
			} else {
				int[] btc = new int[0];
				return btc;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error when to try sql [" + getStatement() + "] ", e);
			
		}finally{
			try{pstmtForBatch.close();}catch(Exception e){}
			if ( !isConnective ) {
				checkOkToCloseConnection();
				try{connForBatch.close();}catch(Exception e){}
			}
		}			
	}
	
	
	public void createUpdateSql() throws Exception{
		final StringBuffer sql_SetPairs = new StringBuffer();

		if(getTableName()==null || getKeyField()==null)
			throw new Exception("Although Update query is set to be build automatically, the table name or key field is not set.");
		
		if(rowSet==null){
			
			ForLoop loopForCacheKeys = new ForLoop(){
				String sep = "";

				public void logic(Object target) {
					String propertyName = (String)target;
					
					if(modifiedFieldMap!=null && !modifiedFieldMap.containsKey(propertyName)) return;

					//ignores metaworks signals
					if("METAWORKSCONTEXT".equals(propertyName)) return;


					sql_SetPairs.append(sep + propertyName + "=?" + propertyName);
					
					sep =", ";
				}
				
			};
			
			loopForCacheKeys.run(cache.keySet());
			sqlStmt = "update " + getTableName() + " set "+ sql_SetPairs +" where " + getKeyField() + "=?" + getKeyField();

		}else{				
			String sep = "";
			ResultSetMetaData rsMetaData = rowSet.getMetaData();
			for(int i=1; i<=rsMetaData.getColumnCount(); i++){
				String propertyName = rsMetaData.getColumnName(i);				
				
				if(propertyName.equalsIgnoreCase(getKeyField()) || modifiedFieldMap!=null && !modifiedFieldMap.containsKey(propertyName)) continue;
				
				sql_SetPairs.append(sep + propertyName + "=?" + propertyName);				
				sep =", ";
			}

			sqlStmt = "update " + getTableName() + " set "+ sql_SetPairs +" where " + getKeyField() + "=?" + getKeyField();

			if(sql_SetPairs.length() == 0)
				sqlStmt = null;
			
		   	adjustMetaDataIfFetched();
		}
	}
	
	public void createSelectSql() throws Exception{
		
//		ArrayList<String> joinTableList = new ArrayList<String>();
		StringBuffer joinTableSQL = new StringBuffer();
//		ArrayList<String> whereClauses = new ArrayList<String>();
		StringBuffer whereClauseSQL = new StringBuffer();
		
		//Automated join query generation: DAO interface should have keyField setting
		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(daoClass.getName());
		for(FieldDescriptor fd : webObjectType.metaworks2Type().getFieldDescriptors()){
			if(fd.isLoadable() && !Database.dbPrimitiveTypes.containsKey(fd.getClassType())){
				WebObjectType referenceFieldWOT = MetaworksRemoteService.getMetaworksType(fd.getClassType().getName());
				
				if(referenceFieldWOT.getKeyFieldDescriptor()==null) 
					continue; //means it's impossible to create relation
				
				String referenceTableName = referenceFieldWOT.metaworks2Type().getName();
//				joinTableList.add(referenceTableName);
				joinTableSQL.append(",").append(referenceTableName);
//				whereClauses.add(getTableName()+"."+fd.getName() + "=" + referenceTableName +"." + referenceFieldWOT.metaworks2Type().getKeyFieldDescriptor().getName());
				whereClauseSQL.append(" and ").append(getTableName()).append(".").append(fd.getName()).append("=").append(referenceTableName).append(".").append(referenceFieldWOT.metaworks2Type().getKeyFieldDescriptor().getName());
			}
		}
		
		sqlStmt = "select * from " + getTableName() + (joinTableSQL) + " where " + getKeyField() + "=?" + getKeyField() + whereClauseSQL;
	}
	
	public int update() throws Exception {
		if(sqlStmt == null && isAutoSQLGeneration()){
			createUpdateSql();
		}
		
		if(sqlStmt == null || updateOnlyWhenDirty() && !isDirty) return 0;
			
//		if ( tc != null ) tc.setHasDML(true);
//		if ( getConnectionFactory() == null ) 
//			setConnectionFactory(new ConnectionFactory());
		
		//rowSet = null;

		//TODO: need to be cached
		Connection con = getConnectionFactory().getConnection();
		PreparedStatement pstmt = null;
		
		try{
//			System.out.println("getStatement() : " + getStatement());
			
			pstmt = con.prepareStatement(getActualSqlStmt(getStatement()));
			
			lateBindProperties(getStatement(), pstmt);
			int rowAffected = pstmt.executeUpdate();
			
			return rowAffected;
		}catch(Exception e){
			throw new Exception("Error when to try sql [" + getStatement() + "] ", e);
		}finally{
			try{pstmt.close();}catch(Exception e){}
			if ( !isConnective ) {
				checkOkToCloseConnection();
				try{con.close();}catch(Exception e){}
			}
		}		
	}	
	
	
	public int call() throws Exception {
		CallableStatement cstmt = null;		
		Connection con = getConnectionFactory().getConnection();
		try{
//			rowSet = null;
			releaseResource();
		
			cstmt = con.prepareCall(getActualSqlStmt(getStatement()));
		
			lateBindProperties(getStatement(), cstmt);
			int rowAffected = cstmt.executeUpdate();
		
			for(Iterator iter = outFieldMap.keySet().iterator(); iter.hasNext();){
				String fieldName = (String)iter.next();
				int order = ((Integer)outFieldMap.get(fieldName)).intValue();
				Object value = cstmt.getObject(order);
//				Array array = cstmt.getArray("");
				cache.put(fieldName.toUpperCase(), value);
			}
		
			return rowAffected;			
		}catch(Exception e){
			throw e;
		}finally{
			try{cstmt.close();}catch(Exception e){}
			if ( !isConnective ) {
				checkOkToCloseConnection();
				try{con.close();}catch(Exception e){}
			}
		}		
	}
	
//	public boolean absolute(int row) throws Exception {
//		if(rowSet==null)
//			throw new Exception("This DAO is not selected yet.");
//
//		return rowSet.absolute(row);
//	}

//	public int getRow() throws Exception {
//		if(rowSet==null)
//			throw new Exception("This DAO is not selected yet.");
//
//		return rowSet.getRow();
//	}

	int cursor = 0;
    protected List<HashMap> cachedRows;
	
	public void moveToInsertRow() throws Exception{
		moveToInsertRow(null);
	}
	
	public void moveToInsertRow(IDAO cache) throws Exception{
		//TODO: this should care all the cases of scenario, user may reuse rowSet to insert something after selecting. then, we have to put changed values to the rowSet instead of cache? how about now?
		if(rowSet==null && cachedRows==null)
			cachedRows = new ArrayList<HashMap>();
		
		cachedRows.add(cache!=null ? cache.getImplementationObject().cache : new HashMap()); //make new space for cache row.
		last();
	}


	public void beforeFirst() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");
		
		if(rowSet!=null)
			rowSet.beforeFirst();
		else if(cachedRows!=null)
			cursor = -1;
	}

	public boolean previous() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

		if(rowSet!=null)
			return rowSet.previous();
		else{
			cursor--;
			if(cursor < 0){
				cursor = 0;
				return false;
			}else
				return true;
		}
			
	}    

    public boolean next() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

    	if(rowSet!=null)
    		return rowSet.next();
    	else{
    		cursor ++;
    		if(cursor >= cachedRows.size()){
    			cursor = cachedRows.size()-1;
    			
    			return false;
    		}else{
    			return true;
    		}
    	}
    		
    }

	public void afterLast() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

    	if(rowSet!=null)
    		rowSet.afterLast();
    	else{
    		moveToInsertRow();
    	}
	}
    
	public boolean last() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

    	if(rowSet!=null)
    		return rowSet.last();
    	else{
    		cursor = cachedRows.size() - 1;
    		return true;
    	}
    		
	}
        
    
    public boolean first() throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

		if(rowSet!=null)
			return rowSet.first();
		else{
			cursor = 0;
			
			return true;
		}
    }
    
    public boolean absolute(int pos) throws Exception {
		if(rowSet==null && cachedRows==null)
			throw new Exception("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

		if(rowSet!=null)
			return rowSet.absolute(pos);
		else{
			if(pos > -1 && pos < cachedRows.size()){
				cursor = pos;
				return true;
			}else{
				return false;
			}
		}
    }    

    public int size() {
		if(rowSet==null && cachedRows==null)
			throw new RuntimeException("This DAO is not selected yet or you never added row by calling moveToInsertRow()");

		if(rowSet!=null)
			return rowSet.size();
		else
			return cachedRows.size();
    }
    
	public String getString(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");

		return rowSet.getString(key);
	}
	public Integer getInt(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");
		return Integer.valueOf(rowSet.getInt(key));
	}
	public Long getLong(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");

		return Long.valueOf(rowSet.getLong(key));		
	}
	public Boolean getBoolean(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");

		return Boolean.valueOf(rowSet.getBoolean(key));			
	}
	
	public Date getDate(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");

		return rowSet.getDate(key);				 
	}

	public Timestamp getTimestamp(String key) throws Exception {
		if(rowSet==null)
			throw new Exception("This DAO is not selected yet.");

		return rowSet.getTimestamp(key);				 
	}

	protected void lateBindProperties(String statement, PreparedStatement pStmt) throws Exception{
		getActualSqlStmt(statement, pStmt);    	
	}
	
	protected String getActualSqlStmt(String statement) throws Exception{
		return getActualSqlStmt(statement, null);
	}

    protected String getActualSqlStmt(String statement, PreparedStatement pstmt) throws Exception{
    	
    	if(statement==null) return null;
    	
     	StringBuffer realSql=new StringBuffer();
    	
		String[] splits = statement.split("\\?");
						
		if(splits.length>0) 
			realSql.append(splits[0]);
			
		outFieldMap.clear();

		if(splits.length>1){
			for(int i=1; i<splits.length; i++){
				String token = splits[i];
		
				String splits2[] = token.split("[ ,);]");        		
				String propertyNamePart = splits2[0];
				String propertyName = propertyNamePart.trim();
				boolean isOut = false;
				
				if(propertyNamePart.startsWith("*")){
					isOut = true;
					propertyName = propertyNamePart.substring(1);
					
					outFieldMap.put(propertyName.toUpperCase(), Integer.valueOf(i));
				}
						
				token = token.replaceFirst((isOut ? "\\*" : "") + propertyName, "\\?");			
				realSql.append(token);
				
///System.out.println("\n\n ===================== real sql =========================\n  " + realSql);
				
				if (pstmt != null) {
					propertyName = propertyName.toUpperCase();
					
					Object value;
					if (rowSet != null)
						value = rowSet.getObject(propertyName);
					else
						value = cache.get(propertyName);
					
					Class type = (Class) propertyTypes.get(propertyName);
					
//					if ( tc != null && tc.getThreadId() != null && tc.isHasDML() ) {
//						apprLogger.warn("sql update value : " + value);
//					}					
					
					boolean failToFindInMethods = false;
					
					if (type == null)
						failToFindInMethods = true;
					if (type == null && value != null)
						type = value.getClass();
					
					if (isOut) {
						CallableStatement cstmt = (CallableStatement) pstmt;
						if (type == String.class) {
							cstmt.registerOutParameter(i, Types.VARCHAR);
						} else if (Number.class.isAssignableFrom(type)) {
							cstmt.registerOutParameter(i, Types.BIGINT);
						} else if (type == java.util.Date.class) {
							cstmt.registerOutParameter(i, Types.DATE);
						}
					} else {
						try{												
							if (type == String.class) {
								pstmt.setString(i, (String) value);
							} else if (type != null && Number.class.isAssignableFrom(type)) {
								if (value != null) {
									Number numberValue;
									if (value instanceof Boolean) {
										numberValue = Long.valueOf(((Boolean)value).booleanValue() ? 1 : 0);
									} else {
										if (value instanceof String)
										try {
											value = Long.valueOf((String)value);
										} catch (Exception e) {
										}
										
										numberValue = (Number)value;
									}
							
									pstmt.setLong(i, numberValue.longValue());
								} else
									pstmt.setNull(i, Types.BIGINT);
								
							} else if (type != null && java.util.Date.class.isAssignableFrom(type)) {
								if (value != null) {
									java.util.Date dateValue = (java.util.Date)value;
						
									long timeInMS = dateValue.getTime(); 
									value = new Timestamp(timeInMS);							 
								}
							
								pstmt.setTimestamp(i, (Timestamp)value);													
							} else if ( type != null && (Boolean.class.isAssignableFrom(type) || boolean.class == type)) {
								Boolean booleanValue = Boolean.valueOf(false);
								if ( value != null) {
									if(value instanceof Boolean)
										booleanValue = (Boolean)value;
									else if(value instanceof Number)
										booleanValue = Boolean.valueOf(((Number)value).intValue() == 1);
								}
								// 0 : false, 1 : true  [default : 0]
								pstmt.setInt(i, (booleanValue.booleanValue())?1:0);
							} else {		
								
/*								if(value instanceof java.util.Date && DAOFactory.getInstance().getDBMSProductName().equals("`")){
									pstmt.setTimestamp(i, new Timestamp(((java.util.Date)value).getTime()));
								}
*/								
								pstmt.setObject(i, value);
							}
							
						}catch(java.sql.SQLException sqlException){
							String additionalInfo = "";
							if(failToFindInMethods && value == null)
								additionalInfo = "Make sure that the property [" + propertyName + "] is declared as a setter/getter.";
																
							throw new Exception("GenericDAO ["+ daoClass.getName() +"] failed to bind value [" + value + "] to field [" + propertyName + "]. " + additionalInfo, sqlException);
						}
					}
				}
			}
		}
		
		return realSql.toString();
    }
   
    
	public Object invoke(Object proxy, Method m, Object[] args)	throws Throwable{
		String methodName = m.getName();
		
		if(m.getName().equals("getImplementationObject")){
			return getImplementationObject();
		}
		
		boolean startWithGet = m.getName().startsWith("get");
		boolean startsWithIs = m.getName().startsWith("is");
		
		//if getter
		if(startWithGet || startsWithIs){
			
			String propertyNameOrg = methodName.substring(startWithGet ? 3 : 2);
			String propertyName = propertyNameOrg.toUpperCase();
			//propertyName = propertyName;
			
			

			if(propertyName.length()==0 && args.length==1){//from untyped DAO's getter
				return get((String)args[0]);
			}else{//from typed DAO's getter
				
				
				Object returnValue = null;
				
				if(rowSet!=null) {
					
					if("METAWORKSCONTEXT".equalsIgnoreCase(propertyName))
						return getMetaworksContext();
					
//					try {
//					if(rowSet.isClosed()) { 
//						throw new UEngineException("This DAO has been already closed. If you use TransactionContext, Use DAO during TransactionContext is alive.");
//					}
//					}catch (Exception ex) {
//						ex.printStackTrace();
//					}
					
					if ( Boolean.class.isAssignableFrom(m.getReturnType()) ) {
						returnValue = Boolean.valueOf( (rowSet.getInt(propertyName)==1)?true:false );
					} else {
						if (m.getName().equals("getInt") ||  
								m.getName().equals("getLong") || 
								m.getName().equals("getBoolean") || 
								m.getName().equals("getDate") || 
								m.getName().equals("getString")) {
							try{
								return m.invoke(this, args);		
							}catch(Exception e){
								throw e.getCause();
							}
						} 
						
						try{
							if(MetaworksRemoteService.getMetaworksType(daoClass.getName()).metaworks2Type().getFieldDescriptor(propertyNameOrg).isLoadable())
								returnValue = rowSet.getObject(propertyName);
							else
								returnValue = null;
							
						}catch(Exception e){
							// It is the chance to convert primitive value to desired object //
							if(!Database.dbPrimitiveTypes.containsKey(m.getReturnType())){
								WebObjectType type = MetaworksRemoteService.getMetaworksType(m.getReturnType().getName());
								ObjectType objectType = (ObjectType) type.metaworks2Type();
								ObjectInstance objInst = (ObjectInstance) objectType.createInstance();
								
								if(IDAO.class.isAssignableFrom(m.getReturnType())){
									objInst.setObject(MetaworksDAO.createDAOImpl(m.getReturnType()));
								}
								
								boolean atLeastOnceHaveValue = false;
								
								ORMapping ormapping = m.getAnnotation(ORMapping.class);
								if(ormapping!=null){
									String[] ORMPropNames = ormapping.objectFields();
									for(int i=0; i<ORMPropNames.length; i++){
										
										try{
											Object value = rowSet.getObject(ormapping.databaseFields()[i]);
											
											String mappingPropName = ORMPropNames[i];
											mappingPropName = WebObjectType.toUpperStartedPropertyName(mappingPropName);
											
											objInst.setFieldValue(mappingPropName, value);
											atLeastOnceHaveValue = true;
										}catch(Exception ex){
										}
									}
									
									if(atLeastOnceHaveValue)
										return objInst.getObject();
								}
								
								for(int i=0; i<objectType.getFieldDescriptors().length; i++){
									FieldDescriptor fd = objectType.getFieldDescriptors()[i];
																
									String actualPropertyName = propertyName + "___" + fd.getName();

									try{
										Object propValue = rowSet.getObject(actualPropertyName.toUpperCase());
										objInst.setFieldValue(fd.getName(), propValue);
										atLeastOnceHaveValue = true;
									}catch(Exception ex){
									}
								}
								
								if(atLeastOnceHaveValue)
									return objInst.getObject();
							}

							throw new Exception("failed to get property value '" + propertyName + "'", e);
						}
					}
				} else {
					if (args!=null && args.length==1 && args[0] instanceof String && 
							(m.getName().equals("getInt") ||  
							m.getName().equals("getLong") || 
							m.getName().equals("getBoolean") || 
							m.getName().equals("getDate") || 
							m.getName().equals("getString"))
						)
					{
						propertyName = ((String) args[0]).toUpperCase();
					}
					
					returnValue = cache.get(propertyName);
					
//					if("MetaworksContext".equals(propertyName) &&
//						returnValue == null){
//						returnValue = new MetaworksContext();
//						cache.put(propertyName, returnValue);
//					}
				}
				
				// try to convert an integer value to proper mapping values for types
				if(returnValue instanceof Number){
					Number returnValueInNumber = (Number)returnValue;
					if(m.getReturnType() == Long.class || m.getReturnType() == long.class ){
						return Long.valueOf(returnValueInNumber.longValue());
					}
					if(m.getReturnType() == Integer.class || m.getReturnType() == int.class ){
						return Integer.valueOf(returnValueInNumber.intValue());
					}
					if(m.getReturnType() == Boolean.class || m.getReturnType() == boolean.class ){
						return Boolean.valueOf(returnValueInNumber.intValue() == 1);
					}
				}
				
				// try to null values into proper default primitive types' values
				if(returnValue == null){
					if(m.getReturnType() == boolean.class){
						return Boolean.valueOf(false);
					}					
					if(m.getReturnType() == int.class){
						return Integer.valueOf(0);
					}					
					if(m.getReturnType() == long.class){
						return Long.valueOf(0);
					}					
				}
				
				//	try to parse the string value into integer type.
				if(returnValue instanceof String){ 
					if(m.getReturnType() == int.class || Integer.class.isAssignableFrom(m.getReturnType())){
						try{
							return new Integer((String)returnValue);
						}catch(Exception e){
						}
					}	
					
					if(m.getReturnType() == long.class || Long.class.isAssignableFrom(m.getReturnType())){
						try{
							return new Long((String)returnValue);
						}catch(Exception e){
						}
					}
				}
				
				//primitive type mappings
				if(returnValue instanceof Boolean && m.getReturnType() == boolean.class){
					return returnValue;
				}
				
				if(returnValue instanceof Number && (m.getReturnType() == int.class || m.getReturnType() == long.class)){
					return returnValue;
				}
				//end

				
				if(returnValue!=null && !m.getReturnType().isAssignableFrom(returnValue.getClass())){
					throw new Exception("DAO's field type of '"+propertyName+"' is mismatch with the actual table's field.");
				}
				
				
				
				return returnValue;
			}
		}else
		//if setter 
		if(m.getName().startsWith("set")){
			String propertyName = methodName.substring(3);
			
			isDirty = true;
			
			
//			if("writer".equalsIgnoreCase(propertyName)){
//				System.out.println();
//			}
			
			// ORMapping option: Object should be mapped into relation fields //
			if(args.length==1 && !Database.dbPrimitiveTypes.containsKey(m.getParameterTypes()[0])){
				
				ORMapping ormapping = m.getAnnotation(ORMapping.class);
				
				if(ormapping==null){
					try{
						Method getter = getDaoClass().getMethod("get" + propertyName, new Class[]{});
					
						if(getter!=null)
							ormapping = getter.getAnnotation(ORMapping.class);
					}catch(Exception ex){
					}
				}
					
				if(args[0]==null) 
					return null; //means ignore when the object

				if(ormapping!=null){
					WebObjectType type = MetaworksRemoteService.getMetaworksType(m.getParameterTypes()[0].getName());
					ObjectType objectType = (ObjectType) type.metaworks2Type();
					ObjectInstance objInst = (ObjectInstance) objectType.createInstance();
					
					objInst.setObject(args[0]);
					
					boolean atLeastOnceHaveValue = false;
					
					String[] ORMPropNames = ormapping.objectFields();
					for(int i=0; i<ORMPropNames.length; i++){
						
						String mappingPropName = ORMPropNames[i];
						mappingPropName = WebObjectType.toUpperStartedPropertyName(mappingPropName);

						Object value = objInst.getFieldValue(mappingPropName);

						String databaseFieldName = ormapping.databaseFields()[i];
						
						//mapping type check
						if(value!=null){
							Class propertyType = (Class) getPropertyType(databaseFieldName.toUpperCase());
							if(propertyType!=null)
								if(!propertyType.isAssignableFrom(value.getClass())){
									throw new Exception("[ORMapping error] the field '" + databaseFieldName + "' cannot be mapped with object field : '" + type.getName() + "." + mappingPropName + "'.");
								}
						}
						
						set(databaseFieldName, value);
						modifiedFieldMap.put(ormapping.databaseFields()[i].toUpperCase(), propertyName);

					}

					return null;
				}
			}
			
			
			Object value;
			
			if(propertyName.length()==0 && args.length==2){
				propertyName = (String) args[0];
				value = args[1];
				
			}else{//TODO: type check for typed DAO 	
				value = args[0];
			}
			
			set(propertyName, value);

			//Synchronize the original value to be set by databaseMe(). that means when you set some value to databaseMe().setXXX then your original value would be changed.
			if(getSynchronizedObject()!=null){
				WebObjectType wot = MetaworksRemoteService.getMetaworksType(getSynchronizedObject().getClass().getName());
				ObjectInstance instance = (ObjectInstance) wot.metaworks2Type().createInstance();
				instance.setObject(getSynchronizedObject());
				instance.setFieldValue(propertyName, value);
			}

			return null;

		}else{
			try{
				return m.invoke(this, args);
				
			}catch(Exception e){
				throw e.getCause();
			}
		}
	}
	
	
//	public static IDAO createDAOImpl(String jndiName, String sqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			daoClass.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(jndiName, sqlStmt, daoClass)
//		);		
//	}
	
	public AbstractGenericDAO getImplementationObject(){
		return this;
	}

//	/**
//	 * @deprecated
//	 */
//	public static IDAO createDAOImpl(Connection conn, String sqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			daoClass.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(conn, sqlStmt, daoClass)
//		);		
//	}
	
//	public static IDAO createDAOImpl(String jndiName, String selectSqlStmt, String insertSqlStmt, String updateSqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			daoClass.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(jndiName, selectSqlStmt, insertSqlStmt, updateSqlStmt, daoClass)
//		);		
//	}
//
//	public static IDAO createDAOImpl(ConnectionFactory conn, String selectSqlStmt, String insertSqlStmt, String updateSqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			daoClass.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(conn, selectSqlStmt, insertSqlStmt, updateSqlStmt, daoClass)
//		);		
//	}

//	public static IDAO createDAOImpl(ConnectionFactory conn, String sqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			daoClass.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(conn, sqlStmt, daoClass)
//		);		
//	}
//
//	public static IDAO createDAOImpl(ConnectionFactory conn, String sqlStmt) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			IDAO.class.getClassLoader(),
//			new Class[]{IDAO.class},
//			new GenericDAO(conn, sqlStmt, IDAO.class)
//		);
//	}
//
//	public static IDAO createDAOImpl(String sqlStmt, Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			IDAO.class.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(new ConnectionFactory(), sqlStmt, daoClass)
//		);
//	}
//
//	public static IDAO createDAOImpl() throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			IDAO.class.getClassLoader(),
//			new Class[]{IDAO.class},
//			new GenericDAO(IDAO.class)
//		);
//	}
//
//	public static IDAO createDAOImpl(Class daoClass) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			IDAO.class.getClassLoader(),
//			new Class[]{daoClass},
//			new GenericDAO(daoClass)
//		);
//	}
//
//	public static IDAO createDAOImpl(String sqlStmt) throws Exception{		
//		return (IDAO)Proxy.newProxyInstance(
//			IDAO.class.getClassLoader(),
//			new Class[]{IDAO.class},
//			new GenericDAO(new ConnectionFactory(), sqlStmt, IDAO.class)
//		);
//	}

	public Object get(String propertyName) throws Exception {
		try {
			propertyName = propertyName.toUpperCase();
			
			if("METAWORKSCONTEXT".equals(propertyName))
				return getMetaworksContext();
			else
			if(rowSet!=null)
				return rowSet.getObject(propertyName);
			else
				return cache.get(propertyName);
		} catch (Exception e) {
			throw new Exception("Failed to get property '" + propertyName + "' from SQL " + getSqlStmt(), e);
		}
	}

	public Object set(String propertyName, Object value) throws Exception {
		
		if("METAWORKSCONTEXT".equalsIgnoreCase(propertyName)){
			setMetaworksContext((MetaworksContext) value);
			
			return value;
		}
		
		isDirty = true;
		
//		if("url".equalsIgnoreCase(propertyName)){
//			System.out.println();
//		}

		
		if(modifiedFieldMap==null) modifiedFieldMap = new HashMap()/*{

			@Override
			public Object put(Object key, Object value) {
				if("writer".equalsIgnoreCase(key.toString())){
					System.out.println();
				}
				return super.put(key, value);
			}
			
		}*/;
		
		String upperPropertyName = propertyName.toUpperCase();
		
		if(modifiedFieldMap.containsKey(upperPropertyName) && value==null){ //means already set by ORMapped key
			String theSetterFieldName = (String) modifiedFieldMap.get(upperPropertyName);
			if(!theSetterFieldName.equals(propertyName)) 
				return null;
		}
		
		modifiedFieldMap.put(upperPropertyName, propertyName);
		
		if(rowSet!=null){
			cache.clear();
			ResultSetMetaData rsMetaData = rowSet.getMetaData();
			for(int i=1; i<=rsMetaData.getColumnCount(); i++){
				String propName = rsMetaData.getColumnName(i);
				cache.put(propName.toUpperCase(), rowSet.getObject(i));
			}
			
			//rowSet = null;
			releaseResource();
		}
		
		


		return cache.put(upperPropertyName, value);
	}
	

	public String getStatement() {
		return sqlStmt.toUpperCase(); //
	}
	
	public void setStatement(String sql) {
//		System.out.println("set SqlStmt : " + sql);
		this.sqlStmt = sql;
	}

	public RowSet getRowSet(){
		return rowSet;
	}
	


	public String toString(){
		StringBuffer sb = new StringBuffer();
	    Iterator i = propertyTypes.keySet().iterator();
	    while (i.hasNext()) {
	    	String propertyName = (String) i.next();
	    	
	    	Object value = null;
	    	try{
	    		value = get(propertyName);
	    	}catch(Exception e){
	    	}
	    	
	    	if (sb.length() > 0) sb.append(", ");
	    	sb.append(propertyName).append("=").append(value);
		};
		
		return sb.toString();
	}
	
	public void releaseResource() throws Exception {
		if (rowSet != null) {
			rowSet.close();
			rowSet = null;
		}
	}
	
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			if(metaworksContext==null)
				metaworksContext = new MetaworksContext();

			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
}
