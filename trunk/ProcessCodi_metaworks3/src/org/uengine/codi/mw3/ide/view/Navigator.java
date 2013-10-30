package org.uengine.codi.mw3.ide.view;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.ResourceTree;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.libraries.RolePerspective;
import org.uengine.codi.mw3.ide.libraries.SearchResultPanel;
import org.uengine.codi.mw3.model.SearchBox;

public class Navigator {

	String id;
		@Id
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		@Name
		@Hidden
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String type;
		@Hidden
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	ResourceTree resourceTree;
		public ResourceTree getResourceTree() {
			return resourceTree;
		}
		public void setResourceTree(ResourceTree resourceTree) {
			this.resourceTree = resourceTree;
		}
		
	RolePerspective	rolePerspective;
		public RolePerspective getRolePerspective() {
			return rolePerspective;
		}
		public void setRolePerspective(RolePerspective rolePerspective) {
			this.rolePerspective = rolePerspective;
		}

	SearchBox searchBox;
		public SearchBox getSearchBox() {
			return searchBox;
		}
		public void setSearchBox(SearchBox searchBox) {
			this.searchBox = searchBox;
		}
		
	public Navigator(){
		this.setId("navigator");
		this.setType(this.getId());
		this.setName("$Navigator");
//		this.rolePerspective = new RolePerspective();
		this.searchBox = new SearchBox();
	}
	
	public void load(Workspace workspace){
		ResourceNode workspaceNode = new ResourceNode();
		workspaceNode.setId(workspace.getId());
		workspaceNode.setRoot(true);
		workspaceNode.setHidden(true);
		
		for(Project project : workspace.getProjects())
			workspaceNode.add(new ResourceNode(project));
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(workspace.getId());
		resourceTree.setNode(workspaceNode);
		
		this.setResourceTree(resourceTree);
	}
	
	
	public void load(String sourceCodeBase, String projectName){
		ResourceNode projectNode = new ResourceNode();
		projectNode.setRoot(true);
		projectNode.setFolder(true);
		projectNode.setType(ResourceNode.TYPE_FOLDER);
		projectNode.setId("");
		projectNode.setName(projectName);
		
		ResourceTree resourceTree = new ResourceTree();
		resourceTree.setId(sourceCodeBase);
		resourceTree.setNode(projectNode);

		this.setResourceTree(resourceTree);		
		
	}
	
	@ServiceMethod(keyBinding="enter", target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow search() throws Exception {
		SearchResultPanel searchResultPanel = new SearchResultPanel();
		searchResultPanel.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(800);
		modalWindow.setHeight(600);
		modalWindow.setTitle("Process List");
		modalWindow.setPanel(searchResultPanel);
		
		return modalWindow;
	}
}
