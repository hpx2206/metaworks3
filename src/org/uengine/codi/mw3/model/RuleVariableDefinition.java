package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.mw3.admin.ClassMethod;
import org.uengine.codi.mw3.admin.ClassModeler;
import org.uengine.codi.mw3.admin.ClassSourceCodes;

public class RuleVariableDefinition extends ClassModeler{


	@Hidden
	public ArrayList<ClassMethod> getClassMethods() {
		// TODO Auto-generated method stub
		return super.getClassMethods();
	}

///////// rename somthing /////
	
	
	@Face(displayName="룰 매개 변수")
	public ArrayList<ClassField> getClassFields() {
		// TODO Auto-generated method stub
		return super.getClassFields();
	}

	@Face(displayName="새 변수")
	public ClassField getNewClassField() {
		// TODO Auto-generated method stub
		return super.getNewClassField();
	}
	

	
	
}
	

