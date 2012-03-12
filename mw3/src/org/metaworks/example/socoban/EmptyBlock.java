package org.metaworks.example.socoban;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class EmptyBlock extends Block{

	public void EmptyBlock(){
		setEmpty(true);
	}

}
