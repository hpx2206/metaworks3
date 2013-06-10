package org.uengine.codi.mw3.ide;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.metaworks.metadata.MetadataBundle;

public class MetadataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	
	public MetadataServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
//		System.out.println("pathInfo = " + pathInfo);
		
		//  TODO url을 통해서 projectId 와 tanentId 를 안다고 가정
		String projectId = null;
		String tanentId = null;
		projectId = "uu";
		tanentId = "uEngine";
		
		if(pathInfo.startsWith("/getMetadataFile")){
			// 요청받은 정보를 가지고, 메타데이터 파일을 찾아서 stream 으로 내려준다.
//			projectId = request.getParameter("projectId");
//			tanentId = request.getParameter("tanentId");
			projectId = "gddf";
			tanentId = "uEngine";
			String metadataFileName = request.getParameter("metadataFileName");
			String projectBasePath = MetadataBundle.getProjectBasePath(tanentId, projectId);
			String filePath = projectBasePath + File.separatorChar + metadataFileName;
			File file = new File(filePath);
	        int length   = 0;
	        ServletOutputStream outStream = response.getOutputStream();
	        ServletContext context  = getServletConfig().getServletContext();
	        String mimetype = context.getMimeType(filePath);
	        
	        // sets response content type
	        if (mimetype == null) {
	            mimetype = "application/octet-stream";
	        }
	        response.setContentType(mimetype);
	        response.setContentLength((int)file.length());
	        String fileName = file.getName();
	        
	        // sets HTTP header
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	        
	        byte[] byteBuffer = new byte[BUFSIZE];
	        DataInputStream in = new DataInputStream(new FileInputStream(file));
	        
	        // reads the file's bytes and writes them to the response stream
	        while ((in != null) && ((length = in.read(byteBuffer)) != -1)){
	            outStream.write(byteBuffer,0,length);
	        }
	        
	        in.close();
	        outStream.close();
	        response.flushBuffer();
//		}else if(pathInfo.startsWith("/getResourceFile")){
		}else{
			String metadataType = request.getParameter("type");
			if( metadataType != null ){
				String projectBasePath = MetadataBundle.getProjectBasePath(tanentId, projectId);
				if( "img".equalsIgnoreCase(metadataType)){
					OutputStream out = null;
					try{
						out = response.getOutputStream();
						File imgFile = new File(projectBasePath + pathInfo);
						BufferedImage bi = ImageIO.read(imgFile);
						ServletContext context  = getServletConfig().getServletContext();
				        String mimetype = context.getMimeType(imgFile.getPath());
				        String fileName = imgFile.getName();
				        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
//				        System.out.println("fileName = " + fileName);
//				        System.out.println("fileType = " + fileType);
				        // sets response content type
				        if (mimetype == null) {
				            mimetype = "image/jpg";
				        }
						response.setContentType(mimetype);
						ImageIO.write(bi, fileType, out);
					}finally{
						if(out != null){
							out.close();
							out = null;
						}
					}
				}
			}
		}
		// 요청받은 앱으로 리소스를 내려준다.
		
	}
}
