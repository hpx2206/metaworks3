package org.uengine.kernel.designer.web;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.codi.mw3.webProcessDesigner.PropertiesWindow;
import org.uengine.kernel.Activity;
import org.uengine.kernel.Pool;

public class PoolView extends CanvasDTO  implements ContextAware {

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	transient Pool pool;
		public Pool getPool() {
			return pool;
		}
		public void setPool(Pool pool) {
			this.pool = pool;
		}

	transient PropertiesWindow propertiesWindow;
	@Hidden
		public PropertiesWindow getPropertiesWindow() {
			return propertiesWindow;
		}
		public void setPropertiesWindow(PropertiesWindow propertiesWindow) {
			this.propertiesWindow = propertiesWindow;
		}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = new ModalWindow();
		
		Pool pool = (Pool)propertiesWindow.getPanel();
		pool.setCurrentEditorId(this.getEditorId());
		pool.setMetaworksContext(propertiesWindow.getMetaworksContext());
		pool.setPoolView(this);
		
		popup.setPanel(pool);
		popup.setId(this.getId());				// 꼭 필요함
		popup.setTitle(pool.getDescription() == null || "".equals(pool.getDescription().getText()) ? pool.getDescription().getText() : "pool setting..");
		
		popup.setWidth(1000);
		popup.setHeight(600);
		
		return popup;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drawActivitysOnDesigner() throws Exception{
		DynamicDrawGeom ddg = this.getPool().getPoolResolutionContext().drawActivitysOnDesigner(); 
		ddg.setEditorId(this.getEditorId());
		ddg.setParentGeomId(this.getId());
		ArrayList<Activity> activityList = ddg.getActivityList();
		for(Activity activity : activityList){
			ActivityView activityView = activity.getActivityView();
			activityView.setEditorId(this.getEditorId());
			activityView.setViewType(this.getViewType());
		}
		return new Object[]{ddg};
	}
}
