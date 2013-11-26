package org.uengine.codi.mw3.ide.editor.form;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.apache.commons.io.FileUtils;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.GlobalContext;

public class FormThumbnail {
	
	Object formObject;
		public Object getFormObject() {
			return formObject;
		}
		public void setFormObject(Object formObject) {
			this.formObject = formObject;
		}
		
	String thumbnailPath;
		public String getThumbnailPath() {
			return thumbnailPath;
		}
		public void setThumbnailPath(String thumbnailPath) {
			this.thumbnailPath = thumbnailPath;
		}

	String formHtml;
		public String getFormHtml() {
			return formHtml;
		}
		public void setFormHtml(String formHtml) {
			this.formHtml = formHtml;
		}
	int imageWidth;
		public int getImageWidth() {
			return imageWidth;
		}
		public void setImageWidth(int imageWidth) {
			this.imageWidth = imageWidth;
		}
	int imageHeight;
		public int getImageHeight() {
			return imageHeight;
		}
		public void setImageHeight(int imageHeight) {
			this.imageHeight = imageHeight;
		}
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_NONE)
	public void formPreView() throws Exception {
		
		/// 1. html 로 변환
		String htmlString = htmlTemplet();
		String title = "New Page";
		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$body", formHtml);
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		Writer out = null;
		try {
			fos = new FileOutputStream(thumbnailPath + ".html");
			osw = new OutputStreamWriter(fos , GlobalContext.ENCODING);
			out = new BufferedWriter(osw);
		    out.write(htmlString);
		} finally {
			if(out != null){
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(osw != null){
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// 2. jpg 파일로 변환
		
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(formHtml);
		imageGenerator.saveAsImage(thumbnailPath + ".png");
//		imageGenerator.saveAsImage(thumbnailPath + ".jpg");
		
//		File file = new File(thumbnailPath + ".jpg");
//		JLabel label = new JLabel(formHtml);
//		label.setSize(imageWidth + 200 , imageHeight + 300);
//		
//		BufferedImage image = new BufferedImage(
//	            label.getWidth(), label.getHeight(), 
//	            BufferedImage.TYPE_INT_ARGB);
//	    {
//	        Graphics g = image.getGraphics();
//	        g.setColor(Color.BLACK);
//	        label.paint(g);
//	        g.dispose();
//	    }
//
//		ImageIO.write(image, "jpg", file); 
		
	}
	
	public String htmlTemplet(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("<!DOCTYPE html PUBLIC");
		sb.append("-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:fb=\"http://ogp.me/ns/fb#\" xml:lang=\"en\" lang=\"en\">");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		sb.append("<title>$title</title>");
		sb.append("</head>");
		sb.append("<body>$body");
		sb.append("</body>");
		sb.append("</html>");

		
		return sb.toString();
	}
	
}
