package org.metaworks.widget.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.ObjectInstance;
import org.metaworks.dao.IDAO;

public class DatabaseGrid extends ObjectGrid{

	public DatabaseGrid() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setObjectData(IDAO dao) throws Exception {

		setData(dao);
	}

	
	
}
