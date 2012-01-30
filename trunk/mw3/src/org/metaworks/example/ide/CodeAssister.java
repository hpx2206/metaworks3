package org.metaworks.example.ide;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class CodeAssister {
	
	SourceCode sourceCode;
		public SourceCode getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(SourceCode sourceCode) {
			this.sourceCode = sourceCode;
		}

	public CodeAssist assist(){
		CodeAssist codeAssist = new CodeAssist();
		codeAssist.setAssistances(new ArrayList<String>());
		codeAssist.getAssistances().add("test");
		
		return codeAssist;
	}

	
}
