package org.uengine.codi.mw3.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.jdk.SimpleCompiler;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CompileError;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.kernel.FormActivity;
import org.uengine.util.UEngineUtil;

public class MemoWorkItem extends WorkItem{
	
	public MemoWorkItem(){
		setType("memo");		
		setMemo(new WebEditor());
	}

	@Hidden(on=false)
	public WebEditor getMemo() {
		// TODO Auto-generated method stub
		return super.getMemo();
	}

	@Override
	public Object[] add() throws Exception {
		
		if(getMemo()!=null && getMemo().getContents()!=null){
			
			//if there is no title has been entered, use the first line of content:
			if(title==null || title.trim().length()==0) 
				try{
					//TODO: since the contents contains some multiple lines of html tags, so it cannot parses real next line of the text.
					title = getMemo().getContents().substring(0, getMemo().getContents().indexOf('\n'));
				}
			catch(Exception e){}
			
			if(getMemo().getContents().length() > 2990){
				
				String relativeFilePath = UEngineUtil.getCalendarDir() + "/memo" + getInstId() + "_" + System.currentTimeMillis() + ".html";
				String absoluteFilePath = FormActivity.FILE_SYSTEM_DIR + relativeFilePath;
				
				File contentFile = new File(absoluteFilePath);
				contentFile.getParentFile().mkdirs();
				
				PrintWriter fos = new PrintWriter(contentFile);
				fos.write(getMemo().getContents());
				fos.close();
				
				setExtFile(relativeFilePath);

				getMemo().setContents("...loading...");
					
			}

		}
		
		return super.add();
	}

	@Override
	public void loadContents() throws Exception {
		if(getExtFile()!=null){
			
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

			String absoluteFilePath = FormActivity.FILE_SYSTEM_DIR + getExtFile();

			UEngineUtil.copyStream(new FileInputStream(absoluteFilePath), bao);
			
			getMemo().setContents(bao.toString());
			setContentLoaded(true);

		}
	}

	
	

	
}
