package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.annotation.Id;

public class Project {

	String id;
		@Id
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
		
		// TODO: src
		//source.setName("src");
		//source.setId(this.getId() + File.separatorChar + source.getName());
		
		source.setName("");
		source.setId(this.getId());
		source.setPath(new File(this.getPath() + File.separatorChar + source.getName()).getAbsolutePath());
		
		ArrayList<Source> sources = new ArrayList<Source>();
		sources.add(source);
		
		BuildOutputPath buildOutputPath = new BuildOutputPath();
		buildOutputPath.setName("bin");
		buildOutputPath.setId(this.getId() + File.separatorChar + buildOutputPath.getName());
		buildOutputPath.setPath(new File(this.getPath() + File.separatorChar + buildOutputPath.getName()).getAbsolutePath());
		
		BuildPath buildPath = new BuildPath();
		buildPath.setSources(sources);
		buildPath.setDefaultBuildOutputPath(buildOutputPath);
		
		this.setBuildPath(buildPath);
	}
	
}
