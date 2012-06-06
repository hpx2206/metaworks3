package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class MarketAnalysis implements ITool , Serializable {

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
	
	private String marketAnalysis;
	
	public String getMarketAnalysis() {
		return marketAnalysis;
	}
	public void setMarketAnalysis(String marketAnalysis) {
		this.marketAnalysis = marketAnalysis;
	}
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub
		
	}
	
	
}
