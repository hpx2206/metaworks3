package org.uengine.codi.mw3.model;

import org.metaworks.dao.Database;

public class PublicServiceIntroduceCode extends Database<IPublicServiceIntroduceCode> implements IPublicServiceIntroduceCode {

	final static String SEC_ = "SEC_";
	final static String SER_ = "SER_";
	final static String TAB_ = "TAB_";
	
	final static String SECTOR = "SECTOR";
	final static String SERVICE = "SERVICE";
	final static String TAB = "TAB";
	
	final static long FRIST_SEQUENCE = 1;
	
	String id;
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String parentId;
		public String getParentId() {
			return parentId;
		}
	
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	Long sequence;
		public Long getSequence() {
			return sequence;
		}
		public void setSequence(Long sequence) {
			this.sequence = sequence;
		}
		
	String tab;
		public String getTab() {
			return tab;
		}
	
		public void setTab(String tab) {
			this.tab = tab;
		}
		
	public IPublicServiceIntroduceCode loadServicesByTab(String codeId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SERVICE'");
		sb.append(" and tab=?tab");
		sb.append(" order by sequence");
		
		IPublicServiceIntroduceCode services = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		services.setTab(codeId);
		services.select();
		
		return services;
	}
	
	public IPublicServiceIntroduceCode loadSectorsByTab(String codeId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SECTOR'");
		sb.append(" and tab=?tab");
		sb.append(" order by sequence");
		
		IPublicServiceIntroduceCode sectors = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		sectors.setTab(codeId);
		sectors.select();
		
		return sectors;
	}
	
	public Long findSectorsSequenceByTab(String codeId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select ifnull(max(sequence)+1,1) sequence");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SECTOR'");
		sb.append(" and tab=?tab");
		
		IPublicServiceIntroduceCode sectors = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		sectors.setTab(codeId);
		sectors.select();
		
		Long sequence = null;
		
		while(sectors.next()) {
			sequence = sectors.getSequence();
			
			break;
		}
		
		return sequence;
	}
	
	public Long findServicesSequenceByTab(String codeId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select ifnull(max(sequence)+1,1) sequence");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SERVICE'");
		sb.append(" and tab=?tab");
		
		IPublicServiceIntroduceCode services = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		services.setTab(codeId);
		services.select();
		
		Long sequence = null;
		
		while(services.next()) {
			sequence = services.getSequence();
			
			break;
		}
		
		return sequence;
	}
	
	public Long findTabsSequence() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select ifnull(max(sequence)+1,1) sequence");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'TAB'");
		
		IPublicServiceIntroduceCode tabs = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tabs.select();
		
		Long sequence = null;
		
		while(tabs.next()) {
			sequence = tabs.getSequence();
			
			break;
		}
		
		return sequence;
		
	}
	
	public int findSectors() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SECTOR'");
		
		IPublicServiceIntroduceCode sectors = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		sectors.select();
		
		return sectors.size();
		
	}
	
	public int findServices() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SERVICE'");
		
		IPublicServiceIntroduceCode services = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		services.select();
		
		return services.size();
	}
	
	public int findTabs() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'TAB'");
		
		IPublicServiceIntroduceCode tabs = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tabs.select();
		
		return tabs.size();
		
	}
	
	public IPublicServiceIntroduceCode loadTabs() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id, name");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'tab'");
		sb.append(" order by sequence");
		
		IPublicServiceIntroduceCode tabs = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tabs.select();
		
		return tabs;
	} 
	
	public IPublicServiceIntroduceCode findCurrentLastTab() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'tab'");
		sb.append("	and sequence = (select MAX(sequence) from public_introduce_code where parentId='tab')");
		sb.append(" order by sequence");
		
		IPublicServiceIntroduceCode tabs = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tabs.select();
		
		return tabs;
		
	}
	
	public void addSector(String name, String codeId) throws Exception {
		// count 보다 1개가 커야한다. 현재 sector가 1개면 그 다음엔 2개니까..
		int count = this.findSectors() + 1;
		String sectorId = SEC_ + count;
		
		this.setId(sectorId);
		this.setName(name);
		this.setParentId(SECTOR);
		this.setSequence(this.findSectorsSequenceByTab(codeId));
		this.setTab(codeId);
		
		this.createDatabaseMe();
		
	}
	
	public void addService(String name, String codeId) throws Exception {
		// count 보다 1개가 커야한다. 현재 service 1개면 그 다음엔 2개니까..
		int count = this.findServices() + 1;
		String serviceId = SER_ + count;
		
		this.setId(serviceId);
		this.setName(name);
		this.setParentId(SERVICE);
		this.setSequence(this.findServicesSequenceByTab(codeId));
		this.setTab(codeId);
		
		this.createDatabaseMe();
		
	}
	
	public void addTab(String name) throws Exception {
		// count 보다 1개가 커야한다. 현재 tab 1개면 그 다음엔 2개니까..
		int count = this.findTabs() + 1;
		String tabId = TAB_ + count;
		
		this.setId(tabId);
		this.setName(name);
		this.setParentId(TAB);
		this.setSequence(this.findTabsSequence());
		
		this.createDatabaseMe();
		
	}
	
	
}
