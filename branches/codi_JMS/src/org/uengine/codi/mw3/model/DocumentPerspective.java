package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class DocumentPerspective extends Perspective  implements ContextAware {
	
	DocumentPanel dtPanel;
		public DocumentPanel getDtPanel() {
			return dtPanel;
		}
		public void setDtPanel(DocumentPanel dtPanel) {
			this.dtPanel = dtPanel;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public DocumentPerspective(){
		setLabel("document");
	}

	@Override
	protected void loadChildren() throws Exception {
		dtPanel = new DocumentPanel();
		if("explorer".equals(getMetaworksContext().getHow())){
			dtPanel.getMetaworksContext().setHow("explorer");
		}else if("perspectivePanel".equals(getMetaworksContext().getHow())){
			dtPanel.getMetaworksContext().setHow("perspectivePanel");
		}
		dtPanel.session = session;
		dtPanel.load();
	}

	@Override
	protected void unloadChildren() throws Exception {
		// TODO Auto-generated method stub
		setDtPanel(null);
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
