package org.metaworks.website;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;

public class SourceCodeContents extends Contents{
	
	public SourceCodeContents(){
		setType("src");
	}

	@Override
	@Hidden(on=false) //overrides the annotation
	public SourceCode getSourceCode(){
		return super.getSourceCode();
	}
	
	
	
//	@Face(
//			ejsPath="genericfaces/SourceEditor.ejs",
//			options={"width"},
//			values={"670"}
//			
//			)
//	public String getParagraph() {
//		// TODO Auto-generated method stub
//		return super.getParagraph();
//	}

	@ServiceMethod(callByContent=true, target=TARGET_SELF)
	public void compile() throws ClassNotFoundException, CompileException, InstantiationException, IllegalAccessException{
		
		if(getSourceCode()==null) return;
		
	    SimpleCompiler compiler = new SimpleCompiler();
	    compiler.setParentClassLoader(this.getClass().getClassLoader());
	    //compiler.set
	    
	    try {
			compiler.cook(getSourceCode().getCode());
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
				
				getSourceCode().setCompileErrors(new CompileError[]{compileError});
					
			}
			
			return;
		}
	   // ClassLoader loader = compiler.getClassLoader();
	    //loader.
	   // Class compClass = loader.loadClass("Test");
	   // Object instance = compClass.newInstance();
	   // System.out.println(instance);
	}
	
	
}
