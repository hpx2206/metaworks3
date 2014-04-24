package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.knowledge.CompetitionNode;
import org.uengine.codi.mw3.knowledge.CompetitionPanel;
import org.uengine.codi.mw3.knowledge.CompetitionTitle;
import org.uengine.codi.mw3.knowledge.ICompetitionNode;

public class CompetitionPerspective extends MoreViewPerspective {

	public CompetitionPerspective() throws Exception {
		this.setLabel("$Competition");
		this.setLoader(true);
	}
	
	@Override
	protected void loadChildren() throws Exception {
		this.setLoaded(true);
		
		ICompetitionNode competition = CompetitionNode.findCompetition(session, this.isMore());
		int count = CompetitionNode.calcCompetitionCount(session);
		if(count > Integer.parseInt(CompetitionNode.DEFAULT_COMPETITION_COUNT))
			this.setEnableMore(true);
		else
			this.setEnableMore(false);
		
		this.setChild(new CompetitionPanel(competition));
	}

	@Override
	public Popup popupAdd() throws Exception{
		
		
		CompetitionTitle competitionTitle = new CompetitionTitle();
		competitionTitle.setMetaworksContext(new MetaworksContext());
		competitionTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		competitionTitle.setLogoFile(new MetaworksFile());
		competitionTitle.setStartDate(new Date());
		competitionTitle.setEndDate(new Date());
		competitionTitle.loadService();
		competitionTitle.session = session;
		Popup popup = new Popup(500, 430, competitionTitle);
		popup.setName("Competition");
		return popup;
	}
}