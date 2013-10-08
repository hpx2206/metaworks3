
package org.uengine.codi.mw3.admin;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public abstract class AbstractDefinition implements ContextAware{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	transient public ProcessManagerRemote processManager;

	public AbstractDefinition(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	String objectType;
		
		
		public String getObjectType() {
			return objectType;
		}
		public void setObjectType(String objectType) {
			this.objectType = objectType;
		}

	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	int version;
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		
	String defId;
	@NonEditable	
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String defVerId;
	@NonEditable
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}
		
	String parentFolder;
	@NonEditable		
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}

	String definitionName;
	
		public String getDefinitionName() {
			return definitionName;
		}
		public void setDefinitionName(String definitionName) {
			this.definitionName = definitionName;
		}
		
	abstract public  String getData() throws Exception;
	
		
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		String fullDefId = processManager.addProcessDefinition(getDefinitionName(), getVersion(), "description", false, getData(), getParentFolder(), getDefId(), getAlias(), getObjectType());
		
		String[] definitionIdAndVersionId = org.uengine.kernel.ProcessDefinition.splitDefinitionAndVersionId(fullDefId);
		
				
		processManager.setProcessDefinitionProductionVersion(definitionIdAndVersionId[1]);
		processManager.applyChanges();
		
		setDefId(definitionIdAndVersionId[0]);
		setDefVerId(definitionIdAndVersionId[1]);
				
	}

	
}