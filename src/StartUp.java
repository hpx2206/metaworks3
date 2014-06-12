import org.essencia.model.MainIDE;
import org.essencia.model.MainPanel;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

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

	@ServiceMethod(callByContent=true)
	public MainPanel load(){
		MainIDE mainIDE = new MainIDE();
		mainIDE.load();
		
		return new MainPanel(mainIDE);
		
	}
}
