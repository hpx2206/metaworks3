package org.metaworks.example.socoban;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;

public class SocobanPanel {
	
	int id = 0;
	
	ArrayList<ArrayList<Block>> blocks;
	
		public ArrayList<ArrayList<Block>> getBlocks() {
			return blocks;
		}
	
		public void setBlocks(ArrayList<ArrayList<Block>> blocks) {
			this.blocks = blocks;
		}
	
	@ServiceMethod
	public void start(){
		
		String stage [][]={
				{" ","*","O"," "},
				{" ","*"," "," "},
				{" "," "," ","O"},
				{" ","*"," "," "},
				{" ","*"," "," "},
		};
		
		blocks = new ArrayList<ArrayList<Block>>();
		
		for(int row=0; row < stage.length; row++){
			
			ArrayList<Block> blockRow = new ArrayList<Block>();
			String[] blockStrRow = stage[row];
			
			for(int col=0; col<blockStrRow.length; col++){
				
				String blockStr = blockStrRow[col];
				
				Block block = null;
				
				if(" ".equals(blockStr)){
					block = (new EmptyBlock());
				}else if("*".equals(blockStr)){
					block = (new BlockingBlock());
				}
				else if("O".equals(blockStr)){
					block = (new MovableBlock());
				}
				
				block.setId(SocobanPanel.this.id++);
				block.setRow(row);
				block.setColumn(col);

				
				blockRow.add(block);
				
			}
			
			blocks.add(blockRow);
		}
		
		
	}

}
