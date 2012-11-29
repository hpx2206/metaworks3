package org.metaworks.component;

public class HorizontalSplitBox {

	public final static String ALIGN_TOP			= "top";
	public final static String ALIGN_BOTTOM			= "bottom";
			
	int fixHeight;
			public int getFixHeight() {
				return fixHeight;
			}
			public void setFixHeight(int fixHeight) {
				this.fixHeight = fixHeight;
			}

	String align;
		public String getAlign() {
			return align;
		}
		public void setAlign(String align) {
			this.align = align;
		}

	Object top;
		public Object getTop() {
			return top;
		}
		public void setTop(Object top) {
			this.top = top;
		}
		
	Object bottom;
		public Object getBottom() {
			return bottom;
		}
		public void setBottom(Object bottom) {
			this.bottom = bottom;
		}
		
	Splitter splitter;
		public Splitter getSplitter() {
			return splitter;
		}
		public void setSplitter(Splitter splitter) {
			this.splitter = splitter;
		}		
		
	public void useSplitter(boolean isUse){
		if(isUse){
			Splitter splitter = new Splitter();
			splitter.setType(Splitter.TYPE_HORIZONTAL);
			splitter.setAlign(this.getAlign());
			splitter.setSize(5);
			
			this.setSplitter(splitter);
		}else{
			this.setSplitter(null);
		}
	}		
		
	public HorizontalSplitBox() {
		this.setAlign(ALIGN_TOP);
	}
	
}
