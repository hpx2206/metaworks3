
package org.uengine.codi.mw3.admin;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiDwrServlet;
import org.uengine.codi.mw3.model.FaceSourceCode;
import org.uengine.codi.mw3.model.JavaSourceCode;

import org.uengine.codi.mw3.model.BrowserWindow;
import org.uengine.codi.mw3.model.FaceHelperSourceCode;
import org.uengine.codi.mw3.model.TemplateDesigner;
import org.uengine.codi.mw3.model.Window;
import org.uengine.codi.mw3.model.MobileWindow;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;


@Face(ejsPath="genericfaces/Tab.ejs",
	options={"hideLabels"},
	values={"true"})

public class ClassSourceCodes{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		

	public ClassSourceCodes(){
		this.sourceCode = new JavaSourceCode();
		this.face = new FaceSourceCode();
		this.faceHelper = new FaceHelperSourceCode();
		this.classModeler = new ClassModeler();
	}
	
	ClassModeler classModeler;
		public ClassModeler getClassModeler() {
			return classModeler;
		}
		public void setClassModeler(ClassModeler classModeler) {
			this.classModeler = classModeler;
		}

	
	JavaSourceCode sourceCode;
		public JavaSourceCode getSourceCode() {
			return sourceCode;
		}
		public void setSourceCode(JavaSourceCode sourceCode) {
			this.sourceCode = sourceCode;
		}
		
	FaceSourceCode face;
			
		public FaceSourceCode getFace() {
			return face;
		}
		public void setFace(FaceSourceCode face) {
			this.face = face;
		}
		
	FaceHelperSourceCode faceHelper;
		public FaceHelperSourceCode getFaceHelper() {
			return faceHelper;
		}
		public void setFaceHelper(FaceHelperSourceCode faceHelper) {
			this.faceHelper = faceHelper;
		}
	
}
