package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.metaworks.component.SelectBox;

public class ClassResourceMethod implements Serializable{

	String resourceClass;
		public String getResourceClass() {
			return resourceClass;
		}
		public void setResourceClass(String resourceClass) {
			this.resourceClass = resourceClass;
		}
	String callMethod;
		public String getCallMethod() {
			return callMethod;
		}
		public void setCallMethod(String callMethod) {
			this.callMethod = callMethod;
		}
		
	SelectBox methodSelectBox;
		public SelectBox getMethodSelectBox() {
			return methodSelectBox;
		}
		public void setMethodSelectBox(SelectBox methodSelectBox) {
			this.methodSelectBox = methodSelectBox;
		}
	public void makeMethodChoice() throws  Exception{
		String rsc = this.getResourceClass();
		if( rsc != null ){
//			Class c = Thread.currentThread().getContextClassLoader().loadClass(rsc.substring(0, rsc.lastIndexOf(".")).replaceAll("/", "."));
			Class c = Thread.currentThread().getContextClassLoader().loadClass(rsc);
	    	Method[] method = c.getDeclaredMethods();
	    	SelectBox methodChoice = new SelectBox();
			methodChoice.add("", "");
			for( int i =0; i <method.length; i++ ){
				String methodName = method[i].getName();
				if( methodName.startsWith( "get" ) || methodName.startsWith( "set" ) || methodName.startsWith( "is" )){
					continue;
				}
				Class[] paramTypes = method[i].getParameterTypes();
				String methodVariable = methodName + "(";
				for( int j =0; j <paramTypes.length; j++ ){
					if( j > 0 ){
						methodVariable += ",";
					}
					methodVariable += paramTypes[j].getSimpleName();
				}
				methodVariable += ")";
				methodChoice.add(methodVariable, methodName);
			}
			setMethodSelectBox(methodChoice);
		}
	}
	
}
