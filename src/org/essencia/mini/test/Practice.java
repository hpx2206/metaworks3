package org.essencia.mini.test;

import java.util.ArrayList;
import java.util.List;

public class Practice {
	
	public Practice(){
		setAlphas(new ArrayList<Component>());
		setActivities(new ArrayList<Component>());
		setCompetencies(new ArrayList<Component>());
	}
	
	private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	private String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	private List<Component> alphas;
		public List<Component> getAlphas() {
			return alphas;
		}
		public void setAlphas(List<Component> alphas) {
			this.alphas = alphas;
		}

	private List<Component> activities;
		public List<Component> getActivities() {
			return activities;
		}
		public void setActivities(List<Component> activities) {
			this.activities = activities;
		}

	private List<Component> competencies;
		public List<Component> getCompetencies() {
			return competencies;
		}
		public void setCompetencies(List<Component> competencies) {
			this.competencies = competencies;
		}

}