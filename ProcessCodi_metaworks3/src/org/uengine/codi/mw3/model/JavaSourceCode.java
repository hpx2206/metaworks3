package org.uengine.codi.mw3.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.JavaFileObject.Kind;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;
import org.metaworks.example.ide.CodeAssister;
import org.metaworks.example.ide.SourceCode;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.CodiMetaworksRemoteService;
import org.uengine.codi.mw3.FileInputJavaFileManager;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.kernel.GlobalContext;
import org.uengine.util.UEngineUtil;
import org.uengine.util.export.DefinitionArchive;
import org.uengine.util.export.UEngineArchive;

public class JavaSourceCode extends SourceCode {

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
			
		if(expression.length() > 0){
					
			String typeName = null;
			String fullTypeName = null;
			String[] lines = getCode().split("\n");
			
			for(int i = getCursorPosition().getRow(); i >= 0 && fullTypeName==null; i--){
				String line = lines[i];
				
				int whereExp = line.indexOf(" " + expression);

				if(typeName == null){ //if typeName is not set, find the expression's type first.
					if(!line.trim().startsWith(expression) && whereExp > 0){
			
						line = line.trim().substring(0, whereExp - 2);
						
						int j = whereExp-3;
						for(; j>=0; j--){
							char charAt = line.charAt(j);
							if(!((charAt > 'A' && charAt <'z') || (charAt > '1' && charAt <'9')))
								break;
						}
						
						typeName = line.substring(j + 1);
						
						if(typeName.indexOf('.') > -1){
							fullTypeName = typeName;
						}
					}
				}else{ //if typeName found, search the import statement.
					line = line.trim();
					if(line.startsWith("import ") && line.endsWith("." + typeName + ";")){
						
						fullTypeName = line.substring(line.indexOf(' '));
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
					for(Class paramCls : method.getParameterTypes()){
						String clsNameOnly = UEngineUtil.getClassNameOnly(paramCls);
						clsNameOnly = clsNameOnly.substring(0, 1).toLowerCase() + clsNameOnly.substring(1, clsNameOnly.length());
						
						paramStmt.append(clsNameOnly);
					}
					paramStmt.append(")");
					
					codeAssist.getAssistances().add(method.getName() + paramStmt);
					
				}
				
				return codeAssist;
				
			}catch(Exception e){
				
			}
			
			if(getLineAssistRequested().trim().startsWith("import "))
			try {
				

				
				URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
				URL urls[] = classLoader.getURLs();
				StringBuffer sbClasspath = new StringBuffer();
				
				for(URL url : urls){
					net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
					net.sf.jazzlib.ZipEntry zipEntry;

				    try {
				    	while((zipEntry = zipIn.getNextEntry()) != null) {
				    		if(zipEntry.getName().startsWith(expression)){
								String clsName = zipEntry.getName();
								
								if(clsName.startsWith(expression) && clsName.endsWith(".class")){
									clsName = clsName.substring(expression.length() + 1, clsName.length() - 6);
								}
								
								codeAssist.getAssistances().add(clsName);
				    			
				    		}
				    	} // end while

				    } catch (Exception e) {
				    	e.printStackTrace();
				    } finally{
						try{zipIn.close();}catch(Exception ex){}   
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
	
				javaObjects = fileManager.list(StandardLocation.CLASS_PATH, expression, kinds, true);
				for(JavaFileObject javaObject : javaObjects){
					String clsName = javaObject.toUri().getPath().split("!")[1].replace('/', '.');
					
					if(clsName.startsWith(expression)){
						clsName = clsName.substring(expression.length() + 1, clsName.length() - 6);
					}
					
					codeAssist.getAssistances().add(clsName);
				}
				
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

		ArrayList<ClassField> classFields = classDefinition.getClassFields();
		if(classFields!=null)
		for (ClassField field : classFields) {
			codeAssist.getAssistances().add(field.getFieldName());
		}

		return codeAssist;
	}

	@AutowiredFromClient
	public ClassDefinition classDefinition;

}
