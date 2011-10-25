package org.metaworks;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import org.metaworks.inputter.Inputter;

public class FaceInput implements Inputter {

	public String faceName;
		public String getFaceName() {
			return faceName;
		}
		public void setFaceName(String faceName) {
			this.faceName = faceName;
		}
	
	
	////////////////////////// just place holder /////////////////////
	

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(Hashtable attrs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEligibleType(Class type) {
		// TODO Auto-generated method stub
		return false;
	}

}
