package org.uengine.codi.mw3.model;

import java.util.ArrayList;

public class PublicServiceIntroduceDetailList {
	
	ArrayList<PublicServiceIntroduceService> serviceList;
		public ArrayList<PublicServiceIntroduceService> getServiceList() {
			return serviceList;
		}
		public void setServiceList(ArrayList<PublicServiceIntroduceService> serviceList) {
			this.serviceList = serviceList;
		}

	ArrayList<PublicServiceIntroduceConstruct> constructList;
		public ArrayList<PublicServiceIntroduceConstruct> getConstructList() {
			return constructList;
		}
		public void setConstructList(
				ArrayList<PublicServiceIntroduceConstruct> constructList) {
			this.constructList = constructList;
		}
}
