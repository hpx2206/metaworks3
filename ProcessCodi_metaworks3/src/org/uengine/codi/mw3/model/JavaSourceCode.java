package org.uengine.codi.mw3.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.JavaFileObject.Kind;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;
import org.metaworks.example.ide.SourceCode;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.CodiMetaworksRemoteService;
import org.uengine.codi.mw3.FileInputJavaFileManager;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.mw3.admin.ClassMethod;
import org.uengine.codi.mw3.admin.ClassModeler;
import org.uengine.util.UEngineUtil;



public class JavaSourceCode extends SourceCode {

	static Map<String, Map> packageNames = new HashMap<String, Map>(); //cached for more faster code assistance. may need to stored separately with tenants. (may naturally done because of classloader is different with web-contexts)
	static Map<String, ArrayList> importNames = new HashMap<String, ArrayList>();
	static ArrayList<String> annotationNames = new ArrayList<String>();

	public void cachePackageNames(CodeAssist codeAssist, String expression) {
		try {			
			//URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
			URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
			URL urls[] = classLoader.getURLs();
			for(URL url : urls){				
				if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){				
					net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
					net.sf.jazzlib.ZipEntry zipEntry;	
				    try {				    	
				    	while((zipEntry = zipIn.getNextEntry()) != null) {
				    		if(zipEntry.getName().startsWith(expression.replace('.', '/'))){
								String clsName = zipEntry.getName();
								
								if(clsName.endsWith(".class")){
									clsName = clsName.substring(0, clsName.length() - 6);
									String[] parts = clsName.split("/");
									if(parts.length > 1){
										
										StringBuffer packageName = new StringBuffer(parts[0]);
										for(int i=1; i<parts.length; i++){
											if(!packageNames.containsKey(packageName.toString())) 
												packageNames.put(packageName.toString(), new HashMap());
	
											Map <String, String> packageTokens = packageNames.get(packageName.toString());
	
											packageTokens.put(parts[i], parts[i]);
	
											packageName.append(".");
											packageName.append(parts[i]);
											
										}
									}
								}
								
				    		}
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
	
	public void cacheImportNames(String typeName) {
		try {			
			//URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
			URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
			URL urls[] = classLoader.getURLs();
			for(URL url : urls){				
				if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){				
					net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
					net.sf.jazzlib.ZipEntry zipEntry;	
				    try {				    	
				    	while((zipEntry = zipIn.getNextEntry()) != null) {
				    		if(zipEntry.getName().endsWith(typeName.replace('.', '/') + ".class")){
								String clsName = zipEntry.getName();
								
								if(clsName.endsWith(".class")){
									clsName = clsName.substring(0, clsName.length() - 6);
									String[] parts = clsName.split("/");
									StringBuffer buffer = new StringBuffer(parts[0]);
									if(parts.length > 0){
										for(int i = 1; i < parts.length; ++i) {
											buffer.append(".");
											buffer.append(parts[i]);
										}
									}
									
									String clsName2 = typeName.substring(typeName.lastIndexOf(".") + 1);
									if(!importNames.containsKey(clsName2)) { 
										importNames.put(clsName2, new ArrayList<String>());
									}
									ArrayList<String> array = importNames.get(clsName2);
									array.add(buffer.toString());
									importNames.get(clsName2);
								}								
				    		}
				    	} // end while			    	
				    } catch (Exception e) {
				    	new Exception(url.getFile() + ": error when to parse url ", e).printStackTrace();
				    } finally{
						try{zipIn.close();}catch(Exception ex){}   
				    }
				}
			}		
			
			try {
	    		String clsName2 = typeName.substring(typeName.lastIndexOf(".") + 1);
	    		String javaLangExp = "java.lang." + clsName2;
	    		Class.forName(javaLangExp);
	    		if(!importNames.containsKey(clsName2)) { 
					importNames.put(clsName2, new ArrayList<String>());
				}
				ArrayList<String> array = importNames.get(clsName2);
				array.add(javaLangExp.toString());
				importNames.get(clsName2);
	    	}catch(Exception ee) {}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cacheAnnotationNames() {
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
							    	annotationNames.add("@" + clsName.substring(clsName.lastIndexOf(".") + 1) + " " + clsName.substring(0, clsName.lastIndexOf(".")));
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
		sortAnnotation();
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
	
	
	@ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist() {

		CodeAssist codeAssist = super.requestAssist();
		
		CodiClassLoader ccl = ((CodiClassLoader)Thread.currentThread().getContextClassLoader());
		Set<Kind> kinds = new HashSet<Kind>();
		kinds.add(Kind.CLASS);
		kinds.add(Kind.SOURCE);
		
		String lineAssistRequested = getLineAssistRequested(); 
		String expression = lineAssistRequested;
		String keyOptions = "";
		if(expression.startsWith("-")) {
			keyOptions = expression.substring(1, 3);
			lineAssistRequested = expression = expression.substring(4).trim();
		}		
		
		int pos = expression.lastIndexOf(' ');
		if(pos != -1)
			expression = expression.substring(expression.lastIndexOf(' ') + 1);
		//else
		//	expression = expression;
		
		System.out.println("expression = " + expression);
		
		//Annotation
		if(expression != null && expression.equals("@")) {
			if(annotationNames != null && annotationNames.size() == 0) cacheAnnotationNames();
			for(int i = 0; i < annotationNames.size(); ++i) 
				codeAssist.getAssistances().add(annotationNames.get(i));
			System.out.println("annotation list count : " + codeAssist.getAssistances().size());
			
			return codeAssist;
		}
		
		
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
//				else if(typeName != null){
//					//Cache PackageNames
//					if(packageNames != null && !packageNames.containsKey(typeName)) cachePackageNames(codeAssist, typeName);
//					
//					Map<String, HashMap> packagesNamePerPrefix = packageNames.get(typeName);
//		    		for(String packageName : packagesNamePerPrefix.keySet()){
//		    			codeAssist.getAssistances().add(packageName);
//					}
//					
//				}
			
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
						codeAssist.getAssistances().add(field.getFieldName());
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
			
			
			
		/*			
					
				//////// case that importing package name has been requested.			
				if(lineAssistRequested.trim().startsWith("import ")) {
					try {
						//Cache PackageNames
						if(packageNames.containsKey(expression)) cachePackageNames(codeAssist, expression);
						
						
							
							
							
							
							
							
							//URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
							URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
							URL urls[] = classLoader.getURLs();
						
							//StringBuffer sbClasspath = new StringBuffer();			
							//if(packageNames.size() == 0){//{.containsKey(expression)) {
							if(!packageNames.containsKey(expression)) {
								if(fullTypeName == null && typeName == null) {
									String clsName = "java.lang." + expression;
									try {
										Class.forName(clsName);
										//System.out.println(clsName.replace(".", "/") + ".class");
										if(keyOptions != null && keyOptions.length() > 0) {
											codeAssist.getAssistances().add(clsName + "-" + keyOptions);
										}
										else {
											codeAssist.getAssistances().add(clsName);
										}
									} catch(ClassNotFoundException ex) {}
								}
							
								for(URL url : urls){
									if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){
								
										net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
										net.sf.jazzlib.ZipEntry zipEntry;
				
										try {
								    	
											while((zipEntry = zipIn.getNextEntry()) != null) {
								    		
												//if(expression.indexOf(".") > -1) expression = expression.replace('.', '/');
								    		
												if((fullTypeName == null && typeName == null) && zipEntry.getName().indexOf("/" + expression + ".class") > -1) {
									    			//System.out.println(zipEntry.getName());
									    			String clsName = zipEntry.getName().replace("/", ".");
									    			if(keyOptions != null && keyOptions.length() > 0) {
									    				codeAssist.getAssistances().add(clsName.substring(0, clsName.lastIndexOf(".")) + "-" + keyOptions);
									    			}
									    			else {
									    				codeAssist.getAssistances().add(clsName.substring(0, clsName.lastIndexOf(".")));
									    			}
									    			continue;
									    		}
								    		
									    		if(zipEntry.getName().startsWith(expression.replace('.', '/'))){
													String clsName = zipEntry.getName();
													
													//expression = expression.replace('/', '.');
													
													if(clsName.endsWith(".class")){
														clsName = clsName.substring(0, clsName.length() - 6);
														String[] parts = clsName.split("/");
														if(parts.length > 1){
															
															StringBuffer packageName = new StringBuffer(parts[0]);
															for(int i=1; i<parts.length; i++){
																if(!packageNames.containsKey(packageName.toString())) 
																	packageNames.put(packageName.toString(), new HashMap());
		
																Map <String, String> packageTokens = packageNames.get(packageName.toString());
			
																packageTokens.put(parts[i], parts[i]);
			
																packageName.append(".");
																packageName.append(parts[i]);
															
															}
														
													
														}
													}
												
									    		}
											} // end while
				
									    } catch (Exception e) {
									    	new Exception(url.getFile() + ": error when to parse url ", e).printStackTrace();
									    } finally{
											try{zipIn.close();}catch(Exception ex){}   
									    }
									}
								}
							}
					
					
					
					
					
							if(fullTypeName == null && typeName == null && codeAssist.getAssistances().size() != 0) return codeAssist;
				
				
					    	if(packageNames.containsKey(expression)){
					    		Map<String, HashMap> packagesNamePerPrefix = packageNames.get(expression);
					    		for(String packageName : packagesNamePerPrefix.keySet()){
					    			codeAssist.getAssistances().add(packageName);
								}
							}
				
				
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
				
	
			//				javaObjects = fileManager.list(StandardLocation.CLASS_PATH, expression, kinds, true);
			//				for(JavaFileObject javaObject : javaObjects){
			//					String clsName = javaObject.toUri().getPath().split("!")[1].replace('/', '.');
			//					
			//					if(clsName.startsWith(expression)){
			//						clsName = clsName.substring(expression.length() + 1, clsName.length() - 6);
			//					}
			//					
			//					codeAssist.getAssistances().add(clsName);
			//				}
							
				//			javaObjects = ccl.getJavaFileManager().list(StandardLocation.CLASS_OUTPUT, getLineAssistRequested(), kinds, true);
				//			for(JavaFileObject javaObject : javaObjects){
				//				codeAssist.getAssistances().add(javaObject.getName());
				//			}
				//			javaObjects = ccl.getJavaFileManager().list(StandardLocation.ANNOTATION_PROCESSOR_PATH, getLineAssistRequested(), kinds, true);
				//			for(JavaFileObject javaObject : javaObjects){
				//				codeAssist.getAssistances().add(javaObject.getName());
				//			}
				//			javaObjects = ccl.getJavaFileManager().list(StandardLocation.SOURCE_OUTPUT, getLineAssistRequested(), kinds, true);
				//			for(JavaFileObject javaObject : javaObjects){
				//				codeAssist.getAssistances().add(javaObject.getName());
				//			}
				//			javaObjects = ccl.getJavaFileManager().list(StandardLocation.PLATFORM_CLASS_PATH, getLineAssistRequested(), kinds, true);
				//			for(JavaFileObject javaObject : javaObjects){
				//				codeAssist.getAssistances().add(javaObject.getName());
				//			}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("fields")){
						ArrayList<ClassField> classFields = classModeler.getClassFields();
						if(classFields!=null)
						for (ClassField field : classFields) {
							codeAssist.getAssistances().add(field.getFieldName());
						}
					}

					if("this".equals(expression) || expression.trim().length()==0 || expression.endsWith("methods")){
						ArrayList<ClassMethod> classMethods = classModeler.getClassMethods();
						if(classMethods!=null)
						for (ClassMethod classMethod : classMethods) {
							codeAssist.getAssistances().add(classMethod.getMethodName());
						}
					}
				*/
		
		return codeAssist;
	}

	@AutowiredFromClient
	public ClassModeler classModeler;
	
	
	
	
	
	
	

}
