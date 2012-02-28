package org.metaworks.widget.grid;

import org.metaworks.dao.IDAO;


public class DatabaseGrid extends ObjectGrid{

	public DatabaseGrid() throws Exception {
		super();
	}

	public void setObjectData(Class dataClass, IDAO dao) throws Exception {
		
		setObjectType(dataClass);
		setData(dao);
	}
}
