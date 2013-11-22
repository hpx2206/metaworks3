package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="genericfaces/Tab.ejs",
options={"hideLabels", "tabsBottom"},
values={"true", "true"})
public class Mashup {
	
	public final static String USE_MASHUP_IMG = GlobalContext.getPropertyString("mashup.img.use", "0");
	public final static String USE_MASHUP_LMS = GlobalContext.getPropertyString("mashup.lms.use", "0");
	public final static String USE_MASHUP_KMS = GlobalContext.getPropertyString("mashup.kms.use", "0");
	public final static String USE_MASHUP_VIDEO = GlobalContext.getPropertyString("mashup.video.use", "0");
	public final static String USE_MASHUP_SLIDE = GlobalContext.getPropertyString("mashup.slide.use", "0");
	public final static String USE_MASHUP_WIKI = GlobalContext.getPropertyString("mashup.wiki.use", "0");
	
	public Mashup(){
		if("1".equals(Mashup.USE_MASHUP_IMG))
			setMashupGoogleImage(new MashupGoogleImage());
		if("1".equals(Mashup.USE_MASHUP_LMS))
			setMashupLMS(new MashupLMS());
		if("1".equals(Mashup.USE_MASHUP_KMS))
			setMashupKMS(new MashupKMS());
		if("1".equals(Mashup.USE_MASHUP_VIDEO))
			setMashupVideo(new MashupVideo());
		if("1".equals(Mashup.USE_MASHUP_SLIDE))
			setMashupSlideshare(new MashupSlideshare());
		if("1".equals(Mashup.USE_MASHUP_WIKI))
			setMashupWiki(new MashupWiki());
	}

	MashupGoogleImage mashupGoogleImage;
		@Face(displayName="$Image")
		@AutowiredToClient
		public MashupGoogleImage getMashupGoogleImage() {
			return mashupGoogleImage;
		}
		public void setMashupGoogleImage(MashupGoogleImage mashupGoogleImage) {
			this.mashupGoogleImage = mashupGoogleImage;
		}
		
	MashupWiki mashupWiki;
	@Face(displayName="$Wiki")
		public MashupWiki getMashupWiki() {
			return mashupWiki;
		}
		public void setMashupWiki(MashupWiki mashupWiki) {
			this.mashupWiki = mashupWiki;
		}

	MashupLMS mashupLMS;
	@Face(displayName="$Contents")
		public MashupLMS getMashupLMS() {
			return mashupLMS;
		}
		public void setMashupLMS(MashupLMS mashupLMS) {
			this.mashupLMS = mashupLMS;
		}

	MashupKMS mashupKMS;
	@Face(displayName="$Knolegemap")
	@AutowiredToClient
		public MashupKMS getMashupKMS() {
			return mashupKMS;
		}
	
		public void setMashupKMS(MashupKMS mashupKMS) {
			this.mashupKMS = mashupKMS;
		}
		
	MashupVideo mashupVideo;
	@Face(displayName="$Youtube")
	@AutowiredToClient
		public MashupVideo getMashupVideo() {
			return mashupVideo;
		}
		
		public void setMashupVideo(MashupVideo mashupVideo) {
			this.mashupVideo = mashupVideo;
		}
	
	MashupSlideshare mashupSlideshare;
	@Face(displayName="$Slide")
	@AutowiredToClient
		public MashupSlideshare getMashupSlideshare() {
			return mashupSlideshare;
		}
		
		public void setMashupSlideshare(MashupSlideshare mashupSlideshare) {
			this.mashupSlideshare = mashupSlideshare;
		}
	
}
