package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

public class BuildPath {
	
	ArrayList<Source> sources;
		public ArrayList<Source> getSources() {
			return sources;
		}
		public void setSources(ArrayList<Source> sources) {
			this.sources = sources;
		}

/*
	String libraryPath;
		public String getLibraryPath() {
			return libraryPath;
		}
		public void setLibraryPath(String libraryPath) {
			this.libraryPath = libraryPath;
		}
*/		
	BuildOutputPath defaultBuildOutputPath;
		public BuildOutputPath getDefaultBuildOutputPath() {
			return defaultBuildOutputPath;
		}
		public void setDefaultBuildOutputPath(BuildOutputPath defaultBuildOutputPath) {
			this.defaultBuildOutputPath = defaultBuildOutputPath;
		}
		
	public BuildPath(){
		
	}
	
	public String makeFullClassName(String path){
		String className = null;
		
		for(Source source : this.getSources()){
			if(path.startsWith(source.getId())){
				className = path.substring(source.getId().length()+1, path.length()-5);
				break;
			}
		}
		
		if(className != null)
			className = className.replace(File.separatorChar, '.');
		
		return className;
	}
	
}
