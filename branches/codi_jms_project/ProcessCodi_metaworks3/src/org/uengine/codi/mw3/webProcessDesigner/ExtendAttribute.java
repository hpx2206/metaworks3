package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.layout.Layout;

public class ExtendAttribute {
	
	public ExtendAttribute(){
		Documentation documentation = new Documentation();
		documentationList = new ArrayList<Documentation>();
//		documentation.setTitle(documentation.getTitle());
//		documentation.setDesc(documentation.getDesc());
//		documentation.setUrl(documentation.getUrl());
		documentation.setAttachfile1(documentation.getAttachfile1());
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
		
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public ModalWindow addDocumentation(){
			Documentation documentation = new Documentation();
			documentation.setMetaworksContext(new MetaworksContext());
			documentation.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			
			ModalWindow modalWindow = new ModalWindow();
			modalWindow.setPanel(documentation);
			
			return modalWindow;
		}
	
}
