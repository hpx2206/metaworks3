package org.metaworks.example.ide;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public class JavaActivity extends Activity{
	
//	String javaCode;
//	@Face(ejsPath="genericfaces/SourceEditor.ejs", displayName="Java Code")
//
//		public String getJavaCode() {
//			return javaCode;
//		}
//	
//		public void setJavaCode(String javaCode) {
//			this.javaCode = javaCode;
//		}

	SourceCode javaCode;
		public SourceCode getJavaCode() {
			return javaCode;
		}
		public void setJavaCode(SourceCode javaCode) {
			this.javaCode = javaCode;
		}

	@ServiceMethod(callByContent=true)
	public void compile() throws ClassNotFoundException, CompileException, InstantiationException, IllegalAccessException{
		
		if(getJavaCode()==null) return;
		
	    SimpleCompiler compiler = new SimpleCompiler();
	    compiler.setParentClassLoader(this.getClass().getClassLoader());
	    //compiler.set
	    
	    try {
			compiler.cook(getJavaCode().getCode());
		} catch (CompileException e) {
			//setCompilationExceptionMessage(e.getMessage());
			
			if(e.getMessage().indexOf("Line") > -1 && e.getMessage().indexOf("Column") > -1){

//				int lineNumber = Integer.parseInt(e.getMessage().split("Line ")[1].split(",")[0]);
//				
				String[] positionAndErrorMessagePart = e.getMessage().split("Column ")[1].split(":");
//				int columnNumber = Integer.parseInt(positionAndErrorMessagePart[0]);
				
				int lineNumber = e.getLocation().getLineNumber();
				int columnNumber = e.getLocation().getColumnNumber();
				
				CompileError compileError = new CompileError();
				compileError.setLine(lineNumber);
				compileError.setColumn(columnNumber);
				compileError.setMessage(positionAndErrorMessagePart[1]);
				
				getJavaCode().setCompileErrors(new CompileError[]{compileError});
					
			}
			
			return;
		}
	    ClassLoader loader = compiler.getClassLoader();
	    //loader.
	    Class compClass = loader.loadClass("Test");
	    Object instance = compClass.newInstance();
	    System.out.println(instance);
	}
	
}