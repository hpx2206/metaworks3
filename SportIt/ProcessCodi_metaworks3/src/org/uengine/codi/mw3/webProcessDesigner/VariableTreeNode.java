package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.FieldDescriptor;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dwr.MetaworksRemoteService;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.ProcessVariable;

public class VariableTreeNode extends TreeNode {
	
	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
	
	
	public void load(ArrayList<ProcessVariable> prcsValiableList) throws ClassNotFoundException{
		this.setName("variable");
		this.setLoaded(true);
		this.setExpanded(true);
		
		for(int i = 0; i < prcsValiableList.size(); i++){
			ProcessVariable processVariable = prcsValiableList.get(i);
			String nameAttr = processVariable.getName();
			// TODO 처음에 로딩할 필요가 없다면 아래 루프 부분은 클릭시 작동하는걸로 뺀다. 
			Class type = Class.forName(processVariable.getTypeInputter());
			if( type == null ) continue;
			
			VariableTreeNode node = new VariableTreeNode();
			node.setId(nameAttr);
			node.setTreeId(this.getTreeId());
			node.setName(nameAttr);
			node.setParentId(this.getName());
			node.setType(TreeNode.TYPE_FILE_HTML);
			node.setLoaded(true);
			node.setExpanded(true);
			node.setFolder(true);
			node.setAlign(this.getAlign());
			
			if( type == ComplexType.class){
				try {
					Object typeAttr = processVariable.getDefaultValue();
					ComplexType complexType = (ComplexType)typeAttr;
					String typeIdAttr = complexType.getTypeId();
					if( typeIdAttr != null && !"".equals(typeIdAttr) ){
						String formName = typeIdAttr.substring(1, typeIdAttr.length() -1); 
						node.setClassName(formName);
					}
					node.setChild(loadExpand(node));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.add(node);
		}
	}
	public ArrayList<TreeNode> loadExpand(VariableTreeNode node) throws Exception{
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( node.getClassName() ); 
		WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
		FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
		for(int j=0; j<fields.length; j++){
			WebFieldDescriptor wfd = wfields[j];
			VariableTreeNode childNode = new VariableTreeNode();
			// 주의 : id에 "." 이 들어간다면 Tree 에서 Id검색을 할수가 없다. 그리하여 "-" 으로 데이터 셋팅함
			childNode.setId(node.getId() + "-" + wfd.getName());
			childNode.setTreeId(node.getTreeId());
			childNode.setName(wfd.getName());
			childNode.setParentId(node.getId());
			childNode.setAlign(node.getAlign());
			if( wfd.getClassName().startsWith("org.uengine.codi.mw3")){
				childNode.setFolder(true);
				childNode.setLoaded(false);
				childNode.setExpanded(false);
				childNode.setClassName(wfd.getClassName());
				childNode.setType(TreeNode.TYPE_FOLDER);
			}else{
				childNode.setFolder(false);
				childNode.setType(TreeNode.TYPE_FILE_TEXT);
			}
			child.add(childNode);
		}
		return child;
	}
	/*
	 * service method
	 */
	@ServiceMethod( callByContent=true ,target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception {
		if( this.getClassName() != null ){
			Tree parentTree = new Tree();
			parentTree.setId(this.getTreeId());
			return new ToAppend( parentTree , this.loadExpand(this));
		}
		return null;
	}
}
