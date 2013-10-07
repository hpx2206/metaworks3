package org.uengine.codi.deltacloud;

import java.util.ArrayList;

import org.uengine.kernel.GlobalContext;

public class DeltaCloud {

	public String url;
	
	public DeltaCloud() {
		this.url = (String)GlobalContext.getPropertyString("deltacloud.api.url");
	}
	
	public ArrayList<Realm> realms() throws Exception {
		Realm realm = new Realm(this.url);
		
		return realm.list();
	}
	
	public ArrayList<Image> images() throws Exception {
		Image image = new Image(this.url);
		
		return image.list();
	}
	
	public ArrayList<HardwareProfile> hardwareProfiles() throws Exception {
		HardwareProfile hardwareProfile = new HardwareProfile(this.url);
		
		return hardwareProfile.list();
	}
	
	public Instance createInstance(String name, String realmId, String imageId, String hardwareProfileId) throws Exception {
		Instance instance = new Instance(this.url);
		
		instance.setName(name);
		instance.setRealmId(realmId);
		instance.setImageId(imageId);
		instance.setHardwareProfileId(hardwareProfileId);
		
		return instance.create();
	}
	
	public WorkflowResult workflowStatus(String workflowId) throws Exception {
		WorkflowResult wfResult = new WorkflowResult(this.url);
		wfResult.setId(workflowId);
		
		return wfResult.requestStatus();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DeltaCloud deltaCloud = new DeltaCloud();
		
		try {
			// realm case
			ArrayList<Realm> arrayList = deltaCloud.realms();
			
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
			ArrayList<Image> arrayList = deltaCloud.images();
			
			System.out.println("Images Case");
			for(int i=0;i<arrayList.size();i++){
				
				String descr = arrayList.get(i).getDescription();
				String[] parser = descr.split("_");
				
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
			ArrayList<HardwareProfile> arrayList = deltaCloud.hardwareProfiles();
			
			System.out.println("HardwareProfiles Case");
			for(int i=0;i<arrayList.size();i++){
				System.out.println("id : "+arrayList.get(i).getId());
				System.out.println("name : "+arrayList.get(i).getName());
				System.out.println("cpu : "+arrayList.get(i).getCpu());
				System.out.println("memory : "+arrayList.get(i).getMemory());
				System.out.println("architecture : "+arrayList.get(i).getArchitecture());
				System.out.println("storage : "+arrayList.get(i).getStorage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
