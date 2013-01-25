package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.component.TreeNode;

public class VariableTreeNode extends TreeNode {

	public void load(ArrayList<PrcsValiable> prcsValiableList){
		this.setName("valiable");
		this.setLoaded(true);
		this.setExpanded(true);
		
		for(int i = 0; i < prcsValiableList.size(); i++){
			PrcsValiable prcsValiable = prcsValiableList.get(i);
			String nameAttr = prcsValiable.getName();
			
			RoleTreeNode node = new RoleTreeNode();
			node.setId(nameAttr);
			node.setName(nameAttr);
			node.setParentId("valiables");
			node.setType(TreeNode.TYPE_FILE_HTML);
			node.setLoaded(true);
			node.setExpanded(true);
			this.add(node);
			
			// TODO 처음에 로딩할 필요가 없다면 아래 루프 부분은 클릭시 작동하는걸로 뺀다. 
			// RoleTreeNode 를 따로 만들어 주어야 한다.
			/*
			String typeIdAttr = prcsValiable.getTypeId();
			String typeAttr = prcsValiable.getDataType().getSelected();
			
			try{
				if( "complexType".equals(typeAttr)){
					WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType( typeIdAttr.substring(0, typeIdAttr.lastIndexOf(".")).replaceAll("/", ".") ); 
					WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
					FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
					for(int j=0; j<fields.length; j++){
						WebFieldDescriptor wfd = wfields[j];
//						FieldDescriptor fd = fields[i];
						RoleTreeNode childNode = new RoleTreeNode();
						childNode.setId(nameAttr + "." + wfd.getName());
						childNode.setName(wfd.getName());
						childNode.setParentId(nameAttr);
						childNode.setType(TreeNode.TYPE_FILE_TEXT);
						node.add(childNode);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}*/
		}
	}
	/*
	 * service method
	 */
	@Override
	public Object expand() throws Exception { 
		// Override method
		
		return null;
	}
}
