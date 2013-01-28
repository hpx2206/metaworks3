package org.uengine.codi.mw3.portraitserver;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uengine.kernel.GlobalContext;

/**
 * Servlet implementation class UserImagesServlet
 */
public class PortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortraitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String name = null;
		String imgFormat = "jpg";
		String mimeType = "image/jpg";
		
		String IMAGE_ROOT = GlobalContext.getPropertyString(
				"server.images.path",
				"./uengine/images/"
			);
		String realPath = IMAGE_ROOT + "/portrait";
		
		String pathInfo = request.getPathInfo();

		if(pathInfo != null){
			name = pathInfo.substring(1);  //because pathInfo is started with a "/" character

			if(pathInfo.endsWith(".png")){
				imgFormat = "png";
				mimeType = "image/png";
			}else if(pathInfo.endsWith(".gif")){
				imgFormat = "gif";
				mimeType = "image/gif";
			}
		}
		
		File f = new File(realPath  + File.separator + name);
		
		if(!f.exists()){
			System.out.println(name + " is not exist!!!!!");
			f = new File(realPath  + File.separator + "unknown_user.gif");
			imgFormat = "gif";
			mimeType = "image/gif";
		}
		
		response.setContentType(mimeType);
		
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, imgFormat, out);
		out.close();

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
