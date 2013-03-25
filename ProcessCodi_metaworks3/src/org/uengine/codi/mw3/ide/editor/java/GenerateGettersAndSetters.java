package org.uengine.codi.mw3.ide.editor.java;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.util.CodiStringUtil;

public class GenerateGettersAndSetters {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	
	Tree fieldTree;
		public Tree getFieldTree() {
			return fieldTree;
		}
		public void setFieldTree(Tree fieldTree) {
			this.fieldTree = fieldTree;
		}
		
	String test;
		
	String test2;
	
	public GenerateGettersAndSetters(){
	}
	
	public void load(){
		JavaParser parser = new JavaParser();
		parser.setContent(this.getContent());
		parser.create();
		
		TreeNode node = new TreeNode();
		node.setName("root");
		node.setId("root");
		node.setType(TreeNode.TYPE_FOLDER);
		node.setExpanded(true);
		node.setRoot(true);
		node.setLoaded(true);
		node.setFolder(true);
		node.setHidden(true);
		
		if(parser.getFields() != null){
			for(JavaField field : parser.getFields()){
				if(field.isStatics())
					continue;

				String upperCasefieldName = CodiStringUtil.firstUpperCase(field.getName());
				String getterName = "get" + upperCasefieldName;
				String setterName = "set" + upperCasefieldName;
				
				TreeNode getter = null;
				if(!parser.getMethods().contains(new JavaMethod(getterName))){
					getter = new TreeNode();
					getter.setType(TreeNode.TYPE_METHOD);
					getter.setLoaded(true);
					getter.setId("get" + CodiStringUtil.firstUpperCase(field.getName()));
					getter.setName(getter.getId());
				}

				TreeNode setter = null;
				if(!parser.getMethods().contains(new JavaMethod(setterName))){
					setter = new TreeNode();
					setter.setType(TreeNode.TYPE_METHOD);
					setter.setLoaded(true);
					setter.setId("set" + CodiStringUtil.firstUpperCase(field.getName()));
					setter.setName(setter.getId() + "(" + field.getType() +")");
				}

				if(getter != null || setter != null){
					TreeNode fieldNode = new TreeNode();
					fieldNode.setLoaded(true);
					fieldNode.setType(TreeNode.TYPE_FIELD);
					fieldNode.setFolder(true);
					fieldNode.setId(field.getName());
					fieldNode.setName(field.getName());

					if(getter != null)
						fieldNode.add(getter);
					if(setter != null)
						fieldNode.add(setter);

					node.add(fieldNode);
				}
			}
		}
		
		Tree fieldTree = new Tree();
		fieldTree.setShowCheckBox(true);
		fieldTree.setNode(node);
		
		setFieldTree(fieldTree);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] ok(){
		
		for(TreeNode node : this.getFieldTree().getCheckNodes()){
			if(TreeNode.TYPE_METHOD.equals(node.getType()))
			System.out.println(node.getName());
		}
		
		return new Object[]{new Remover(new ModalWindow())};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}

}
