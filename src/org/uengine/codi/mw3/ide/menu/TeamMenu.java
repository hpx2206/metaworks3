package org.uengine.codi.mw3.ide.menu;

import org.metaworks.component.MenuItem;

public class TeamMenu extends CloudMenu {

	public TeamMenu(){

		this.setId("team");
		this.setName("Team");
		
		this.add(new MenuItem("teamSynchronize", "Synchronize with Repository"));
		this.add(new MenuItem("teamCommit", "Commit"));
		this.add(new MenuItem("teamUpdate", "Update"));
	}
}
