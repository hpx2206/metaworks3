package org.essencia.mini.test;

import java.util.ArrayList;
import java.util.List;

import org.metaworks.annotation.Face;
import org.metaworks.component.TreeNode;
@Face(displayName="Component", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class Component implements TreeNodable{
	
	public Component(){
		setChildComponents(new ArrayList<Component>());
	}

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
	protected String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
	protected String parentComponentId;
		public String getParentComponentId() {
			return parentComponentId;
		}
		public void setParentComponentId(String parentComponentId) {
			this.parentComponentId = parentComponentId;
		}
		
		@Override
		public String getParentId() {
			
			return getParentComponentId();
		}
		@Override
		
		public List<Component> getChildList() {
			// TODO Auto-generated method stub
			return childComponents;
		}
		

}