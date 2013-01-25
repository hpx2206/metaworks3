package org.metaworks.example.socoban;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class Block {

	
	int id;
	@Id
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

	boolean isEmpty;
		
		public boolean isEmpty() {
			return isEmpty;
		}
		public void setEmpty(boolean isEmpty) {
			this.isEmpty = isEmpty;
		}

	int row;
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}

	int column;
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}
		

}
