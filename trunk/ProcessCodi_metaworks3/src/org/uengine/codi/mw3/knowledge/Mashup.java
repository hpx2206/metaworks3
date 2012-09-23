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
		setMashupVideo(new MashupVideo());
		setMashupSlideshare(new MashupSlideshare());
	}

	MashupGoogleImage mashupGoogleImage;
	@Face(displayName="이미지")
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
		
	MashupVideo mashupVideo;
	@Face(displayName="유튜브")
	@AutowiredToClient
		public MashupVideo getMashupVideo() {
			return mashupVideo;
		}
		
		public void setMashupVideo(MashupVideo mashupVideo) {
			this.mashupVideo = mashupVideo;
		}
	
	MashupSlideshare 	mashupSlideshare;
	@Face(displayName="SlideShare")
	@AutowiredToClient
	public MashupSlideshare getMashupSlideshare() {
		return mashupSlideshare;
	}
	
	public void setMashupSlideshare(MashupSlideshare mashupSlideshare) {
		this.mashupSlideshare = mashupSlideshare;
	}
	
}
