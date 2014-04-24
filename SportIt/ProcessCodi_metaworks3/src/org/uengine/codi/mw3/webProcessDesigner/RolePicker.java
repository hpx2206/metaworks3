package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.ToOpener;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.kernel.RoleResolutionContext;

import com.defaultcompany.organization.DefaultCompanyRoleResolutionContext;

public class RolePicker implements ContextAware {
	boolean loaded;
		@Hidden
		public boolean isLoaded() {
			return loaded;
		}
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}

	DefaultCompanyRoleResolutionContext roleResolutionContext;
		public DefaultCompanyRoleResolutionContext getRoleResolutionContext() {
			return roleResolutionContext;
		}
		public void setRoleResolutionContext(
				DefaultCompanyRoleResolutionContext roleResolutionContext) {
			this.roleResolutionContext = roleResolutionContext;
		}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	@Hidden
	@ServiceMethod(callByContent=true)
	public void load() {
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setHow("pick");
		
		this.setMetaworksContext(metaworksContext);
		this.setLoaded(true);
		
		if( roleResolutionContext == null ){
			roleResolutionContext = new DefaultCompanyRoleResolutionContext();
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] select(){
		return new Object[]{new Remover(new ModalWindow()),new ToOpener(this.getRoleResolutionContext()), new ToEvent(this.getRoleResolutionContext(), EventContext.EVENT_CHANGE)};
	}
}
