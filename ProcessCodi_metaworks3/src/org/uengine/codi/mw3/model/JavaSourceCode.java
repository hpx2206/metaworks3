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
import org.uengine.codi.mw3.admin.ClassModeler;
import org.uengine.util.UEngineUtil;



public class JavaSourceCode extends SourceCode {

	static Map<String, Map> packageNames = new HashMap<String, Map>(); //cached for more faster code assistance. may need to stored separately with tenants. (may naturally done because of classloader is different with web-contexts)

	
	public String getPackagePath(String expression, String[] path) {
		int index = 1;
		for(int i = 0; i < expression.length(); ++i)
			if(expression.charAt(i) == '.') index++;
		//System.out.println("index = " + index);
		
		StringBuffer pathStr = new StringBuffer();
		
		//if(path.length - index == 1) return path[path.length - 1] + ";";
		
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
		
		String expression;
		int pos = getLineAssistRequested().lastIndexOf(' ');
		if(pos != -1)
			expression = getLineAssistRequested().substring(getLineAssistRequested().lastIndexOf(' ') + 1);
		else
			expression = getLineAssistRequested();
		
		//System.out.println("expression = " + expression);
		
		String code = this.getCode();
		int lineCnt = 0;
		for(int i = 0; i < code.length(); ++i) {
			if((int)code.charAt(i) == 10) lineCnt++;			
		}
		//System.out.println("Line Count = " + lineCnt);
		
		if(expression.length() > 0){
					
			String typeName = null;
			String fullTypeName = null;
			String[] lines = getCode().split("\n");
			
			//if(getCursorPosition().getRow() > 0)
			if(lineCnt > 0)
			//for(int i = getCursorPosition().getRow(); i >= 0 && fullTypeName==null; i--){
			for(int i = lineCnt; i >= 0 && fullTypeName==null; i--){
				String line = lines[i];
				
				int whereExp = line.indexOf(" " + expression);

				if(typeName == null){ //if typeName is not set, find the expression's type first.
					if(!line.trim().startsWith(expression) && whereExp > 0){
			
						line = line.substring(0, whereExp).trim();
						
						int j=line.length()-1;
						for(; j>=0; j--){
							char charAt = line.charAt(j);
							if(!((charAt > 'A' && charAt <'z') || (charAt > '1' && charAt <'9')))
								break;
						}
						
						if(j < 0) j = 0;
						
						typeName = line.substring(j).trim();
						if(typeName.startsWith(".")) typeName = typeName.substring(1);
						
						if(typeName.equals("return"))
							typeName = null; //ignores 'return' is recognized as typeName
						
						if(typeName!=null && typeName.indexOf('.') > -1){
							fullTypeName = typeName;
						}
					}
				}else{ //if typeName found, search the import statement.
					line = line.trim();
					if((line.startsWith("import ") && line.endsWith(".*;")) || (line.startsWith("import ") && line.endsWith("." + typeName + ";"))) {
						if(line.endsWith(".*;")) {
							String searchClass = line.substring(line.indexOf(' '), line.length()-2).trim() + typeName;
							try {
								Thread.currentThread().getContextClassLoader().loadClass(searchClass);
								fullTypeName = searchClass;
							}catch(Exception ex) {}
						}
						else {
							fullTypeName = line.substring(line.indexOf(' '), line.length()-1).trim();
						}
						
					}					
					
				}
			}
			
			//TODO:
			//if there should be use of asterisk in the import statement. etc. import com.abc.*,
			// we need to try to append the typeName where the asterisk and load the class, if the class successfully loaded, 
			// we can get the class is right one. (sometimes, ambiguity issue can be arise)
			
			if(fullTypeName!=null)
			try{
				Class theClass = Thread.currentThread().getContextClassLoader().loadClass(fullTypeName);
				
				
				for(Field field : theClass.getFields()){
					codeAssist.getAssistances().add(field.getName());
					
				}
				
				for(Method method : theClass.getMethods()){
					
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
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			//////// case that importing package name has been requested.
			
			if(getLineAssistRequested().trim().startsWith("import "))
			try {
				
				
				//URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
				URLClassLoader classLoader = (URLClassLoader) org.metaworks.MetaworksContext.class.getClassLoader();
				URL urls[] = classLoader.getURLs();
				
				//StringBuffer sbClasspath = new StringBuffer();
				
		    	
				
				
				if(!packageNames.containsKey(expression)) {
					for(URL url : urls){
						
						if(url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip") ){
						
							net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
							net.sf.jazzlib.ZipEntry zipEntry;
		
						    try {
						    	
						    	while((zipEntry = zipIn.getNextEntry()) != null) {
						    		
						    		//if(expression.indexOf(".") > -1) expression = expression.replace('.', '/');
						    		
						    		if(zipEntry.getName().startsWith(expression.replace('.', '/'))){
										String clsName = zipEntry.getName();
										
										//expression = expression.replace('/', '.');
										
										if(clsName.endsWith(".class")){
											clsName = clsName.substring(0, clsName.length() - 6);
											String[] parts = clsName.split("/");
											if(parts.length > 1){
												if(!packageNames.containsKey(expression)) 
													packageNames.put(expression, new HashMap());
												if(!packageNames.get(expression).containsKey(getPackagePath(expression, parts))) {
													packageNames.get(expression).put(getPackagePath(expression, parts), getPackagePath(expression, parts));
													//System.out.println(getPackagePath(parts));
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

		ArrayList<ClassField> classFields = classModeler.getClassFields();
		if(classFields!=null)
		for (ClassField field : classFields) {
			codeAssist.getAssistances().add(field.getFieldName());
		}

		return codeAssist;
	}

	@AutowiredFromClient
	public ClassModeler classModeler;

}
