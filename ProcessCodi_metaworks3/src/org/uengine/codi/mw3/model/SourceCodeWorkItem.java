package org.uengine.codi.mw3.model;

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
	    //compiler.set
	    
	    try {
			compiler.cook(getSourceCode().getCode());
		} catch (CompileException e) {
			
			if(e.getMessage().indexOf("Line") > -1 && e.getMessage().indexOf("Column") > -1){

				int lineNumber = Integer.parseInt(e.getMessage().split("Line ")[1].split(",")[0]);
				
				String[] positionAndErrorMessagePart = e.getMessage().split("Column ")[1].split(":");
				int columnNumber = Integer.parseInt(positionAndErrorMessagePart[0]);
				
				CompileError compileError = new CompileError();
				compileError.setLine(lineNumber);
				compileError.setColumn(columnNumber);
				compileError.setMessage(positionAndErrorMessagePart[1]);
				
				getSourceCode().setCompileErrors(new CompileError[]{compileError});
					
			}
			
			return;
		}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			
//			
//
//			if(e.getMessage().indexOf("Line") > -1 && e.getMessage().indexOf("Column") > -1){
//
//				int lineNumber = Integer.parseInt(e.getMessage().split("Line ")[1].split(",")[0]);
//				
//				String[] positionAndErrorMessagePart = e.getMessage().split("Column ")[1].split(":");
//				int columnNumber = Integer.parseInt(positionAndErrorMessagePart[0]);
//				
//				CompileError compileError = new CompileError();
//				compileError.setLine(lineNumber);
//				compileError.setColumn(columnNumber);
//				compileError.setMessage(positionAndErrorMessagePart[1]);
//				
//				getSourceCode().setCompileErrors(new CompileError[]{compileError});
//					
//			}
//			
//			return;
//			
//		} catch (ScanException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


	
	
	
}
