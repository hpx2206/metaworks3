package org.metaworks.example.ide;

public class Folder {

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	CompilationUnit[] compilationUnits;
		public CompilationUnit[] getCompilationUnits() {
			return compilationUnits;
		}
		public void setCompilationUnits(CompilationUnit[] compilationUnits) {
			this.compilationUnits = compilationUnits;
		}
		
	Folder[] childFolders;
		public Folder[] getChildFolders() {
			return childFolders;
		}
		public void setChildFolders(Folder[] childFolders) {
			this.childFolders = childFolders;
		}
	
}
