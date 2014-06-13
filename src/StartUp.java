import org.essencia.model.MainIDE;
import org.essencia.model.MainPanel;
import org.metaworks.annotation.ServiceMethod;

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
	public void load() throws Exception{
		MainIDE mainIDE = new MainIDE();
		mainIDE.load();
		
		this.setContent(new MainPanel(mainIDE));
	}
	
}
