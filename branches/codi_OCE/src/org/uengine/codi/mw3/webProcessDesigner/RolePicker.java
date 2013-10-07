package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

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
		/*
	ArrayList<RoleResolutionContext> roleResolutionContext;
		public ArrayList<RoleResolutionContext> getRoleResolutionContext() {
			return roleResolutionContext;
		}
		public void setRoleResolutionContext(
				ArrayList<RoleResolutionContext> roleResolutionContext) {
			this.roleResolutionContext = roleResolutionContext;
		}
		*/
		

	/*	RoleResolutionContext[] roleResolutionContext;
		public RoleResolutionContext[] getRoleResolutionContext() {
			return roleResolutionContext;
		}
		public void setRoleResolutionContext(
				RoleResolutionContext[] roleResolutionContext) {
			this.roleResolutionContext = roleResolutionContext;
		}
	}*/
	


	@Hidden
	@ServiceMethod
	public void load() {
		MetaworksContext metaworksContext = new MetaworksContext();
		metaworksContext.setHow("pick");
		
		this.setMetaworksContext(metaworksContext);
		this.setLoaded(true);
		
		this.setRoleResolutionContext(new DefaultCompanyRoleResolutionContext());
		//this.setRoleResolutionContext(new ArrayList<RoleResolutionContext>());
		//this.getRoleResolutionContext().add(new DefaultCompanyRoleResolutionContext());
/*		roleResolutionContext = new RoleResolutionContext[]{
			new DefaultCompanyRoleResolutionContext()
		};
*/	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] select(){
		return new Object[]{new Remover(new ModalWindow()), new ToOpener(this.getRoleResolutionContext())};
	}
}
