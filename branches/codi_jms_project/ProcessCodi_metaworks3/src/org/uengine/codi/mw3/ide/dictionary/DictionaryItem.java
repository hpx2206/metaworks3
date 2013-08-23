package org.uengine.codi.mw3.ide.dictionary;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.knowledge.ITopicNode;
import org.uengine.codi.mw3.knowledge.TopicTitle;
import org.uengine.codi.mw3.model.Popup;

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
	
	
//	public IDictionaryItem load() throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select * from dictionary dic");
//		sb.append(" where dic.dicId =?dicId");
//		sb.append(" and dic.dicType =?dicType");
//		sb.append(" and dic.dicName =?dicName");
//		sb.append(" and dic.dicDescription =?dicDescription");
//		sb.append(" and dic.dicLinked =?dicLinked");
//		
//		IDictionaryItem dao = (IDictionaryItem)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), IDictionaryItem.class); 
//		dao.set("dicId", DIC_ID);
//		dao.set("dicType", this.getDicType());
//		dao.set("dicName", this.getDicName());
//		dao.set("dicDiscription", this.getDicDescription());
//		dao.set("dicLinked", this.getDicLinked());
//		dao.select();
//		
//		return dao; 
//		
//	}
	
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
//		StringBuffer sb = new StringBuffer();
//		sb.append("update dictionary ");
//		sb.append(" where dicType=?dicType");
//		sb.append(" order by dicName desc");
	}
	
	public void delete() throws Exception {
		this.deleteDatabaseMe();
		
	}
	

	
}
