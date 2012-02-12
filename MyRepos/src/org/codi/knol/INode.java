package org.codi.knol;

import org.metaworks.annotation.*;
import org.metaworks.dao.*;

public interface INode extends IDAO{
        @Id
		public java.lang.String getText();
		public void setText(java.lang.String text);
    
        @NonLoadable
        @NonSavable
		public INode getChilds();
		public void setChilds(INode childs);
  
  
      @ServiceMethod(callByContent = true, target="self")
    public INode[] addSibling() throws Exception;

 }