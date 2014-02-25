package org.uengine.codi.mw3.uploadimageserver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static int SIZE_LIMIT = 10 * 1024 * 1024 ; // 10 메가까지 제한 넘어서면 예외발생
	final static String UPLOAD_FOLDER_NAME = "uploadImages";
	final static String REQUEST_URL = "upload";
	final static String REQUEST_FILE_NAME = "filename";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String uploadPath = request.getRealPath("/") + UPLOAD_FOLDER_NAME;
		
		// 사진 폴더 생성
		File upload = new File(uploadPath);		
		if(!upload.exists() || !upload.isDirectory())
			upload.mkdirs();
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, SIZE_LIMIT, "UTF-8", new DefaultFileRenamePolicy());
		Enumeration formNames = multi.getFileNames();  // 폼의 이름 반환
	    String formName = (String)formNames.nextElement(); // 자료가 많을 경우엔 while 문을 사용
	    String fileName = multi.getFilesystemName(formName); // 파일의 이름 얻기
	    
	    if(fileName == null) {   // 파일이 업로드 되지 않았을때
	    	out.print("File Upload Fail");
	    
	    } else {  // 파일이 업로드 되었을때
	    	fileName = new String(fileName.getBytes());
	    	
	    	// 파일명 변경 (현재 시간)
	    	File originalFile = new File(uploadPath + "/" + fileName);
	    	
	    	// request 파일의 확장자를 기억하고 있어야 한다. (확장자로 이미지 파일 판단.)
	    	int extPosition = fileName.lastIndexOf(".");
	    	String fileExt = fileName.substring(extPosition + 1); 
	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
	    	Date currentTime = new Date();
	    	String time = simpleDateFormat.format(currentTime);
	    	File changedFile = new File(uploadPath + "/" + time + "." + fileExt);
	    	originalFile.renameTo(changedFile);
	    	
	    	// callback method에 사용할 인자 값 셋팅
	        String callBackId = request.getParameter("CKEditorFuncNum");
	        String responseURL = request.getRequestURL().toString().replaceAll(REQUEST_URL, "") + UPLOAD_FOLDER_NAME + "/" + changedFile.getName();
	        
	        // 파일 확장자가 jpeg, jpg, png, gif가 아니면 그냥 엑박을 띄워도 되니 업로드만 하고 미리보기 지원하지 않아도 된다. 파일만 업로드.
	        if(fileExt.equals("png") || fileExt.equals("jpeg") || fileExt.equals("jpg") || fileExt.equals("gif") ||
	        	fileExt.equals("PNG") || fileExt.equals("JPEG") || fileExt.equals("JPG") || fileExt.equals("GIF")) {
	        	
	        	out.println("<script type=\"text/javascript\">");  
	        	out.println("	window.parent.CKEDITOR.tools.callFunction('"+callBackId+"', '"+responseURL+"', 'Image Upload complete'); "); //CKEDITOR API  
	        	out.println("</script>");
		        
	        } else {
	        	out.println("<script type=\"text/javascript\">");  
	        	out.println("	window.parent.CKEDITOR.tools.callFunction('"+callBackId+"', 'This file is not Image File', 'Image Upload complete'); "); //CKEDITOR API  
	        	out.println("</script>");
	        }
        	
	    }
	}

}
