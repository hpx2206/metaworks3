
package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.example.ide.FormDesigner;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.JavaSourceCode;


@Face(ejsPath="genericfaces/Tab.ejs",
	options={"hideLabels", "tabsBottom"},
	values={"true", "true"})

public class ClassSourceCodes{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		

	public ClassSourceCodes(){
	}
	
	public void load(){
		this.sourceCode = new JavaSourceCode();
		this.face = new FaceEditor();
		this.faceHelper = new FaceHelperSourceCode();
		this.classModeler = new ClassModeler();
		this.mobileDesigner = new MobileDesigner();
		//this.formDesigner = new FormDesigner();
		
	}
	
	JavaSourceCode sourceCode;
		@Available(where={"class"})
		public JavaSourceCode getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(JavaSourceCode sourceCode) {
			this.sourceCode = sourceCode;
		}

	ClassModeler classModeler;
		@Face(displayName="변수정의")
		@Available(where={"class", "form"})
		public ClassModeler getClassModeler() {
			return classModeler;
		}
		public void setClassModeler(ClassModeler classModeler) {
			this.classModeler = classModeler;
		}
	

	FaceEditor face;			
		@Face(displayName="에디터편집기")
		@Available(where={"class", "form"})
		public FaceEditor getFace() {
			return face;
		}
		public void setFace(FaceEditor face) {
			this.face = face;
		}
		
	FormDesigner formDesigner;
		@Available(where={"class"})
		public FormDesigner getFormDesigner() {
			return formDesigner;
		}
		public void setFormDesigner(FormDesigner formDesigner) {
			this.formDesigner = formDesigner;
		}

	FaceHelperSourceCode faceHelper;
		@Available(where={"class"})
		public FaceHelperSourceCode getFaceHelper() {
			return faceHelper;
		}
		public void setFaceHelper(FaceHelperSourceCode faceHelper) {
			this.faceHelper = faceHelper;
		}
	
	MobileDesigner mobileDesigner;
		@Available(where={"class"})
		public MobileDesigner getMobileDesigner() {
			return mobileDesigner;
		}
		public void setMobileDesigner(MobileDesigner mobileDesigner) {
			this.mobileDesigner = mobileDesigner;
		}
	
}
