
<!-- 
分页技术练习
实现了简单分页
 防止用户直接登录wel.jsp 要使用session技术
 -->
<!-- 引包  -->
<%@page import="com.model.UserBeanResolve"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" import="com.mysql.jdbc.Driver,java.sql.*,java.util.*,com.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	String user =request.getParameter("user");
	HttpSession hs=request.getSession(true);
	String s1=(String)hs.getAttribute(user);
	//System.out.println("wel --- session s1  "+user);
	if(user==null||s1==null){
		response.sendRedirect("login.jsp?0");
	}
	//out.println(s1);
%>
<title><%=user%>  登录中...</title>
<style>
	
	#wel{
		width:100%;
		height:30px;
		padding-top:10px;
		padding-bottom:10px;
		text-align:right;
	}
	
	table{
		border:1px solid gray;
	}
	td,th{
		border:1px solid gray;
	}
	.table{
		wdith:100%;
		height:300px;
		text-align:right;
		position:absolute;
		margin-left:200px;
	}
	.page{
		width:200px;
		height:50px;
		margin-left:220px;
		position:absolute;
		margin-top:120px;
	}
	
</style>

</head>

<body>
	<div id="wel"><%=user%>  欢迎你！</div>
	<hr></hr>
	<%
	
		//从控制器中获取当前要显示的信息
		//这里需要对泛型指定，否则 v 无法使用 UserBean中定义的方法
		Vector<UserBean> v=(Vector)request.getAttribute("result");
		int pageCount=(Integer)request.getAttribute("pageCount");
		int pageNow=(Integer)request.getAttribute("pageNow");
	%>
	
	<div class="table">
		<table  >
		<tr><th>用户编号</th><th>昵称</th><th>邮箱</th></tr>
		<%
			int n=v.size();
			int i=0;
			while(i<n){
				%>
					<tr><td><%=v.get(i).getUserId()%></td><td><%=v.get(i).getNickname()%></td><td><%=v.get(i).getEmail()%></td></tr>
				<%
				i++;
			}
		%>
		</table><br>
	</div>
	
	<!-- 显示页码 -->
	<div class="page">
		<%
			//out.println(pageCount+"  "+infoCount+" "+pageSize);
			if(pageCount!=0&&pageNow!=1){
				//这里传递用户这个变量的原因是咋session中是需要使用用户名来判断当前用户的是否在线，如果不传递 ，每次都会跳会到初始页面
				out.print("  <a href='UserBeanResolveConroller?pageNow="+(pageNow-1)+"&user="+user+"'>上一页</a>  ");
			}
			int j=0;
			for(;pageNow+j<=pageCount&&j<4;j++){
				out.print("  <a href='UserBeanResolveConroller?pageNow="+(pageNow+j)+"&user="+user+"'>"+(pageNow+j)+"</a>  ");
			}
			if(pageCount!=0&&pageNow!=pageCount&&pageNow<pageCount){
				out.print("  <a href='UserBeanResolveConroller?pageNow="+(pageNow+1)+"&user="+user+"'>下一页</a>  ");
			}
		%>
	</div>
	
</body>
</html>