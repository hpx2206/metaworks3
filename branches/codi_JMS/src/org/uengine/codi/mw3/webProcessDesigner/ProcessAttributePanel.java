package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;

public class ProcessAttributePanel {
	
	String defId;
	@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	Documentation documentation;
		public Documentation getDocumentation() {
			return documentation;
		}
		public void setDocumentation(Documentation documentation) {
			this.documentation = documentation;
		}
			
	public ProcessAttributePanel(){
		setDocumentation(new Documentation());
		setDefId(defId);
	}
	public void load( ProcessDesignerContainer processDesignerContainer ) throws Exception {
		Documentation doc = processDesignerContainer.getProcessDetailPanel().getDocumentation();
		doc.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		setDocumentation(doc);
		
	}
	

}
