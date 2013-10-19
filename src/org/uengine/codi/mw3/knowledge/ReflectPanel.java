package org.uengine.codi.mw3.knowledge;

import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath = "", options = { "fieldOrder" }, values = { "warFile,sqlFile" })
public class ReflectPanel {

	MetadataFile warFile;

	@Face(displayName = "WarFile")
	public MetadataFile getWarFile() {
		return warFile;
	}

	public void setWarFile(MetadataFile warFile) {
		this.warFile = warFile;
	}

	MetadataFile sqlFile;

	@Face(displayName = "SqlFile")
	public MetadataFile getSqlFile() {
		return sqlFile;
	}

	public void setSqlFile(MetadataFile sqlFile) {
		this.sqlFile = sqlFile;
	}

	@Face(displayName = "$devreflect")
	@ServiceMethod(callByContent = true)
	public Remover reflect() throws Exception {
		// 배포 할ㄸㅐ 할일들
		int reflectVer, releaseVer;
		WfNode wfNode = new WfNode();
		wfNode.setId(session.getLastSelectedItem());
		wfNode.copyFrom(wfNode.databaseMe());
		FilepathInfo filepathinfo = new FilepathInfo();
		filepathinfo.setId(wfNode.getId());

		if ("war".equals(wfNode.getVisType())) {
			reflectVer = filepathinfo.findReflectVersion(filepathinfo.getId());
			if (reflectVer == 0) {
				reflectVer = 1;
			} else {
				reflectVer++;
			}

			releaseVer = filepathinfo.findReleaseVersion(filepathinfo.getId());

			filepathinfo.setSqlPath(this.getSqlFile().getFilename());
			filepathinfo.setWarPath(this.getWarFile().getFilename());
			filepathinfo.setReflectVer(reflectVer);
			filepathinfo.setReleaseVer(releaseVer);
			filepathinfo.setFileType(wfNode.getVisType());

			filepathinfo.createDatabaseMe();
		} else if ("svn".equals(wfNode.getVisType())) {
			
		}

		return new Remover(new ModalWindow());
	}

	@AutowiredFromClient
	public Session session;
}
