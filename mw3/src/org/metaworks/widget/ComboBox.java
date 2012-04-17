package org.metaworks.widget;

import java.util.ArrayList;

import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;

public abstract class ComboBox{

	ArrayList<String> items;
		public ArrayList<String> getItems() {
			return items;
		}
		public void setItems(ArrayList<String> items) {
			this.items = items;
		}
	

	String selectedItem;
		public String getSelectedItem() {
			return selectedItem;
		}
		public void setSelectedItem(String selectedItem) {
			this.selectedItem = selectedItem;
		}
		
	abstract public void onLoad();

	@ServiceMethod
	abstract public Object[] SelectDone();
	
}