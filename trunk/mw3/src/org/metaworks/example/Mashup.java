package org.metaworks.example;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs", options={"hideLabels", "tabsBottom"}, values={"true", "true"})
public class Mashup {

   public Mashup(){
		setMashupWiki("test");
	}

   String mashupWiki;
      @Face(displayName="위키피디아")
      public String getMashupWiki() {
         return mashupWiki;
      }
      public void setMashupWiki(String mashupWiki) {
         this.mashupWiki = mashupWiki;
      }
 
}