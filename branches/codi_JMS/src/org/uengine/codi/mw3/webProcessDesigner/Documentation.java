package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.admin.WebEditor;

@Face(displayName="절차서 속성 정보", ejsPath="dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Documentation.ejs",
	ejsPathMappingByContext = {
		"{when:'edit', face : 'dwr/metaworks/genericfaces/FormFace.ejs'}"
	}
	, options={"fieldOrder"}
	,values={"departManagement,regulation,define,description"})
public class Documentation implements Serializable , ContextAware{
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String departManagement;
		@Face(displayName="목적")
		public String getDepartManagement() {
			return departManagement;
		}
		public void setDepartManagement(String departManagement) {
			this.departManagement = departManagement;
		}

	WebEditor description;
	@Face(displayName="정의")
		public WebEditor getDescription() {
			return description;
		}
		public void setDescription(WebEditor description) {
			this.description = description;
		}
	String processMeasure;
		@Face(displayName="참조")
		public String getProcessMeasure() {
			return processMeasure;
		}
		public void setProcessMeasure(String processMeasure) {
			this.processMeasure = processMeasure;
		}
	String document;
		@Face(displayName="번호")	
		public String getDocument() {
			return document;
		}
		public void setDocument(String document) {
			this.document = document;
		}

	String reference;
	@Face(displayName="이름")
		public String getReference() {
			return reference;
		}
		public void setReference(String reference) {
			this.reference = reference;
		}

	WebEditor regulation;
		@Face(displayName="적용범위")
		public WebEditor getRegulation() {
			return regulation;
		}
		public void setRegulation(WebEditor regulation) {
			this.regulation = regulation;
		}

		
	String title;
	@Face(displayName="종류")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	String url;
	@Face(displayName="Full Name")
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	String subject;
	@Face(displayName="목적")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	String rangeApplication;
	@Face(displayName="적용범위")
		public String getRangeApplication() {
			return rangeApplication;
		}
		public void setRangeApplication(String rangeApplication) {
			this.rangeApplication = rangeApplication;
		}

	WebEditor define;
	@Face(displayName="참조")
		public WebEditor getDefine() {
			return define;
		}
		public void setDefine(WebEditor define) {
			this.define = define;
		}

	
	
	MetaworksFile attachfile1;
		@Face(displayName="$attachfile1")
		public MetaworksFile getAttachfile1() {
			return attachfile1;
		}
		public void setAttachfile1(MetaworksFile attachfile1) {
			this.attachfile1 = attachfile1;
		}
	MetaworksFile attachfile2;
	@Face(displayName="$attachfile2")
		public MetaworksFile getAttachfile2() {
			return attachfile2;
		}
		public void setAttachfile2(MetaworksFile attachfile2) {
			this.attachfile2 = attachfile2;
		}
	MetaworksFile attachfile3;
		@Face(displayName="$attachfile3")
		public MetaworksFile getAttachfile3() {
			return attachfile3;
		}
		public void setAttachfile3(MetaworksFile attachfile3) {
			this.attachfile3 = attachfile3;
		}
		
	String type;
	@Face(displayName="종류")	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	String number;
	@Face(displayName="번호")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	String name;
	@Face(displayName="이름")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	String fullName;
	@Face(displayName="Full Name")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
		
		
		
	public Documentation(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		setDescription(new WebEditor());
		setDefine(new WebEditor());
		setRegulation(new WebEditor());
		setAttachfile1(new MetaworksFile());
		setAttachfile2(new MetaworksFile());
		setAttachfile3(new MetaworksFile());
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Documentation)) return false;
		int returnFlag =  0;
		Documentation doc = (Documentation)obj;
		
		
		if( !(this.title == null && doc.title == null ) && !(this.title != null && this.title.equals(doc.getTitle()))){
			returnFlag++;
		}
		if( !(this.document == null && doc.document == null ) && !(this.document != null && this.document.equals(doc.getDocument()))){
			returnFlag++;
		}
		if( !(this.reference == null && doc.reference == null ) && !(this.reference != null && this.reference.equals(doc.getReference()))){
			returnFlag++;
		}
		if( !(this.url == null && doc.url == null ) && !(this.url != null && this.url.equals(doc.getUrl()))){
			returnFlag++;
		}
		if( !(this.departManagement == null && doc.departManagement == null ) && !(this.departManagement != null && this.departManagement.equals(doc.getDepartManagement()))){
			returnFlag++;
		}
		if( !(this.regulation == null && doc.regulation == null ) && !(this.regulation != null && this.regulation.equals(doc.getRegulation()))){
			returnFlag++;
		}
		if( !(this.description == null && doc.description == null ) &&
				!(this.description != null && this.description.getContents() != null 
				&& doc.getDescription() != null && doc.getDescription().getContents() != null && this.description.getContents().equals(doc.getDescription().getContents()))){
			returnFlag++;
		}
		
		if(returnFlag == 0){
			return true;
		}else{
			return false;
		}
		
	}
		
}
