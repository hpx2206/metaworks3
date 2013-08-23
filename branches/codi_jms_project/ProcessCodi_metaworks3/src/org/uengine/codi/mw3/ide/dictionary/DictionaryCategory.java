package org.uengine.codi.mw3.ide.dictionary;


public class DictionaryCategory{
	public static final String TYPE_ACTIVITY = "Activity";
	public static final String TYPE_DOCUMENTATION = "Documentation";
	public static final String TYPE_PROCESS = "Process";
	public static final String TYPE_ROLE = "Role";
	
	public DictionaryCategory() {
		activityDictionary = new DictionaryItem(DictionaryCategory.TYPE_ACTIVITY);
		documentationDictionary = new DictionaryItem(DictionaryCategory.TYPE_DOCUMENTATION);
		processDictionary = new DictionaryItem(DictionaryCategory.TYPE_PROCESS);
		roleDictionary = new DictionaryItem(DictionaryCategory.TYPE_ROLE);
	}
	
	DictionaryItem activityDictionary;
		public DictionaryItem getActivityDictionary() {
			return activityDictionary;
		}
		public void setActivityDictionary(DictionaryItem activityDictionary) {
			this.activityDictionary = activityDictionary;
		}
	DictionaryItem documentationDictionary;
		public DictionaryItem getDocumentationDictionary() {
			return documentationDictionary;
		}
		public void setDocumentationDictionary(DictionaryItem documentationDictionary) {
			this.documentationDictionary = documentationDictionary;
		}
	DictionaryItem processDictionary;
		public DictionaryItem getProcessDictionary() {
			return processDictionary;
		}
		public void setProcessDictionary(DictionaryItem processDictionary) {
			this.processDictionary = processDictionary;
		}
	DictionaryItem roleDictionary;
		public DictionaryItem getRoleDictionary() {
			return roleDictionary;
		}
		public void setRoleDictionary(DictionaryItem roleDictionary) {
			this.roleDictionary = roleDictionary;
		}
	DictionaryView dictionaryView;
		public DictionaryView getDictionaryView() {
			return dictionaryView;
		}
		public void setDictionaryView(DictionaryView dictionaryView) {
			this.dictionaryView = dictionaryView;
		}

}
