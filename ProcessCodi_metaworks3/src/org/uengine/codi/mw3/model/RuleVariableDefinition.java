package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.ClassField;
import org.uengine.codi.mw3.admin.ClassMethod;
import org.uengine.codi.mw3.admin.ClassSourceCodes;

public class RuleVariableDefinition extends ClassDefinition{

	@Hidden
	public String getAlias() {
		// TODO Auto-generated method stub
		return super.getAlias();
	}

	@Hidden
	public int getVersion() {
		// TODO Auto-generated method stub
		return super.getVersion();
	}

	@Hidden
	public String getDefId() {
		// TODO Auto-generated method stub
		return super.getDefId();
	}

	@Hidden
	public String getDefVerId() {
		// TODO Auto-generated method stub
		return super.getDefVerId();
	}

	@Hidden
	public String getParentFolder() {
		// TODO Auto-generated method stub
		return super.getParentFolder();
	}

	@Hidden
	public String getPackageName() {
		// TODO Auto-generated method stub
		return super.getPackageName();
	}

	@Hidden
	public String getClassName() {
		// TODO Auto-generated method stub
		return super.getClassName();
	}

	@Hidden
	public ArrayList<ClassMethod> getClassMethods() {
		// TODO Auto-generated method stub
		return super.getClassMethods();
	}

	@Hidden
	public ClassSourceCodes getSourceCodes() {
		// TODO Auto-generated method stub
		return super.getSourceCodes();
	}

	@Hidden
	public void generateSourceCode() {
		// TODO Auto-generated method stub
		super.generateSourceCode();
	}

	@Hidden
	public void compile() throws Exception {
		// TODO Auto-generated method stub
		super.compile();
	}

	@Hidden
	public Object run() throws Exception {
		// TODO Auto-generated method stub
		return super.run();
	}

	@Hidden
	public Object design() throws Exception {
		// TODO Auto-generated method stub
		return super.design();
	}

	@Hidden
	public Object runMobile() throws Exception {
		// TODO Auto-generated method stub
		return super.runMobile();
	}

	@Hidden
	public Object runFullWindow() throws Exception {
		// TODO Auto-generated method stub
		return super.runFullWindow();
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
	

