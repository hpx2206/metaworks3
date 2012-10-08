package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;

public class FaceEditor {
	WebEditor instruction;
	@Face(displayName="에디터")
	public WebEditor getInstruction() {
		return instruction;
	}
	public void setInstruction(WebEditor instruction) {
		this.instruction = instruction;
	}
	
	public FaceEditor(){
		init();
	}
	
	private void init(){
		WebEditor editor = new WebEditor();
	}
	
	String code;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	
}
