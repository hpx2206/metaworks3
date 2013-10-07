package org.uengine.codi.mw3.project;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.ITool;

@Face(displayName="텤플릿 선택", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"vmRealmCombo,vmImageCombo"})
public class VMRequest implements ITool, ContextAware {
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	String vmRealmId;
		@Hidden
		public String getVmRealmId() {
			return vmRealmId;
		}
		public void setVmRealmId(String vmRealmId) {
			this.vmRealmId = vmRealmId;
		}
	
	String vmImageId;
		@Hidden
		public String getVmImageId() {
			return vmImageId;
		}
		public void setVmImageId(String vmImageId) {
			this.vmImageId = vmImageId;
		}
	
	VMRealmCombo vmRealmCombo;
		@Face(displayName="망", options={"required"}, values={"true"})
		public VMRealmCombo getVmRealmCombo() {
			return vmRealmCombo;
		}
		public void setVmRealmCombo(VMRealmCombo vmRealmCombo) {
			this.vmRealmCombo = vmRealmCombo;
		}
	
	VMImageCombo vmImageCombo;
		@Face(displayName="템플릿", options={"required"}, values={"true"})
		public VMImageCombo getVmImageCombo() {
			return vmImageCombo;
		}
		public void setVmImageCombo(VMImageCombo vmImageCombo) {
			this.vmImageCombo = vmImageCombo;
		}
		
	public VMRequest(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setVmRealmCombo(new VMRealmCombo(null));
			this.setVmImageCombo(new VMImageCombo(this.getVmRealmCombo().getSelected(), null));
		}
	}

	@Hidden
	@ServiceMethod(callByContent=true, eventBinding="change", bindingFor="vmRealmCombo", bindingHidden=true)
	public void changeVMRealmCombo(){
		this.setVmImageCombo(new VMImageCombo(this.getVmRealmCombo().getSelected(), null));
	}

	@Override
	public void beforeComplete() throws Exception {
		this.setVmRealmId(this.getVmRealmCombo().getSelected());
		this.setVmImageId(this.getVmImageCombo().getSelected());
	}
	
	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
