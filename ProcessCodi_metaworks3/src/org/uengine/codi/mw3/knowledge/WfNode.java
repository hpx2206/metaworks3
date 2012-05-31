package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dao.UniqueKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.NewInstanceWindow;
import org.uengine.codi.mw3.model.ProcessDefinition;

public class WfNode extends Database<IWfNode> implements IWfNode {
	@AutowiredFromClient
	public WfPanel wfPanel;
	
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
		
	String nameNext;
		public String getNameNext() {
			return nameNext;
		}
		public void setNameNext(String nameNext) {
			this.nameNext = nameNext;
		}
	
	boolean focus;	
		public boolean isFocus() {
			return focus;
		}
		public void setFocus(boolean focus) {
			this.focus = focus;
		}

	Long linkedInstId;	
		public Long getLinkedInstId() {
			return linkedInstId;
		}
		public void setLinkedInstId(Long linkedInstanceId) {
			this.linkedInstId = linkedInstanceId;
		}		
		
	int no;
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}

	String parentId;			
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

	WfNode dragNode;
		public WfNode getDragNode() {
			return dragNode;
		}
		public void setDragNode(WfNode dragNode) {
			this.dragNode = dragNode;
		}
		
	ArrayList<WfNode> childNode;
		public ArrayList<WfNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<WfNode> childNode) {
			this.childNode = childNode;
		}		
	
	public WfNode() {
		setChildNode(new ArrayList<WfNode>());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load(String nodeId) throws Exception {
		
		try {
			setId(nodeId);
			
			IWfNode dao = (IWfNode)get(getClass(), createKeyObject(), this);
			this.copyFrom(dao);
		} catch (Exception e) {
			if(!"ROOT".equals(getMetaworksContext().getHow()))
				getMetaworksContext().setHow("NONE");
		}
		
		this.loadChildren();
	}
	
	public void loadChildren() throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM workflowy");
		sb.append(" WHERE parentId=?parentId");
		sb.append(" ORDER BY no");
		
		IWfNode findNode = (IWfNode) sql(IWfNode.class,	sb.toString());
		
		findNode.set("parentId", this.getId());
		findNode.select();
		
		if(findNode.size() > 0){
			while (findNode.next()) {
				WfNode node = new WfNode();
				
				node.copyFrom(findNode);
				node.setChildNode(new ArrayList<WfNode>());
				node.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
				node.loadChildren();
				
				addChildNode(node);
			}
		}
	}
	
	public void createMe() throws Exception {
		String nodeId = String.valueOf(UniqueKeyGenerator.issueKey("workflowy", TransactionContext.getThreadLocalInstance()));
		
		setId(nodeId);

		createDatabaseMe();
		flushDatabaseMe();
	}

	public void saveMe() throws Exception {
		syncToDatabaseMe();
		flushDatabaseMe();
	}
	
	public void deleteMe() throws Exception {
		deleteDatabaseMe();
	}
	
	public void clearFocus() {
		setFocus(false);
		
		for(int i=0; i<getChildNode().size(); i++){
			getChildNode().get(i).clearFocus();
		}
	}
	
	/*******************************************
	 * 
	 * function
	 *  
	 *******************************************/	
	public void addChildNode(WfNode newNode) throws Exception {
		addChildNode(childNode.size(), newNode);	
	}
	
	public void addChildNode(int index, WfNode newNode) throws Exception {
		newNode.setNo(index);
		newNode.setParentId(this.getId());
		
		childNode.add(index, newNode);
		
		if(childNode.size() > index){
			for(int i=index; i<childNode.size(); i++){
				IWfNode node = (WfNode)childNode.get(i);
				
				if(node.getNo() != i){
					node.setNo(i);
					node.saveMe();
				}
			}
		}
	}
	
	public void removeChildNode(int index) throws Exception {
		childNode.remove(index);
		
		if(childNode.size() > 0){
			for(int i=index; i<childNode.size(); i++){
				IWfNode node = (WfNode)childNode.get(i);
				
				node.setNo(i);
				node.saveMe();
			}			
		}
	}
	
	private int getNodeIndex(){
		WfNode parentNode = wfPanel.getNode(this.getParentId());
		
		return parentNode.getChildNode().indexOf(this);
	}
	
	public WfNode getNode(String findId){
		
		WfNode resultNode = null;
		
		if(getId().equals(findId)){
			resultNode = this;
		}else{
			for(int i =0; i<getChildNode().size(); i++){
				resultNode = getChildNode().get(i).getNode(findId);
				
				if(resultNode != null)
					break;				
			}
		}
		
		return resultNode;			
	}
	
	/*******************************************
	 * 
	 * Service Method
	 *  
	 *******************************************/
	public WfNode newNode() throws Exception {
		WfNode newNode = new WfNode();
		addChildNode(newNode);
		
		newNode.createMe();
		newNode.setFocus(true);
		
		return this;
	}
	
	public WfNode add() throws Exception {
		
		// 입력 값이 없고 위치가 최상위가 아니라면 outdent
		if(getName().length() == 0 && getNameNext().length() == 0){
			WfNode outdentNode = this.outdent();
			
			if(outdentNode != null) 
				return outdentNode;
		}
		
		// 현재 노드의 위치를 구한다.
		int nodeIndex = this.getNo();	

		// 액션 노드의 context 변경 및 저장
		this.saveMe();
		
		wfPanel.clearFocus();
		
		// 추가될 노드의 focus 를 위한 context 변경
		WfNode newNode = new WfNode();
		newNode.setFocus(true);
		newNode.getMetaworksContext().setHow("add");
		
		if(getNameNext().length() == 0){
			if(getChildNode().size() > 0){
				addChildNode(0, newNode);
				
			}else{
				// 부모 노드를 구한다
				WfNode parentNode = wfPanel.getNode(this.getParentId());
				
				parentNode.addChildNode(nodeIndex+1, newNode);
			}
		}else{
			// 부모 노드를 구한다
			WfNode parentNode = wfPanel.getNode(this.getParentId());
			
			parentNode.addChildNode(nodeIndex+1, newNode);
			
			newNode.setName(getNameNext());
		}
		newNode.createMe();
		
		return wfPanel.getWfNode();

	}
	
	public WfNode indent() throws Exception {

		int nodeIndex = this.getNo();		
		if(nodeIndex == 0){
			return null;			
		}
		
		// 부모 노드를 구한다
		WfNode parentNode = wfPanel.getNode(this.getParentId());		
		
		// 부모 노드의 마지막 자식 노드를 구한다. (변경 부모 노드)
		WfNode appendNode = parentNode.getChildNode().get(nodeIndex - 1);
		
		// 부모 변경 처리
		parentNode.removeChildNode(nodeIndex);	// 이전 부모 노드에서 제거
		appendNode.addChildNode(this);			// 변경 부모 노드에 추가
						
		// 저장
		this.saveMe();
		
		wfPanel.clearFocus();
		WfNode focusNode = wfPanel.getNode(this.getId());
		focusNode.setFocus(true);
		focusNode.getMetaworksContext().setHow("indent");
		
		return wfPanel.getWfNode();		
		
	}
	
	public WfNode outdent() throws Exception {
		
		// 부모 노드의 부모 존재여부 검사
		if(wfPanel.getWfNode().getId().equals(this.getParentId())){
			return null;
		}
		
		// 현재 노드의 위치를 구한다.
		int nodeIndex = this.getNo();
				
		// 부모 노드를 구한다
		WfNode parentNode = wfPanel.getNode(this.getParentId());			
		// 부모 노드의 부모를 구한다. (변경 부모 노드)
		WfNode appendNode = wfPanel.getNode(parentNode.getParentId());

		// 부모 변경 처리
		parentNode.removeChildNode(nodeIndex);	// 이전 부모 노드에서 제거
		appendNode.addChildNode(this);			// 변경 부모 노드에 추가
				
		// 저장
		this.saveMe();
		
		// context 정보 초기화
		wfPanel.clearFocus();
		WfNode focusNode = wfPanel.getNode(this.getId());
		focusNode.getMetaworksContext().setHow("outdent");
		focusNode.setFocus(true);		
				
		return wfPanel.getWfNode();
	}	
	
	public WfNode remove() throws Exception {
		
		// context 정보 초기화
		wfPanel.clearFocus();
		
		// 현재 노드의 위치를 구한다.
		int nodeIndex = this.getNo();
		
		if(nodeIndex == 0){
			if(getChildNode().size() > 0){	// 처리X - 첫번째 노드, 자식 존재
				return null;
			}else if(wfPanel.getWfNode().getId().equals(this.getParentId())){
				return null;
			}else{
				if(getNameNext().length() > 0){	// 처리X - 첫번째 노드, 자식 미존재, 붙일 content 존재
					return null;	
				}else{	// 처리O - 첫번째 노드, 자식 미존재, 붙일 content 미존재 -> 현재 노드 제거, 부모 노드 포커싱
					WfNode parentNode = wfPanel.getNode(this.getParentId());
					
					parentNode.removeChildNode(nodeIndex);
					parentNode.setFocus(true);
					parentNode.getMetaworksContext().setHow("remove");
					
					// 삭제
					deleteMe();
				}
			}
		}else{
			WfNode parentNode = wfPanel.getNode(this.getParentId());
			WfNode actionNode = parentNode.getChildNode().get(nodeIndex-1);

			if(getChildNode().size() > 0){
				if(actionNode.getChildNode().size() > 0){ // 처리X - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 존재
					return null;
				}else{	// 처리O - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 미존재 -> 현재 노드 제거, 현재 노드에 자식들을 이전 노드로 변경, 이전 노드 포커싱					
					// 윗노드 삭제 처리
					parentNode.removeChildNode(actionNode.getNo());
					actionNode.deleteMe();
					
					// 현재 노드 저장 처리
					this.setName(actionNode.getName());
					this.saveMe();
					
					WfNode focusNode = wfPanel.getNode(this.getId());
					focusNode.setFocus(true);					
					focusNode.getMetaworksContext().setHow("remove");
				}	
			}else{
				
				if(actionNode.getChildNode().size() > 0 && this.getNameNext().length() > 0){ // 처리X - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 존재 and 붙일 content 존재
					return null;
				}else{	// 처리O - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 미존재 or 붙일 content 미존재 -> 현재 노드 제거, 이전 노드 또는 이전 노드 의 마지막 자식노드에 선택 and 붙임
					// 현재 노드 삭제 처리
					parentNode.removeChildNode(nodeIndex);
					deleteMe();
					
					while (actionNode.getChildNode().size() > 0)					
						actionNode = actionNode.getChildNode().get(actionNode.getChildNode().size()-1);
						
					if(actionNode.getName() != null)
						actionNode.setName(actionNode.getName() + this.getNameNext());
					else
						actionNode.setName(this.getNameNext());
					
					actionNode.setNameNext(this.getNameNext());
					actionNode.getMetaworksContext().setHow("remove");
					actionNode.setFocus(true);
					
					// 저장
					actionNode.saveMe();
				}
				
			}
			
		}
		
		return wfPanel.getWfNode();
	}
	
	public WfNode move() throws Exception {
		
		if(this.equals(getDragNode())){
			return null;
		}				
		
		WfNode dragNode = this.getDragNode();
		WfNode parentNode = wfPanel.getNode(dragNode.getParentId());
		
		int dragIndex = dragNode.getNo();

		// 자식 존재
		// 자식 맨 위에 붙임
		if(getChildNode().size() > 0){
			// 드래그된 노드의 부모에서 제거 드래그노드 제거
			parentNode.removeChildNode(dragIndex);
			addChildNode(0, dragNode);
			
			dragNode.saveMe();
			
		// 자식 없음
		// 다음 노드에 붙임	
		} else {
			int nodeIndex = this.getNo();
			
			parentNode.removeChildNode(dragIndex);
						
			if(this.getParentId().equals(dragNode.getParentId())){
				if(nodeIndex > dragIndex)
					nodeIndex = nodeIndex - 1;
			}			
			
			parentNode.addChildNode(nodeIndex+1, dragNode);
			
			dragNode.saveMe();
			
		}
		
		return wfPanel.getWfNode();
	}	
	
	public void save() throws Exception {
		this.saveMe();
	}
	
	@Autowired 
	public ProcessDefinition processDefinition;
	
	@Autowired
	public InstanceViewContent  instanceViewContent;
	
	public ContentWindow linkInstance() throws Exception {
		Instance instance = new Instance();
		instance.setInstId(new Long(getLinkedInstId()));
		
		instanceViewContent.load(instance);
		
		return instanceViewContent;
	}
	
	public ContentWindow newProcessInstance() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.setKnowledgeNodeId(this.getId());
		newInstancePanel.load();
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
	public ContentWindow newDocument() throws Exception{
		processDefinition.setDefId(new Long(150));
		return (ContentWindow) processDefinition.initiate()[0];		
	}
}