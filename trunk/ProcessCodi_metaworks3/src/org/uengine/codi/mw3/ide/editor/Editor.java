package org.uengine.codi.mw3.ide.editor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.common.MetaworksUtil;
import org.uengine.codi.mw3.ide.CloudContent;
import org.uengine.codi.mw3.ide.JavaBuildPath;
import org.uengine.codi.mw3.ide.editor.java.JavaParser;

public class Editor implements CloudContent {
	
	public final static String TYPE_RESOURCE = "resource";
	public final static String TYPE_FILE = "file";
	
	@AutowiredFromClient
	public JavaBuildPath jbPath;

	String filename;
		@Id
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}

	String name;
		@Name
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		this(null);
	}
	
	public Editor(String filename){
		this(filename, TYPE_FILE);
	}
	
	public Editor(String filename, String type){
		if(filename == null)
			return;
		
		char separatorChar = File.separatorChar;

		this.setFilename(filename);
		this.setType(type);
		
		if(filename.lastIndexOf(separatorChar) > -1){
			this.setName(filename.substring(filename.lastIndexOf(separatorChar)+1));
		}		
	}
	
	@ServiceMethod(callByContent=true, except="content", target=ServiceMethodContext.TARGET_NONE)
	public String load() {
		InputStream is = null;
		ByteArrayOutputStream bao = null;
		
		try {
			bao = new ByteArrayOutputStream();
			
			//if(TYPE_FILE.equals(this.getType())){
				File file = new File(jbPath.getBasePath() + this.getFilename());
				if(file.exists()){
					
					try {
						is = new FileInputStream(file);
					} catch (Exception e) {
						e.printStackTrace();
					}			
				}
			/*
			}else{
				
				CloudClassLoader ccl = new CloudClassLoader(jbPath.getLibraryPath(), jbPath.getDefaultBuildOutputPath());
				ccl.load();
				System.out.println("change : " + ccl.getSourceResourceName(this.getFilename()));
				
				try {
					is = ccl.getCl().getResourceAsStream(ccl.getSourceResourceName(this.getFilename()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			*/
				
			MetaworksUtil.copyStream(is, bao);
			
			this.setContent(bao.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			if(bao != null){
				try {
					bao.close();
					bao = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return this.getContent();
	}
	
	public Object save(){
		FileWriter writer = null;

		try {
			File file = new File(jbPath.getBasePath() + this.getFilename());
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			writer = new FileWriter(file);
			writer.write(this.getContent()!=null?this.getContent():"");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return null;
	}
	
	@ServiceMethod(payload={"content"}, target=ServiceMethodContext.TARGET_NONE)
	public Object parsing() {
		JavaParser javaParser = new JavaParser();
		
		javaParser.setContent(this.getContent());
		javaParser.create();
		
		return javaParser;
	}
}
