package org.metaworks;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectType;
import org.metaworks.Type;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.RepresentativeImagePath;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.inputter.SelectInput;

public class WebObjectType{
	
	boolean isInterface;
	
		public boolean isInterface() {
			return isInterface;
		}
	
		public void setInterface(boolean isInterface) {
			this.isInterface = isInterface;
		}
	
	String name;
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		
	String displayName;
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		
	List<String> superClasses;
		public List<String> getSuperClasses() {
			return superClasses;
		}
		public void setSuperClasses(List<String> superClasses) {
			this.superClasses = superClasses;
		}

	Map<String, ServiceMethodContext> serviceMethodContexts;
		public Map<String, ServiceMethodContext> getServiceMethodContexts() {
			return serviceMethodContexts;
		}
		public void setServiceMethodContexts(
				Map<String, ServiceMethodContext> serviceMethodContexts) {
			this.serviceMethodContexts = serviceMethodContexts;
		}

	String faceComponentPath;
	
		public String getFaceComponentPath() {
			return faceComponentPath;
		}
	
		public void setFaceComponentPath(String faceComponentPath) {
			this.faceComponentPath = faceComponentPath;
		}
		
	WebFieldDescriptor fieldDescriptors[];
		public WebFieldDescriptor[] getFieldDescriptors() {
			return fieldDescriptors;
		}
		public void setFieldDescriptors(WebFieldDescriptor[] fieldDescriptors) {
			this.fieldDescriptors = fieldDescriptors;
		}
		
	Map<String, String> autowiredFields;
		public Map<String, String> getAutowiredFields() {
			return autowiredFields;
		}
		public void setAutowiredFields(Map<String, String> autowiredFields) {
			this.autowiredFields = autowiredFields;
		}

	Type metaworks2Type;
		public Type metaworks2Type() {
			return metaworks2Type;
		}
	
	Class iDAOClass;
		public Class iDAOClass() {
			return iDAOClass;
		}
		
	WebFieldDescriptor keyFieldDescriptor;
		public WebFieldDescriptor getKeyFieldDescriptor() {
			return keyFieldDescriptor;
		}
	
		public void setKeyFieldDescriptor(WebFieldDescriptor keyFieldDescriptor) {
			this.keyFieldDescriptor = keyFieldDescriptor;
		}

