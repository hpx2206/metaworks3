package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.component.SelectBox;

public class AddPropertyPopup implements ContextAware {
	@AutowiredFromClient
	public MetadataXmlEditor metadataXmlEditor;
	@AutowiredFromClient
	public MetadataContentDesigner metadataContentDesigner;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String filePath;
		@Id
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
	String key; 
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	String value; 
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	SelectBox type;
		public SelectBox getType() {
			return type;
		}
		public void setType(SelectBox type) {
			this.type = type;
		}
		
	public AddPropertyPopup(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		type = new SelectBox();
		type.add("텍스트", "string");
		type.add("파일", "file");
		type.add("이미지", "img");
		type.add("프로세스", "process");
	}
	
}
