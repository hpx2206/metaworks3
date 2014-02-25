package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class ConsultingServiceChoiceList {
	
	String selfTest;
		public String getSelfTest() {
			return selfTest;
		}
		public void setSelfTest(String selfTest) {
			this.selfTest = selfTest;
		}

	String regionMatching;
		public String getRegionMatching() {
			return regionMatching;
		}
		public void setRegionMatching(String regionMatching) {
			this.regionMatching = regionMatching;
		}
}
