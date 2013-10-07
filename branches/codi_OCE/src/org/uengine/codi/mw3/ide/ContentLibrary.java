package org.uengine.codi.mw3.ide;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentLibrary {
	Map<String, List<String>> packageMap;
		public Map<String, List<String>> getClassMap() {
			return classMap;
		}
		public void setClassMap(Map<String, List<String>> classMap) {
			this.classMap = classMap;
		}
	
	Map<String, List<String>> classMap;
		public Map<String, List<String>> getPackageMap() {
			return packageMap;
		}
		public void setPackageMap(Map<String, List<String>> packageMap) {
			this.packageMap = packageMap;
		}
	
	Map<String, List<String>> annotationMap;
		public Map<String, List<String>> getAnnotationMap() {
			return annotationMap;
		}
		public void setAnnotationMap(Map<String, List<String>> annotationMap) {
			this.annotationMap = annotationMap;
		}
	
	public void load(CloudClassLoader ccl){
		Map<String, List<String>> packageMap = new HashMap<String, List<String>>();
		Map<String, List<String>> classMap = new HashMap<String, List<String>>();
		Map<String, List<String>> annotationMap = new HashMap<String, List<String>>();
		
		try {
			if( ccl != null  && ccl.getCl() != null ){
				for(URL url : ccl.getCl().getURLs()){
					if(url.getFile().endsWith(".jar")){
						net.sf.jazzlib.ZipInputStream zipIn = new net.sf.jazzlib.ZipInputStream(url.openStream());
						net.sf.jazzlib.ZipEntry zipEntry;	
					    try {				    	
					    	while((zipEntry = zipIn.getNextEntry()) != null) {
					    		String name = zipEntry.getName();
					    		
					    		if(name.endsWith(".class")){
					    			/*
					    			 * parsing path to info
					    			 */
					    			name = name.substring(0, name.length() - 6);
					    			name = name.replaceAll("/", ".");
					    			
					    			String packageName = null;
					    			String className = null;
					    			
					    			int pos = name.lastIndexOf(".");
					    			if(pos > -1){
					    				packageName = name.substring(0, pos);
					    				className = name.substring(pos + 1);
					    			}else{
					    				className = name;
					    			}
					    			
					    			/*
					    			 * make package map
					    			 */
					    			if(packageName != null){
					    				List<String> classList = null;
					    				
					    				if(packageMap.containsKey(packageName))
					    					classList = (ArrayList<String>)packageMap.get(packageName);
					    				else
					    					classList = new ArrayList<String>();
					    				
					    				classList.add(className);
					    				
					    				packageMap.put(packageName, classList);
	
					    			}else{
					    				packageName = "";
					    			}
	
					    			/*
					    			 * make class map
					    			 */
					    			List<String> packageList = null;				    			
					    			if(classMap.containsKey(className))
					    				packageList = (ArrayList<String>)classMap.get(className);
					    			else
					    				packageList = new ArrayList<String>();
					    			
					    			packageList.add(packageName);
					    			
					    			classMap.put(className, packageList);
					    			
	/*				    			try{
					    				
					    				Class cls = ccl.getCl().loadClass(name);
					    				
					    				//Class cls = Class.forName(name, false, ccl.getCl());
	 
							    		if(java.lang.annotation.Annotation.class.isAssignableFrom(cls)) {
							    			List<String> annotationPackageList = null;				    			
							    			if(annotationMap.containsKey(className))
							    				annotationPackageList = (ArrayList<String>)classMap.get(className);
							    			else
							    				annotationPackageList = new ArrayList<String>();
							    			
							    			annotationPackageList.add(packageName);
							    			annotationMap.put(className, annotationPackageList);
							    		}
							    		
					    			}catch(Throwable e){				    				
					    			}*/
					    		}
					    	}
					    	
					    	//System.out.println("jar : " + url.getFile());
					    }catch(Exception e){
					    	e.printStackTrace();
					    }
					    
					}else if(url.getFile().endsWith("/")){
						//System.out.println("dir : " + url.getFile());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.setPackageMap(packageMap);
		this.setClassMap(classMap);
		this.setAnnotationMap(annotationMap);
	}
}
