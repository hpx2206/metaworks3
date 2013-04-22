package org.uengine.codi.mw3.project;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.codi.ITool;
import org.uengine.codi.deltacloud.DeltaCloud;
import org.uengine.codi.deltacloud.dto.RealmsDDTO;

public class VMRequest implements ITool, ContextAware {
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	VMTempleteCombo vmTempleteCombo;
		public VMTempleteCombo getVmTempleteCombo() {
			return vmTempleteCombo;
		}
		public void setVmTempleteCombo(VMTempleteCombo vmTempleteCombo) {
			this.vmTempleteCombo = vmTempleteCombo;
		}
		
	public VMRequest(){
		this.setMetaworksContext(new MetaworksContext());
	}
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setVmTempleteCombo(new VMTempleteCombo(null));
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*public VMRequest() {
		
		this.osSelect = new SelectBox();
		this.dbSelect = new SelectBox();
		this.wasSelect = new SelectBox();
		
		this.osSelect.add("Windows", "Windows");
		this.osSelect.add("Ubuntu", "Ubuntu");
		this.osSelect.add("Ios", "Ios");
		
		this.dbSelect.add("Cubrid", "Cubrid");
		this.dbSelect.add("Oracle", "Oracle");
		this.dbSelect.add("Mysql", "Mysql");
		this.dbSelect.add("Mssql", "Mssql");
		
		this.wasSelect.add("Apache Tomcat7.0", "Apache Tomcat7.0");	
		this.wasSelect.add("Apache Tomcat6.0", "Apache Tomcat6.0");	
		this.wasSelect.add("IIS", "IIS");	
		this.wasSelect.add("JBOSS", "JBOSS");	
		this.wasSelect.add("IBM Web Spere", "IBM Web Spere");	
	}

	SelectBox osSelect;
		@Face(displayName="OS: ")
		public SelectBox getOsSelect() {
			return osSelect;
		}	
		public void setOsSelect(SelectBox osSelect) {
			this.osSelect = osSelect;
		}
		
	SelectBox dbSelect;
		@Face(displayName="DB: ")
		public SelectBox getDbSelect() {
			return dbSelect;
		}
		public void setDbSelect(SelectBox dbSelect) {
			this.dbSelect = dbSelect;
		}
		
	SelectBox wasSelect;
		@Face(displayName="DB: ")
		public SelectBox getWasSelect() {
			return wasSelect;
		}
		public void setWasSelect(SelectBox wasSelect) {
			this.wasSelect = wasSelect;
		}
		
	String name;
		@NonLoadable
		public String getName() {
			return name;
		}	
		public void setName(String name) {
			this.name = name;
		}
		
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub	
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.print(this.osSelect.getSelectedText());
	}*/

}
