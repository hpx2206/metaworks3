package org.uengine.codi.mw3.ide.dictionary;

public class DictionaryManagement {
	
	public DictionaryManagement() {
	}
	
	DictionaryCategory dictionaryCategory;
		public DictionaryCategory getDictionaryCategory() {
			return dictionaryCategory;
		}
		public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
			this.dictionaryCategory = dictionaryCategory;
		}
	DictionaryTopMenu dictionaryTopMenu;
		public DictionaryTopMenu getDictionaryTopMenu() {
			return dictionaryTopMenu;
		}
		public void setDictionaryTopMenu(DictionaryTopMenu dictionaryTopMenu) {
			this.dictionaryTopMenu = dictionaryTopMenu;
		}
	DictionaryView dictionaryView;
		public DictionaryView getDictionaryView() {
			return dictionaryView;
		}
		public void setDictionaryView(DictionaryView dictionaryView) {
			this.dictionaryView = dictionaryView;
		}

	public void load() {
		dictionaryCategory = new DictionaryCategory();
		dictionaryTopMenu = new DictionaryTopMenu();
		dictionaryView = new DictionaryView();
		
	}

}
