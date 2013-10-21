package org.uengine.codi.mw3.knowledge;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.widget.ModalWindow;

@Face(ejsPath="", options={"fieldOrder"},values={"reflectVersion,check,warFile,sqlFile"})
public class ReleasePanel {
	
	SelectBox reflectVersion;
		@Face(displayName = "$ReflectVersion")
		public SelectBox getReflectVersion() {
			return reflectVersion;
		}
		public void setReflectVersion(SelectBox reflectVersion) {
			this.reflectVersion = reflectVersion;
		}
	
	Boolean check;
		@Face(displayName="$newFile")
		public Boolean getCheck() {
			return check;
		}
		public void setCheck(Boolean check) {
			this.check = check;
		}
		
	MetadataFile warFile;
		@Face(displayName="$WarFile")
		public MetadataFile getWarFile() {
			return warFile;
		}
		public void setWarFile(MetadataFile warFile) {
			this.warFile = warFile;
		}
		
	MetadataFile sqlFile;
		@Face(displayName="$SqlFile")
		public MetadataFile getSqlFile() {
			return sqlFile;
		}
		public void setSqlFile(MetadataFile sqlFile) {
			this.sqlFile = sqlFile;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	@Face(displayName = "$release")
	@ServiceMethod(callByContent = true)
	public Object release() throws Exception{
		
		FilepathInfo filepathinfo = new FilepathInfo();
		filepathinfo.setInfoId(Integer.parseInt(this.getReflectVersion().toString()));
		filepathinfo.copyFrom(filepathinfo.databaseMe());
		
		if(!this.getCheck()){	//체크 박스 미 체크시 서버로 파일 전송
			
		}
		else{	//체크 박스 체크시 입력 받게 끔
			
		}
		
		return new Remover(new ModalWindow());
	}
	
}
