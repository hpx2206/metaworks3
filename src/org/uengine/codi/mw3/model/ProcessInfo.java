package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;

public class ProcessInfo extends PerspectiveInfo{

	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	public ProcessInfo(){
		
	}
	public ProcessInfo(Session session){
		this.session = session;
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	@Override
	public Object[] delete() throws Exception {
		// TODO Auto-generated method stub
		return super.delete();
	}

	@Override
	public ModalWindow modify() throws Exception {
		// TODO Auto-generated method stub
		return super.modify();
	}

	@Override
	public void load() throws Exception {
		System.out.println("TODO : DEFID-----ProcessInfo Load...");

	}
	
}
