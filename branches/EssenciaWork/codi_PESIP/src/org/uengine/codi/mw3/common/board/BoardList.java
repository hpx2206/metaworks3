package org.uengine.codi.mw3.common.board;

import javax.validation.Valid;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;

public class BoardList implements ContextAware {

	public BoardList(){	
		this(null, MetaworksContext.WHEN_VIEW);
	}
	
	public BoardList(Object list, String metaworksContext){
		this(null, list, metaworksContext);
	}
	
	public BoardList(String id, Object list, String metaworksContext){
		setId(id);
		
		setBoardList(list);
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		getMetaworksContext().setHow(metaworksContext);
	}
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	Object boardList;
		@Valid
		public Object getBoardList() {
			return boardList;
		}
		public void setBoardList(Object boardList) {
			this.boardList = boardList;
		}	

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
}
