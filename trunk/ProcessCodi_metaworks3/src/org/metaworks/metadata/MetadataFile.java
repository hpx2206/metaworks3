package org.metaworks.metadata;


import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.kernel.GlobalContext;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs")
public class MetadataFile extends AbstractMetaworksFile {


	String company;
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		
	String filePath;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	
	@Override
	public String overrideUploadPathPrefix() {
		
		String sep ="/";
		String codebase = GlobalContext.getPropertyString("codebase");
		String tenantId = this.getCompany();
		String filePath = this.getFilePath();
		filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
		
		return codebase + sep + tenantId  + sep + filePath;
		
	}

}
