package org.uengine.codi.mw3.ide.menu;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.CloudTab;
import org.uengine.codi.mw3.ide.CloudWindow;

public class WindowContextMenu extends CloudMenu {

	public WindowContextMenu(){
		this.setId("WindowContext");
		this.setName("WindowContext");
		this.setContext(true);
		
		this.add(new MenuItem("close", "Close"));
		this.add(new MenuItem("closeOther", "Close Other"));
		this.add(new MenuItem("closeAll", "Close All"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object close(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof CloudTab){
			CloudTab cloudTab = (CloudTab)clipboard;
			return new Remover(cloudTab);			
		}
		
		return null;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] closeOther(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof CloudTab){
			CloudTab cloudTab = (CloudTab)clipboard;
			
			return new Object[]{new Remover(this)};
//			return new Object[]{new ToAppend(new Remover(new CloudTab(cloudTab.getParentId())), this)};
		}
		return null;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object[] closeAll(){
		Object clipboard = session.getClipboard();
		if(clipboard instanceof CloudTab){
			CloudTab cloudTab = (CloudTab)clipboard;
			
			return new Object[]{new Refresh(new CloudWindow(cloudTab.getParentId()))};
		}
		
		return null;
	}
	
}
