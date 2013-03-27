package org.uengine.codi.mw3.ide;


public class JavaBuildPath {
	
	public JavaBuildPath(String libraryPath, String defaultBuildOutputPath) {
		this.setLibraryPath(libraryPath);
		this.setDefaultBuildOutputPath(defaultBuildOutputPath);
	}

	String basePath;
		public String getBasePath() {
			return basePath;
		}
		public void setBasePath(String basePath) {
			this.basePath = basePath;
		}

	String srcPath;
		public String getSrcPath() {
			return srcPath;
		}
		public void setSrcPath(String srcPath) {
			this.srcPath = srcPath;
		}

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
		
	public JavaBuildPath(){
		
	}
	
}
