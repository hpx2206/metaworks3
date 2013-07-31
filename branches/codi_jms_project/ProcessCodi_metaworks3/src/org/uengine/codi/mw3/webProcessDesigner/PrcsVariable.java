package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;

public class PrcsVariable implements ContextAware , Cloneable {
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	String variableType;
		public String getVariableType() {
			return variableType;
		}
		public void setVariableType(String variableType) {
			this.variableType = variableType;
		}
	String initValue;
		public String getInitValue() {
			return initValue;
		}
		public void setInitValue(String initValue) {
			this.initValue = initValue;
		}
	String typeId;
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public PrcsVariable() throws Exception{
		
	}

	@Override
	public boolean equals(Object obj) {
		return obj!=null && obj instanceof PrcsVariable && ((PrcsVariable)obj).getName().equals(getName());
	}
}
