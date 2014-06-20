package org.essencia.mini.view;

import java.util.ArrayList;
import java.util.List;

import org.essencia.mini.kernel.Component;

public class DataSourceFactory {
	
	
	private static class TreeDataSourceImpl implements TreeDataSource {
		
		private Component component;
		
		private TreeDataSourceImpl(Component component) {
			this.component = component;
		}

		@Override
		public String getId() {
			return component.getId();
		}

		@Override
		public String getName() {
			return component.getName();
		}

		@Override
		public String getParentId() {
			return component.getParentComponentId();
		}

		@Override
		public List<TreeDataSource> getChildList() {
			List<Component> childComponents = component.getChildComponents();
			List<TreeDataSource> childList = new ArrayList<TreeDataSource>();
			for( Component child : childComponents ) {
				TreeDataSourceImpl datasource = new TreeDataSourceImpl(child);
				childList.add(datasource);
			}
			return childList;
		}
		
	}
	
	public static TreeDataSource getTreeDataSource(Component component) {
		return new DataSourceFactory.TreeDataSourceImpl(component);
	}
	
	
	
}
