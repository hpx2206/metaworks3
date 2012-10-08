package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.example.ide.FormDesigner;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.JavaSourceCode;

@Face(ejsPath="genericfaces/Tab.ejs",
options={"hideLabels", "tabsBottom"},
values={"true", "true"})
public class FormSourceCodes extends ClassSourceCodes {
	
	public FormSourceCodes(){
		init();
	}
	
	@Override
	public void init(){
		this.classModeler = new ClassModeler();
		this.face = new FaceEditor();
	}
	
	@Hidden(on=true)
	@Override
	public JavaSourceCode getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(JavaSourceCode sourceCode) {
		this.sourceCode = sourceCode;
	}
	@Hidden(on=true)
	@Override
	public FormDesigner getFormDesigner() {
		return formDesigner;
	}
	public void setFormDesigner(FormDesigner formDesigner) {
		this.formDesigner = formDesigner;
	}
	@Hidden
	@Override
	public FaceHelperSourceCode getFaceHelper() {
		return faceHelper;
	}
	public void setFaceHelper(FaceHelperSourceCode faceHelper) {
		this.faceHelper = faceHelper;
	}
}
