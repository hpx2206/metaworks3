package org.uengine.codi.mw3.webProcessDesigner;

import net.sf.json.JSONObject;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.ContentWindow;

public class ProcessDesignerWebContentPanel extends ContentWindow implements ContextAware {
	
	public void load(String defId) throws Exception{
		
	}
	
//	@ServiceMethod
//	public Object[] clear() throws Exception{
//		
//		return new Object[]{};
//	}
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	JSONObject jsonObj;
		public JSONObject getJsonObj() {
			return jsonObj;
		}
		public void setJsonObj(JSONObject jsonObj) {
			this.jsonObj = jsonObj;
		}
		
	@ServiceMethod(callByContent=true)
	public Object[] getJSONObj() throws Exception{
		// 일단 테스트로 전체 json 데이터를 받아본다
		System.out.println("====== " + this.getJsonObj() );
		return null;
	}
}
