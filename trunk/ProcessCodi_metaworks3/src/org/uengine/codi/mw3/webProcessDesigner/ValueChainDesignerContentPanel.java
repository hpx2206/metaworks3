package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.editor.valuechain.ValueChainEditor;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ValueChainDefinition;
import org.uengine.util.UEngineUtil;

public class ValueChainDesignerContentPanel extends ProcessDesignerContentPanel{

	public ValueChainDesignerContentPanel() throws Exception {
		
		super();
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("valuechain");
	}
	
	@Override
	public String load(String definitionString) throws Exception{
		ValueChainDefinition def = (ValueChainDefinition) GlobalContext.deserialize(definitionString);
		this.processDesignerContainer.setEditorId(alias);
		this.processDesignerContainer.loadValueChain(def);
		
		processNameView.setFileId(alias);
		processNameView.setAlias(def.getName().getText());
		
		return def.getProcessDesignerInstanceId();
	}
		
	public void saveMe(ValueChainEditor valueChainEditor) throws Exception{
		ValueChainDefinition def = processDesignerContainer.containerToValueChainDefinition(processDesignerContainer);
		
		TextContext text = new TextContext();
		text.setText(this.getProcessName());
		def.setName(text);
		if( valueChainEditor.getProcessDesignerInstanceId() != null ){
			def.setProcessDesignerInstanceId(valueChainEditor.getProcessDesignerInstanceId());
		}
		FileOutputStream fos = null;
		try{
			File file = new File(valueChainEditor.getResourceNode().getPath());
			fos = new FileOutputStream(file);
			String definitionInString = (String)GlobalContext.serialize(def, ProcessDefinition.class);
			ByteArrayInputStream bai = new ByteArrayInputStream(definitionInString.getBytes(GlobalContext.ENCODING));
			UEngineUtil.copyStream(bai, fos);
		} catch (Exception e) {
			throw e;//e.printStackTrace();
		} finally{
			if(fos!=null)
				fos.close();
		}
	}
	
}
