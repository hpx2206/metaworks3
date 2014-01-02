package org.uengine.codi.mw3.ide;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.metaworks.metadata.MetadataBundle;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.mw3.CodiClassLoader;

public class MetadataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	
	public MetadataServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
//		System.out.println("pathInfo = " + pathInfo);
		
		//  TODO url을 통해서 projectId 를 안다고 가정
		String projectId = null;
		String tenantId = null;
		if(pathInfo.startsWith("/getMetadataFile")){
			// 요청받은 정보를 가지고, 메타데이터 파일을 찾아서 stream 으로 내려준다.
			projectId = request.getParameter("projectId");
			tenantId = request.getParameter("tenantId");
			String metadataFileName = request.getParameter("metadataFileName");
			String projectBasePath;
			if( tenantId != null){
				projectBasePath = MetadataBundle.getProjectBasePath(projectId , tenantId);
			}else{
				projectBasePath = MetadataBundle.getProjectBasePath(projectId);
			}
				
			String filePath = projectBasePath + File.separatorChar + metadataFileName;
			File file = new File(filePath);
			if( !file.exists() ){
				// 해당 프로젝트에 파일이 존재하지 않는 경우 최상위를 확인한다.
				filePath = MetadataBundle.getProjectBasePath(projectId) + File.separatorChar + metadataFileName;
				file = new File(filePath);
				if( !file.exists() ){
					return;
				}
			}
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
			
		}else{
			String metadataType = request.getParameter("type");
			if( metadataType != null ){
				// codebase/appId/root
				projectId = MetadataBundle.getProjectId();
				tenantId = TenantContext.getThreadLocalInstance().getTenantId();
				String projectBasePath;
				if( tenantId != null){
					projectBasePath = MetadataBundle.getProjectBasePath(projectId , tenantId);
				}else{
					projectBasePath = MetadataBundle.getProjectBasePath(projectId);
				}
				// codebase/appId/tenentId
				String tenentBasePath = CodiClassLoader.getMyClassLoader().getCodebase();
				if( "image".equalsIgnoreCase(metadataType)){
					OutputStream out = null;
					try{
						out = response.getOutputStream();
						// tenentBasePath 를 먼저 살펴 본 후에 없으면  root 를 살펴본다
						File imgFile = new File(tenentBasePath + pathInfo);
						if( !imgFile.exists() ){
							imgFile = new File(projectBasePath + pathInfo);
						}
						
						BufferedImage bi = ImageIO.read(imgFile);
						ServletContext context  = getServletConfig().getServletContext();
				        String mimetype = context.getMimeType(imgFile.getPath());
				        String fileName = imgFile.getName();
				        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
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
