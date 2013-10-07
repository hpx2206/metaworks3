package org.uengine.codi.mw3.project;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Choice;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.Realm;

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
			ArrayList<Realm> realms = deltaCloud.realms();
			
			for(int i=0;i<realms.size();i++){
				Realm realm = realms.get(i);
				
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
