package org.uengine.codi.mw3.processexplorer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.model.DocumentPerspective;
import org.uengine.codi.mw3.model.Session;

public class ProcessExplorerPerspectivePanel  implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
		
	ValuechainPerspective valuechainPerspective;
		public ValuechainPerspective getValuechainPerspective() {
			return valuechainPerspective;
		}
		
		public void setValuechainPerspective(ValuechainPerspective valuechainPerspective) {
			this.valuechainPerspective = valuechainPerspective;
		}

	FavoritePerspective favoritePerspective;
		public FavoritePerspective getFavoritePerspective() {
			return favoritePerspective;
		}
		
		public void setFavoritePerspective(FavoritePerspective favoritePerspective) {
			this.favoritePerspective = favoritePerspective;
		}

	DocumentPerspective documentPerspective;
		public DocumentPerspective getDocumentPerspective() {
			return documentPerspective;
		}
		public void setDocumentPerspective(DocumentPerspective documentPerspective) {
			this.documentPerspective = documentPerspective;
		}
		
	public ProcessExplorerPerspectivePanel() throws Exception {
		this(null);
	}
	
	public ProcessExplorerPerspectivePanel(Session session) throws Exception{
		valuechainPerspective = new ValuechainPerspective();
		valuechainPerspective.session = session;
		valuechainPerspective.select();
		
		favoritePerspective = new FavoritePerspective();
		favoritePerspective.session = session;
		favoritePerspective.select();		
		
		setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("explorer");
		
		documentPerspective = new DocumentPerspective();
		documentPerspective.session = session;		
		documentPerspective.setMetaworksContext(this.getMetaworksContext());
		documentPerspective.select();
	}
	
}
