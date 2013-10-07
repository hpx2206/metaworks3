
package org.uengine.codi.mw3.knowledge;
import java.io.FileInputStream;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.Download;
import org.metaworks.website.OpenBrowser;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder", "methodOrder"}, values={"projectName,domainName,svn,description", "downloadEclipse,downloadVirtualMachine,downloadSandbox"})
public class ProjectInfo  implements ContextAware {
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	

	String projectId;
		@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String projectName;
		@Face(displayName="$project.name")
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		
	String domainName;
		@Face(displayName="$project.domain")
		public String getDomainName() {
			return domainName; 
		}
		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}

	String svn;
		@Face(displayName="$project.svn")
		public String getSvn() {
			return svn;
		}
		public void setSvn(String svn) {
			this.svn = svn;
		}
			
	String description;
		@Face(displayName="$project.description")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
	/*
	@Hidden
	String os;
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		
	@Hidden
	String db;
		public String getDb() {
			return db;
		}
		public void setDb(String db) {
			this.db = db;
		}
			
	@Hidden
	String was;
		public String getWas() {
			return was;
		}
		public void setWas(String was) {
			this.was = was;
		}
		
	@Hidden
	String vm;
		public String getVm() {
			return vm;
		}
		public void setVm(String vm) {
			this.vm = vm;
		}*/
		
	/*@Hidden
	String ci;
		public String getCi() {
			return ci;
		}
		public void setCi(String ci) {
			this.ci = ci;
		}*/
	
	/*@Face(displayName="@Hudson")
	String hudson;
		public String getHudson() {
			return hudson;
		}
		public void setHudson(String hudson) {
			this.hudson = hudson;
		}
	
	@Face(displayName="@TemplateName")
	String templateName;
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		
	@Face(displayName="$Ip")
	String ip;
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
	
	@Hidden
	String vmDouwnUrl;
		public String getVmDouwnUrl() {
			return vmDouwnUrl;
		}
		public void setVmDouwnUrl(String vmDouwnUrl) {
			this.vmDouwnUrl = vmDouwnUrl;
		}*/
		
	
	public ProjectInfo(){
		this(null);
	}
	
	public ProjectInfo(String projectId){
		this.setProjectId(projectId);
		
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load() {
		
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getProjectId());
		
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		if(wfNode.getLinkedInstId() != null){
			String linkedId = String.valueOf(wfNode.getLinkedInstId());*/
			
			this.projectName = wfNode.getName();
			this.domainName = this.getProjectName() + ".com";			
			this.svn = GlobalContext.getPropertyString("vm.manager.url") + "/" + this.getProjectName(); 
			this.description = wfNode.getDescription();
			
			
			/*
			Serializable serial = null;
			serial = processManager.getProcessVariable(linkedId, "", "VMRequest");
			if(serial instanceof VMRequest) {
				VMRequest vmRequest = (VMRequest)serial;
				this.templateName = vmRequest.getVmImageCombo().getSelectedText();
			}
			
			this.ip = (String)(Serializable)processManager.getProcessVariable(linkedId, "", "vm_ip");
			
			this.svn = "svn://" + GlobalContext.getPropertyString("vm.manager.ip") + "/" + this.projectName;
			
			
			this.hudson = GlobalContext.getPropertyString("vm.hudson.url");
			*/
		//}
	}
	
	@Face(displayName="$projet.download.sandboxfordeveloper")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Download downloadSandbox() throws Exception{
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String sendboxPath = fileSystemPath + "/resource/sandbox_final.ova";
		
		return new Download(new FileTransfer(new String("sandbox_final.ova".getBytes("UTF-8"),"ISO8859_1"), null,  new FileInputStream(sendboxPath)));		
	}
	
	@Face(displayName="$projet.download.eclipseforegovframe")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Download downloadEclipse() throws Exception{
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String sendboxPath = fileSystemPath + "/resource/govFramEclpse64.zip";
		
		return new Download(new FileTransfer(new String("govFramEclpse64.zip".getBytes("UTF-8"),"ISO8859_1"), null,  new FileInputStream(sendboxPath)));		
	}
	
	@Face(displayName="$projet.download.virtualmachine")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public OpenBrowser downloadVirtualMachine() throws Exception{
		
		String url =  GlobalContext.getPropertyString("vm.download.url");
		
		return new OpenBrowser(url);        
	}
}
