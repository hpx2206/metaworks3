package org.uengine.codi.mw3.model;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class WorkflowyNode implements ContextAware {

//	public MongoDB mongoDB;

	public WorkflowyNode() throws UnknownHostException, MongoException{		
		this(0);
	}
	
	public WorkflowyNode(int nodeId) throws UnknownHostException, MongoException{
		Mongo m = new Mongo();
		
		//mongoDB = new MongoDB();
		
		if(nodeId == 0){
			nodeId = makeId();
		}
		
		setId(nodeId);
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		childNode = new ArrayList<WorkflowyNode>();
	}	
		
	int id;
		@Id
		public int getId() {
			return id;
		}	
		public void setId(int id) {
			this.id = id;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			setChanged(true);
			
			this.name = name;
		}

	String nameNext;
		public String getNameNext() {
			return nameNext;
		}
		public void setNameNext(String nameNext) {
			this.nameNext = nameNext;
		}
	
	boolean changed;	
		public boolean isChanged() {
			return changed;
		}
		public void setChanged(boolean changed) {
			this.changed = changed;
		}
	
	WorkflowyNode parentNode;	
		public WorkflowyNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(WorkflowyNode parentNode) {
			setChanged(true);
			
			this.parentNode = parentNode;
		}	
		
	WorkflowyNode dragNode;
		public WorkflowyNode getDragNode() {
			return dragNode;
		}
		public void setDragNode(WorkflowyNode dragNode) {
			this.dragNode = dragNode;
		}
		
	ArrayList<WorkflowyNode> childNode;
		public ArrayList<WorkflowyNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<WorkflowyNode> childNode) {
			this.childNode = childNode;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	private void addChildNode(WorkflowyNode newNode){
		addChildNode(childNode.size(), newNode);	
	}
	
	private void addChildNode(int index, WorkflowyNode newNode){
		childNode.add(index, newNode);
		
		for(int i=0; i<childNode.size(); i++)
			childNode.get(i).setChanged(true);		
	}
	
	private void removeChildNode(int index){
		childNode.remove(index);
		
		for(int i=0; i<childNode.size(); i++)
			childNode.get(i).setChanged(true);		
	}
	
	private WorkflowyNode getRootNode(){
		
		if(getParentNode() == null)
			return this;
		else
			return getParentNode().getRootNode();
	}	
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public ArrayList<WorkflowyNode> add() throws UnknownHostException, MongoException{		
		if(getParentNode().getParentNode() != null && getName().length()==0)
			return outdent();
		
		ArrayList<WorkflowyNode> result = new ArrayList<WorkflowyNode>();
		
		setChanged(true);
				
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		WorkflowyNode newNode = new WorkflowyNode();
		newNode.getMetaworksContext().setHow("add");
		 
		if(getNameNext().length() == 0){
			if(getChildNode().size()>0){			
				newNode.setParentNode(this);
				addChildNode(0, newNode);
				
				// return object
				result.add(this);
			}else{
				newNode.setParentNode(getParentNode());
				
				int nodeIndex = getParentNode().getChildNode().indexOf(this);			
				getParentNode().addChildNode(nodeIndex+1, newNode);
				
				// return object
				result.add(getParentNode());
			}
		}else{
			int nodeIndex = getParentNode().getChildNode().indexOf(this);			
			
			for(int i=0; i<getChildNode().size(); i++)
				getChildNode().get(i).setParentNode(newNode);
			
			newNode.setParentNode(getParentNode());
			newNode.setChildNode(getChildNode());
			newNode.setName(getNameNext());
			
			setChildNode(new ArrayList<WorkflowyNode>());
			getParentNode().addChildNode(nodeIndex+1, newNode);
			
			// return object
			result.add(getParentNode());
		}
		
		// save object
		save();
		newNode.save();
		
		return result;
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode remove() throws UnknownHostException, MongoException{
				
		int nodeIndex = getParentNode().getChildNode().indexOf(this);
		
		if(nodeIndex == 0){
			// 처리X - 첫번째 노드, 자식 존재
			if(getChildNode().size() > 0){
				getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				getMetaworksContext().setHow("remove");
				
				setName(getName() + getNameNext());
				
				//return getRootNode();
				//return getParentNode();
				return this;
			}else{
				// 처리X - 첫번째 노드, 자식 미존재, 붙일 content 존재
				if(getNameNext().length() > 0){
					getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getMetaworksContext().setHow("remove");
					
					setName(getName() + getNameNext());
					
					return this;
					//return getRootNode();
					
					
					// :TODO this를 retuen 시에 setName() 에 값을 넣어 return 하는데 retuen 이 되지를 않음 이유 알 수 없음		
				
				// 처리O - 첫번째 노드, 자식 미존재, 붙일 content 미존재
				// 현재 노드 제거, 부모 노드 포커싱
				}else{
					getParentNode().removeChildNode(nodeIndex);
					getParentNode().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getParentNode().getMetaworksContext().setHow("remove");
					
					// 삭제
					delete();
					
					return getParentNode();
					//return getRootNode();
				}
			}
		} else {
			if(getChildNode().size() > 0){
				// 처리X - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 존재
				if(getParentNode().getChildNode().get(nodeIndex-1).getChildNode().size() > 0)
					return this;
				// 처리O - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 미존재
				// 현재 노드 제거, 현재 노드에 자식들을 이전 노드로 변경, 이전 노드 포커싱
				else {
					getParentNode().removeChildNode(nodeIndex);
					
					// 삭제
					delete();
					
					WorkflowyNode targetNode = getParentNode().getChildNode().get(nodeIndex-1);
					
					for(int i=0; i<getChildNode().size(); i++){
						WorkflowyNode childNode = getChildNode().get(i);
						
						childNode.setParentNode(targetNode);
					}
					
					targetNode.setChildNode(getChildNode());					
					targetNode.setName(targetNode.getName()+getNameNext());
					targetNode.setName(getNameNext());
					targetNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					targetNode.getMetaworksContext().setHow("remove");
					
					targetNode.save();
					
					return getParentNode();
					//return getRootNode();
				}					
			}else{
				// 처리X - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 존재 and 붙일 content 존재
				if(getParentNode().getChildNode().get(nodeIndex-1).getChildNode().size() > 0 && getNameNext().length() > 0){
					getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getMetaworksContext().setHow("remove");
					setName(getName() + getNameNext());
					
					return this;
					//return getRootNode();
					
					// :TODO this를 retuen 시에 setName() 에 값을 넣어 return 하는데 retuen 이 되지를 않음 이유 알 수 없음						
				// 처리O - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 미존재 or 붙일 content 미존재
				// 현재 노드 제거, 이전 노드 또는 이전 노드 의 마지막 자식노드에 선택 and 붙임
				}else{					
					getParentNode().removeChildNode(nodeIndex);

					// 삭제
					delete();
					
					WorkflowyNode targetNode = getParentNode().getChildNode().get(nodeIndex-1);
					
					while (targetNode.getChildNode().size() > 0)					
						targetNode = targetNode.getChildNode().get(targetNode.getChildNode().size()-1);
						
					targetNode.setName(targetNode.getName()+getNameNext());
					targetNode.setNameNext(getNameNext());
					targetNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					targetNode.getMetaworksContext().setHow("remove");
					
					// 저장
					targetNode.save();
					
					return getParentNode();
					//return getRootNode();
				}				
			}
		}
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode indent() throws UnknownHostException, MongoException{
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		getMetaworksContext().setHow("indent");
		
		int nodeIndex = getParentNode().getChildNode().indexOf(this);
		
		if(nodeIndex == 0){
			return null;			
		}
		
		getParentNode().removeChildNode(nodeIndex);
		
		setParentNode(getParentNode().getChildNode().get(nodeIndex-1));
		getParentNode().addChildNode(this);
		
		// 저장
		save();
		
		return getRootNode();		
		//return getParentNode();
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public ArrayList<WorkflowyNode> outdent() throws UnknownHostException, MongoException{
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		getMetaworksContext().setHow("outdent");
		
		ArrayList<WorkflowyNode> result = new ArrayList<WorkflowyNode>();
		
		if(getParentNode().getParentNode() == null){
			return null;
		}
		
		int nodeIndex = getParentNode().getParentNode().getChildNode().indexOf(getParentNode());
					
		getParentNode().removeChildNode(getParentNode().getChildNode().indexOf(this));
		
		setParentNode(getParentNode().getParentNode());
		getParentNode().addChildNode(nodeIndex+1, this);
		
		// save
		save();
		
		// reload
		//getParentNode().load();
		
		// return object
		//result.add(getParentNode());
		result.add(getRootNode());
		
		return result;
	}
	
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public ArrayList<WorkflowyNode> move(){
		getDragNode().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		getDragNode().getMetaworksContext().setHow("drag");
		
		ArrayList<WorkflowyNode> nodeList = new ArrayList<WorkflowyNode>();
		
		if(this.equals(getDragNode())){
			nodeList.add(this);
			
			return nodeList;
		}
				
		int dragIndex = getDragNode().getParentNode().getChildNode().indexOf(getDragNode());
						
		// 자식 존재
		// 자식 맨 위에 붙임
		if(getChildNode().size() > 0){			
			// 드래그된 노드의 부모에서 제거 드래그노드 제거
			getDragNode().getParentNode().removeChildNode(dragIndex);
			getDragNode().setParentNode(this);
			
			addChildNode(0, getDragNode());
			
		// 자식 없음
		// 다음 노드에 붙임	
		} else {
			int index = getParentNode().getChildNode().indexOf(this);
			
			getDragNode().getParentNode().removeChildNode(dragIndex);
						
			if(getParentNode().equals(getDragNode().getParentNode())){
				if(index > dragIndex)
					index = index - 1;
			}			
			
			getDragNode().setParentNode(getParentNode());
			getParentNode().addChildNode(index+1, getDragNode());
			
		}
		
		nodeList.add(dragNode.getParentNode());
		nodeList.add(getParentNode());
		
		// 저장
		getParentNode().save();
		
		return nodeList;
	}	

	@ServiceMethod(callByContent=true)
	public void save(){
		
		try{
			if(getId() > 1){
				Mongo m = new Mongo();
				DB db = m.getDB("testdb");
				//DB db = mongoDB.getDB();
				
				
				BasicDBObject find = new BasicDBObject();
				find.put("id", getId());
				
				DBCollection coll = db.getCollection("workflowy");
				DBCursor cur = coll.find(find);
				
				BasicDBObject node = new BasicDBObject();
				node.put("id", getId());
				node.put("name", getName());
				node.put("parentId", getParentNode().getId());
				node.put("index", getParentNode().getChildNode().indexOf(this));
				
				if(cur.hasNext()){
					coll.update(find, node);
				}else{
					coll.insert(node);
				}
				
				setChanged(false);
			}
			
			for(int i=0; i<getChildNode().size(); i++){
				WorkflowyNode childNode = getChildNode().get(i);
				
				if(childNode.isChanged())
					childNode.save();
					
			}
		}catch(UnknownHostException e1){
			e1.printStackTrace();
		}catch(MongoException e2){
			e2.printStackTrace();
		}
	}
	
	@ServiceMethod(callByContent=true)
	public void delete() throws UnknownHostException, MongoException {
		Mongo m = new Mongo();
		DB db = m.getDB("testdb");
		//DB db = mongoDB.getDB();
		
		BasicDBObject find = new BasicDBObject();
		find.put("id", getId());
		
		DBCollection coll = db.getCollection("workflowy");
		coll.remove(find);
		
	}
	
	@ServiceMethod(callByContent=true)
	public void load() throws UnknownHostException, MongoException{
		
		DB db = null;
		DBCursor cur = null;
		DBCollection coll = null;
		
		try{					
			Mongo m = new Mongo();
			db = m.getDB("testdb");
			//DB db = mongoDB.getDB();
			coll = db.getCollection("workflowy");
			
			BasicDBObject find = new BasicDBObject();
			BasicDBObject sort = new BasicDBObject();
			
			/*
			
			// 자신 설정하기 현재는 비활성화
			if(isChanged()){				
				find.append("id", getId());	
				
				DBCursor cur = coll.find(find);
				
		        while(cur.hasNext()) {
		        	DBObject result = cur.next();
		        	
		        	setName((String)result.get("name"));
		        }
	        					
			}
			*/
			
			// 자식 불러오기
		    find.clear();
		    find.append("parentId", getId());
		    
		    sort.clear();
		    sort.append("index", 0);
		    
		    cur = coll.find(find).sort(sort);

	        while(cur.hasNext()) {
	        	DBObject result = cur.next();
	        	
	        	int id = ((Integer)result.get("id")).intValue();
	        	String name = (String)result.get("name");
	        	
	        	WorkflowyNode node = new WorkflowyNode(id);
	        	node.setParentNode(this);
	        	node.setName(name);
	        	
	        	addChildNode(node);
	        	
	        	node.load();
	        }
	        
	        
	        
		}finally{
			if(cur != null)
				cur.close();
		}

		
	}
	
	@ServiceMethod(callByContent=true)
	public WorkflowyNode newNode() throws UnknownHostException, MongoException{		
		WorkflowyNode newNode = new WorkflowyNode();		

		newNode.setParentNode(this);
		addChildNode(newNode);
		
		return this;
	}	
	
	private int makeId() throws UnknownHostException, MongoException {

		Mongo m = new Mongo();
		DB db = m.getDB("testdb");
		//DB db = mongoDB.getDB();
		
		DBCollection coll = db.getCollection("seq");
		
	    BasicDBObject find = new BasicDBObject();
	    find.append("_id", "node_seq");
	    
	    if(!coll.find(find).hasNext()){
	    	BasicDBObject insert = new BasicDBObject();
	    	insert.put("_id", "node_seq");
	    	insert.put("seq", 1);
	    	
	    	coll.insert(insert);
	    }
	    
		BasicDBObject update = new BasicDBObject();
		BasicDBObject inc = new BasicDBObject();
		
		inc.put("seq", 1);
		update.put("$inc", inc);
		
		DBObject result = coll.findAndModify(find, update);
		
		Integer seq = (Integer)(result.get("seq"));
			
		return seq.intValue();
	}
}
