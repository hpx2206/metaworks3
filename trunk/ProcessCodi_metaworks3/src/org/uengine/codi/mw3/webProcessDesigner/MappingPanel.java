package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class MappingPanel implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String elementId;
		public String getElementId() {
			return elementId;
		}
		public void setElementId(String elementId) {
			this.elementId = elementId;
		}
	String mapperData;
		public String getMapperData() {
			return mapperData;
		}
		public void setMapperData(String mapperData) {
			this.mapperData = mapperData;
		}
	public MappingPanel() throws Exception{
		setMetaworksContext(new MetaworksContext());
	}
	public void load() throws Exception{
		
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] doSaveMapper() throws Exception{
		GeomShape geomShape = new GeomShape();
		geomShape.setId(elementId);
		geomShape.setData(mapperData);
//		System.out.println(mapperData);
		return new Object[]{ new Remover(new ModalWindow()), geomShape};
	}
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
}
