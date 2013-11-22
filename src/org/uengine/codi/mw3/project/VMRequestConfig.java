package org.uengine.codi.mw3.project;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

@Face(displayName="가성서버 설정", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"vmHardwareProfileCombo,serverName"})
public class VMRequestConfig implements ITool, ContextAware {
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String vmHardwareProfileId;
		@Hidden
		public String getVmHardwareProfileId() {
			return vmHardwareProfileId;
		}
		public void setVmHardwareProfileId(String vmHardwareProfileId) {
			this.vmHardwareProfileId = vmHardwareProfileId;
		}

	VMHardwareProfileCombo vmHardwareProfileCombo;
		@Face(displayName="스펙")
		public VMHardwareProfileCombo getVmHardwareProfileCombo() {
			return vmHardwareProfileCombo;
		}
		public void setVmHardwareProfileCombo(
				VMHardwareProfileCombo vmHardwareProfileCombo) {
			this.vmHardwareProfileCombo = vmHardwareProfileCombo;
		}
		
	String serverName;
		@Face(displayName="가상서버명")
		public String getServerName() {
			return serverName;
		}
		public void setServerName(String serverName) {
			this.serverName = serverName;
		}
		
	public VMRequestConfig(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setVmHardwareProfileCombo(new VMHardwareProfileCombo(null));
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		this.setVmHardwareProfileId(this.getVmHardwareProfileCombo().getSelected());
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
	}
}
