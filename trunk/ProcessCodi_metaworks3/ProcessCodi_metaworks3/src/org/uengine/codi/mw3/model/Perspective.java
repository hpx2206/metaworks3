package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class Perspective {
	
	public Perspective(){}
	
	protected Perspective(String label){
		this.label = label;
	}
	
	String label;
		@Id
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
	
	boolean selected;
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
	
	@ServiceMethod
	public Object[] select() throws Exception{
		
		setSelected(!isSelected()); //toggle
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);

		return new Object[]{this, instanceListPanel};
	}
	
	@AutowiredFromClient
	public Session session;
	
}
