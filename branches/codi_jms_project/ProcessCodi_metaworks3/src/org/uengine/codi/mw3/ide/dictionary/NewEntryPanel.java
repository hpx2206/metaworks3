package org.uengine.codi.mw3.ide.dictionary;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.codi.mw3.model.Popup;

public class NewEntryPanel implements ContextAware{
	public NewEntryPanel() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	WebEditor webEditor;
		public WebEditor getWebEditor() {
			return webEditor;
		}
		public void setWebEditor(WebEditor webEditor) {
			this.webEditor = webEditor;
		}
	SelectBox categorySelect;
		public SelectBox getCategorySelect() {
			return categorySelect;
		}
		public void setCategorySelect(SelectBox categorySelect) {
			this.categorySelect = categorySelect;
		}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
//		
	public void load() throws Exception {
		webEditor = new WebEditor();
		
		categorySelect = new SelectBox();
		categorySelect.add("activity", DictionaryCategory.TYPE_ACTIVITY);
		categorySelect.add("documentation", DictionaryCategory.TYPE_DOCUMENTATION);
		categorySelect.add("process", DictionaryCategory.TYPE_PROCESS);
		categorySelect.add("role", DictionaryCategory.TYPE_ROLE);
		categorySelect.setId("category"); 
		
	}
	
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] confirm() throws Exception {
		
		DictionaryItem dictionaryItem = new DictionaryItem();
		
		dictionaryItem.setDicType(categorySelect.getSelected());
		dictionaryItem.setDicName(this.getName());
		dictionaryItem.setDicDescription(webEditor.getContents());
		dictionaryItem.setDicLinked("임의");
		
		dictionaryItem.createDatabaseMe();
		return new Object[] {new Remover(new Popup(), true) };
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Object cancel() throws Exception {
		return new Remover(new Popup(), true);
	}
}
