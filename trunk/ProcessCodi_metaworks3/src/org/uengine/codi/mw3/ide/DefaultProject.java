package org.uengine.codi.mw3.ide;

import org.metaworks.annotation.Face;
import org.metaworks.metadata.MetadataBundle;

@Face(ejsPath="genericfaces/EmptyEjs.ejs")
public class DefaultProject extends Project{

	public DefaultProject() throws Exception {
		this.setId(MetadataBundle.getProjectId());
		this.setName(this.getId());
		
		this.load();
	}
}
