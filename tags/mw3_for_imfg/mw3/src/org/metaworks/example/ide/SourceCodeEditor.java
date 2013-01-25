package org.metaworks.example.ide;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public class SourceCodeEditor {

	String code;
	@Face(ejsPath="genericfaces/SourceEditor.ejs")
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

		
	String compilationExceptionMessage;
		@NonEditable
		public String getCompilationExceptionMessage() {
			return compilationExceptionMessage;
		}
		public void setCompilationExceptionMessage(String compilationExceptionMessage) {
			this.compilationExceptionMessage = compilationExceptionMessage;
		}

	@ServiceMethod(callByContent=true)
	public void compile() throws ClassNotFoundException, CompileException, InstantiationException, IllegalAccessException{
	    SimpleCompiler compiler = new SimpleCompiler();
	    compiler.setParentClassLoader(this.getClass().getClassLoader());
	    try {
			compiler.cook(getCode());
		} catch (CompileException e) {
			setCompilationExceptionMessage(e.getMessage());
			return;
		}
	    ClassLoader loader = compiler.getClassLoader();
	    //loader.
	    Class compClass = loader.loadClass("Test");
	    Object instance = compClass.newInstance();
	    System.out.println(instance);
	}

}
