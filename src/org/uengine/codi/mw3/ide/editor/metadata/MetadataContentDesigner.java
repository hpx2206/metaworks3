package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
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
	}
	String id;
		@Id
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	ResourceNode resourceNode;
		@Hidden
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
	String processBasePath;	
		@Hidden
		public String getProcessBasePath() {
			return processBasePath;
		}
		public void setProcessBasePath(String processBasePath) {
			this.processBasePath = processBasePath;
		}
	String imgBasePath;
		@Hidden
		public String getImgBasePath() {
			return imgBasePath;
		}
		public void setImgBasePath(String imgBasePath) {
			this.imgBasePath = imgBasePath;
		}
	String uploadBasePath;
		@Hidden
		public String getUploadBasePath() {
			return uploadBasePath;
		}
		public void setUploadBasePath(String uploadBasePath) {
			this.uploadBasePath = uploadBasePath;
		}
	CommonProperty commonProperties;
		@Hidden
		public CommonProperty getCommonProperties() {
			return commonProperties;
		}
		public void setCommonProperties(CommonProperty commonProperties) {
			this.commonProperties = commonProperties;
		}
	CustomizeProperty customizeProperies;
		public CustomizeProperty getCustomizeProperies() {
			return customizeProperies;
		}
		public void setCustomizeProperies(CustomizeProperty customizeProperies) {
			this.customizeProperies = customizeProperies;
		}
	
			
	@Hidden
	@ServiceMethod(callByContent=true)
	public void load() throws Exception{
		/* TODO  앱에서는 메타데이터 파일이 없을수도 있다.
			파일이 있는 경우에도, property 값에 아무것도 안들어가 있다면, 상위 프로젝트에서 정보를 요청하도록...
			xml파일에는 써지지 않지만, 메타데이터 디자인 텝에서는 상위 프로젝트의 메타데이터 정보를 읽어와야한다.
			저장할때 주의해야함. */
		// TODO 앱일경우 상위 프로젝트 찾기
		setId(this.getResourceNode().getId());
		
		commonProperties = new CommonProperty();
		
		customizeProperies = new CustomizeProperty();
		customizeProperies.setResourceNode(this.getResourceNode());
		customizeProperies.load();
		
	}
}
