package org.uengine.codi.mw3.model;

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
			if(getMemo().getContents().length() > 2990){
				
				getMemo().setContents(getMemo().getContents().substring(0, 2990) + "...");
				
				
			}

		}
		
		return super.add();
	}
	

	
}
