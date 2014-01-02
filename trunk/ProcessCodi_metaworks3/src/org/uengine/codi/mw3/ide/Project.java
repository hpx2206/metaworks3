package org.uengine.codi.mw3.ide;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.annotation.Id;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.kernel.GlobalContext;

public class Project {

	public final static String METADATA_FILENAME = "uengine.metadata";
	
	String id;
		@Id
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

	String path;
		public String getPath() {
			return path;
		}
	
		public void setPath(String path) {
			this.path = path;
		}
		
	public void load(){
		
	}
}
