package org.metaworks.example.ide;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class SourceCode {
	
	public SourceCode(){
		setCode("... ");
	}
	
	
	String code;
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
	
	CompileError[] compileErrors;
	
	String language;

	public CompileError[] getCompileErrors() {
		return compileErrors;
	}

	public void setCompileErrors(CompileError[] compileErrors) {
		this.compileErrors = compileErrors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	Position cursorPosition;
		public Position getCursorPosition() {
			return cursorPosition;
		}	
		public void setCursorPosition(Position cursorPosition) {
			this.cursorPosition = cursorPosition;
		}
		
	String clientObjectId;
		public String getClientObjectId() {
			return clientObjectId;
		}
		public void setClientObjectId(String clientObjectId) {
			this.clientObjectId = clientObjectId;
		}
	
	String lineAssistRequested;
			
		public String getLineAssistRequested() {
			return lineAssistRequested;
		}
	
		public void setLineAssistRequested(String lineAssistRequested) {
			this.lineAssistRequested = lineAssistRequested;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_STICK)
	public CodeAssist requestAssist(){
		CodeAssist codeAssist = new CodeAssist();
//		codeAssist.getAssistances().add("");
		
		codeAssist.setSrcCodeObjectId(clientObjectId);
		
		return codeAssist;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public void installBreakpoint(){
		
		System.out.println("install breakpoint at : " + lineAssistRequested);
	}
	
	

	
}
