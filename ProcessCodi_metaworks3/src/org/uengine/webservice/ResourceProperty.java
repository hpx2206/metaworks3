package org.uengine.webservice;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("resource")
public class ResourceProperty {
	
	@XStreamAsAttribute
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	ArrayList<MethodProperty> methods;
		public ArrayList<MethodProperty> getMethods() {
			return methods;
		}
		public void setMethods(ArrayList<MethodProperty> methods) {
			this.methods = methods;
		}

	ArrayList<ResourceProperty> child;
		public ArrayList<ResourceProperty> getChild() {
			return child;
		}
		public void setChild(ArrayList<ResourceProperty> child) {
			this.child = child;
		}
		
	public ResourceProperty(){
		methods 	= new ArrayList<MethodProperty>();
		child 		= new ArrayList<ResourceProperty>();
	}
}
