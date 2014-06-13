package org.uengine.codi.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.uengine.codi.mw3.model.ClientSessions;

public class SessionUtil {

	public static HashMap<String, String> toSessionIdMap(HashMap<String, ClientSessions> companyUsers){
		HashMap<String, String> pushUserMap = new HashMap<String, String>();
		
		Iterator iterator = companyUsers.entrySet().iterator();
		
		while(iterator.hasNext()){
			Entry entry = (Entry)iterator.next();
			ClientSessions tempSessions = (ClientSessions) entry.getValue();
			Iterator tempIter = tempSessions.clientSessionInfo.entrySet().iterator();
			
			while(tempIter.hasNext()){
				Entry tempEntry = (Entry)tempIter.next();
				System.out.println(tempEntry.getKey());
				
				pushUserMap.put((String) tempEntry.getKey(), (String) tempEntry.getKey());
			}
		}
		
		return pushUserMap;
	}
}
