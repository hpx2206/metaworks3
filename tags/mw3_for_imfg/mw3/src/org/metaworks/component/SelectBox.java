package org.metaworks.component;

import org.metaworks.common.ChoiceBox;

public class SelectBox extends ChoiceBox{
	int selectSize;
		public int getSelectSize() {
			return selectSize;
		}
		public void setSelectSize(int selectSize) {
			this.selectSize = selectSize;
		}
	String selectStyle;
		public String getSelectStyle() {
			return selectStyle;
		}
		public void setSelectStyle(String selectStyle) {
			this.selectStyle = selectStyle;
		}
		
	public SelectBox(){
		super();
		setSelectSize(1);
		setSelectStyle("");
	}
}
