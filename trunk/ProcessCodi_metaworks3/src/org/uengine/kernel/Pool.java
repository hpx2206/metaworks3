package org.uengine.kernel;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Order;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ApplyProperties;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.designer.web.ActivityView;
import org.uengine.kernel.designer.web.DynamicDrawGeom;
import org.uengine.kernel.designer.web.PoolView;

import com.defaultcompany.external.DefaultCompanyPoolResolutionContext;

@Face(ejsPath="genericfaces/ActivityFace.ejs", options={"fieldOrder"},values={"name,description"})
public class Pool implements java.io.Serializable, ContextAware{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String name;
	@Face(displayName="$activityName")
	@Order(1)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	TextContext description = TextContext.createInstance();
	@Face(displayName="$activityDisplayName")
	@Order(2)
		public TextContext getDescription() {
			return description;
		}
		public void setDescription(TextContext string) {
			description = string;
		}
		public void setDescription(String string) {
			description.setText(string);
		}
		
	PoolResolutionContext poolResolutionContext;
	@Face(displayName="웹서비스 선택")
	@Order(3)
		public PoolResolutionContext getPoolResolutionContext() {
			if( poolResolutionContext == null ){
				String poolResolutionContexts = GlobalContext.getPropertyString("poolresolutioncontexts", null);
				if( poolResolutionContexts != null ){
					try{
						Class<?> clazz = Class.forName(poolResolutionContexts);
						if( clazz.equals(DefaultCompanyPoolResolutionContext.class)){
							poolResolutionContext = (DefaultCompanyPoolResolutionContext)clazz.newInstance();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			return poolResolutionContext;
		}
		public void setPoolResolutionContext(PoolResolutionContext poolResolutionContext) {
			this.poolResolutionContext = poolResolutionContext;
		}
		
	PoolView	poolView;
		@Hidden
		public PoolView getPoolView() {
			return poolView;
		}
		public void setPoolView(PoolView poolView) {
			this.poolView = poolView;
		}
	transient String currentEditorId;
	@Hidden
		public String getCurrentEditorId() {
			return currentEditorId;
		}
		public void setCurrentEditorId(String currentEditorId) {
			this.currentEditorId = currentEditorId;
		}	
	public Pool(){
		setMetaworksContext(new MetaworksContext());
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] apply(){
		
		if ( this.getDescription() == null || "".equals(this.getDescription().getText())){
			this.setDescription(this.getName());
		}
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setId(this.getPoolView().getId());
		
		return new Object[]{new ApplyProperties(this.getPoolView().getId() , this), new Remover(modalWindow, true)  };
	}

	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setId(this.getPoolView().getId());
		return new Object[]{new Remover(modalWindow , true)};
		
	}
}
