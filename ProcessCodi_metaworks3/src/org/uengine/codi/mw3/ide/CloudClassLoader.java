package org.uengine.codi.mw3.ide;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.metaworks.annotation.Hidden;


public class CloudClassLoader {

	String libraryPath;
		public String getLibraryPath() {
			return libraryPath;
		}
		public void setLibraryPath(String libraryPath) {
			this.libraryPath = libraryPath;
		}
		
	String defaultBuildOutputPath;
		public String getDefaultBuildOutputPath() {
			return defaultBuildOutputPath;
		}
		public void setDefaultBuildOutputPath(String defaultBuildOutputPath) {
			this.defaultBuildOutputPath = defaultBuildOutputPath;
		}
		
	URLClassLoader cl;
		@Hidden
		public URLClassLoader getCl() {
			return cl;
		}
		public void setCl(URLClassLoader cl) {
			this.cl = cl;
		}
		
	public CloudClassLoader(String libraryPath, String defaultBuildOutputPath) {
		this.setLibraryPath(libraryPath);
		this.setDefaultBuildOutputPath(defaultBuildOutputPath);
	}

	public void load(){
		
		List<URL> classpath = new ArrayList<URL>();
		
		try {
			File classesFile = new File(this.getDefaultBuildOutputPath());
			
			System.out.println(classesFile.getAbsolutePath());
			// classes for .class file
			classpath.add(classesFile.toURI().toURL());
			
			// lib for .jar file
			File libFile = new File(this.getLibraryPath());		
			for(String filename : libFile.list()){
				if(filename.endsWith(".jar")){
					File file = new File(libFile.getAbsolutePath() + File.separator + filename);
					classpath.add(file.toURI().toURL());
				}
			}
			
			URLClassLoader cl =  new URLClassLoader(classpath.toArray(new URL[classpath.size()]), null);
			
			this.setCl(cl);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
    public static String getSourceResourceName(String className) {

        // Strip nested type suffixes.
        {
            int idx = className.lastIndexOf('.') + 1;
            idx = className.indexOf('$', idx);
            if (idx != -1) className = className.substring(0, idx);
        }

        return className.replace('.', '/') + ".java";
    }

}
