package org.metaworks.metadata;

import java.io.File;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath = "dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs",
		ejsPathMappingByContext={
	"{where: 'ide', face: 'dwr/metaworks/org/metaworks/metadata/ProcessProperty.ejs'}",
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/ProcessProperty.ejs'}"
})
@XStreamAlias("MetadataProperty")
public class ProcessProperty extends MetadataProperty{
	
	public ProcessProperty() {
		setType(MetadataProperty.PROCESS_PROP);
	}
	
	String value;
	@Hidden
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public Object modify() throws Exception {
		
		// TODO : 살려야함 cjw
//		Project project = workspace.findProject(this.getProjectId());
//
//		ProcessNode node = new ProcessNode();
//		node.setId(this.getProjectId() + File.separatorChar + this.getValue());
//		node.setName(this.getValue());
//		node.setPath(project.getBuildPath().getSources().get(0).getPath() + File.separatorChar + this.getValue());
//		node.setProjectId(this.getProjectId());
//
//		ProcessEditor processEditor = new ProcessEditor(node);
//		processEditor.setUseClassLoader(true);
//		processEditor.load();
//		
//		ModalWindow modalWindow = new ModalWindow(processEditor, 0, 0, "$metadata.process.edit");
//		
//		modalWindow.getButtons().put("$Save", "save");
//		modalWindow.getButtons().put("$Cancel", null);
//		modalWindow.getCallback().put("$Save", "changeFile");
//		
//		return modalWindow;
		
		return null;
	}		
	
	@ServiceMethod(callByContent=true, bindingHidden=true, bindingFor="file", eventBinding={"uploaded"})
	public void changeFile() throws Exception {
		
		//파일첨부 클릭 시 메타데이터 파일 바로 수정
		if(this.getFile().getUploadedPath() != null){
			
			int index = metadataXML.properties.indexOf(this);
			if( index > 0 ){
				String metadataFilePath = metadataXML.getFilePath() ;
				
				MetadataProperty editProperty = metadataXML.properties.get(index);
				editProperty.setName(this.getName());
				editProperty.setChange(true);
				editProperty.setType(this.getType());
				
				
				MetadataFile file = new MetadataFile();
				file.setTypeDir(this.getType());
				file.setUploadedPath(this.getFile().getUploadedPath());
				file.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				file.setMimeType(ResourceNode.findNodeType(this.getFile().getFilename()));
				file.setFileTransfer(this.getFile().getFileTransfer());
				
				editProperty.setFile(file);
				editProperty.setValue(file.getUploadedPath());
				editProperty.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				
				
				MetadataEditor metadataEditor = new MetadataEditor();
				metadataEditor.setResourceNode(new ResourceNode());
				metadataEditor.getResourceNode().setPath(metadataFilePath);
				metadataEditor.setContent(metadataXML.toXmlXStream());
				metadataEditor.save();
			}
		}else {
			ProcessViewPanel processViewPanel = new ProcessViewPanel();
			processViewPanel.setDefId(this.getValue());
			processViewPanel.setAlias(this.getValue());
			processViewPanel.setViewType("definitionView");
			processViewPanel.load();
			setFilePreview(processViewPanel);
		}
	}
}
