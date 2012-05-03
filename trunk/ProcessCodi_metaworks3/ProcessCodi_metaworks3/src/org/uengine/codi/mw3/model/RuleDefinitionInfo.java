package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

public class RuleDefinitionInfo  implements ContextAware{

	String defId;
	@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String name;
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
		
	String alias;
		public String getAlias() {
			return alias;
		}
	
		public void setAlias(String alias) {
			this.alias = alias;
		}

		
	String parentFolder;
	@Hidden
		public String getParentFolder() {
			return parentFolder;
		}
	
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}

	String description;
	@Face(ejsPath="genericfaces/richText.ejs")
		public String getDescription() {
			return description;
		}
	
		public void setDescription(String description) {
			this.description = description;
		}

	MetaworksContext metaworksContext;

		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
