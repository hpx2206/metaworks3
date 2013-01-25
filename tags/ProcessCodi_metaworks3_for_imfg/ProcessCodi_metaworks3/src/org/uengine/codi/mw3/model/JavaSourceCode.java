package org.uengine.codi.mw3.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;
import org.metaworks.example.ide.SourceCode;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.mw3.admin.ClassMethod;
import org.uengine.codi.mw3.admin.ClassModeler;
import org.uengine.codi.mw3.admin.JavaCodeAssist;
import org.uengine.util.UEngineUtil;

public class JavaSourceCode extends SourceCode {
	static Map<String, Map> packageNames = new HashMap<String, Map>(); //cached for more faster code assistance. may need to stored separately with tenants. (may naturally done because of classloader is different with web-contexts)
	static ArrayList<String> classNames = new ArrayList<String>();
	static ArrayList<String> annotationNames = new ArrayList<String>();
	
	public void cacheLibrary() {
		try {
			packageNames.clear();
			classNames.clear();
			//expression = expression.replace('.', '/');

			//URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
			URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
			URL urls[] = classLoader.getURLs();
			for(URL url : urls){				
				if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){				
					net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
					net.sf.jazzlib.ZipEntry zipEntry;	
				    try {				    	
				    	while((zipEntry = zipIn.getNextEntry()) != null) {
				    		String name = zipEntry.getName();
				    		
				    		//if(name.startsWith(expression)){				    			
								if(name.endsWith(".class")){
									name = name.substring(0, name.length() - 6);
																		
									String[] parts = name.split("/");
									if(parts.length > 1){																			
										StringBuffer packageName = new StringBuffer(parts[0]);
										
										for(int i=1; i<parts.length; i++){
											if(!packageNames.containsKey(packageName.toString())) 
												packageNames.put(packageName.toString(), new HashMap<String, String>());
											
											Map<String, String> packageTokens = packageNames.get(packageName.toString());
	
											if(i == parts.length-1)
												packageTokens.put(parts[i], "");
	
											packageName.append(".");
											packageName.append(parts[i]);
											
										}
									}
									
							    	String pkgName = "";
							    	String clsName = "";
							    	
							    	clsName = name;
							    	
							    	int pos = clsName.lastIndexOf("/");
							    	if(pos != -1){
							    		pkgName = clsName.substring(0, pos).replaceAll("/", ".");
							    		clsName = clsName.substring(pos + 1);
							    	}
							    	
							    	classNames.add(clsName + "/" + pkgName);
								}
				    		//}
				    	} // end while
	
				    } catch (Exception e) {
				    	new Exception(url.getFile() + ": error when to parse url ", e).printStackTrace();
				    } finally{
						try{zipIn.close();}catch(Exception ex){}   
				    }
				}
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean confirmAnnotation(String className) {
		
		try {
			int index = annotationNames.indexOf(className);
			if(index != -1)
				return true;
			
		} catch (Exception ex) {			
		}
		return false;		
	}
	
	public void cacheAnnotationNames() {
		annotationNames.clear();
		
		URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
		URL urls[] = classLoader.getURLs();
		for(URL url : urls){
			if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){		
				try {
					net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
					net.sf.jazzlib.ZipEntry zipEntry;
					try {
				    	while((zipEntry = zipIn.getNextEntry()) != null) {
				    		String clsName = zipEntry.getName().replace("/", ".");
				    		if(!(clsName.startsWith("org.metaworks.annotation") 
									|| clsName.startsWith("org.springframework.beans.factory.annotation")
									|| clsName.startsWith("org.springframework.context.annotation")
									|| clsName.startsWith("org.springframework.core.annotation")
									|| clsName.startsWith("org.springframework.format.annotation")
									|| clsName.startsWith("org.springframework.jmx.export.annotation")
									|| clsName.startsWith("org.springframework.scheduling.annotation")
									|| clsName.startsWith("org.springframework.test.annotation")
									|| clsName.startsWith("org.springframework.transaction.annotation")
									|| clsName.startsWith("org.springframework.validation.annotation")
									|| clsName.startsWith("org.springframework.web.bind.annotation")
									|| clsName.startsWith("org.springframework.web.portlet.bind.annotation")
									|| clsName.startsWith("org.springframework.web.servlet.config.annotation")
									|| clsName.startsWith("org.springframework.stereotype")
									|| clsName.startsWith("java.lang.annotation"))) continue;
				    		
				    		if(clsName.endsWith(".class")) {
				    			Class cls = Thread.currentThread().getContextClassLoader().loadClass(clsName.substring(0, clsName.length() - 6));
					    		if(java.lang.annotation.Annotation.class.isAssignableFrom(cls)) {
							    	clsName = clsName.substring(0, clsName.length() - 6);
							    	
							    	String pkgName = "";
							    	
							    	int pos = clsName.lastIndexOf(".");
							    	if(pos != -1){
							    		pkgName = clsName.substring(0, pos);
							    		clsName = clsName.substring(pos + 1);
							    	}
							    	
							    	annotationNames.add(clsName + "/" + pkgName + "/annotation");
					    		}					    							    		
				    		}
				    	}
					} catch (Exception e) {
				    	new Exception(url.getFile() + ": error when to parse url ", e).printStackTrace();
				    } finally{
						try{zipIn.close();}catch(Exception ex){}   
				    }
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//annotation sorting
		//sortAnnotation();
	}

	public void sortAnnotation() {
		if(annotationNames == null || annotationNames.size() == 0) return;
		String[] annotation = new String[annotationNames.size()];
		
		for(int i = 0; i < annotationNames.size(); ++i) {
			annotation[i] = annotationNames.get(i);
		}		
		
		String temp = "";
		for(int i = 0; i < annotation.length - 1; ++i) {
			for(int j = i + 1; j < annotation.length; ++j) {
				if((annotation[i].substring(1, annotation[i].indexOf(" "))).compareToIgnoreCase(annotation[j].substring(1, annotation[j].indexOf(" "))) > 0) {
					temp = annotation[i];
					annotation[i] = annotation[j];
					annotation[j] = temp;
				}
			}
		}
		
		annotationNames = new ArrayList<String>();
		for(int i = 0; i < annotation.length; ++i) {
			annotationNames.add(annotation[i]);
		}
		
		System.out.println("Sorting Finish!");
	}
	
	public String getPackagePath(String expression, String[] path) {
		int index = 1;
		for(int i = 0; i < expression.length(); ++i)
			if(expression.charAt(i) == '.') index++;
		
		StringBuffer pathStr = new StringBuffer();
		
		for(int i = index; i < path.length - 1; ++i) {
			pathStr.append(path[i] + ".");
			if(i == path.length - 2) pathStr.append("*;");
		}
		return pathStr.toString();
	}	
	
	private String extraPackageName(String value){
		return "";
	}
	
	public ArrayList<String> findPackage(String expression, String endChar){
		ArrayList<String> pkgNames = new ArrayList<String>();
		
		for(String packageName : packageNames.keySet()){
			if(packageName.startsWith(expression)){
				pkgNames.add(packageName + endChar + "/" + packageName + "/package/");
			}
		}
		
		return pkgNames;
	}

	public ArrayList<String> findPackageClass(String expression){
		ArrayList<String> clsNames = new ArrayList<String>();		
		Map<String, String> result = packageNames.get(expression);
		
		if(result != null){
			for(String clsName : result.keySet())   {
				if(result.get(clsName).isEmpty()){
					if(confirmAnnotation(clsName))
						result.put(clsName, "annotation");
					else
						result.put(clsName, "class");
				}
				
				clsNames.add(clsName + "/" + expression + "/" + result.get(clsName) + "/") ;
			}    
		}
		
		return clsNames;
	}
	
	public ArrayList<String> findClasses(String expression){
		if(!expression.endsWith("."))
			expression += "/";
		
		ArrayList<String> clsNames = new ArrayList<String>();		
		for(int i=0; i<classNames.size(); i++){
			String clsName = classNames.get(i);
			
			if(clsName.startsWith(expression)){
				String confirmClsName = clsName;
				
				if(confirmClsName.indexOf("//") == -1){
					String[] temp = clsName.split("/");
					confirmClsName = temp[1] + "." + temp[0];										
					
					if(confirmAnnotation(confirmClsName))
						clsName = clsName + "/annotation//";
					else
						clsName = clsName + "/class//";
					
					classNames.set(i, clsName);
				}else{
					String[] temp = confirmClsName.split("/");
					confirmClsName = temp[1] + "." + temp[0];					
				}
				
				System.out.println("confirmClsName : " + confirmClsName);
				
				ArrayList<String> clsInfo = this.findClass(confirmClsName);    	
				for(int j=0; j<clsInfo.size(); j++)
					clsNames.add(clsInfo.get(j));
			}
		}  
		
		return clsNames;
		
	}

	public ArrayList<String> findClass(String expression){
		ArrayList<String> classInfo = new ArrayList<String>();
			
		try{
			Class theClass = Thread.currentThread().getContextClassLoader().loadClass(expression);					
			for(Field field : theClass.getFields()) {
				classInfo.add(field.getName() + "/" + expression + "/field/");
			}
		
			for(Method method : theClass.getMethods()) {						
				StringBuffer paramStmt = new StringBuffer("(");
				String sep = "";
				for(Class paramCls : method.getParameterTypes()){
					String clsNameOnly = UEngineUtil.getClassNameOnly(paramCls);
					clsNameOnly = sep + clsNameOnly.substring(0, 1).toLowerCase() + clsNameOnly.substring(1, clsNameOnly.length());							
					paramStmt.append(clsNameOnly);
					sep = ", ";
				}
				paramStmt.append(")");						
				
				classInfo.add(method.getName() + paramStmt + "/" + expression + "/method/");		
			}
		}catch(Exception e){			
		}
		
		return classInfo;
	}
	
	public String findClassDefine(String expression){
		
		ArrayList<String> importedList = new ArrayList<String>();
		ArrayList<String> tryLoadList = new ArrayList<String>();
		
		String[] lines = getCode().split("\n");		
		String typeName = null;
		
		for(int i=0; i<lines.length; i++){
			String[] linesDetail;
			
			if(lines[i].indexOf(";") == -1){
				lines[i] += ";";
			}			
			linesDetail = lines[i].split(";");
						
			for(int j=0; j<linesDetail.length; j++){
				String line = linesDetail[j];
				line = line.trim();
				
				int whereExp = line.indexOf(" " + expression);
	
				if(line.startsWith("import ")){
					System.out.println(line);
					
					String importName = line.substring(line.indexOf(" ")).trim();
					
					importedList.add(importName);
				}else if(typeName == null){ //if typeName is not set, find the expression's type first.
					if(!line.trim().startsWith(expression) && whereExp > 0){
			
						line = line.substring(0, whereExp).trim();
						
						int k=line.length()-1;
						for(; k>=0; k--){
							char charAt = line.charAt(k);
							if(!((charAt > 'A' && charAt <'z') || (charAt > '1' && charAt <'9') || charAt == '.'))
								break;
						}
						
						typeName = line.substring(k + 1).trim();
						
						if(typeName.equals("return"))
							typeName = null; //ignores 'return' is recognized as typeName							
						if(typeName!=null && typeName.equals("=")) 
							typeName = null; //ignores '=' is recognized as typeName
					}
				}else{ //if typeName found, search the import statement.
					if(typeName!=null && typeName.indexOf('.') > -1){
						if(tryLoadList.indexOf(typeName) == -1){	
							tryLoadList.add(typeName);
							
							try {
								Thread.currentThread().getContextClassLoader().loadClass(typeName);
								return typeName;
							}catch(Throwable ex) {}
						}
					}else{
						String javaLangExp = "java.lang." + typeName;
						
						if(tryLoadList.indexOf(javaLangExp) == -1){
							tryLoadList.add(javaLangExp);							
							try{							
								Thread.currentThread().getContextClassLoader().loadClass(javaLangExp);
								return javaLangExp;
							}catch(Exception e){}
						}
						
						for(int k=0; k<importedList.size(); k++){
							String importedName = importedList.get(k);
							boolean checkImport = false;
							
							if(importedName.endsWith(".*") || importedName.endsWith("." + typeName)){
								String loadClass = importedName.substring(0, importedName.lastIndexOf(".")+1) + typeName;
								
								if(tryLoadList.indexOf(loadClass) == -1){									
									tryLoadList.add(loadClass);
									
									System.out.println("loadClass" + loadClass);
									try {
										Thread.currentThread().getContextClassLoader().loadClass(loadClass);
										
										System.out.println("loadedClass : " + loadClass);
										
										return loadClass;
									}catch(Throwable ex) {}
								}																
							}
						}
					}
					
					System.out.println("typeName : " + typeName);
					
					typeName = null;
				}
			}
			
			
		}		
		
		
		
		return null;
	}

	public ArrayList<String> findPackageStartWith(String expression){
		ArrayList<String> pkgNames = new ArrayList<String>();
		
		for(String packageName : packageNames.keySet()){
			if(packageName.toLowerCase().startsWith(expression))
				pkgNames.add(packageName);    				
		}
		
		return pkgNames;
	}
	
	public ArrayList<String> findClassesStartWith(String expression){
		ArrayList<String> clsNames = new ArrayList<String>();
		
		for(int i=0; i<classNames.size(); i++){
			String clsName = classNames.get(i);
			
			if(clsName.toLowerCase().startsWith(expression)){
				if(clsName.indexOf("//") == -1){
					String[] temp = clsName.split("/");				
					String confirmClsName = temp[0];
					if(temp.length > 1)
						confirmClsName = temp[1] + "." + confirmClsName;
					
					
					if(confirmAnnotation(confirmClsName))
						clsName = clsName + "/annotation//";
					else
						clsName = clsName + "/class//";
					
					classNames.set(i, clsName);
				}
				
				clsNames.add(clsName);    				
			}

		}

		return clsNames;
	}

	public ArrayList<String> findThisClass(){
		ArrayList<String> classInfo = new ArrayList<String>();
		
		ArrayList<ClassField> classFields = classModeler.getClassFields();
		if(classFields!=null) {
			for (ClassField field : classFields) {
				classInfo.add(field.getId() + "/" + "this" + "/field/");
			}
		}

		ArrayList<ClassMethod> classMethods = classModeler.getClassMethods();
		if(classMethods!=null) {
			for (ClassMethod classMethod : classMethods) {
				classInfo.add(classMethod.getMethodName() + "/" + "this" + "/method/");
			}
		}
		
		return classInfo;
	}	
	
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist() {
		System.out.println("requestAssist");		
		
		
		CodeAssist codeAssist = new JavaCodeAssist();
		codeAssist.setSrcCodeObjectId(getClientObjectId());
		
		
		String command = getLineAssistRequested(); 
		String options = "";		
		
		boolean isImport = false;
		boolean isAnnotation = false;
		
		String endChar = "";
		
		// get options
		if(command.startsWith("-")) {
			int pos = command.indexOf(' ');
			if( pos != -1){
				options = command.substring(1, pos);
			}
		}
		System.out.println(command);
		
		// get import
		if(command.startsWith("import ")) {
			isImport = true;
			endChar = ".*";
			
			command = command.substring(7).trim();
		}else if(command.startsWith("@")){			
			isAnnotation = true;
			
			command = command.substring(1).trim();
		}
		System.out.println(command);

		
		ArrayList<String> pkgNames = new ArrayList<String>();
		ArrayList<String> clsNames = new ArrayList<String>();
		
		if(annotationNames.size() == 0)
			cacheAnnotationNames();
		if(packageNames.size() == 0)
			cacheLibrary();
		
		// step 1 : isAnnotation, check options
		if(isAnnotation){				
			
			String expression = command.toLowerCase();
			
			for(int i = 0; i < annotationNames.size(); ++i) {
				if(expression.length() == 0 || annotationNames.get(i).toLowerCase().startsWith(expression))
					codeAssist.getAssistances().add(annotationNames.get(i));
			}
			
		}else if(options.isEmpty()){
			
			// step 2 : check endsWith(".")
			if(command.endsWith(".")){
				String expression = command.substring(0, command.length() - 1);
				
				System.out.println("expression : " + expression);
				System.out.println("isImport : " + isImport);
				
				// step 3 : check import
				if(isImport){
					ArrayList<String> packages = findPackage(command, endChar);
					for(int i=0; i<packages.size();i++)
						pkgNames.add(packages.get(i));
					
					ArrayList<String> classes = findPackageClass(expression);
					for(int i=0; i<classes.size();i++)
						clsNames.add(classes.get(i));
					
					
				}else{
					// step 3-1 : this
					if("this".equals(expression)){
						ArrayList<String> thisClassInfo = findThisClass();
						for(int i=0; i<thisClassInfo.size();i++)
							clsNames.add(thisClassInfo.get(i));
					}else{
					
						String classDefine = findClassDefine(expression);
						System.out.println("findClassDefine : " + classDefine);
						
						if(classDefine != null){
							ArrayList<String> classInfo = findClass(classDefine);
							for(int i=0; i<classInfo.size();i++)
								clsNames.add(classInfo.get(i));
						}else{
							ArrayList<String> packageClasses = findPackageClass(expression);
							for(int i=0; i<packageClasses.size();i++)
								clsNames.add(packageClasses.get(i));
							
							ArrayList<String> classes = findClasses(expression);
							for(int i=0; i<classes.size();i++)
								clsNames.add(classes.get(i));
						
							ArrayList<String> packages = findPackage(expression, endChar);
							for(int i=0; i<packages.size(); i++)
								pkgNames.add(packages.get(i));
						}
					}
				}
			}else{
				if(command.isEmpty()){
					ArrayList<String> thisClassInfo = findThisClass();
					for(int i=0; i<thisClassInfo.size();i++)
						clsNames.add(thisClassInfo.get(i));					
				}else{
					String expression = command.toLowerCase();
					
					ArrayList<String> packages = findPackageStartWith(expression);
					for(int i=0; i<packages.size(); i++)
						pkgNames.add(packages.get(i));
					
					ArrayList<String> classes = findClassesStartWith(expression);
					for(int i=0; i<classes.size();i++)
						clsNames.add(classes.get(i));
				}
			}
		}
		
		Collections.sort(clsNames);		
		Collections.sort(pkgNames);
		
		// make assistances class
		for(int i=0; i<clsNames.size(); i++){
			codeAssist.getAssistances().add(clsNames.get(i));
		}
			
		// make assistances package
		for(int i=0; i<pkgNames.size(); i++)
			codeAssist.getAssistances().add(pkgNames.get(i));
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		CodeAssist codeAssist = new JavaCodeAssist();
		codeAssist.getMetaworksContext().setHow("normal");
		codeAssist.setSrcCodeObjectId(getClientObjectId());
		

		CodiClassLoader ccl = ((CodiClassLoader)Thread.currentThread().getContextClassLoader());
		Set<Kind> kinds = new HashSet<Kind>();
		kinds.add(Kind.CLASS);
		kinds.add(Kind.SOURCE);
		
		String command = getLineAssistRequested(); 
		
		String expression = command.toLowerCase();
		String options = "";
		
		boolean annotationMode = false;
		boolean importMode = false;
		
		System.out.println("command : " + command);
		// get options
		if(command.startsWith("-")) {
			int pos = command.indexOf(' ');
			if( pos != -1){
				options = command.substring(1, pos);
			}
			
			command = command.substring(pos + 1).trim();
			expression = command.toLowerCase();
		}		
		System.out.println("options : " + options);
		
		
		// mode confirm
		if(expression.startsWith("import")){
			importMode = true;
			
			expression = expression.substring("import".length()).trim();
			
			//codeAssist.getMetaworksContext().setHow("import");
		}else if(expression.startsWith("@")){
			annotationMode = true;
			
			expression = expression.substring("@".length()).trim();
		}
		System.out.println("expression : " + expression);
			
		if(annotationMode){
			// make annotation cache
			//if(annotationNames != null && annotationNames.size() == 0)
				cacheAnnotationNames();
			
			for(int i = 0; i < annotationNames.size(); ++i) {
				if(expression.length() == 0 || annotationNames.get(i).toLowerCase().startsWith(expression))
					codeAssist.getAssistances().add(annotationNames.get(i));
			}
		}else{
			// expression is empty
			if(expression.length() == 0){
				return codeAssist;
			}
			
			ArrayList<String> pkgNames = new ArrayList<String>();
			ArrayList<String> clsNames = new ArrayList<String>();
			
			String expressionValue = expression;
			
			if(expression.endsWith(".")){
				expressionValue = expressionValue.substring(0, expressionValue.length()-1);
			}
			
			// make package cache
			if(packageNames != null)
				cacheLibrary();			

			System.out.println("packageNames.size : " + packageNames.size());
			System.out.println("classNames.size : " + classNames.size());
			
			if("oi".equals(options) == false){
	    		for(String packageName : packageNames.keySet()){
	    			if(packageName.startsWith(expression))
	    				pkgNames.add(packageName);    				
				}    		
	    		Collections.sort(pkgNames);
	    		System.out.println("pkgNames.size : " + pkgNames.size());

	    		// find class in package
	    		if(packageNames.containsKey(expressionValue)){
	    			Map<String, String> result = packageNames.get(expressionValue);
	    			
	    			for(String clsName : result.keySet())   {
	    				boolean annotation = false;
	    				
	    				if(result.get(clsName).isEmpty()){
	    					if(confirmAnnotation(clsName))
	    						result.put(clsName, "annotation");
	    					else
	    						result.put(clsName, "class");
	    				}
	    				
	    				clsNames.add(clsName + "," + expressionValue + "," + result.get(clsName)) ;
	    			}    					    			
	    		}
	    		System.out.println("clsNames.size 1: " + clsNames.size());	  
	    		
	    		try {
	        		Iterable<JavaFileObject> javaObjects;
	    			
	    			FileInputJavaFileManager fileManager = (FileInputJavaFileManager) ccl.getJavaFileManager();

	    			javaObjects = fileManager.list(StandardLocation.SOURCE_PATH, expression, kinds, true);
	    			for(JavaFileObject javaObject : javaObjects){
	    				String clsName = fileManager.getBinaryName(javaObject);
	    	
	    				if(clsName.startsWith(expression)){
	    					clsName = clsName.substring(expression.length() + 1, clsName.length());
	    				}
	    				
	    				System.out.println("clsName : " + clsName);
	    				//codeAssist.getAssistances().add(clsName);
	    			}
	    		}catch(Exception ex) {}	    
	    		
	    		if(classModeler != null){
					if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("fields")){
						ArrayList<ClassField> classFields = classModeler.getClassFields();
						if(classFields!=null) {
							for (ClassField field : classFields) {
								//codeAssist.getAssistances().add(field.getId());
								System.out.println("field.getId() : " +field.getId());
							}
						}
					}
	
					if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("methods")){
						ArrayList<ClassMethod> classMethods = classModeler.getClassMethods();
						if(classMethods!=null) {
							for (ClassMethod classMethod : classMethods) {
								//codeAssist.getAssistances().add(classMethod.getMethodName());
								System.out.println("classMethod.getMethodName() : " +classMethod.getMethodName());
							}
						}
					}
	    		}
			}
			
    		
    		// find class
			if(!command.endsWith(".")){
				
				
	    		for(int i=0; i<classNames.size(); i++){
	    			boolean isAddClass = false;
	    			String clsName = classNames.get(i);
	    			
	    			if("oi".equals(options)){
	        			if(clsName.startsWith(command + ",")){
	        				isAddClass = true;
	        			}
	    				
	    			}else{    				
	        			if(clsName.toLowerCase().startsWith(expression)){
	        				isAddClass = true;
	        			}
	    			}
	    			
	    			if(isAddClass){
	    				if(clsName.indexOf(",,") == -1){
	    					String confirmClsName = clsName;
	    					
	    					int pos = clsName.indexOf(",");
	    					if(pos != -1)
	    						confirmClsName = clsName.substring(pos, clsName.length()) + "." + clsName.substring(0, pos - 1);
	    					
	    					
	    					if(confirmAnnotation(confirmClsName))
	    						clsName = clsName + ",annotation,,";
	    					else
	    						clsName = clsName + ",class,,";
	    					classNames.set(i, clsName);
	    				}
	    				
	    				clsNames.add(clsName);    				
	    			}
	
	    		}
			}
			
    		// sort class
    		Collections.sort(clsNames);
    		System.out.println("clsNames.size 2: " + clsNames.size());
    		
    		
    		// make assistances class
    		for(int i=0; i<clsNames.size(); i++){
    			codeAssist.getAssistances().add(clsNames.get(i));
    		}
    			
    		// make assistances package
    		for(int i=0; i<pkgNames.size(); i++)
    			codeAssist.getAssistances().add(pkgNames.get(i));
    		
    		System.out.println("complete");
		}
		*/
		
		/*
		if(expression.length() > 0){
			
			String typeName = null;
			String fullTypeName = null;
			String[] lines = getCode().split("\n");
			
			int defineRow = -1;
			boolean checkImport = false;
		
			//if(getCursorPosition().getRow() > 0)
			if(lines.length > 0) {
				//for(int i = getCursorPosition().getRow(); i >= 0 && fullTypeName==null; i--){
				for(int i = lines.length-1; i >= 0 && fullTypeName==null; i--){
					String line = lines[i];					
					int whereExp = defineRow = line.indexOf(" " + expression);									
					System.out.println("whereExp = " + whereExp);	
					if(typeName == null){ //if typeName is not set, find the expression's type first.
						if(!line.trim().startsWith(expression) && whereExp > 0){				
							line = line.substring(0, whereExp).trim();							
							int j=line.length()-1;
							for(; j>=0; j--){
								char charAt = line.charAt(j);
								if(!((charAt > 'A' && charAt <'z') || (charAt > '1' && charAt <'9') || charAt == '.'))
									break;
							}							
							if(j < 0) j = 0;							
							typeName = line.substring(j).trim();
							
							if(typeName.equals("import")) {
								if(packageNames != null && !packageNames.containsKey(expression)) cachePackageNames(codeAssist, expression);
								
								Map<String, HashMap> packagesNamePerPrefix = packageNames.get(expression);
					    		for(String packageName : packagesNamePerPrefix.keySet()){
					    			codeAssist.getAssistances().add(packageName);
								}			    		
					    		return codeAssist;
							}
													
							if(typeName.equals("return"))
								typeName = null; //ignores 'return' is recognized as typeName							
							if(typeName!=null && typeName.equals("=")) 
								typeName = null; //ignores '=' is recognized as typeName							
							//if(typeName!=null && typeName.startsWith(".")) 
							//	typeName = typeName.substring(1);							
							if(typeName!=null && typeName.indexOf('.') > -1){
								fullTypeName = typeName;
							}
						}
					} else if(typeName.equals("import")) {
						if(packageNames != null && !packageNames.containsKey(expression)) cachePackageNames(codeAssist, expression);
						
						Map<String, HashMap> packagesNamePerPrefix = packageNames.get(expression);
			    		for(String packageName : packagesNamePerPrefix.keySet()){
			    			codeAssist.getAssistances().add(packageName);
						}			    		
			    		return codeAssist;
					
					} else { //if typeName found, search the import statement.
						line = line.trim();
						System.out.println("line = " + line);
						System.out.println("typeName = " + typeName);
						
						if((line.startsWith("import ") && line.endsWith(".*;")) || (line.startsWith("import ") && line.endsWith("." + typeName + ";"))) {
							if(line.endsWith(".*;")) {
								String searchClass = line.substring(line.indexOf(' '), line.length()-2).trim() + typeName;
								try {
									Thread.currentThread().getContextClassLoader().loadClass(searchClass);
									fullTypeName = searchClass;
									checkImport = true;
								}catch(Exception ex) {}
							}
							else {
								fullTypeName = line.substring(line.indexOf(' '), line.length()-1).trim();
								checkImport = true;
							}						
						} 						
					}
				}
				
				//import 기능 수행
				if((fullTypeName == null || fullTypeName.length() == 0) && !checkImport) {
					System.out.println(typeName);
					
					if(importNames != null && !importNames.containsKey(typeName)) cacheImportNames(typeName);						
				
					ArrayList<String> preImport = importNames.get(typeName);
		    		for(int j = 0; j < preImport.size(); ++j){
		    			codeAssist.getAssistances().add(preImport.get(j));
					}
		    		
					return codeAssist;
				}
				
				if(typeName != null) {
					try{
						String javaLangExp = "java.lang." + typeName;
						Class.forName(javaLangExp);
						fullTypeName = javaLangExp;
					}catch(Exception e){
						
					}
				}				
				
				//TODO:
				//if there should be use of asterisk in the import statement. etc. import com.abc.*,
				// we need to try to append the typeName where the asterisk and load the class, if the class successfully loaded, 
				// we can get the class is right one. (sometimes, ambiguity issue can be arise)
				
				System.out.println("fullTypeName = " + fullTypeName);
				
				if(fullTypeName != null) {
					try{
						Class theClass = Thread.currentThread().getContextClassLoader().loadClass(fullTypeName);					
						for(Field field : theClass.getFields()) {
							codeAssist.getAssistances().add(field.getName());						
						}
					
						for(Method method : theClass.getMethods()) {						
							StringBuffer paramStmt = new StringBuffer("(");
							int index = 0;
							String sep = "";
							for(Class paramCls : method.getParameterTypes()){
								String clsNameOnly = UEngineUtil.getClassNameOnly(paramCls);
								clsNameOnly = sep + clsNameOnly.substring(0, 1).toLowerCase() + clsNameOnly.substring(1, clsNameOnly.length());							
								paramStmt.append(clsNameOnly);
								sep = ", ";
							}
							paramStmt.append(")");						
							codeAssist.getAssistances().add(method.getName() + paramStmt);
						
						}					
						return codeAssist;					
					}catch(Exception e) {
						e.printStackTrace();
					}
				} 
			}
			
			
			try {
	    		Iterable<JavaFileObject> javaObjects;
				
				FileInputJavaFileManager fileManager = (FileInputJavaFileManager) ccl.getJavaFileManager();
	
				javaObjects = fileManager.list(StandardLocation.SOURCE_PATH, expression, kinds, true);
				for(JavaFileObject javaObject : javaObjects){
					String clsName = fileManager.getBinaryName(javaObject);
		
					if(clsName.startsWith(expression)){
						clsName = clsName.substring(expression.length() + 1, clsName.length());
					}
					
					codeAssist.getAssistances().add(clsName);
				}
    		}catch(Exception ex) {}
			
			if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("fields")){
				ArrayList<ClassField> classFields = classModeler.getClassFields();
				if(classFields!=null) {
					for (ClassField field : classFields) {
						codeAssist.getAssistances().add(field.getId());
					}
				}
			}

			if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("methods")){
				ArrayList<ClassMethod> classMethods = classModeler.getClassMethods();
				if(classMethods!=null) {
					for (ClassMethod classMethod : classMethods) {
						codeAssist.getAssistances().add(classMethod.getMethodName());
					}
				}
			}
			
		}	
		*/	
		
		return codeAssist;
	}

	@AutowiredFromClient
	public ClassModeler classModeler;
}
