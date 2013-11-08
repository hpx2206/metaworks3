package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class DocumentPanel implements ContextAware {

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	
	
	public DocumentPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	@AutowiredFromClient
	transient public Session session;
	

	@ServiceMethod
	public Object[] load() throws Exception{

		String title = "도큐멘트";
		Object[] returnObject = Perspective.loadDocumentListPanel(session, "document", "1", title);
		
		return returnObject;
	}
}
