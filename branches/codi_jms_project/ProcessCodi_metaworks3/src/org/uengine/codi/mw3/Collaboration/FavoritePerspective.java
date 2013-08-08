package org.uengine.codi.mw3.Collaboration;

import org.uengine.codi.mw3.model.IProcessMap;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.ProcessMap;

public class FavoritePerspective  extends Perspective {
	IProcessMap processMapList;
		public IProcessMap getProcessMapList() {
			return processMapList;
		}	
		public void setProcessMapList(IProcessMap processMapList) {
			this.processMapList = processMapList;
		}
	public FavoritePerspective(){
		setLabel("Favorite");
		
	}
	
	@Override
	protected void loadChildren() throws Exception {
		// TODO Auto-generated method stub
		IProcessMap processMap = ProcessMap.loadList(session);
		processMap.getMetaworksContext().setWhen("filter");
		
		setProcessMapList(processMap);

	}
	
	@Override
	protected void unloadChildren() throws Exception{
		setProcessMapList(null);
	}
}
