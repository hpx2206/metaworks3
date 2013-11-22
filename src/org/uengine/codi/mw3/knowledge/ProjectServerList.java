package org.uengine.codi.mw3.knowledge;

public class ProjectServerList {

	ProjectServer[] value;
		public ProjectServer[] getValue() {
			return value;
		}
		public void setValue(ProjectServer[] value) {
			this.value = value;
		}
		
	public ProjectServerList() {
		ProjectServer[] value = new ProjectServer[0];
		
		this.setValue(value);
	}
}
