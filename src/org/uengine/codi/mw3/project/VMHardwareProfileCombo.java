package org.uengine.codi.mw3.project;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Choice;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.HardwareProfile;

@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
public class VMHardwareProfileCombo extends Choice {

	public VMHardwareProfileCombo(){
		
	}
	
	public VMHardwareProfileCombo(String defaultValue){
		this.onLoad(defaultValue);
		
		if(this.getSize() > 0)
			this.select(0);
	}
	
	public void onLoad(String defaultValue){
		DeltaCloud deltaCloud = new DeltaCloud();
		
		try {
			ArrayList<HardwareProfile> hardwareProfiles = deltaCloud.hardwareProfiles();
			
			for(int i=0;i<hardwareProfiles.size();i++){
				HardwareProfile hardwareProfile = hardwareProfiles.get(i);
				
				String name = hardwareProfile.getName();
				name += " | " + hardwareProfile.getMemory() + "MB RAM";
				name += " | " + hardwareProfile.getCpu() + " VCPU";
				
				this.add(name, hardwareProfile.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
