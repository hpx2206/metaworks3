package org.uengine.codi.mw3.ide;

import org.metaworks.metadata.MetadataBundle;

public class DefaultProject extends Project{

	public DefaultProject() throws Exception {
		this.setId(MetadataBundle.getProjectId());
		this.setName(this.getId());
		
		this.load();
	}
}
