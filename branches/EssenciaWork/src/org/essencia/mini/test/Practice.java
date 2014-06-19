package org.essencia.mini.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.essencia.ide.ResourceNode;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;

@Face(displayName="Practice", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder"},values={"id,name,description"})
public class Practice {
	
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	private String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	private List<Component> alphas;
		public List<Component> getAlphas() {
			return alphas;
		}
		public void setAlphas(List<Component> alphas) {
			this.alphas = alphas;
		}

	private List<Component> activities;
		public List<Component> getActivities() {
			return activities;
		}
		public void setActivities(List<Component> activities) {
			this.activities = activities;
		}

	private List<Component> competencies;
		public List<Component> getCompetencies() {
			return competencies;
		}
		public void setCompetencies(List<Component> competencies) {
			this.competencies = competencies;
		}
	
	public Practice(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		alphas = new ArrayList<Component>();
		competencies = new ArrayList<Component>();
		activities = new ArrayList<Component>();
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] create() throws Exception{
		
		
		
		ComponentTreeNode node = new ComponentTreeNode();
		node.setName(this.getName());
		node.setId(this.getId());
		node.setPath("D:/codi/essencia/"+ File.separatorChar + node.getName());
//		node.setType(TreeNode.TYPE_FOLDER);
		node.setFolder(false);
		
		File file = new File(node.getPath());
		
		if(!file.exists())
			file.mkdirs();
		
		return new Object[]{new Remover(new ModalWindow()), new ToAppend(this,node), new Refresh(node)};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}