package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.ILogin;
import org.uengine.codi.mw3.admin.PageNavigator;

public class Tray {

	public Tray(){
		
	}

	ArrayList<TrayItem> trayItems;
	@Face(options="alignment", values="horizontal")
		public ArrayList<TrayItem> getTrayItems() {
			return trayItems;
		}
		public void setTrayItems(ArrayList<TrayItem> trayItems) {
			this.trayItems = trayItems;
		}

		
	@ServiceMethod(callByContent=true)
	public void addTrayItem(){
		
		if(trayItems==null)
			trayItems = new ArrayList<TrayItem>();
		
		trayItems.add(getTargetItem());
	}
	
	TrayItem targetItem;
		public TrayItem getTargetItem() {
			return targetItem;
		}
		public void setTargetItem(TrayItem targetItem) {
			this.targetItem = targetItem;
		}
			
	@ServiceMethod(mouseBinding="drop", callByContent=true)
	public void drop() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IInstance){
			Instance instance = (Instance)clipboard;
			TrayItem ti = new TrayItem();
			ti.setInstId(instance.getInstId() + "");
			ti.setTitle(instance.databaseMe().getName());
			setTargetItem(ti);
			addTrayItem();
		}
	}
	
	@AutowiredFromClient
	public Session session;
}

