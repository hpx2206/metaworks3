package org.uengine.codi.mw3.ide.editor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.common.MetaworksUtil;
import org.uengine.codi.mw3.ide.editor.java.JavaParser;

public class Editor {

	String filename;
		@Id
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}

	String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	boolean loaded;
		public boolean isLoaded() {
			return loaded;
		}
		public void setLoaded(boolean loaded) {
			this.loaded = loaded;
		}
		
	public Editor(){
		
	}
	
	public Editor(String filename){
		this.setFilename(filename);
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE)
	public String load() {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		
		File file = new File(this.getFilename());
		if(file.exists()){
			bao = new ByteArrayOutputStream();
			try {
				is = new FileInputStream(file);
				MetaworksUtil.copyStream(is, bao);
				
				this.setContent(bao.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return this.getContent();
	}
	
	@ServiceMethod(payload={"content"}, target=ServiceMethodContext.TARGET_NONE)
	public Object parsing() {
		JavaParser javaParser = new JavaParser();
		
		javaParser.setContent(this.getContent());
		javaParser.create();
		
		return javaParser;
	}
}
