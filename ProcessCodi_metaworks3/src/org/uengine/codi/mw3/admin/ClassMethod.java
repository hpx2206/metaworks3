
package org.uengine.codi.mw3.admin;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;

public class ClassMethod implements Cloneable, ContextAware{

	public ClassMethod(){
	}
	
	String methodName;
	@Id  // TODO: lesson 1 (object addressing and correlation)
		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

	
	String returnType;
		public String getReturnType() {
			return returnType;
		}
		public void setReturnType(String returnType) {
			this.returnType = returnType;
		}

				
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public ClassDefinition add() throws Exception{

		if(classDefinition.classMethods==null)
			classDefinition.classMethods = new ArrayList<ClassMethod>();//new FormField[]{};
		
		//TODO: lesson 3 (validation with throwing exception)
		if(classDefinition.classFields.contains(this))
			throw new Exception("There's already existing method named '" + getMethodName() + "'.");
		ClassMethod clonedOne = (ClassMethod) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)

		clonedOne.setMetaworksContext(new MetaworksContext());  //TODO: lesson 4 (context injection)
		clonedOne.getMetaworksContext().setWhere("in-container");
		
		classDefinition.classMethods.add(clonedOne); 

		//clear the entries for newFormField	//TODO: lesson 6 (context clearing)
		classDefinition.init();
		//
		
		return classDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="in-container", callByContent=true)
	public ClassDefinition save() throws Exception{
		//TODO: lesson 3 (validation with throwing exception)
		
		int index = classDefinition.classFields.indexOf(this);
		if(index==-1)
			throw new Exception("There's no existing method named '" + getMethodName() + "'.");
					
		classDefinition.classMethods.remove(this);
		
		ClassMethod clonedOne = (ClassMethod) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)
		
		classDefinition.classMethods.add(index, clonedOne); 
		
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return classDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public ClassDefinition remove(){
		classDefinition.classFields.remove(this);
		
		return classDefinition;
	}
	
	//TODO: quiz 2 (when the form field is first order, this button should be shown.
	//              Improve 'up' method and 'down' method not to be shown when it is in the first order and in the last order.
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public ClassDefinition up(){
		int index = classDefinition.classMethods.indexOf(this);
		
		if(index>0){
			classDefinition.classMethods.remove(this);
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classDefinition.classMethods.add(index-1, this);
		}

		return classDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container") 
	public ClassDefinition down(){
		int index = classDefinition.classMethods.indexOf(this);
		
		if(index<classDefinition.classFields.size()-1){
			classDefinition.classMethods.remove(this);      //TODO: lesson 1 (object addressing and correlation)
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classDefinition.classMethods.add(index+1, this);
		}

		return classDefinition;
	}
	
	
	
	@Override //TODO: lesson 1 (object addressing and correlation)
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisFieldName = this.getMethodName();
		String comparatorFieldName = ((ClassMethod)obj).getMethodName();
		
		return thisFieldName.equals(comparatorFieldName);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public ClassDefinition classDefinition;
	

	
	///// context //////  TODO: lesson 4 (context injection)
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
