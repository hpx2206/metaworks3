package org.metaworks.example.socoban;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class MovableBlock extends Block{


		
	@ServiceMethod(keyBinding="left", callByContent=true)
	public SocobanPanel moveLeft(){
		
		if(column == 0) return socobanPanel;
		Block placeToLocate = socobanPanel.blocks.get(row).get(column - 1);
		
		if(placeToLocate.isEmpty()){
			socobanPanel.blocks.get(row).set(column, placeToLocate);
			column--;
			socobanPanel.blocks.get(row).set(column, this);
		}
		
		return socobanPanel;
	}

	@ServiceMethod(keyBinding="right")
	public void moveRight(){
		
	}

	@ServiceMethod(keyBinding="up")
	public void moveUp(){
		
	}

	@ServiceMethod(keyBinding="down")
	public void moveDown(){
		
	}
	
	@AutowiredFromClient
	public SocobanPanel socobanPanel;

}
