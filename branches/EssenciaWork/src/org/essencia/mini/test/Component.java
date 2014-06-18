package org.essencia.mini.test;

import java.util.List;

public class Component {

	protected String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	protected String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	protected String concern;
		public String getConcern() {
			return concern;
		}
		public void setConcern(String concern) {
			this.concern = concern;
		}
		
	protected String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	protected List<Component> childComponents; 
		public List<Component> getChildComponents() {
			return childComponents;
		}
		public void setChildComponents(List<Component> childComponents) {
			this.childComponents = childComponents;
		}
	protected Component parentCompenent;
		public Component getParentCompenent() {
			return parentCompenent;
		}
		public void setParentCompenent(Component parentCompenent) {
			this.parentCompenent = parentCompenent;
		} 

}