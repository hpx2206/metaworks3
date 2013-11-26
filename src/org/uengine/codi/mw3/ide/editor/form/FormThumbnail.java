package org.uengine.codi.mw3.ide.editor.form;

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
		// TODO input 이 안보임....  나중에 변경해야함
		File  file = new File(thumbnailPath);
		String codebase = GlobalContext.getPropertyString("codebase", "codebase/");
		File htmlTemplateFile = new File(codebase + File.separatorChar +"templet.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String title = "New Page";
		htmlString = htmlString.replace("$title", title);
		htmlString = htmlString.replace("$body", formHtml);
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		Writer out = null;
		try {
			fos = new FileOutputStream(thumbnailPath);
			osw = new OutputStreamWriter(fos , GlobalContext.ENCODING);
			out = new BufferedWriter(osw);
		    out.write(htmlString);
		} finally {
			if(fos != null){
				try {
					fos.close();
					fos = null;
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
			if(out != null){
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		File newHtmlFile = new File(thumbnailPath);
//		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		
		
		/*
		JLabel label = new JLabel(formHtml);
		label.setSize(imageWidth + 200 , imageHeight + 300);
		
		BufferedImage image = new BufferedImage(
	            label.getWidth(), label.getHeight(), 
	            BufferedImage.TYPE_INT_ARGB);
	    {
	        Graphics g = image.getGraphics();
	        g.setColor(Color.BLACK);
	        label.paint(g);
	        g.dispose();
	    }
	    
//		BufferedImage bi = ImageIO.read(stream_file);
//
//		// 원본 이미지의 비율에 맞게 썸네일 이미지 비율을 정하는 부분.
//		float cvtWidth = 0.0f;
//		float cvtHeight = 0.0f;
//
//
//		BufferedImage thumb = new BufferedImage
//				((int)cvtWidth, (int)cvtHeight, BufferedImage.TYPE_INT_RGB);
//		Graphics2D  g2 = thumb.createGraphics();
//
//		g2.drawImage(bi, 0, 0, (int)cvtWidth, (int)cvtHeight, null);

		ImageIO.write(image, "png", file); 
		
		*/
	}
}
