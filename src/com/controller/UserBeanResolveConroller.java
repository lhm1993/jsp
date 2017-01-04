//控制wel.jsp页面的分页显示(在web.xml中配置servlet)
package com.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.UserBean;
import com.model.UserBeanResolve;

/**
 * Servlet implementation class UserBeanResolveConroller
 */
@WebServlet("/UserBeanResolveConroller")
public class UserBeanResolveConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_pageNow=request.getParameter("pageNow");
		int pageNow=Integer.parseInt(s_pageNow);
		UserBeanResolve ubr=new UserBeanResolve();
		Vector<UserBean> v=(Vector)ubr.getUsersInfo(pageNow);
		int pageCount=ubr.getPageCount();
		request.setAttribute("result", v);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		
		request.getRequestDispatcher("wel.jsp?pageNow="+pageNow).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
