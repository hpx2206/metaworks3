package org.uengine.codi.mw3.ide.editor.form;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.FileWorkItem;
import org.uengine.codi.mw3.webProcessDesigner.ImageMagick;
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
		// 1) ImageMagick 사용
		// TODO 한글 문제로 인하여 보류
//		ImageMagick imageMagick = new ImageMagick();
//		imageMagick.convertFile(thumbnailPath + ".html", thumbnailPath + ".png");
		
		// 2) 오픈 오피스 사용
//		FileWorkItem fileWorkItem = new FileWorkItem();
//		fileWorkItem.convertPdf();
		
		// 3) HTMLEditorKit 사용
//		BufferedImage ire;
//		  // 서버의 웹서버로 접근
//		  String url="https://www.google.co.kr/";
//		  //String url="file:///D:\\print.html"; 서버의 파일을 부를 때
//		  // 저장될 이미지 위치와 이름이 명
//		  String path	=	thumbnailPath + ".jpg";
//		  // 이미지 크기
//		  ire = create(url, 800, 800);
//		  try{
//		   ImageIO.write(ire, "jpg", new File(path));
//		  }catch(IOException e){
//		   e.printStackTrace();
//		  }catch(NullPointerException e){
//		   e.printStackTrace();
//		  }catch(IllegalArgumentException e){
//		   e.printStackTrace();
//		  }

		// 4) html2Image 사용
		// input 박스가 안보임
//		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
//		imageGenerator.loadHtml(formHtml);
//		imageGenerator.saveAsImage(thumbnailPath + ".png");
//		imageGenerator.saveAsHtmlWithMap(thumbnailPath + ".html", thumbnailPath + ".png"); 
//		imageGenerator.saveAsImage(thumbnailPath + ".jpg");
//		File file = new File(thumbnailPath + ".jpg");
		
		// 5) JLabel 사용
		// JLabel 로 변환하면서 input 태그가 사라짐
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
	
//	@SuppressWarnings("serial")
//	 static class Kit extends HTMLEditorKit {
//	  public Document createDefaultDocument() {
//	   HTMLDocument doc = (HTMLDocument) super.createDefaultDocument();
//	   doc.setTokenThreshold(Integer.MAX_VALUE);
//	   doc.setAsynchronousLoadPriority(-1);
//	   return doc;
//	  }
//	 }
//
//
//	public static BufferedImage create(String src, int width, int height) {
//		  BufferedImage image = null;
//		  JEditorPane pane = new JEditorPane();
//		  Kit kit = new Kit();
//		  pane.setEditorKit(kit);
//		  pane.setEditable(false);
//		  pane.setMargin(new Insets(0,20,0,20));
//		  pane.setContentType("text/html; charset=UTF-8");
//		  //pane.setFont(new Font("serif",Font.BOLD,20));
//  
//		  try {
//		   pane.setPage(new URL(src));
//		   // HTML 내용을 콘솔창 출력
//		   System.out.println(pane.getText());
//		   image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		   Graphics g = image.createGraphics();
//		   Container c = new Container();
//		   SwingUtilities.paintComponent(g, pane, c, 0, 0, width, height);
//		   g.dispose();
//		  } catch (Exception e) {
//		   System.out.println(e);
//  		}
//  		return image;
//	}
	
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
