package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.MetaworksContext;

public class CommonProperty {
	
	public CommonProperty() {
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}

	String processPath;
		public String getProcessPath() {
			return processPath;
		}
		public void setProcessPath(String processPath) {
			this.processPath = processPath;
		}

	String imagePath;
		public String getImagePath() {
			return imagePath;
		}
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

	String filePath;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	
	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			projectName = "프로젝트 이름";
			this.projectName = projectName;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
}
