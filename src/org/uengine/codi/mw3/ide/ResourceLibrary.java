package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceLibrary {
	Map<String, List<String>> resourceMap;
		public Map<String, List<String>> getResourceMap() {
			return resourceMap;
		}
		public void setResourceMap(Map<String, List<String>> resourceMap) {
			this.resourceMap = resourceMap;
		}

	public ResourceLibrary(){
		this.setResourceMap(new HashMap<String, List<String>>());
	}
	
	public void load(String basePath){
		File file = new File(basePath);
		if(file.exists()){
			if(file.isDirectory()){
				this.read(file);
			}
		}
	}
	
	public void read(File file){
		for(String childFilename : file.list()){
			if(childFilename.startsWith("."))
				continue;
			
			File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilename);
			
			if(childFile.isDirectory()){
				this.read(childFile);
			}else{
    			List<String> pathList = null;				    			
    			if(resourceMap.containsKey(childFilename))
    				pathList = (ArrayList<String>)resourceMap.get(childFilename);
    			else
    				pathList = new ArrayList<String>();
    			
    			pathList.add(file.getAbsolutePath());
    			
    			resourceMap.put(childFilename, pathList);

			}
			
		}
		
	}
}
