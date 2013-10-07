package org.uengine.codi.mw3.ide.editor.java;

public class JavaCodeError {

	public final static String TYPE_ERROR = "error";
	public final static String TYPE_WARNING = "warning";
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	
	int lineNumber;
		public int getLineNumber() {
			return lineNumber;
		}
		public void setLineNumber(int lineNumber) {
			this.lineNumber = lineNumber;
		}

	public JavaCodeError(){
		this.setType(TYPE_ERROR);
	}
}
