package org.essencia.mini.view;

import java.util.List;

public interface TreeNodable {
	String getId();
	String getName();
	String getParentId();
	List<? extends TreeNodable> getChildList();
	
}
