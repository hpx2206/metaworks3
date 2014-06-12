import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class StartUp {

	public StartUp(){
	}

	Object content;
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

	@ServiceMethod
	public void load(){
		Layout layout = new Layout();
		layout.setWest("West");
		layout.setCenter("Center");
		
		this.setContent(layout);
	}
}
