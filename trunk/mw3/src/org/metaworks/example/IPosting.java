package org.metaworks.example;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="Posting")
public interface IPosting extends IDAO{

	public IPerson getWriter();
	public void setWriter(IPerson writer);

	@Id
	public String getDocument();
	public void setDocument(String document);

	public boolean isLikeIt();
	public void setLikeIt(boolean likeIt);
	
	
	@ServiceMethod(when=WHEN_VIEW)
	public void like() throws Exception;
	
	@ServiceMethod(when=WHEN_NEW, callByContent=true)
	public Object post() throws Exception;
	
	@ServiceMethod(when=WHEN_EDIT, callByContent=true)
	public void save() throws Exception;

	@ServiceMethod(needToConfirm=true)
	@Face(displayName = "ªË¡¶")
	public void delete() throws Exception;

	@ServiceMethod(clientSide=true)
	public void postToGooglePlus();
}
