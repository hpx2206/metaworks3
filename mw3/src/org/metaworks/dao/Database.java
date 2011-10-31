package org.metaworks.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.ObjectInstance;
import org.metaworks.ObjectType;
import org.metaworks.Type;
import org.metaworks.WebObjectType;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.IPosting;
import org.metaworks.example.Posting;

public class Database {
	
	public static IDAO create(Object key, Object defaultValue) throws Exception{
		
		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(defaultValue.getClass().getName());
		ObjectInstance objInst = (ObjectInstance) webObjectType.metaworks2Type().createInstance();
		objInst.setObject(defaultValue);
		//Object keyValue = objInst.getKeyFieldValue();
		
		
		Class iDAOType = webObjectType.iDAOClass();
		if(iDAOType == null) iDAOType = IDAO.class;
		
		//this will add the dao to cache list which would be inserted or updated when the transaction is committed
		// so that all the changes in the database is manipulated in memory space, and updated only the changes to the database.
		IDAO dao = TransactionContext.getThreadLocalInstance().createSynchronizedDAO(
			webObjectType.metaworks2Type().getName(), 
			webObjectType.metaworks2Type().getKeyFieldDescriptor().getName(), 
			key, 
			iDAOType,
			defaultValue
		);
		
		if(defaultValue==null) return dao;
		
		
		//set the object value to prepare the where clauses well. this part is not for getting relation tuple to object, is for converting object to tuple.
		for(FieldDescriptor fd : webObjectType.metaworks2Type().getFieldDescriptors()){
			Object fieldValue = objInst.getFieldValue(fd.getName()); 
			
			if(webObjectType.iDAOClass()!=null){
				//if IDAO interface doesn't have this field, it is not a database synchronizable field
				if(MetaworksRemoteService.getMetaworksType(webObjectType.iDAOClass().getName()).metaworks2Type().getFieldDescriptor(fd.getName()) == null) 
					continue;
			}
			
			
			ObjectInstance databaseObjInst = (ObjectInstance) MetaworksRemoteService.getMetaworksType(dao.getImplementationObject().getDaoClass().getName()).metaworks2Type().createInstance();
			databaseObjInst.setObject(dao);

			if(fd.getAttribute("ormapping")!=null){
				
				databaseObjInst.setFieldValue(fd.getName(), fieldValue);
				
			}else if(fd.isSavable() || fd.isKey()){
			
				if(!dbPrimitiveTypes.containsKey(fd.getClassType())){
					ObjectType referenceTableType = (ObjectType) MetaworksRemoteService.getMetaworksType(fd.getClassType().getName()).metaworks2Type();
					referenceTableType.getKeyFieldDescriptor();
					ObjectInstance instance = (ObjectInstance) referenceTableType.createInstance();
					instance.setObject(fieldValue);
					
					fieldValue = instance.getKeyFieldValue();
				}
				
				dao.set(fd.getName(), fieldValue);
			}
			
		}
		
		return dao;

	}
	
	public static IDAO sql(Class<?> classType, String sql) throws Exception{
		
		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(classType.getName());
		
		IDAO dao = (IDAO) MetaworksDAO.createDAOImpl(
			TransactionContext.getThreadLocalInstance(), 
			sql, 
			webObjectType.iDAOClass() != null ? webObjectType.iDAOClass() : IDAO.class
		);
		
		return dao;
	}
	
//		selections.select();
//		//Collection collection = postings.getImplementationObject().toCollection();
//		
//		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(classType.getName());
//		
//		ObjectInstance objInst = (ObjectInstance) webObjectType.metaworks2Type().createInstance();
//
//		int i = 0;
//		Object array[] = new Object[selections.size()];
//		while(selections.next()){
//			
//			for(FieldDescriptor fd : webObjectType.metaworks2Type().getFieldDescriptors()){
//				Object fieldValue = selections.get(fd.getName()); 
//				
//				objInst.setFieldValue(fd.getName(), fieldValue);
//			}
//			
//			array[i++] = objInst.getObject();
//			
//		}
//		
//		return array;
//	}
	
	protected static HashMap<Class, Class> dbPrimitiveTypes = new HashMap<Class, Class>();
	static{
		dbPrimitiveTypes.put(String.class, String.class);
		dbPrimitiveTypes.put(Number.class, Number.class);
		dbPrimitiveTypes.put(Integer.class, Integer.class);
		dbPrimitiveTypes.put(int.class, int.class);
		dbPrimitiveTypes.put(boolean.class, boolean.class);
		dbPrimitiveTypes.put(Boolean.class, Boolean.class);
		dbPrimitiveTypes.put(Long.class, Long.class);
		dbPrimitiveTypes.put(Calendar.class, Calendar.class);
		dbPrimitiveTypes.put(Date.class, Date.class);
		
		//it is special case
		dbPrimitiveTypes.put(MetaworksContext.class, MetaworksContext.class);
		
	}
	
	public static IDAO get(Class classType, Object key) throws Exception{
		return get(classType, key, null);		
	}
	
	public static IDAO get(Class classType, Object key, Object synchronizedObject) throws Exception{
		
		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(classType.getName());

		IDAO dao = TransactionContext.getThreadLocalInstance().findSynchronizedDAO(
				webObjectType.metaworks2Type().getName(), 
				webObjectType.metaworks2Type().getKeyFieldDescriptor().getName(), 
				key, 
				webObjectType.iDAOClass() != null ? webObjectType.iDAOClass() : IDAO.class,
				synchronizedObject);
			
		return dao;
		
	}
	
	public static void flush(Class classType, Object key) throws Exception{
		WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(classType.getName());

		TransactionContext.getThreadLocalInstance().flushSynchronizedDAO(
				webObjectType.metaworks2Type().getName(), 
				key
		);
	}
	
	public static Object createReferenceObject(Class classType, IDAO dao) throws Exception{

		if(!Database.dbPrimitiveTypes.containsKey(classType) && classType != MetaworksContext.class){
			WebObjectType webObjectType = MetaworksRemoteService.getMetaworksType(classType.getName());
			ObjectInstance objInst = (ObjectInstance) webObjectType.metaworks2Type().createInstance();
			
			Class daoClass = webObjectType.iDAOClass();
			IDAO refDAO = null;
			if(daoClass != null && IDAO.class.isAssignableFrom(daoClass)){
				refDAO = MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), null, daoClass);
			}
			
			for(FieldDescriptor fd : webObjectType.metaworks2Type().getFieldDescriptors()){
				try{
					if(!fd.isLoadable()) 
						continue;
					
					if(refDAO==null)
						objInst.setFieldValue(fd.getName(), dao.get(fd.getName()));
					else{
						refDAO.set(fd.getName(), dao.get(fd.getName()));
					}
				}catch(Exception e){
					e.printStackTrace();
					System.err.println("Error to map reference dao's property from " + fd.getName() + " Automated DAO. You may add isLoadable=false annotation to the setter field 'set" + fd.getName() + "()' of class '" + classType.getName() + "'. For example Metaworks lets you expression like following SQL: select * from A, B, C and all the fields from three tables, then Metaworks tries to map all the DAO fields to the mapped class A, B, C with the retrieved selected column names. Metaworks will tryies these everytime, so if you don't want to do this, mark isLoadable = false to the getter method definition.");
				}
			}
			
			if(refDAO!=null) 
				return refDAO;
			
			Object referenceObject = objInst.getObject();
			
			return referenceObject;
		}
		
		return null;
	}
}
