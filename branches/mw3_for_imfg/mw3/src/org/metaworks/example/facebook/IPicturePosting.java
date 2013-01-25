package org.metaworks.example.facebook;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.annotation.*;

@Table(name="Posting")
public interface IPicturePosting extends IPosting{

	@Id
	public String getDocument();


	public String getPictureURL();
	public void setPictureURL(String picture);
}
