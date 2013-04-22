package org.uengine.codi.mw3.project;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Choice;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.dto.RealmsDDTO;

@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
public class VMTempleteCombo extends Choice {

	public VMTempleteCombo(){
		
	}
	
	public VMTempleteCombo(String defaultValue){
		this.onLoad(defaultValue);
	}
	
	public void onLoad(String defaultValue){
		DeltaCloud deltaCloud = new DeltaCloud();
		
		try {
			// realm case
			ArrayList<RealmsDDTO> realms = deltaCloud.realms();
			
			System.out.println("Realms Case");
			for(int i=0;i<realms.size();i++){
				RealmsDDTO realm = realms.get(i);
				
				if("available".equals(realm.getState())){
					this.add(realm.getName(), realm.getId());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
