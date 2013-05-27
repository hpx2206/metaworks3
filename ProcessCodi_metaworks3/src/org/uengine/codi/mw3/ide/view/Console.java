package org.uengine.codi.mw3.ide.view;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;

@Face(displayName="$Console")
public class Console {

	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String name;
		@Name
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	public Console(){
		this.setId("console");
		this.setType(this.getId());
		this.setName("$Console");
		
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					new TopicListener().run();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}).start();
		*/
	}
		
}
