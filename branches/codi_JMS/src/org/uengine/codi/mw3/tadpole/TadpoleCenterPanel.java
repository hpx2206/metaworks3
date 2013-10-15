package org.uengine.codi.mw3.tadpole;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.widget.IFrame;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"},
		displayName="Tadpole"
	)
public class TadpoleCenterPanel {
	
	public TadpoleCenterPanel(){
		
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
