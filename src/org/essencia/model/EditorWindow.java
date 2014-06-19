package org.essencia.model;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class EditorWindow extends Window{

	public EditorWindow(){
		super();
	}
	public EditorWindow(Object panel){
		super(panel);
	}	
	public EditorWindow(Object panel, String title){
		super(panel, title);
	}	
}