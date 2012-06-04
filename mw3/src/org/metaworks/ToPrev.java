package org.metaworks;

public class ToPrev {
	
	Object target;

		public Object getTarget() {
			return target;
		}
	
		public void setTarget(Object target) {
			this.target = target;
		}
		
	Object next;
		public Object getNext() {
			return next;
		}	
		public void setNext(Object next) {
			this.next = next;
		}

	public ToPrev(Object next, Object target){
		setNext(next);
		setTarget(target); //TODO: need remove detail properties except the key value or clone a new key object containing the only key parts for network optimization
	}
}
