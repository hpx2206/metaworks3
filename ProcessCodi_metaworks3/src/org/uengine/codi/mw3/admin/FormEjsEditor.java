package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;

public class FormEjsEditor {
	WebEditor instruction;
	@Face(displayName="에디터")
	public WebEditor getInstruction() {
		return instruction;
	}
	public void setInstruction(WebEditor instruction) {
		this.instruction = instruction;
	}
	
	public FormEjsEditor(){
		init();
	}
	
	private void init(){
		WebEditor editor = new WebEditor();
	}
}
