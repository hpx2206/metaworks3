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
	
	public String makePackageName(String path){
		String packageName = null;
		
		for(Source source : this.getSources()){
			if(path.startsWith(source.getId())){
				if(path.length() > source.getId().length())
					packageName = path.substring(source.getId().length()+1);
				
				break;
			}
		}
		
		if(packageName != null){
			if(packageName.endsWith(".java")){
				packageName = packageName.substring(0, packageName.length()-5);
				
				if(packageName.indexOf(File.separatorChar) > -1)
					packageName = packageName.substring(0, packageName.lastIndexOf(File.separatorChar));
				else
					packageName = null;
			}
		}
		
		if(packageName != null)
			packageName = packageName.replace(File.separatorChar, '.');
		
		return packageName;
	}
	
	public String makeClassName(String path){
		String className = null;
		
		for(Source source : this.getSources()){
			if(path.startsWith(source.getId())){
				if(path.length() > source.getId().length())
					className = path.substring(source.getId().length()+1);
				
				break;
			}
		}

		
		if(className != null){
			if(className.endsWith(".java"))
				className = className.substring(0, className.length()-5);
			
			if(className.indexOf(File.separatorChar) > -1)
				className = className.substring(className.lastIndexOf(File.separatorChar)+1);

			
			className = className.replace(File.separatorChar, '.');
		}
		
		return className;
	}
	
	public String makeFullClassName(String path){
		String packageName = this.makePackageName(path);
		String className = this.makeClassName(path);
		
		if(packageName != null)
			return packageName + "." + className;
		else
			return className;
	}
	
}
