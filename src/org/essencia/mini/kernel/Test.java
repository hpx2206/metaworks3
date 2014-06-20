package org.essencia.mini.kernel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;

public class Test {
	Practice practice;
	public void makeArchitecturePractice(){
			practice = new Practice();
			practice.setId("practice1");
			practice.setName("Architecture Practice");
			practice.setDescription("describe about Architecture Practice");
			
			//alphas
			Component alphas = new Alpha();
			alphas.setId("1_alphas");
			alphas.setName("Alphas");
			alphas.setDescription("describe about Alphas");

			//sub
			Component change = new Alpha();
				change.setId("alpha3");
				change.setName("Change");
				change.setDescription("describe about Change");
				change.setParentComponentId("alpha2");

			//main
			Component opportunity = new Alpha();
			opportunity.setId("alpha1");
			opportunity.setName("Opportunity");
			opportunity.setDescription("describe about Opportunity");
			opportunity.setParentComponentId("1_alphas");
			
			Component requirements = new Alpha();
			requirements.setId("alpha2");
			requirements.setName("Requirements");
			requirements.setDescription("describe about Requirements");
			requirements.getChildComponents().add(change);
			requirements.setParentComponentId("1_alphas");
			
			//add
			alphas.getChildComponents().add(opportunity);
			alphas.getChildComponents().add(requirements);
				
			
			//activities
			Component activities = new Activity();
			activities.setId("1_activities");
			activities.setName("Activities");
			activities.setDescription("describe about Activities");

			//sub
				Component identifyArchitectureRequirements = new Activity();
				identifyArchitectureRequirements.setId("activity3");
				identifyArchitectureRequirements.setName("IdentifyArchitectureRequirements");
				identifyArchitectureRequirements.setDescription("describe about IdentifyArchitectureRequirements");
				identifyArchitectureRequirements.setParentComponentId("activity1");
			
			//main
			Component specficyTheSystem = new Activity();
			specficyTheSystem.setId("activity1");
			specficyTheSystem.setName("SpecficyTheSystem");
			specficyTheSystem.setDescription("describe about SpecficyTheSystem");
			specficyTheSystem.getChildComponents().add(identifyArchitectureRequirements);
			specficyTheSystem.setParentComponentId("1_activities");

			Component shapeTheSystem = new Activity();
			shapeTheSystem.setId("activity2");
			shapeTheSystem.setName("ShapeTheSystem");
			shapeTheSystem.setDescription("describe about ShapeTheSystem");
			shapeTheSystem.setParentComponentId("1_activities");
			//
			
			//add
			activities.getChildComponents().add(specficyTheSystem);
			activities.getChildComponents().add(shapeTheSystem);
			
			//competencies
			Component competencies = new Competency();
			competencies.setId("1_competencies");
			competencies.setName("Competencies");
			competencies.setDescription("describe about Competencies");
			
			//main
			Component analyst = new Competency();
			analyst.setId("competency1");
			analyst.setName("Analyst");
			analyst.setParentComponentId("1_competencies");
			
			Component tester = new Competency();
			tester.setId("competency2");
			tester.setName("Tester");
			tester.setParentComponentId("1_competencies");
			//
			
			//add
			competencies.getChildComponents().add(analyst);
			competencies.getChildComponents().add(tester);
			
			//add to practice
			practice.getAlphas().add(alphas);
			practice.getActivities().add(activities);
			practice.getCompetencies().add(competencies);
	}
	
	public void makeTeamPractice(){
		practice = new Practice();
		practice.setId("practice2");
		practice.setName("Team Practice");
		practice.setDescription("describe about Team Practice");
		
		//alphas
		Component alphas = new Alpha();
		alphas.setId("2_alphas");
		alphas.setName("Alphas");
		alphas.setDescription("describe about Alphas");
		
			//sub
			Component teamMember = new Alpha();
			teamMember.setId("alpha3");
			teamMember.setName("TeamMember");
			teamMember.setDescription("describe about TeamMember");
			teamMember.setParentComponentId("alpha1");

			Component teamCharter = new Alpha();
			teamCharter.setId("alpha4");
			teamCharter.setName("TeamCharter");
			teamCharter.setDescription("describe about TeamCharter");
			teamCharter.setParentComponentId("alpha1");
		
		//main
		Component team = new Alpha();
		team.setId("alpha1");
		team.setName("Team");
		team.setDescription("describe about Team");
		team.setParentComponentId("2_alphas");
		team.getChildComponents().add(teamMember);
		team.getChildComponents().add(teamCharter);
		
		Component wayOfWorking = new Alpha();
		wayOfWorking.setId("alpha2");
		wayOfWorking.setName("WayOfWorking");
		wayOfWorking.setDescription("describe about WayOfWorking");
		wayOfWorking.setParentComponentId("2_alphas");
		
		//add
		alphas.getChildComponents().add(team);
		alphas.getChildComponents().add(wayOfWorking);
		
		
		//activities
		Component activities = new Activity();
		activities.setId("2_activities");
		activities.setName("Activities");
		activities.setDescription("describe about Activities");
			
			//sub
			Component launchProject = new Activity();
			launchProject.setId("activity3");
			launchProject.setName("LaunchProject");
			launchProject.setDescription("describe about LaunchProject");
			launchProject.setParentComponentId("activity1");
			//sub
			Component guideTeam = new Activity();
			guideTeam.setId("activity4");
			guideTeam.setName("GuideTeam");
			guideTeam.setDescription("describe about GuideTeam");
			guideTeam.setParentComponentId("activity2");
		
		//main
		Component establishProject = new Activity();
		establishProject.setId("activity1");
		establishProject.setName("EstablishProject");
		establishProject.setDescription("describe about EstablishProject");
		establishProject.setParentComponentId("2_activities");
		establishProject.getChildComponents().add(launchProject);
		
		Component supportTeam = new Activity();
		supportTeam.setId("activity2");
		supportTeam.setName("SupportTeam");
		supportTeam.setDescription("describe about SupportTeam");
		supportTeam.setParentComponentId("2_activities");
		supportTeam.getChildComponents().add(guideTeam);
		//
		
		//add
		activities.getChildComponents().add(establishProject);
		activities.getChildComponents().add(supportTeam);
		
		//competencies
		Component competencies = new Competency();
		competencies.setId("2_competencies");
		competencies.setName("Competencies");
		competencies.setDescription("describe about Competencies");
		
		//main
		Component customerRepresentative = new Competency();
		customerRepresentative.setId("competency1");
		customerRepresentative.setName("CustomerRepresentative");
		customerRepresentative.setParentComponentId("2_competencies");
		
		Component leaderShip = new Competency();
		leaderShip.setId("competency2");
		leaderShip.setName("LeaderShip");
		leaderShip.setParentComponentId("2_competencies");
		//
		
		//add
		competencies.getChildComponents().add(customerRepresentative);
		competencies.getChildComponents().add(leaderShip);
		
		//add to practice
		practice.getAlphas().add(alphas);
		practice.getActivities().add(activities);
		practice.getCompetencies().add(competencies);
	}
	
	void toXML(){
		XStream aa = new XStream();
		String xml = aa.toXML(practice);
		System.out.println(xml);
	}
	
	Practice fromXML(){
		FileInputStream is;
		Practice p =null;
		try {
			is = new FileInputStream("C:/practices/Team_Practice.xml");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			XStream x = new XStream();
			p = (Practice)x.fromXML(isr);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return p;
	}
	public static void main(String[] args){
		Test m = new Test();
//		m.makeArchitecturePractice();
		m.makeTeamPractice();
		m.toXML();
//		m.fromXML();
	}
}
