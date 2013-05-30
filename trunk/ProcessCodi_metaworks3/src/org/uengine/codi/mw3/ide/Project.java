package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

public class Project {

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String path;
		public String getPath() {
			return path;
		}
	
		public void setPath(String path) {
			this.path = path;
		}
		
	boolean close;
		public boolean isClose() {
			return close;
		}
		public void setClose(boolean close) {
			this.close = close;
		}
	
	BuildPath buildPath;
		public BuildPath getBuildPath() {
			return buildPath;
		}
		public void setBuildPath(BuildPath buildPath) {
			this.buildPath = buildPath;
		}
		
	public void load(){
		Source source = new Source();
		source.setName("src");
		source.setId(this.getId() + File.separatorChar + source.getName());
		
		ArrayList<Source> sources = new ArrayList<Source>();
		sources.add(source);
		
		BuildOutputPath buildOutputPath = new BuildOutputPath();
		buildOutputPath.setName("bin");
		buildOutputPath.setId(this.getId() + File.separatorChar + buildOutputPath.getId());
		
		BuildPath buildPath = new BuildPath();
		buildPath.setSource(sources);
		buildPath.setDefaultBuildOutputPath(buildOutputPath);
		
		this.setBuildPath(buildPath);
		
	}
	
}
