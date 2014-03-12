package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CategoryTitle;

public class PublicServiceIntroduceCode extends Database<IPublicServiceIntroduceCode> implements IPublicServiceIntroduceCode {

	final static String SEC_ = "SEC_";
	final static String SER_ = "SER_";
	final static String TAB_ = "TAB_";
	
	final static String SECTOR = "SECTOR";
	final static String SERVICE = "SERVICE";
	final static String TAB = "TAB";
	
	final static long FRIST_SEQUENCE = 1;
	
	public final static String CODE_MODIFY = "code_modify";
	
	
	String id;
		@Id
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
	
	public Long findSectorSeqeunce() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(sequence) as sequence");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SECTOR'");
		
		IPublicServiceIntroduceCode sectors = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		sectors.select();
		
		Long sequence = null;
		
		while(sectors.next()) {
			sequence = sectors.getSequence();
		}
		
		return sequence;
		
	}
	
	public Long findServiceSeqeunce() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select MAX(sequence) as sequence");
		sb.append(" from public_introduce_code");
		sb.append("	where parentId = 'SERVICE'");
		
		IPublicServiceIntroduceCode services = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		services.select();
		
		Long sequence = null;
		
		while(services.next()) {
			sequence = services.getSequence();
		}
		
		return sequence;
		
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
	
	public IPublicServiceIntroduceCode findSector() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where id = ?id");
		
		IPublicServiceIntroduceCode sector = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		sector.setId(this.getId());
		sector.select();
		
		return sector;
	}
	
	public IPublicServiceIntroduceCode findService() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where id = ?id");
		
		IPublicServiceIntroduceCode service = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		service.setId(this.getId());
		service.select();
		
		return service;
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
	
	public IPublicServiceIntroduceCode findTabInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_code");
		sb.append("	where id = ?id");
		
		IPublicServiceIntroduceCode tab = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tab.setId(this.getId());
		tab.select();
		
		return tab;
	}
	
	public void addSector(String name, String codeId) throws Exception {
		// count 보다 1개가 커야한다. 현재 sector가 1개면 그 다음엔 2개니까..
		// 그런데 섹터가 아예없을 때는 초기값으로 1을 준다.
		String sectorId = null;
		int count = 0;
		
		if(this.findSectorSeqeunce() == null) {
			++count;
			sectorId = SEC_ + count;
			
		} else {
			count = (int) (this.findSectorSeqeunce() + 1);
			sectorId = SEC_ + count;
			
		}
		
		this.setId(sectorId);
		this.setName(name);
		this.setParentId(SECTOR);
		this.setSequence(this.findSectorsSequenceByTab(codeId));
		this.setTab(codeId);
		
		this.createDatabaseMe();
		
	}
	
	public void addService(String name, String codeId) throws Exception {
		// count 보다 1개가 커야한다. 현재 sector가 1개면 그 다음엔 2개니까..
		// 그런데 서비스가 아예없을 때는 초기값으로 1을 준다.
		String serviceId = null;
		int count = 0;
		
		if(this.findServiceSeqeunce() == null) {
			++count;
			serviceId = SER_ + count;
			
		} else {
			count = (int) (this.findServiceSeqeunce() + 1);
			serviceId = SER_ + count;
			
		}
		
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
	
	@ServiceMethod(callByContent=true, needToConfirm=true, inContextMenu=true, target=TARGET_POPUP)
	@Face(displayName="$Delete")
	public Object[] delete() throws Exception {
		// sector와 service를 구분하며 지워야 한다.
		String[] parseId = this.getId().split("_");
		
		// sector인 경우
		if("SEC".equals(parseId[0])) {
			// item 삭제 후 div 까지 지워야 한다.
			PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
			publicServiceIntroduceItem.deleteBySector(this.getId());
			
			// item 삭제 후 code 삭제
			PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
			publicServiceIntroduceCode.setId(this.getId());
			publicServiceIntroduceCode.deleteDatabaseMe();
			
			// 화면 refresh
			PublicServiceIntroduce publicServiceIntroduce = new PublicServiceIntroduce();
			publicServiceIntroduce.setId(this.getTab());
			publicServiceIntroduce.loadChart(this.getTab());
			
			return new Object[] { new Refresh(publicServiceIntroduce) };
					
		// service 인 경우	
		} else {
			// item 삭제 후 div 까지 지워야 한다.
			PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
			publicServiceIntroduceItem.deleteByService(this.getId());
			
			// item 삭제 후 code 삭제
			PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
			publicServiceIntroduceCode.setId(this.getId());
			publicServiceIntroduceCode.deleteDatabaseMe();
			
			// 화면 refresh
			PublicServiceIntroduce publicServiceIntroduce = new PublicServiceIntroduce();
			publicServiceIntroduce.setId(this.getTab());
			publicServiceIntroduce.loadChart(this.getTab());
			
			return new Object[] { new Refresh(publicServiceIntroduce) };
		}
	}
	
	public void deleteTab() throws Exception {
		this.deleteDatabaseMe();
	}
	
	public void deleteByTab(String tabId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete");
		sb.append(" from public_introduce_code");
		sb.append("	where tab = ?tab");
		
		IPublicServiceIntroduceCode tabs = (IPublicServiceIntroduceCode) sql(IPublicServiceIntroduceCode.class, sb.toString());
		tabs.setTab(tabId);
		tabs.update();
	}
	
	@ServiceMethod(callByContent=true, inContextMenu=true, target=TARGET_POPUP)
	@Face(displayName="$Modify")
	public ModalWindow modify() throws Exception {
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		categoryTitle.getMetaworksContext().setHow(CODE_MODIFY);
		categoryTitle.setTab(this.getTab());
		
		String[] temp = this.getId().split("_");
		// sector면
		if("SEC".equals(temp[0])) { 
			categoryTitle.setSectorId(this.getId());
			
		}
		// service면
		else {
			categoryTitle.setServiceId(this.getId());
			
		}
		categoryTitle.setContentName(this.getName());
		
		return new ModalWindow(categoryTitle , 500, 200,  "$Modify");
	}
	
	public void modifyTab() throws Exception {
		this.syncToDatabaseMe();
	}
	
}
