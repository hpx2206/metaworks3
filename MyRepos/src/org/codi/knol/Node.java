package org.codi.knol;

import java.util.ArrayList;
import org.metaworks.dao.Database;
import org.metaworks.dao.*;
import org.metaworks.annotation.*;

public class Node extends Database<INode> implements INode{

	java.lang.String text;
		public java.lang.String getText(){ return text; }
		public void setText(java.lang.String text){ this.text = text; }

	Node parent;
		public Node getParent(){ return parent; }
		public void setParent(Node parent){ this.parent = parent; }

	INode childs;
    @NonLoadable
    @NonSavable
		public INode getChilds(){ return childs; }
		public void setChilds(INode childs){ this.childs = childs; }
                
    @ServiceMethod(callByContent = true, target="self")
    public INode[] addSibling() throws Exception{
        createDatabaseMe();
        Node newNode = new Node();
        newNode.setParent(getParent());
        
        return new INode[]{databaseMe(), newNode};
    }

}