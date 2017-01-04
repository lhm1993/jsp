<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>LoginPage</title>
<script language="javascript">
<!--
	//将输入框置空，重新输入
	function resetInput(){
		
		formLogin.user.value="";
		formLogin.passwd.value="";
		
	}
	
	//检测是否有空输入
	function check(){
	
		if(formLogin.user.value==""||formLogin.passwd.value==""){
			window.alert("用户名或密码不能为空！");
			return false;
		}
	}
//-->
</script>
</head>
<body>
<h1 style="font-family:宋体; font-size:40px;">用户登录</h1>
<hr></hr><br>
	<form id="formLogin" action="loginResolveServlet" method="post" style="font-family:宋体;">
		用户名：<input type="text" name="user"/><br><br>
		密&nbsp&nbsp码：<input type="password" name="passwd"/><br><br>
		<input type="submit" value="登录" size="100" onclick="return check();"/>
		&nbsp&nbsp&nbsp&nbsp
		<input type="button" value="重置" size="100" onclick="resetInput();"/>
	</form>
</body>
</html>