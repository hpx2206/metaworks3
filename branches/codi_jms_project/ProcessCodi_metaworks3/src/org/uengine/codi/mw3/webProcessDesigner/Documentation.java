package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.admin.WebEditor;

public class Documentation implements Serializable , ContextAware{
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
//	ArrayList<ParticipateGroup> participateGroupList;
//	@Hidden
//		public ArrayList<ParticipateGroup> getParticipateGroupList() {
//			return participateGroupList;
//		}
//		public void setParticipateGroupList(
//				ArrayList<ParticipateGroup> participateGroupList) {
//			this.participateGroupList = participateGroupList;
//		}
		
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
	
	String departManagement;
		@Face(displayName="$departManagement")
		public String getDepartManagement() {
			return departManagement;
		}
		public void setDepartManagement(String departManagement) {
			this.departManagement = departManagement;
		}

	String description;
		@Face(displayName="$description")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	
	String processMeasure;
		@Face(displayName="$processMeasure")
		public String getProcessMeasure() {
			return processMeasure;
		}
		public void setProcessMeasure(String processMeasure) {
			this.processMeasure = processMeasure;
		}
	String document;
		@Face(displayName="$document")	
		public String getDocument() {
			return document;
		}
		public void setDocument(String document) {
			this.document = document;
		}
	String Reference;
		@Face(displayName="$Reference")
		public String getReference() {
			return Reference;
		}
		public void setReference(String reference) {
			Reference = reference;
		}
	String regulation;
		@Face(displayName="$regulation")
		public String getRegulation() {
			return regulation;
		}
		public void setRegulation(String regulation) {
			this.regulation = regulation;
		}

	
	public Documentation(){
		setMetaworksContext(new MetaworksContext());
//		setDesc(new WebEditor());
//		setAttachfile1(new MetaworksFile());
	}
	
	String title;
		@Face(displayName="$Subject")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
//	WebEditor desc;
//		@Face(displayName="$Contents")
//		public WebEditor getDesc() {
//			return desc;
//		}
//		public void setDesc(WebEditor desc) {
//			this.desc = desc;
//		}
	String Url;
		@Face(displayName="$URL")
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
//	MetaworksFile attachfile1;
//		@Face(displayName="$attachfile1")
//		public MetaworksFile getAttachfile1() {
//			return attachfile1;
//		}
//		public void setAttachfile1(MetaworksFile attachfile1) {
//			this.attachfile1 = attachfile1;
//		}
//		MetaworksFile attachfile2;
//		@Face(displayName="$attachfile2")
//		public MetaworksFile getAttachfile2() {
//			return attachfile2;
//		}
//		public void setAttachfile2(MetaworksFile attachfile2) {
//			this.attachfile2 = attachfile2;
//		}
//		MetaworksFile attachfile3;
//		@Face(displayName="$attachfile3")
//		public MetaworksFile getAttachfile3() {
//			return attachfile3;
//		}
//		public void setAttachfile1(MetaworksFile attachfile3) {
//			this.attachfile3 = attachfile3;
//		}
		
}
