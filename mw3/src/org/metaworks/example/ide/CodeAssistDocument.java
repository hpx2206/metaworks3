package org.metaworks.example.ide;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.metaworks.website.MetaworksFile;

public class CodeAssistDocument {
	
	String html;
	
		public String getHtml() {
			return html;
		}
	
		public void setHtml(String html) {
			this.html = html;
		}
		
	public CodeAssistDocument(){}
		
		
	public CodeAssistDocument(CodeAssist codeAssist){
		if(codeAssist.getSelectedItem()==null){
			setHtml("...");
		}
		
		String expression;
		int pos = codeAssist.getLineAssistRequested().lastIndexOf(' ');
		if(pos != -1)
			expression = codeAssist.getLineAssistRequested().substring(codeAssist.getLineAssistRequested().lastIndexOf(' ') + 1);
		else
			expression = codeAssist.getLineAssistRequested();

		InputStream javaDocIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(expression.replace(".", "/") + codeAssist.getSelectedItem() + ".html");
		
		if(javaDocIS!=null){
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			try {
				MetaworksFile.copyStream(javaDocIS, bao);
				
				setHtml(bao.toString());
			} catch (Exception e) {
				setHtml("No Doc...");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					javaDocIS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					bao.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
