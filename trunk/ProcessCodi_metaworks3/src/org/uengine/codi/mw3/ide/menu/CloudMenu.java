package org.uengine.codi.mw3.ide.menu;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.component.Menu;
import org.uengine.codi.mw3.model.Session;

public class CloudMenu extends Menu {
	@AutowiredFromClient
	public Session session;
}
