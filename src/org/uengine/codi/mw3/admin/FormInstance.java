package org.uengine.codi.mw3.admin;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.GlobalContext;

public class FormInstance{

	
	@ServiceMethod(callByContent=true)
	@Face(displayName = "submit")
	public void save() throws Exception {
		
		//CodiClassLoader.codiClassLoader.sourceCodeBase() == /Users/jyjang
		GlobalContext.serialize(this, new FileOutputStream("/Users/jyjang/formInst.xml"), FormDefinition.class);
	}
	
	

}
