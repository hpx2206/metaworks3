package org.uengine.codi.mw3.model;

import java.util.ArrayList;

public class WorldMapChart {
	
	ArrayList<PastOrderInformation> pastOrderInformationList;
		public ArrayList<PastOrderInformation> getPastOrderInformationList() {
			return pastOrderInformationList;
		}
		public void setPastOrderInformationList(
				ArrayList<PastOrderInformation> pastOrderInformationList) {
			this.pastOrderInformationList = pastOrderInformationList;
		}

	public void load(IOrderInformation iOrderInformation) throws Exception {
		if(pastOrderInformationList == null) {
			pastOrderInformationList = new ArrayList<PastOrderInformation>();
		}
		
		while(iOrderInformation.next()) {
			PastOrderInformation pastOrderInformation = new PastOrderInformation();
			pastOrderInformation.setRegionName(iOrderInformation.getCountryname());
			pastOrderInformation.setNumber(iOrderInformation.getCount());
			pastOrderInformation.setOrderRegDate(iOrderInformation.getRegdate().toString());
			pastOrderInformation.setProjectName(iOrderInformation.getProjectname());
			
			pastOrderInformationList.add(pastOrderInformation);
		}
	}

}
