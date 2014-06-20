package org.essencia.mini.view;

import java.util.List;

public interface TreeDataSource {
	String getId();
	String getName();
	String getParentId();
	List<TreeDataSource> getChildList();
	
}
