package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToNext;
import org.metaworks.ToPrev;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dao.UniqueKeyGenerator;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceView;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.NewInstanceWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessDefinition;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.model.UnstructuredProcessInstanceStarter;
import org.uengine.codi.mw3.model.User;
import org.uengine.codi.mw3.model.WorkItem;
import org.uengine.kernel.GlobalContext;

public class WfNode extends Database<IWfNode> implements IWfNode {
	
	static Hashtable<Integer, ArrayList<String>> nodeListeners = new Hashtable<Integer, ArrayList<String>>();
	public final static int LOAD_DEPTH = 3;
	
	String id;
		public String getId() {
			return id;
		}	
		public void setId(String id) {
			this.id = id;
		}	
		
	String type;		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	String visType;

		public String getVisType() {
			return visType;
		}
		public void setVisType(String visType) {
			this.visType = visType;
		}

	String connType;
		
		public String getConnType() {
			return connType;
		}
		public void setConnType(String connType) {
			this.connType = connType;
		}


	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String authorId;
			
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
		
	IUser author;

		public IUser getAuthor() {
			return author;
		}
		public void setAuthor(IUser author) {
			this.author = author;
		}

	String nameNext;
		public String getNameNext() {
			return nameNext;
		}
		public void setNameNext(String nameNext) {
			this.nameNext = nameNext;
		}
		
	String typeNext;
	
		public String getTypeNext() {
			return typeNext;
		}
		public void setTypeNext(String typeNext) {
			this.typeNext = typeNext;
		}

	boolean focus;	
		public boolean isFocus() {
			return focus;
		}
		public void setFocus(boolean focus) {
			this.focus = focus;
		}
		
	boolean close;
		public boolean isClose() {
			return close;
		}
		public void setClose(boolean close) {
			this.close = close;
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
		
	public int loadDepth = 0;
		public int getLoadDepth() {
			return loadDepth;
		}
		public void setLoadDepth(int loadDepth) {
			this.loadDepth = loadDepth;
		}
		
	public WfNode() {
		setChildNode(new ArrayList<WfNode>());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	 
	
	public void search(String keyword) throws Exception {
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT *");
			sb.append("  FROM bpm_knol");
			sb.append(" WHERE name like ?name");
			sb.append(" ORDER BY no");
			
			IWfNode node = sql(sb.toString());
			node.setName("%" + keyword + "%");
			node.select();
			
			while(node.next()){
				WfNode addNode = new WfNode();
				
				addNode.copyFrom(node);
				addNode.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
				addNode.loadChildren();
				
				this.addChildNode(addNode);
			}
		} catch (Exception e) {
			if(!"ROOT".equals(getMetaworksContext().getHow()))
				getMetaworksContext().setHow("NONE");
		}
	}
	
	public void searchKMS(String keyword) throws Exception {
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT *");
			sb.append("  FROM bpm_knol");
			sb.append(" WHERE name REGEXP ?name");
			sb.append(" ORDER BY no");
			
			IWfNode node = sql(sb.toString());
			node.setName(keyword);
			node.select();
			
			while(node.next()){
				WfNode addNode = new WfNode();
				
				addNode.copyFrom(node);
				addNode.getMetaworksContext().setWhen(getMetaworksContext().getWhen());
				addNode.loadChildren();
				
				this.addChildNode(addNode);
			}
		} catch (Exception e) {
			if(!"ROOT".equals(getMetaworksContext().getHow()))
				getMetaworksContext().setHow("NONE");
		}
	}
	
	public void load() throws Exception {
		load(this.getId());
	}
	
	public void load(String nodeId) throws Exception {
		
		try {
			setId(nodeId);
			
			if(this.getLoadDepth() > -1)
				setLoadDepth(0);
			
			IWfNode dao = (IWfNode)get(getClass(), createKeyObject(), this);
			this.copyFrom(dao);
		} catch (Exception e) {
//			if(!"ROOT".equals(getMetaworksContext().getHow()))
//				getMetaworksContext().setHow("NONE");
		}
		
		this.loadChildren();
	}
	
	
	
