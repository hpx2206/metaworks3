package org.uengine.codi.mw3.ide.form;

import org.metaworks.widget.layout.Layout;

public class NewFormDesignerLayout {

	Layout layout;
	 	public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
	
	public void load(String alias) {
		
		NewFormDesigner formDesigner = new NewFormDesigner();
		formDesigner.setAlias(alias);
		formDesigner.load();
		
		Layout outerLayout = new Layout();
		
		outerLayout.setOptions("togglerLength_open:10, west__size:'60%', east__size:'40%'");
		outerLayout.setCenter(formDesigner.getForm());		
		
		Layout eastLayout = new Layout();
		eastLayout.setOptions("togglerLength_open:10, south__size:'60%', north__size:'40%'");
		eastLayout.setCenter(formDesigner.getProperties());
		eastLayout.setSouth(formDesigner.getMenu());
		
		outerLayout.setEast(eastLayout);
		
		setLayout(outerLayout);
	}
}
