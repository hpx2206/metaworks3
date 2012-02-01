package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(displayName="답글")
public class CommentWorkItem extends WorkItem{
	
	public CommentWorkItem(){
		setType("comment");
	}

	@Hidden(on=false)
	@Range(size = 80)
	@Face(displayName = "")
	public String getTitle() {
		return super.getTitle();
	}
	
}
