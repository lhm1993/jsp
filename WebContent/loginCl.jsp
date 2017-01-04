<!--
function: 处理login页面传递过来的信息已废弃 
 -->

<%@ page language="java" import="com.mysql.jdbc.Driver,java.sql.*,com.model.*;" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 接收上个页面传进来的信息的java片段 -->
<%
	String user=request.getParameter("user");
	String pass=request.getParameter("passwd");
	
	UserBeanResolve ubr=new UserBeanResolve();
	boolean isLogin=ubr.loginCheck(user, pass);
	if(isLogin){
		HttpSession hs=request.getSession(true);
		hs.putValue(user, "yes");
		response.sendRedirect("wel.jsp?user="+user);
	}else{
		response.sendRedirect("login.jsp?2");
	}
	
	

%>
</head>
<body>
</body>
</html>