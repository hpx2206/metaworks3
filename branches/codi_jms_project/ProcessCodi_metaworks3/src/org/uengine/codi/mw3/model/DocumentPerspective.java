package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicTitle;

public class DocumentPerspective extends Perspective  implements ContextAware {
	
	DocumentPanel documentPanel;
		public DocumentPanel getDocumentPanel() {
			return documentPanel;
		}
	
		public void setDocumentPanel(DocumentPanel documentPanel) {
			this.documentPanel = documentPanel;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public DocumentPerspective(){
		setLabel("$Document");
	}

	@Override
	protected void loadChildren() throws Exception {
		documentPanel = new DocumentPanel();
		if("explorer".equals(getMetaworksContext().getHow())){
			documentPanel.getMetaworksContext().setHow("explorer");
		}else if("perspectivePanel".equals(getMetaworksContext().getHow())){
			documentPanel.getMetaworksContext().setHow("perspectivePanel");
		}
		documentPanel.session = session;
		documentPanel.load();
	}

	@Override
	protected void unloadChildren() throws Exception {
		// TODO Auto-generated method stub
		setDocumentPanel(null);
	}
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="$addDocument")
	public ModalWindow addDocument() throws Exception{ 
		DocumentTitle documentTitle = new DocumentTitle();
		documentTitle.setMetaworksContext(new MetaworksContext());
		documentTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		documentTitle.session = session;
		return new ModalWindow(documentTitle , 500, 200,  "$addDocument");
		
	}

}
