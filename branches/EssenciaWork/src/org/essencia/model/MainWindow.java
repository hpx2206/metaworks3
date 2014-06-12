package org.essencia.model;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class MainWindow extends Window{

	public MainWindow(){
		super();
	}
	public MainWindow(Object panel){
		super(panel);
	}	
	public MainWindow(Object panel, String title){
		super(panel, title);
	}	
}
