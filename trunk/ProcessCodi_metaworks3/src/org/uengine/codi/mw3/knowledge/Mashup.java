package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs",
options={"hideLabels", "tabsBottom"},
values={"true", "true"})

public class Mashup {
	
	
	public Mashup(){
		setMashupGoogleImage(new MashupGoogleImage());
	}

	MashupGoogleImage mashupGoogleImage;
	@Face(displayName="Image")
	@AutowiredToClient
	
		public MashupGoogleImage getMashupGoogleImage() {
			return mashupGoogleImage;
		}
	
		public void setMashupGoogleImage(MashupGoogleImage mashupGoogleImage) {
			this.mashupGoogleImage = mashupGoogleImage;
		}

	
}
