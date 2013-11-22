package org.uengine.codi.mw3.nkia;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.widget.IFrame;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="Nkia"
	)
public class NkiaCenterPanel {
	
	public NkiaCenterPanel(){
		
	}
	
	IFrame tadpoleHome;
		public IFrame getTadpoleHome() {
			return tadpoleHome;
		}
		public void setTadpoleHome(IFrame tadpoleHome) {
			this.tadpoleHome = tadpoleHome;
		}
	
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}
}
