package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.codi.mw3.admin.EntityQuery;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

//
@Face(ejsPath = "genericfaces/Tab.ejs", options = { "hideLabels" }, values = { "true" })
public class EntityDesignerContentPanel {

	public EntityDesignerContentPanel() {
		setMetaworksContext(new MetaworksContext());
	}

	EntityDefinition entityDefinition;

	public EntityDefinition getEntityDefinition() {
		return entityDefinition;
	}

	public void setEntityDefinition(EntityDefinition entityDefinition) {
		this.entityDefinition = entityDefinition;
	}

	EntityQuery entityQuery;

	public EntityQuery getEntityQuery() {
		return entityQuery;
	}

	public void setEntityQuery(EntityQuery entityQuery) {
		this.entityQuery = entityQuery;
	}

	/*
	 * ClassDefinition daoInterface;
	 * 
	 * @Face(displayName="DAO Interface") public ClassDefinition
	 * getDaoInterface() { return daoInterface; } public void
	 * setDaoInterface(ClassDefinition daoInterface) { this.daoInterface =
	 * daoInterface; }
	 * 
	 * 
	 * 
	 * ClassDefinition daoImplementation;
	 * 
	 * @Face(displayName="DAO Implementation") public ClassDefinition
	 * getDaoImplementation() { return daoImplementation; } public void
	 * setDaoImplementation(ClassDefinition daoImplementation) {
	 * this.daoImplementation = daoImplementation; }
	 */

	/*
	 * EntityData entityData; public EntityData getEntityData() { return
	 * entityData; } public void setEntityData(EntityData entityData) {
	 * this.entityData = entityData; }
	 */

	public void newEntity(String parentFoler) throws Exception {
		entityDefinition = new EntityDefinition();
		entityDefinition.init();
		entityDefinition.setParentFolder(parentFoler);
		entityDefinition.getMetaworksContext().setWhere("newEntity");

		entityQuery = new EntityQuery();
		// daoInterface = new ClassDefinition();
		// daoImplementation = new ClassDefinition("daoImplementation");

	}

	transient MetaworksContext metaworksContext;

	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}

	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
}
