package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

public class FaceEditor {
	
	WebEditor editor;
	@Face(displayName="에디터")
	public WebEditor getEditor() {
		return editor;
	}
	public void setEditor(WebEditor editor) {
		this.editor = editor;
	}
	
	public FaceEditor(){
		init();
	}
	
	private void init(){
		editor = new WebEditor();
	}
	
	String code;
	@Hidden
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	
}
