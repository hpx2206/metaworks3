package org.uengine.codi.mw3.ide.menu;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.ResourceNode;


public class TeamMenu extends ResourceMenu {

	@AutowiredFromClient(select="")
	public ResourceNode selectedNode;
	
	public TeamMenu(){

		this.setId("team");
		this.setName("Team");
		
		this.add(new MenuItem("teamSynchronize", "Synchronize with Repository"));
		this.add(new MenuItem("teamCommit", "Commit"));
		this.add(new MenuItem("teamUpdate", "Update"));
	}
}
