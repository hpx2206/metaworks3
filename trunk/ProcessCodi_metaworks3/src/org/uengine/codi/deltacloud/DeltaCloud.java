package org.uengine.codi.deltacloud;

import java.util.ArrayList;

import org.uengine.codi.deltacloud.dto.HardwareProfilesCDTO;
import org.uengine.codi.deltacloud.dto.HardwareProfilesDDTO;
import org.uengine.codi.deltacloud.dto.ImagesDDTO;
import org.uengine.codi.deltacloud.dto.ImagesSF;
import org.uengine.codi.deltacloud.dto.RealmsDDTO;

public class DeltaCloud {

	public final static String url = "http://192.168.50.14/deltacloud/api";
	
	public ArrayList<RealmsDDTO> realms() throws Exception {
		return RealmsSF.realmsXmlParser(this.url);
		 
	}
	
	public ArrayList<ImagesDDTO> images() throws Exception {
		return ImagesSF.imagesXmlParser(this.url);
	}
	
	public ArrayList<HardwareProfilesDDTO> hardwareProfiles() throws Exception {
		return HardwareProfilesSF.hardwareProfilesXmlParser(this.url);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeltaCloud deltaCloud = new DeltaCloud();
		
		try {
			// realm case
			ArrayList<RealmsDDTO> arrayList = deltaCloud.realms();
			
			System.out.println("Realms Case");
			for(int i=0;i<arrayList.size();i++){
				System.out.println("id : "+arrayList.get(i).getId());
				System.out.println("name : "+arrayList.get(i).getName());
				System.out.println("state : "+arrayList.get(i).getState());
				System.out.println("limit : "+arrayList.get(i).getLimit());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// image case
			ArrayList<ImagesDDTO> arrayList = deltaCloud.images();
			
			System.out.println("Images Case");
			for(int i=0;i<arrayList.size();i++){
				System.out.println("id : "+arrayList.get(i).getId());
				System.out.println("name : "+arrayList.get(i).getName());
				System.out.println("description : "+arrayList.get(i).getDescription());
				System.out.println("architecture : "+arrayList.get(i).getArchitecture());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			// hardwareprofiles case
			ArrayList<HardwareProfilesDDTO> arrayList = deltaCloud.hardwareProfiles();
			
			System.out.println("HardwareProfiles Case");
			for(int i=0;i<arrayList.size();i++){
				System.out.println("id : "+arrayList.get(i).getId());
				System.out.println("name : "+arrayList.get(i).getName());
				
				int size = arrayList.get(i).getHardwareProfilesCDTOs().length;
				HardwareProfilesCDTO[] dtos = new HardwareProfilesCDTO[size];
				for(int j=0;j<size;j++){
					dtos=arrayList.get(i).getHardwareProfilesCDTOs();
					System.out.println("value : "+dtos[j].getValue()+" unit : "+dtos[j].getUnit()+" kind : "+dtos[j].getKind()+" name : "+dtos[j].getName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