	public void loadChildren() throws Exception {
		
		setChildNode(new ArrayList<WfNode>());		
		
		if(this.getLoadDepth() < LOAD_DEPTH){
			if(this.getLoadDepth() > -1)
				setLoadDepth(getLoadDepth()+1);						
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT *");
			sb.append("  FROM bpm_knol");
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
					
					if(node.getVisType()==null && !"ROOT".equals(getMetaworksContext().getHow())){
						node.getMetaworksContext().setHow(getMetaworksContext().getHow());
					}else{
						node.getMetaworksContext().setHow(node.getVisType());
					}
					
					node.setLoadDepth(this.getLoadDepth());
					node.loadChildren();  //재귀호출로 하위를 갖고 오네... 기본 3 depth정도로 제한을 둬야할듯.. 
					
					addChildNode(node);
				}
			}
		}else{
			setClose(true);
		}
	}
	
	public String makeId() throws Exception {
		return String.valueOf(UniqueKeyGenerator.issueKey("bpm_knol", TransactionContext.getThreadLocalInstance()));
	}
		
	public void createMe() throws Exception {
		String nodeId = this.makeId();
		
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
	public int getNodeIndex(WfNode node){
		for(int i=0; i<childNode.size(); i++){
			if(((WfNode)childNode.get(i)).getId().equals(node.getId()))
				return i;
			
		}
		return -1;
	}
	public void addChildNode(WfNode newNode) throws Exception {
		addChildNode(childNode.size(), newNode);	
	}
	
	public void addChildNode(int index, WfNode newNode) throws Exception {
		newNode.setNo(index);
		newNode.setParentId(this.getId());
		
		childNode.add(index, newNode);
		
		// update
		if(childNode.size()-1 > index){
			StringBuffer sb = new StringBuffer();
			sb.append("update bpm_knol");
			sb.append("   set no=no+1");
			sb.append(" where parentId=?parentId");
			sb.append("   and no>=?no");
			
			IWfNode updateNode = sql(sb.toString());
			updateNode.setParentId(this.getId());
			updateNode.setNo(index);
			
			updateNode.update();
		}
		
		// reorder
		for(int i=index; i<childNode.size(); i++){
			IWfNode node = (WfNode)childNode.get(i);
			
			node.setNo(i);
		}
		
	}
	
	public void removeChildNode(int index) throws Exception {
		childNode.remove(index);
		
		if(childNode.size() > 0){
			for(int i=index; i<childNode.size(); i++){
				IWfNode node = (WfNode)childNode.get(i);
				
				node.setNo(i);
			}
			
			// update
			if(childNode.size()-1 > index){
				StringBuffer sb = new StringBuffer();
				sb.append("update bpm_knol");
				sb.append("   set no=no-1");
				sb.append(" where parentId=?parentId");
				sb.append("   and no>=?no");
				
				IWfNode updateNode = sql(sb.toString());
				updateNode.setParentId(this.getId());
				updateNode.setNo(index);
				
				updateNode.update();
			}			
		}
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
	
	public void copyMetaworksContext(MetaworksContext metaworksContext){
		this.getMetaworksContext().setWhen(metaworksContext.getWhen());
		this.getMetaworksContext().setHow(metaworksContext.getHow());
		this.getMetaworksContext().setWhere(metaworksContext.getWhere());
	}
	/*******************************************
	 * 
	 * Service Method
	 *  
	 *******************************************/
	private void addNodeListener(Integer nodeId){
		
		ArrayList<String> nodeListenerArray = null;
		if(!nodeListeners.containsKey(nodeId)){
			nodeListenerArray = new ArrayList<String>();
			
			nodeListeners.put(nodeId, nodeListenerArray);
		}else{
			nodeListenerArray = nodeListeners.get(nodeId);
		}
		WebContext wctx = WebContextFactory.get();

		nodeListenerArray.add(wctx.getScriptSession().getId());

	}
	
	public Object[] add() throws Exception {
		
		
		// 입력 값이 없고 위치가 최상위가 아니라면 outdent
		if(getName().length() == 0 && getNameNext().length() == 0){
			final Object[] returnObjects = this.outdent();
			
			if(returnObjects != null)
				return returnObjects;				
		}
		
		// 반환 값
		Object targetObject = null;
		
		// 부모 노드를 구한다
		WfNode parentNode = new WfNode();
		parentNode.copyMetaworksContext(getMetaworksContext());
		parentNode.setLoadDepth(-1);
		parentNode.load(this.getParentId());
		
		// 내 노드를 구한다. -> 저장
		final WfNode node = parentNode.getNode(this.getId());
		node.setName(this.getName());
		node.save();
			
		// 새로운 노드를 만든다.
		final WfNode newNode = new WfNode();
		
		if(getTypeNext() != null && getTypeNext().length() > 0){
			newNode.setName(getNameNext());
			newNode.setType(getTypeNext());
			
			node.addChildNode(newNode);
			
			node.setFocus(true);
			node.getMetaworksContext().setHow("add");
			
		}else{
			newNode.setFocus(true);
			newNode.getMetaworksContext().setHow("add");
			
			if(getNameNext().length() == 0){
				if(node.getChildNode().size() > 0){
					node.addChildNode(0, newNode);
				}else{				
					parentNode.addChildNode(node.getNo()+1, newNode);
					
					targetObject = new ToNext(this, newNode);
				}				
			}else{
				newNode.setName(getNameNext());
				
				parentNode.addChildNode(node.getNo()+1, newNode);			
							
				targetObject = new ToNext(this, newNode);				
			}
		}
		
		newNode.setAuthorId(session.getUser().getUserId());		
		newNode.createMe();

		
		WebContext wctx = WebContextFactory.get();
		String mySessionId = wctx.getScriptSession().getId();

		// 근방에 있는 노드들에게 알려줌... 여그 노드 추가됐어여~~
		//Integer nodeIndexInteger = new Integer(parentId);
		//if(nodeListeners.containsKey(nodeIndexInteger)){
		//	ArrayList<String> parentNodeListeners = nodeListeners.get(nodeIndexInteger);
		
		//ArrayList<String> parentNodeListeners = wctx.getScriptSession();

		final Object[] returnObjects;
		
		if(targetObject == null)
			returnObjects = new Object[]{new Refresh(node)};
		else
			returnObjects = new Object[]{new Refresh(node), targetObject};
			
		String currentPage = wctx.getCurrentPage();

		   Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);

			for(ScriptSession session : sessions ){
				String parentNodeListenerSessionId = session.getId();
				
				if(!parentNodeListenerSessionId.equals(mySessionId))
				Browser.withSession(parentNodeListenerSessionId, new Runnable(){

					@Override
					public void run() {
						ScriptSessions.addFunctionCall("mw3.locateObject", new Object[]{returnObjects, null, "body"});
						ScriptSessions.addFunctionCall("mw3.onLoadFaceHelperScript", new Object[]{});

					}
					
				});

			}
		//}

		
		return returnObjects;

	}
	
	public Object[] indent() throws Exception {

		// 부모 노드를 구한다
		WfNode parentNode = new WfNode();
		parentNode.setMetaworksContext(getMetaworksContext());
		parentNode.setLoadDepth(-1);
		parentNode.load(this.getParentId());		

		WfNode node = parentNode.getNode(this.getId());		
		if(node.getNo() == 0){
			return null;			
		}
		
		// 부모 노드의 마지막 자식 노드를 구한다. (변경 부모 노드)
		WfNode appendNode = parentNode.getChildNode().get(node.getNo() - 1);
		
		// 부모 변경 처리
		parentNode.removeChildNode(node.getNo());	// 이전 부모 노드에서 제거
		appendNode.addChildNode(node);				// 변경 부모 노드에 추가
						
		// 변경 정보 적용
		node.setName(this.getName() + this.getNameNext());
		node.setNameNext(this.getNameNext());
		node.saveMe();
		node.setFocus(true);
		
		if(node.getNo() == 0){
			return new Object[]{new Remover(this), new Refresh(appendNode)};
		}else{
			WfNode targetNode = appendNode.getChildNode().get(node.getNo()-1);
			targetNode.setChildNode(null);
			
			return new Object[]{new Remover(this), new ToNext(targetNode, node)};			
		}
	}
		
	
	public Object[] outdent() throws Exception {

		// 부모 존재여부 검사
		if("-1".equals(this.getParentId()))
			return null;
				
		// 부모 노드를 구한다
		WfNode parentNode = new WfNode();
		parentNode.setMetaworksContext(this.getMetaworksContext());
		parentNode.setId(this.getParentId());		
		
		String parentParentId = parentNode.databaseMe().getParentId();
		
		// 부모 노드의 부모를 구한다. (변경 부모 노드)
		WfNode appendNode = new WfNode();
		appendNode.setMetaworksContext(this.getMetaworksContext());
		appendNode.setId(parentParentId);		
		
		if("-1".equals(parentParentId)){
			appendNode.setLoadDepth(-1);
			appendNode.loadChildren();
			appendNode.getMetaworksContext().setWhen("ROOT");
		}else{
			appendNode.setLoadDepth(-1);
			appendNode.load();			
		}
		parentNode = appendNode.getNode(parentNode.getId());		
		
		// 현재 노드를 구한다.
		WfNode node = parentNode.getNode(this.getId());
		
		// 부모 변경 처리
		appendNode.addChildNode(node);				// 변경 부모 노드에 추가
				
		// 변경 정보 적용
		node.setName(this.getName() + this.getNameNext());
		node.setNameNext(this.getNameNext());
		node.saveMe();
		node.setFocus(true);
		
		WfNode targetNode = appendNode.getChildNode().get(node.getNo()-1);
		targetNode.setChildNode(null);
		
		return new Object[]{new Remover(this), new ToNext(targetNode, node)};
	}	
	
	public Object[] remove() throws Exception {
		
		// 부모 노드를 구한다
		WfNode parentNode = new WfNode();
		parentNode.setMetaworksContext(this.getMetaworksContext());
		parentNode.setId(this.getParentId());
		parentNode.setLoadDepth(-1);
		parentNode.load();
		
		if("-1".equals(parentNode.getId()) && parentNode.getChildNode().size() == 1)
			return null;

		// 현재 노드를 구한다.
		WfNode node = parentNode.getNode(this.getId());
		
		// 반환 노드
		WfNode resultNode = null;
		
		if(node.getNo() == 0){
			if(node.getChildNode().size() > 0){	// 처리X - 첫번째 노드, 자식 존재
				return null;
			}else{
				if(this.getNameNext() != null && this.getNameNext().length() > 0){	// 처리X - 첫번째 노드, 자식 미존재, 붙일 content 존재
					return null;	
				}else{	// 처리O - 첫번째 노드, 자식 미존재, 붙일 content 미존재 -> 현재 노드 제거, 부모 노드 포커싱
					resultNode = parentNode;
				}
			}
		}else{
			WfNode actionNode = parentNode.getChildNode().get(node.getNo()-1);

			if(node.getChildNode().size() > 0){
				if(actionNode.getChildNode().size() > 0){ // 처리X - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 존재
					return null;
				}else{	// 처리O - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 미존재 -> 현재 노드 제거, 현재 노드에 자식들을 이전 노드로 변경, 이전 노드 포커싱					
					// 윗노드 삭제 처리
					actionNode.setName(actionNode.getName() + this.getNameNext());
					actionNode.setNameNext(this.getNameNext());
					actionNode.saveMe();
					
					resultNode = actionNode;
				}	
			}else{
				
				if(actionNode.getChildNode().size() > 0 && this.getNameNext().length() > 0){ // 처리X - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 존재 and 붙일 content 존재
					return null;
				}else{	// 처리O - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 미존재 or 붙일 content 미존재 -> 현재 노드 제거, 이전 노드 또는 이전 노드 의 마지막 자식노드에 선택 and 붙임
					while (actionNode.getChildNode().size() > 0)					
						actionNode = actionNode.getChildNode().get(actionNode.getChildNode().size()-1);
						
					actionNode.setName(actionNode.getName() +this.getNameNext());					
					actionNode.setNameNext(this.getNameNext());
					actionNode.saveMe();
					
					resultNode = actionNode;
				}
				
			}			
		}
		
		parentNode.removeChildNode(node.getNo());
		node.deleteMe();
		
		if(resultNode == null){
			return new Object[]{new Remover(this)};
		}else{
			// reload
			resultNode.load();
			resultNode.getMetaworksContext().setHow("remove");
			resultNode.setFocus(true);
			
			return new Object[]{new Remover(this), new Refresh(resultNode)};	
		}
	}
	
	public Object[] move() throws Exception {
		
		
		if(this.getId() == this.getDragNode().getId()){
			return null;
		}				

		WfNode parentNode = new WfNode();
		parentNode.setMetaworksContext(this.getMetaworksContext());
		parentNode.setId(this.getDragNode().getParentId());
		parentNode.load();

		WfNode dragNode = parentNode.getNode(this.getDragNode().getId());		
		
		// 부모 노드를 구한다
		parentNode = new WfNode();
		parentNode.setMetaworksContext(this.getMetaworksContext());
		parentNode.setId(this.getParentId());
		parentNode.load();
		
		// 자신을 구한다.
		WfNode node = parentNode.getNode(this.getId());
		
		if(node.getParentId().equals(dragNode.getParentId()) && node.getNo() == dragNode.getNo()){
			return null;
		}
		
		// 자식 존재
		// 자식 맨 위에 붙임
		if(node.getChildNode().size() > 0){
			if(node.getId().equals(dragNode.getParentId()) && dragNode.getNo() == 0){
				return null;
			}
			
			parentNode.removeChildNode(dragNode.getNo());
			node.addChildNode(0, dragNode);
			
			dragNode.saveMe();
			
			WfNode targetNode = node.getChildNode().get(1);
			targetNode.setChildNode(null);
			
			return new Object[]{new Remover(dragNode), new ToPrev(targetNode, dragNode)};
			
		// 자식 없음
		// 다음 노드에 붙임	
		} else {
			parentNode.removeChildNode(dragNode.getNo());
			parentNode.addChildNode(node.getNo()+1, dragNode);			
			dragNode.saveMe();
			
			return new Object[]{new Remover(dragNode), new ToNext(this, dragNode)};
			
		}
		
		
	}	
	
	public void save() throws Exception {
		this.saveMe();
	}
	
	public Object[] removeNode() throws Exception {
		
		// 부모 노드를 구한다
		WfNode parentNode = new WfNode();
		parentNode.setMetaworksContext(this.getMetaworksContext());
		parentNode.setId(this.getParentId());
		parentNode.load();
		
		if("-1".equals(parentNode.getId()) && parentNode.getChildNode().size() == 1)
			return null;
		
		if(getAuthor()==null || getAuthor().getUserId().equals(session.getUser().getUserId()) || session.getEmployee().getIsAdmin()) {
		
			WfNode node = parentNode.getNode(this.getId());		
			parentNode.removeChildNode(node.getNo());
			
			node.deleteMe();
			
		} else {
			throw new Exception("$AuthorOnly");
		}
		
		return new Object[]{new Remover(this)};
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
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		newInstancePanel.getNewInstantiator().setTitle(getName());
		
		return new NewInstanceWindow(newInstancePanel);
	}
	
	public ContentWindow mashup() throws Exception{
		return new ContentWindow(new Mashup());
	}
	
	public ContentWindow newDocument() throws Exception{
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.setKnowledgeNodeId(this.getId());
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		newInstancePanel.getNewInstantiator().session = session;
		newInstancePanel.setNewInstantiator((WorkItem) newInstancePanel.getNewInstantiator().newFile());
		newInstancePanel.getNewInstantiator().setTitle(getName());
		
		return new NewInstanceWindow(newInstancePanel);		
	}	

	public WfNode expand() throws Exception {
		if(this.getLoadDepth() == LOAD_DEPTH){
			this.setLoadDepth(this.getLoadDepth()-1);
			this.loadChildren();
		}
			
		setClose(false);
		
		return this;
	}
	
	@AutowiredFromClient
	public Session session;
	
	
	public WfNode collapse() throws Exception {
		setClose(true);
		
		return this;
	}
	
	public ModalWindow presentation() throws Exception{
		WfPanel_pt panel = new WfPanel_pt();
		panel.session = session;
		panel.load(/*session.getCompany().getComCode() */ getParentId(), "presentation");
		return new ModalWindow(panel , 1000, 600,  "학습창" );
	}
	
	@Override
	public WfPanel drillInto() throws Exception {
		WfPanel panel = new WfPanel();
		panel.session = session;
		panel.load(getId());
		
		return panel;
	}
	
	@Override
	public Object[] makeAsTemplate() throws Exception {
		// TODO Auto-generated method stub
		return new Object[]{new ModalWindow()};
	}
	
	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IInstance){
			IInstance instanceInClipboard = (IInstance) clipboard;
			
			Instance locatorForInstanceInClipboard = new Instance();
			locatorForInstanceInClipboard.setInstId(instanceInClipboard.getInstId());
			
			instanceInClipboard = locatorForInstanceInClipboard.databaseMe();

			WfNode child = new WfNode();		
			child.setName(instanceInClipboard.getName());
			child.setLinkedInstId(instanceInClipboard.getInstId());
			
			this.addChildNode(child);
			child.createMe();
			
			this.save();
			
			return new Object[]{new Refresh(this)};
		}else if(clipboard instanceof IUser){
			
			IUser user = (IUser) clipboard;
			
			//UnstructuredProcessInstanceStarter instanceStarter = new UnstructuredProcessInstanceStarter();
			//instanceStarter.processManager = processManager;
			instanceStarter.session = session;
			instanceStarter.setTitle("[TODO] " + getName());
			
			instanceStarter.setFriend(user);
			Object[] instanceViewAndInstanceList = instanceStarter.start();
			
			InstanceView instanceView = ((InstanceViewContent)instanceViewAndInstanceList[0]).instanceView;
			Long instId = new Long(instanceView.getInstanceId());
			
			setLinkedInstId(instId);

			
			Instance instanceRef = new Instance();
			instanceRef.setInstId(instId);
			instanceRef.databaseMe().setStatus("Running");

			save();
			
			Employee theUser = new Employee();
			theUser.setEmpCode(user.getUserId());
			
			return new Object[]{new Refresh(this), new ModalWindow(instanceView.schedule().getPanel(), 400, 300, theUser.databaseMe().getEmpName() + "님에게 과제를 부여합니다.")};
		}
		
		
		return null;
	}
	
	
	
	@Override
	public Session drag() {
		session.setClipboard(this);
		return session;
	}
	
	@Autowired
	public UnstructuredProcessInstanceStarter instanceStarter;
	
	
	@Override
	public Popup visualizationType() {
		return new Popup(new VisualizationType(this));
		
	}
	@Override
	public Popup connectionType() {
		// TODO Auto-generated method stub
		return new Popup(new ConnectionType(this));
	}
	@Override
	public Popup xml() throws Exception {
		
		WfNodeXML wfNodeXML = new WfNodeXML();
		
		SourceCode sourceCode = new SourceCode();
		sourceCode.setCode(GlobalContext.serialize(this, Object.class));
		wfNodeXML.setXml(sourceCode);
		wfNodeXML.setNodeId(getId());
		
		Popup popup = new Popup(wfNodeXML);
		popup.setName("To/From XML");
		
		return popup;
	}
	
	
	
}