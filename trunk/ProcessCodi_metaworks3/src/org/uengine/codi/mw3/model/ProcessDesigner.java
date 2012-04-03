package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.ClassDefinition;

public class ProcessDesigner extends ResourceDesigner{
	
	PureWebProcessDesigner pureWebProcessDesigner;

		public PureWebProcessDesigner getPureWebProcessDesigner() {
			return pureWebProcessDesigner;
		}
	
		public void setPureWebProcessDesigner(
				PureWebProcessDesigner pureWebProcessDesigner) {
			this.pureWebProcessDesigner = pureWebProcessDesigner;
		}

	@ServiceMethod
	@Override
	public void load() {
		pureWebProcessDesigner = new PureWebProcessDesigner();
		pureWebProcessDesigner.load();
		
	}

}
