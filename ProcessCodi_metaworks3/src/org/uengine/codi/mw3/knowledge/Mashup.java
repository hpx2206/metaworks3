package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs",
options={"hideLabels", "tabsBottom"},
values={"true", "true"})

public class Mashup {
	
	
	public Mashup(){
		setMashupGoogleImage(new MashupGoogleImage());
		setMashupLMS(new MashupLMS());
		setMashupKMS(new MashupKMS());
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
		
		
	MashupLMS mashupLMS;
	@Face(displayName="학습 콘텐츠")
		public MashupLMS getMashupLMS() {
			return mashupLMS;
		}
	
		public void setMashupLMS(MashupLMS mashupLMS) {
			this.mashupLMS = mashupLMS;
		}

	MashupKMS mashupKMS;
	@Face(displayName="지식맵")
	@AutowiredToClient
		public MashupKMS getMashupKMS() {
			return mashupKMS;
		}
	
		public void setMashupKMS(MashupKMS mashupKMS) {
			this.mashupKMS = mashupKMS;
		}
		
}
