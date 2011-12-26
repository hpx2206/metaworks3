package org.metaworks.example.ide;

public class SourceCode {
	String code;
	
	CompileError[] compileErrors;
	
	String language;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
	
	
	
}
