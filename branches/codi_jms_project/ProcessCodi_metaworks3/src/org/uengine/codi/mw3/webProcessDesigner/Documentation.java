package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.codi.mw3.model.MemoWorkItem;

public class Documentation implements Serializable , ContextAware{
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	MemoWorkItem memoWorkItem;
	@Hidden	
		public MemoWorkItem getMemoWorkItem() {
			return memoWorkItem;
		}
		public void setMemoWorkItem(MemoWorkItem memoWorkItem) {
			this.memoWorkItem = memoWorkItem;
		}
	String defId;
		@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
	@Hidden
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	public Documentation(){
		setMetaworksContext(new MetaworksContext());
		setDesc(new WebEditor());
		setAttachfile1(new MetaworksFile());
	}
	
	String title;
		@Face(displayName="제목")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
	WebEditor desc;
		@Face(displayName="내용")
		public WebEditor getDesc() {
			return desc;
		}
		public void setDesc(WebEditor desc) {
			this.desc = desc;
		}
	String Url;
		@Face(displayName="URL")
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
	MetaworksFile attachfile1;
		@Face(displayName="파일첨부1")
		public MetaworksFile getAttachfile1() {
			return attachfile1;
		}
		public void setAttachfile1(MetaworksFile attachfile1) {
			this.attachfile1 = attachfile1;
		}
	
	@AutowiredFromClient
	public ExtendAttribute extendAttribute;
	
	
	@ServiceMethod(callByContent=true, when="edit")
	public Object[] add(){
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
//		extendAttribute.participateGroupList.add(this);
		extendAttribute.documentationList.add(this);
		}
		return new Object[]{new  Remover(new ModalWindow() , true) ,   extendAttribute};
	}


	
	
}
