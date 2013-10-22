package org.uengine.codi.mw3.knowledge;

import javax.persistence.GeneratedValue;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="filepathinfo")
public interface IFilepathInfo  extends IDAO{
	
	public String getProjectId();
	public void setProjectId(String projectId);

	public int getReflectVer();
	public void setReflectVer(int reflectVer);

	public int getReleaseVer();
	public void setReleaseVer(int releaseVer);

	public String getWarPath();
	public void setWarPath(String warPath);

	public String getSqlPath();
	public void setSqlPath(String sqlPath);
	
	public String getFileType();
	public void setFileType(String fileType);

	@Id
	@GeneratedValue(generator="BPM_SEQ")
	public int getId();
	public void setId(int id);
}
