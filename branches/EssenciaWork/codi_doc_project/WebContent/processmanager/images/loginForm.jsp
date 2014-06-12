<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,javax.sql.*,javax.naming.*"  %>
<%@ page import="org.uengine.util.dao.DefaultConnectionFactory"%>
<%
	try {
		session.invalidate();
	} catch (Exception e) {
	}
%>
<html>
<head>
    <title>Login Form</title>
    <style type="text/css">
    p { font-family: georgia, sans-serif; font-size: 11px; }
    </style>

	<link href="style/uengine.css" type="text/css" rel="stylesheet">

	<script type="text/javascript">
	function formValidate(){
		return true;
	}
	</script>


    <style>
html, body {
	height: 100%;
	margin:0;
	padding:0;
}

* {
	margin:0;
	padding:0;
	font-family:"돋움", Dotum, "굴림", Gulim, AppleGothic, Sans-serif;
	font-size:11px;
	color:#333333;
}
</style>
</head>

<body >

<div style="height:518px; width:896px; background:url(images/login/bground.gif) no-repeat; top:50%; left:50%; margin-left:-448px; margin-top:-309px; position:absolute; border:0px">
	<div style="margin:90px 0 0 194px;">
    	<div style="float:left;"><img src="images/login/logo.gif" border="0"></div>
        <div style="float:left; margin-top:28px;"> <span style="font-size:11px; color:#999; ">Ver 3.501</span></div>    
    </div>
    <div style=" clear:both; width:369px; margin:-38px 0 0 250px;">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="15%" height="30"><img src="images/login/i_id.gif" width="41" height="13"></td>
                <td width="69%" rowspan="2"><form action="login.jsp" method="post" style=" margin-left:20px;">
                        <input type="hidden" name="return_to" value="" />
                        <font color="gray">
                        <select name="userId" style="width:190px">
                        <%
                    
                            Connection conn = null;
                            Statement stmt = null;
                            ResultSet rs = null;
                            
                            try {
                                conn = DefaultConnectionFactory.create().getConnection();
                                stmt = conn.createStatement();
                                
                                StringBuffer sql = new StringBuffer();
                                sql.append(" select EMPNAME, EMPCODE  ");
                                sql.append(" from   EMPTABLE ");
                                sql.append(" where ISDELETED = '0' ");
                                sql.append(" group by EMPNAME,EMPCODE ");
                    
                                rs = stmt.executeQuery(sql.toString());
                    
                                while (rs.next()) {
                                    out.println("	<option value='" + rs.getString("EMPCODE") + "'>" + rs.getString("EMPNAME"));
                                }
                            } finally {
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (Exception e) { }
                                }
                                if (stmt != null) {
                                    try {
                                        stmt.close();
                                    } catch (Exception e) { }
                                }
                                if (conn != null) {
                                    try {
                                        conn.close();
                                    } catch (Exception e) { }
                                }
                            }
                        %>
                        </select>
                        <br />
                        <input type="password" name="passWd" style="width:190px; margin-top:8px;"/>
                        <br />
                    
                        </p>
                        <!--<input name="input" type="text"  style="width:95%"/>
                            <br />
                            <input name="input2" type="password"  style="width:95%; margin-top:10px;"/>-->
                    
                    
                    
                    <input type="hidden" name="login" value="Login">
                   </td>
                <td width="16%" rowspan="2"><a href="login.jsp" onClick="return formValidate();" ><input type="image" src="images/login/b_login.gif"></a></td> </form>
            </tr>
            <tr>
                <td height="30"><img src="images/login/i_pw.gif" width="41" height="13"></td>
            </tr>
        </table>

    </div>
    <div style=" background:url(images/login/i_blue01.gif) no-repeat 2px;; padding:0 0 0 10px;  margin:28px 0 0 250px; width:380px; color:#808080 ">Administrator User/Password is <span style="color:#09C;">test / test</span></div>
    <div style=" background:url(images/login/i_blue01.gif) no-repeat 2px;; padding:0 0 0 10px;  margin:7px 0 0 250px; width:380px; color:#808080 ">보안을 위하여 로그인 하신 후 패스워드를 변경하여 주시기 바랍니다.
    </div>
</div>



    
</body>
</html>