package org.uengine.codi.mw3.tadpole;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

public class Tadpole {
	
	@AutowiredFromClient
	public Session session;
	
	Layout layout;
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
		
	PageNavigator pageNavigator;
		public PageNavigator getPageNavigator() {
			return pageNavigator;
		}
		public void setPageNavigator(PageNavigator pageNavigator) {
			this.pageNavigator = pageNavigator;
		}		

		
	@ServiceMethod
	public void load() throws Exception {
		
		//top
		TadpoleTopPanel top = new TadpoleTopPanel(session);
		
		
		//center
		String ip = GlobalContext.getPropertyString("pole.call.ip","localhost");
		String port = GlobalContext.getPropertyString("pole.call.port","80");
		String db  = GlobalContext.getPropertyString("pole.call.db","/tadpole");
		
		String url = "http://" + ip + ":" + port + db;
		
		IFrame goTadPole = new IFrame();
		goTadPole.setSrc(url);
		
		TadpoleCenterPanel center = new TadpoleCenterPanel();
		center.setTadpoleHome(goTadPole);
		
		Layout mainLayout = new Layout();
		
		mainLayout.setName("center");
		mainLayout.setId("main");
		mainLayout.setCenter(center);
		
		Layout storeLayout = new Layout();
		storeLayout.setNorth(top);
		storeLayout.setCenter(mainLayout);
		
		this.setLayout(storeLayout);
		
		pageNavigator = new PageNavigator();
		
	}
	
	public void createUserAtTadpole(String parameter) throws Exception {
		

		String ip = GlobalContext.getPropertyString("pole.call.ip","localhost");
		String port = GlobalContext.getPropertyString("pole.call.port","80");
		String db  = GlobalContext.getPropertyString("pole.call.db","/tadpole");
		
		String sUrl = "http://" + ip + ":" + port + db + "/create" + parameter;
		
		URL url;					//URL 주소 객체
		URLConnection connection;	//URL접속을 가지는 객체
		InputStream is;				//URL접속에서 내용을 읽기위한 Stream
		InputStreamReader isr;
		BufferedReader br;
		try{
			
			//URL객체를 생성하고 해당 URL로 접속한다..
			url = new URL(sUrl);
			connection = url.openConnection();
			
			//내용을 읽어오기위한 InputStream객체를 생성한다..
			is = connection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			//내용을 읽어서 화면에 출력한다..
			String buf = null;
			
			while(true){
				buf = br.readLine();
				if(buf == null) break;
				System.out.println(buf);
			}
			
		}catch(IOException ioe){
			System.err.println("IOException " + ioe);
			ioe.printStackTrace();
		}

	}
}
