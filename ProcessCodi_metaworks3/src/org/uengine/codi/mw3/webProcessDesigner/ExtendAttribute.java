package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ExtendAttribute {
	
	public ExtendAttribute(){
		documentationList = new ArrayList<Documentation>();
		Documentation documentation = new Documentation();
		documentation.setMetaworksContext(new MetaworksContext());
		documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		documentation.setDesc("가가가");
		documentationList.add(documentation);
	}
	
	ArrayList<Documentation> documentationList;
		public ArrayList<Documentation> getDocumentationList() {
			return documentationList;
		}
		public void setDocumentationList(ArrayList<Documentation> documentationList) {
			this.documentationList = documentationList;
		}
	ArrayList<ParticipateGroup> participateGroupList;
		public ArrayList<ParticipateGroup> getParticipateGroupList() {
			return participateGroupList;
		}
		public void setParticipateGroupList(
				ArrayList<ParticipateGroup> participateGroupList) {
			this.participateGroupList = participateGroupList;
		}
		
	@ServiceMethod(callByContent=true)
	public ModalWindow addDocumentation(){
		Documentation documentation = new Documentation();
		documentation.setMetaworksContext(new MetaworksContext());
		documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setPanel(documentation);
		
		return modalWindow;
	}
}
