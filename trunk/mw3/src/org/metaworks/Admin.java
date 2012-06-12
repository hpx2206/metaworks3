package org.metaworks;

import javax.persistence.Id;

import org.metaworks.annotation.Default;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;

public class Admin {

	public Admin() {
		setTargetClassName("*");
	}
	
	String targetClassName;
	@Id
	@Default(value="*")
		public String getTargetClassName() {
			return targetClassName;
		}
		public void setTargetClassName(String targetClassName) {
			this.targetClassName = targetClassName;
		}
	

	@ServiceMethod
	public void refreshMetadata() throws Exception{
		System.out.println("targetClassName : " + getTargetClassName());
		
		
		MetaworksRemoteService.getInstance().clearMetaworksType(getTargetClassName());
	}
	


}
