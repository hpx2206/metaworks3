package com.defaultcompany.sample;

import org.metaworks.annotation.ServiceMethod;

public class ComtableList {
	IComtable comtable;
		public IComtable getComtable() {
			return comtable;
		}
		public void setComtable(IComtable comtable) {
			this.comtable = comtable;
		}
	
	@ServiceMethod
	public void load() throws Exception {
		setComtable(Comtable.list());
	}
}
