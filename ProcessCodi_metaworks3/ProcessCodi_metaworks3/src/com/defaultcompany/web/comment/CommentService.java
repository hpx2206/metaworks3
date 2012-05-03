package com.defaultcompany.web.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.uengine.kernel.GlobalContext;
import org.uengine.util.dao.DefaultConnectionFactory;

public class CommentService extends HttpServlet {
    private static final long serialVersionUID = GlobalContext.SERIALIZATION_UID;
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(GlobalContext.ENCODING);
        HttpSession session = request.getSession();
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html;charset=utf-8");
        
        String loggedUserGlobalCom 	= (String)session.getAttribute("loggedUserGlobalCom");
        String loggedUserId 		= (String)session.getAttribute("loggedUserId");
        String bpm_comment 			= request.getParameter("bpm_comment");
        String instid				= request.getParameter("instid");
        String idx					= request.getParameter("idx");
        String type					= request.getParameter("type");
        
        PrintWriter writer 	= response.getWriter();
        CommentDAO  dao 	= new CommentDAO(DefaultConnectionFactory.create());
        
        if("insert".equals(type)){
        	writer.print((dao.insertCommentList(instid, loggedUserId, bpm_comment, loggedUserGlobalCom)).toString());
        }else if("delete".equals(type)){
        	writer.print((dao.deleteCommentList(idx, instid, loggedUserId, loggedUserGlobalCom)).toString());
        }else{
        	writer.print((dao.selectCommentList(instid, loggedUserId, loggedUserGlobalCom, new StringBuffer())).toString());
        }
        
        writer.flush();
        writer.close();
    }
}
