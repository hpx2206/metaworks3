package org.metaworks.metadata;

import java.io.File;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.webProcessDesigner.InstanceMonitorPanel;

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
	InstanceMonitorPanel processInstanceMonitorPanel;
	@Hidden
		public InstanceMonitorPanel getProcessInstanceMonitorPanel() {
			return processInstanceMonitorPanel;
		}
		public void setProcessInstanceMonitorPanel(
				InstanceMonitorPanel processInstanceMonitorPanel) {
			this.processInstanceMonitorPanel = processInstanceMonitorPanel;
		}

	@Available(when = MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_POPUP)
	public Object modify() throws Exception {
		String projectId = this.getProjectId();
		String sourceCodeBase = CodiClassLoader.mySourceCodeBase(projectId) + "src" + File.separatorChar;
		
		ResourceNode node = new ResourceNode();
		node.setId(this.getValue());
		node.setName(this.getValue());
		node.setPath(sourceCodeBase + this.getValue());
		
		ProcessEditor processEditor = new ProcessEditor(node);
		processEditor.load();
		
		ModalWindow modalWindow = new ModalWindow(processEditor, 0, 0, "프로세스 편집");
		
		modalWindow.getButtons().put("$Save", "save");
		modalWindow.getButtons().put("$Cancel", null);
		modalWindow.getCallback().put("$Save", "changeFile");
		
		return modalWindow;
	}		
	
	@ServiceMethod(callByContent=true)
	public void changeFile() throws Exception {

		InstanceMonitorPanel processInstanceMonitorPanel = new InstanceMonitorPanel();
		processInstanceMonitorPanel.loadProcess(this.getValue());
		this.setProcessInstanceMonitorPanel(processInstanceMonitorPanel);
		
	}
}
