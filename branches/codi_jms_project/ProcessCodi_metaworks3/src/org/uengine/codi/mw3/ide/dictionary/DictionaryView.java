package org.uengine.codi.mw3.ide.dictionary;


public class DictionaryView {
	public DictionaryView() {
	}
	
	String dicType;
		public String getDicType() {
			return dicType;
		}
		public void setDicType(String dicType) {
			this.dicType = dicType;			
		}
	IDictionaryItem iDictionaryItem;
		public IDictionaryItem getiDictionaryItem() {
			return iDictionaryItem;
		}
	
		public void setiDictionaryItem(IDictionaryItem iDictionaryItem) {
			this.iDictionaryItem = iDictionaryItem;
		}

	public Object load() throws Exception {
		DictionaryItem dictionaryItem = new DictionaryItem();
		iDictionaryItem = dictionaryItem.load(this.dicType);
		
		return iDictionaryItem;
	}
	
}
