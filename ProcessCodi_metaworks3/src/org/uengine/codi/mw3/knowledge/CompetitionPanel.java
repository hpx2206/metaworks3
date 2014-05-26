package org.uengine.codi.mw3.knowledge;

public class CompetitionPanel {

	ICompetitionNode competitionNode;
		public ICompetitionNode getCompetitionNode() {
			return competitionNode;
		}
		public void setCompetitionNode(ICompetitionNode competitionNode) {
			this.competitionNode = competitionNode;
		}

	public CompetitionPanel(){
		
	}
	
	public CompetitionPanel(ICompetitionNode competitionNode){
		this.setCompetitionNode(competitionNode);
	}
}