	public WebObjectType(Class actCls) throws Exception {
		ObjectType objectType = new ObjectType(actCls);
		this.metaworks2Type = objectType;
		
		setName(objectType.getName());
		setInterface(actCls.isInterface());
		
		superClasses = new ArrayList<String>();
		Class superCls = actCls;
		while(superCls!=Object.class && superCls!=null && MetaworksObject.class!=superCls){
			superClasses.add(superCls.getName());
			superCls = superCls.getSuperclass();
		}

		//search IDAO in the implementation interfaces
		if(actCls.isInterface() && IDAO.class.isAssignableFrom(actCls)){
			this.iDAOClass = actCls;
		}else{
			Class[] interfaces = actCls.getInterfaces();
			for(int i=0; i<interfaces.length; i++){
				if(IDAO.class.isAssignableFrom(interfaces[i])){
					this.iDAOClass = interfaces[i];
				}
				
				superClasses.add(interfaces[i].getName());
			}
		}

		//setting face
		Face typeFace = (Face)getAnnotationDeeply(actCls, iDAOClass, null, Face.class);
		if(typeFace!=null && typeFace.ejsPath().length() > 0){
			setFaceComponentPath(typeFace.ejsPath());
		}else
			setFaceComponentPath(getComponentLocationByEscalation(actCls, "faces"));
	
		if(this.iDAOClass!=null && getFaceComponentPath()==null){
			setFaceComponentPath(getComponentLocationByEscalation(iDAOClass, "faces"));
		}
		
		if(getFaceComponentPath()==null)
			setFaceComponentPath("genericfaces/ObjectFace.ejs");
		
		System.out.println("Metaworks mapped face file : " +  getFaceComponentPath() + " to class: " + getName());


		//set the table name if specified in the annotation.
		
		try{
			
			javax.persistence.Table ejb3Table = (javax.persistence.Table) getAnnotationDeeply(actCls, iDAOClass, null, javax.persistence.Table.class);
			
			org.metaworks.annotation.Table metaworksTable = (org.metaworks.annotation.Table) getAnnotationDeeply(actCls, iDAOClass, null, org.metaworks.annotation.Table.class);
			
			if(metaworksTable != null && metaworksTable.name().length() > 0)
				objectType.setName(metaworksTable.name());
			else if(ejb3Table!=null)
				objectType.setName(ejb3Table.name());
			else
				objectType.setName(getClassNameOnly(actCls));
				
			org.metaworks.annotation.Face objectFace = (Face) getAnnotationDeeply(actCls, iDAOClass, null, Face.class);
			if(objectFace != null && objectFace.displayName().length() > 0)
				setDisplayName(objectFace.displayName());
			else{
				setDisplayName(objectType.getName());
			}
			
		}catch(Exception ex){};

		
		//analyzing 'autowiredFromClient' fields
		Field[] fields = actCls.getFields();
		for(int i=0; i<fields.length; i++){
			Annotation autowiredFromClient = (Annotation) fields[i].getAnnotation(AutowiredFromClient.class);
			
			if(autowiredFromClient!=null){
				if(autowiredFields == null)
					autowiredFields = new HashMap<String, String>();
				
				autowiredFields.put(fields[i].getName(), fields[i].getType().getName());
			}
		}
		
		
		//analyzing setter/getter bean properties
		WebFieldDescriptor[] webFieldDescriptors = new WebFieldDescriptor[objectType.getFieldDescriptors().length];
	
		FieldDescriptor keyField = null;
		for(int i=0; i<objectType.getFieldDescriptors().length; i++){
			
			FieldDescriptor fd = objectType.getFieldDescriptors()[i];
			
			//TODO: change to vector or hashmap the fieldDescriptor. will need to study the field order differences
			//ignores meta-meta data  
//			if(fd.getClassType() == MetaworksContext.class) 
//				continue;
			
			//replacing primitive class type to corresponding object type
			if(fd.getClassType().isPrimitive()){
				
				String clsName = fd.getClassType().getName();
				
				if("int".equals(clsName)){
					fd.setClassType(Integer.class);
				}else if("boolean".equals(clsName)){
					fd.setClassType(Boolean.class);
				}//TODO: consider other primitive class types as well
			}

			
			
			WebViewer viewer = new WebViewer();
			
			boolean isKeyField = false;

			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), NonSavable.class)!=null)
				fd.setSavable(false);

			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), NonLoadable.class)!=null)
				fd.setLoadable(false);

			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), Hidden.class)!=null)
				fd.setAttribute("hidden", new Boolean(true));

			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), AutowiredToClient.class)!=null)
				fd.setAttribute("autowiredToClient", new Boolean(true));

			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), NonEditable.class)!=null)
				fd.setAttribute("nonEditable", new Boolean(true));
			
			if(getAnnotationDeeply(actCls, iDAOClass, fd.getName(), RepresentativeImagePath.class)!=null){
				fd.setAttribute("representativeImagePath", new Boolean(true));
			}
			
			ORMapping orm;
			if((orm = (ORMapping) getAnnotationDeeply(actCls, iDAOClass, fd.getName(), ORMapping.class))!=null){
				fd.setAttribute("ormapping", orm);
			}
			
			Face face = (Face) getAnnotationDeeply(actCls, iDAOClass, fd.getName(), Face.class);
			if(face!=null){
				
				if(face.ejsPath().length() >0){
					viewer.setFace(face.ejsPath());
					
					FaceInput faceInput = new FaceInput();
					faceInput.setFaceName(face.ejsPath());
					fd.setInputter(faceInput);
				}else
					viewer.setFace(getComponentLocationByEscalation(fd.getClassType(), "faces"));
				
				
				if(face.displayName().length() > 0){
					fd.setDisplayName(face.displayName());
				}
			}
		
			Range range = (Range) getAnnotationDeeply(actCls, iDAOClass, fd.getName(), Range.class);
			if(range!=null){
				if(range.options().length > 0){
					SelectInput selectInput = new SelectInput();
					selectInput.setSelections(range.options());
					selectInput.setValues(range.values());
					
					fd.setInputter(selectInput);
					
					viewer.setFace(getComponentLocationByEscalation( SelectInput.class, "genericfaces"));
				}
			}


			if(getAnnotationDeeply(actCls,iDAOClass, fd.getName(), Id.class)!=null 
					|| getAnnotationDeeply(actCls,iDAOClass, fd.getName(), javax.persistence.Id.class)!=null){
				isKeyField = true;
				keyField = fd;
			}
			
			fd.setViewer(viewer);
	
			webFieldDescriptors[i] = new WebFieldDescriptor(fd);
			
			if(isKeyField)
				setKeyFieldDescriptor(webFieldDescriptors[i]);
		}
		
		if(iDAOClass != null){
			if(keyField!=null)
				objectType.setKeyFieldDescriptor(keyField);
			else
				System.err.println("[WARN] Although domain class '" + actCls.getName() + "' look like a database synchronizable object, it has no key field description. Give @org.metaworks.Id for the key field's GETTER[!] method NOT the SETTER.");
		}

		setFieldDescriptors(webFieldDescriptors);
		
		
		//method list

		serviceMethodContexts = new HashMap<String, ServiceMethodContext>();
		for(Method method : actCls.getMethods()){
			ServiceMethod annotation = method.getAnnotation(ServiceMethod.class);
			Face face = method.getAnnotation(Face.class);
			
			if(annotation==null && iDAOClass != null){
				try{
					annotation = iDAOClass.getMethod(method.getName(), new Class[]{}).getAnnotation(ServiceMethod.class);
					face = iDAOClass.getMethod(method.getName(), new Class[]{}).getAnnotation(Face.class);
				}catch(Exception e){
					
				}
			}
			
			if(method.getParameterTypes().length == 0 && annotation!=null){
				
				
				ServiceMethodContext smc = new ServiceMethodContext();
				smc.setMethodName(method.getName());
				smc.setWhen(annotation.when());
				smc.setWhere(annotation.where());
				smc.setCallByContent(annotation.callByContent());
				smc.setNeedToConfirm(annotation.needToConfirm());
				smc.setClientSide(annotation.clientSide());
				smc.setTarget(annotation.target());

				if(face!=null){
					smc.setDisplayName(face.displayName());
				}else{
					smc.setDisplayName(method.getName());
				}
				
				serviceMethodContexts.put(smc.getMethodName(), smc);
				
			}
		}
		
	}
	
	static public Annotation getAnnotationDeeply(Class cls, Class iDAOCls, String fieldName, Class annotationCls) throws Exception{
		//		Class annotationCls = Class.forName("org.metaworks." +annotationName);
		Annotation annotation = null;
		Class[] tryingClasses = {cls, iDAOCls};
		
		for(int i=0; i<tryingClasses.length; i++){
			Class clazz = tryingClasses[i];
			
			if(clazz==null) return null;
			
			if(fieldName!=null){
				Method getter;
				try {
					getter = clazz.getMethod("get" + fieldName, new Class[]{});
					if(getter!=null)
						annotation = getter.getAnnotation(annotationCls);
					
					if(annotation!=null) 
						return annotation;
				} catch (NoSuchMethodException e) {
				}
				
				try {
					getter = clazz.getMethod("is" + fieldName, new Class[]{});
					
					if(getter!=null)
						annotation = getter.getAnnotation(annotationCls);
					
					if(annotation!=null) 
						return annotation;
				} catch (NoSuchMethodException e) {
				}
			}else{
				annotation = clazz.getAnnotation(annotationCls);
				
				if(annotation!=null) 
					return annotation;
			}
		}

		return null;
	}

	static public String getClassNameOnly(Class activityCls){
		return getClassNameOnly(activityCls.getName());
	}
	
	static public String getClassNameOnly(String clsName){
		return clsName.substring( clsName.lastIndexOf(".")+1);
	}


	static public String getComponentLocation(Class cls, String compType, boolean isDefault, boolean overridesPackage){
		if(cls.isArray())
			return "genericfaces/ArrayFace.ejs";
		
		String pkgName = cls.getPackage().getName();
		String clsName = getClassNameOnly(cls);
		
//		return pkgName + "." + compType +(isDefault ? ".Default" : ".")+ clsName + compType.substring(0, 1).toUpperCase() + compType.substring(1, compType.length());		
		return compType + "/" + pkgName.replaceAll("\\.", "/") + "/" + clsName + ".ejs";		
	}
	
	static public boolean tryToFindComponent(String componentName){		
		try{
			HttpServletRequest request = TransactionContext.getThreadLocalInstance().getRequest();
			
	        String url = request.getRequestURL().toString();
	        String codebase = url.substring( 0, url.lastIndexOf( "/" ) );
	        URL urlURL = new java.net.URL(codebase);
	       	String host = urlURL.getHost();
	       	int port = urlURL.getPort();
	       	String path = urlURL.getPath();
	       	String contextOnly = path.substring(0, path.substring(1).indexOf("/")+1);
			String protocol = urlURL.getProtocol();
			
			new URL(protocol + "://" + host + ":" + port + contextOnly + "/metaworks/" + componentName).openStream();
			
			return true;
		}catch(Exception e){
			return false;
		}
	}

	
	static public String getComponentLocationByEscalation(Class cls, String compType){
		Class copyOfCls = cls;
		
		//try to find proper component by escalation (prior to overriding package)
		String overridingPackageName = null;//GlobalContext.ACTIVITY_DESCRIPTION_COMPONENT_OVERRIDER_PACKAGE;
//		if(overridingPackageName!=null){
			do{
//				String componentClsName = getComponentLocation(copyOfCls, compType, false, true);
	
//				if(tryToFindComponent(componentClsName)) return componentClsName;
				
				//try to find proper component by escalation (with original package)
				String componentClsName = getComponentLocation(copyOfCls, compType, false, false);
				if(tryToFindComponent(componentClsName)) return componentClsName;
			
				copyOfCls = copyOfCls.getSuperclass();
			}while(copyOfCls!=Object.class && copyOfCls !=null);
//		}
		

		return null;//"genericfaces/ObjectFace.ejs";
	}
	
	static public String toUpperStartedPropertyName(String propertyName){
		return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}
}
