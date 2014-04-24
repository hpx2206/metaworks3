package org.uengine.codi.mw3.project;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Choice;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.Image;

@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
public class VMImageCombo extends Choice {

	String realmId;
		public String getRealmId() {
			return realmId;
		}
		public void setRealmId(String realmId) {
			this.realmId = realmId;
		}

	public VMImageCombo(){		
		
	}
	
	public VMImageCombo(String realmId, String defaultValue){
		this.onLoad(realmId, defaultValue);
		
		
		if(this.getSize() > 0)
			this.select(0);
	}
	
	public void onLoad(String realmId, String defaultValue){
		DeltaCloud deltaCloud = new DeltaCloud();
		
		try {
			// realm case
			ArrayList<Image> images = deltaCloud.images();
			
			for(int i=0;i<images.size();i++){
				Image image = images.get(i);
				
				System.out.println(image.getDescription());
								
				String imageRealmId = image.getDescription().split("_")[9];
				if(realmId == null || imageRealmId.equals(realmId))
					this.add(image.getName(), image.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
