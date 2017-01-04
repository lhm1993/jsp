/**
 * function:���������û������û��ĵ�½����֤
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
			//���������true��ʾ ���session���ڣ��ͷ��ظ�session�������ھʹ���һ���µ�
			//��������false��session���ھͷ��ظ�session�������ھͷ���һ��null�����½�session
			HttpSession hs=request.getSession(true);
			//setsetAttribute()�����Ѿ�ȡ�� putValue()������
			hs.setAttribute(user, "yes");
			
			//����һ����ʾҳ��׼������Ϣ
			Vector v=ubr.getUsersInfo(1);
			int pageCount=ubr.getPageCount();
			request.setAttribute("result", v);
			request.setAttribute("pageCount", pageCount);
			//��ʼ��ʾ��һҳ
			request.setAttribute("pageNow", 1);
			//response.sendRedirect("wel.jsp?user="+user); //Ч��̫��
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