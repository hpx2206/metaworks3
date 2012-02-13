package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.jdk.SimpleCompiler;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;

public class SourceCodeWorkItem extends WorkItem{
	
	public SourceCodeWorkItem(){
		setType("src");
	}
	
	
	@Hidden(on=false)
	public SourceCode getSourceCode() {
		// TODO Auto-generated method stub
		return super.getSourceCode();
	}
	
	
	@ServiceMethod(callByContent=true)
	public void compile() throws ClassNotFoundException, CompileException, InstantiationException, IllegalAccessException{
		
		if(getSourceCode()==null) return;
		
	    SimpleCompiler compiler = new SimpleCompiler();
	    compiler.setParentClassLoader(this.getClass().getClassLoader());
	    
	    ArrayList<CompileError> compileErrors = new ArrayList<CompileError>();
	    
	    try {
			compiler.cook(getSourceCode().getCode());
		} catch (CompileException e) {
			
			String message = e.getMessage();
			String[] parts = null;
			int lineNumber = 0;
			
			try {
				lineNumber = Integer.parseInt((parts = message.split(":"))[2]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				parts = null;
				//e1.printStackTrace();
			}
			
			CompileError compileError = new CompileError();
			compileError.setLine(lineNumber);
			compileError.setColumn(1);
			compileError.setMessage(parts != null ? parts[3] : message);
			
			compileErrors.add(compileError);
		}
	    
		CompileError[] complieErrorInArray = new CompileError[compileErrors.size()];
		compileErrors.toArray(complieErrorInArray);
		
		getSourceCode().setCompileErrors(complieErrorInArray);	    

	}
}
