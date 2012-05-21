package org.uengine.codi.mw3.model;

public class Navigation {
	
	PersonalPerspective personalPerspective;

		public PersonalPerspective getPersonalPerspective() {
			return personalPerspective;
		}
	
		public void setPersonalPerspective(PersonalPerspective personalPerspective) {
			this.personalPerspective = personalPerspective;
		}

	OrganizationPerspective organizationPerspective;

		public OrganizationPerspective getOrganizationPerspective() {
			return organizationPerspective;
		}
	
		public void setOrganizationPerspective(
				OrganizationPerspective organizationPerspective) {
			this.organizationPerspective = organizationPerspective;
		}

	ProcessPerspective processPerspective;

		public ProcessPerspective getProcessPerspective() {
			return processPerspective;
		}
	
		public void setProcessPerspective(ProcessPerspective processPerspective) {
			this.processPerspective = processPerspective;
		}
	
	ProcessStatusPerspective processStatusPerspective;
	
		public ProcessStatusPerspective getProcessStatusPerspective() {
			return processStatusPerspective;
		}
	
		public void setProcessStatusPerspective(
				ProcessStatusPerspective processStatusPerspective) {
			this.processStatusPerspective = processStatusPerspective;
		}

	public Navigation() {
		personalPerspective = new PersonalPerspective();
		organizationPerspective = new OrganizationPerspective();
		processPerspective = new ProcessPerspective();
		processStatusPerspective = new ProcessStatusPerspective();
	}
}
