/**
 * function:控制器：用户控制用户的登陆的验证
 */
package com.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.UserBeanResolve;

/**
 * Servlet implementation class loginResolveServlet
 */
@WebServlet("/loginResolveServlet")
public class LoginResolveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String pass=request.getParameter("passwd");
		
		UserBeanResolve ubr=new UserBeanResolve();
		boolean isLogin=ubr.loginCheck(user, pass);
		if(isLogin){
			//括弧里面的true表示 如果session存在，就返回该session，不存在就创建一个新的
			//如果填的是false，session存在就返回该session，不存在就返回一个null，不新建session
			HttpSession hs=request.getSession(true);
			//setsetAttribute()方法已经取代 putValue()方法了
			hs.setAttribute(user, "yes");
			
			//给第一次显示页面准备好信息
			Vector v=ubr.getUsersInfo(1);
			int pageCount=ubr.getPageCount();
			request.setAttribute("result", v);
			request.setAttribute("pageCount", pageCount);
			//初始显示第一页
			request.setAttribute("pageNow", 1);
			//response.sendRedirect("wel.jsp?user="+user); //效率太低
			request.getRequestDispatcher("wel.jsp?user="+user).forward(request, response);
		}else{
			//response.sendRedirect("login.jsp?2");
			request.getRequestDispatcher("login.jsp?2").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
