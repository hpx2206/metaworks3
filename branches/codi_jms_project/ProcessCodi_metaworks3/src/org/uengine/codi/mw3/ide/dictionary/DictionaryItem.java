package org.uengine.codi.mw3.ide.dictionary;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.knowledge.ITopicNode;

public class DictionaryItem extends Database<IDictionaryItem> implements IDictionaryItem{
	
	public static int DIC_ID = 1;

	public DictionaryItem(){
		this(null);
		this.getMetaworksContext().setWhen(WHEN_NEW);
	}
	public DictionaryItem(String dicType){
		this.dicType = dicType;
	}
	
	@Hidden
	int dicId;
		public int getDicId() {
			return dicId;
		}
		public void setDicId(int dicId) {
			this.dicId = dicId;
		}
	String dicType;	
		public String getDicType() {
			return dicType;
		}
		public void setDicType(String dicType) {
			this.dicType = dicType;
		}
	String dicName;
		public String getDicName() {
			return dicName;
		}
		public void setDicName(String dicName) {
			this.dicName = dicName;
		}
	String dicDescription;
		public String getDicDescription() {
			return dicDescription;
		}
		public void setDicDescription(String dicDescription) {
			this.dicDescription = dicDescription;
		}
	String dicLinked;
		public String getDicLinked() {
			return dicLinked;
		}
		public void setDicLinked(String dicLinked) {
			this.dicLinked = dicLinked;
		}
	@Hidden
	String startDate;
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
	@Hidden
	String modDate;
		public String getModDate() {
			return modDate;
		}
		public void setModDate(String modDate) {
			this.modDate = modDate;
		}
	DictionaryView dictionaryView;
		public DictionaryView getDictionaryView() {
			return dictionaryView;
		}
		public void setDictionaryView(DictionaryView dictionaryView) {
			this.dictionaryView = dictionaryView;
		}
		
	@ServiceMethod(callByContent = true)
	public Object[] clickDictionary() throws Exception {
		if(dictionaryView == null) 
			dictionaryView = new DictionaryView();
		
		dictionaryView.setDicType(this.getDicType());
		dictionaryView.load();
		
		return new Object[] { dictionaryView };
		
	}
	
	public IDictionaryItem load(String dicType) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append(" from dictionary");
		sb.append(" where dicType=?dicType");
		sb.append(" order by dicName desc");
		
		IDictionaryItem iDictionaryItem = (IDictionaryItem)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IDictionaryItem.class); 
		iDictionaryItem.set("dicType", dicType);
		iDictionaryItem.set("dicName", this.getDicName());
		iDictionaryItem.set("dicDescription", this.getDicDescription());
		iDictionaryItem.set("dicLinked", this.getDicLinked());
		iDictionaryItem.select();
		
		return iDictionaryItem;
	}
	
	public void modify() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update dictionary ");
		sb.append(" set dicType=?dicType");
		sb.append(" and dicName=?dicName");
		sb.append(" and dicDescription=?dicDescription");
		sb.append(" where dicId=?dicId");
		
		IDictionaryItem iDictionaryItem = (IDictionaryItem) sql(IDictionaryItem.class, sb.toString());
		iDictionaryItem.set("dicId", this.dicId);
		iDictionaryItem.set("dicType", this.getDicType());
		iDictionaryItem.set("dicName", this.getDicName());
		iDictionaryItem.set("dicDescription", this.getDicDescription());
		iDictionaryItem.update();
	}
	
	public void delete() throws Exception {
		this.deleteDatabaseMe();
		
	}

	
}
