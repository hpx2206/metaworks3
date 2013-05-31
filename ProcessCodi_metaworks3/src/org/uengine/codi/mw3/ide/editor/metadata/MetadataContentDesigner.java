package org.uengine.codi.mw3.ide.editor.metadata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.uengine.codi.mw3.ide.ResourceNode;

import com.thoughtworks.xstream.XStream;

public class MetadataContentDesigner implements ContextAware {

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public MetadataContentDesigner(){
		this(null);
	}
	public MetadataContentDesigner(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		metaworksContext = new MetaworksContext();
		metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
	}
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	ResourceNode resourceNode;
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
	String processBasePath;
		public String getProcessBasePath() {
			return processBasePath;
		}
		public void setProcessBasePath(String processBasePath) {
			this.processBasePath = processBasePath;
		}
	String imgBasePath;
		public String getImgBasePath() {
			return imgBasePath;
		}
		public void setImgBasePath(String imgBasePath) {
			this.imgBasePath = imgBasePath;
		}
	String uploadBasePath;
		public String getUploadBasePath() {
			return uploadBasePath;
		}
		public void setUploadBasePath(String uploadBasePath) {
			this.uploadBasePath = uploadBasePath;
		}
	MatadataXML metadata;
		public MatadataXML getMetadata() {
			return metadata;
		}
		public void setMetadata(MatadataXML metadata) {
			this.metadata = metadata;
		}
		
	public void load(){
		/* TODO  앱에서는 메타데이터 파일이 없을수도 있다.
			파일이 있는 경우에도, property 값에 아무것도 안들어가 있다면, 상위 프로젝트에서 정보를 요청하도록...
			xml파일에는 써지지 않지만, 메타데이터 디자인 텝에서는 상위 프로젝트의 메타데이터 정보를 읽어와야한다.
			저장할때 주의해야함. */
		
		// TODO 앱일경우 상위 프로젝트 찾기
		XStream xstream = new XStream();
		FileInputStream fin;
		try {
			fin = new FileInputStream(this.getResourceNode().getPath());
			xstream.alias("metadata", MatadataXML.class);
			xstream.alias("MetadataProperty", MetadataProperty.class);
			
			MatadataXML metadata = (MatadataXML)xstream.fromXML( fin );
			metadata.setFilePath(this.getResourceNode().getPath());
			setMetadata(metadata);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
