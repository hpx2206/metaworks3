package org.uengine.codi.mw3.admin;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;

public class WebEditor implements ContextAware {

	public WebEditor() {
		this("");
	}

	public WebEditor(String contents) {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		setContents(contents);		
	}
	
	String contents;
		@Hidden
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
}
