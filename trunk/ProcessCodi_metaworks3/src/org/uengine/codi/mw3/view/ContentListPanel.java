package org.uengine.codi.mw3.view;

import org.metaworks.annotation.Order;

public class ContentListPanel {
	
	Object content;
	@Order(value=1)
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
}
