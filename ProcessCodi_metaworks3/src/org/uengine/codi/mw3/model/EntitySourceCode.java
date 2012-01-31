package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.example.ide.SourceCode;
import org.uengine.codi.mw3.admin.EntityDefinition;

public class EntitySourceCode extends SourceCode {

	@AutowiredFromClient
	public EntityDefinition entityDefinition;

}
