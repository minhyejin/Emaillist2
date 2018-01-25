package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.EmaillistDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.EmaillistVo;


@WebServlet("/el")
public class EmaillistServelet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("servelet 진입");
		
		request.setCharacterEncoding("UTF-8");
		
		
		String actionName = request.getParameter("a");
		if ("form".equals(actionName)) {
			
			System.out.println("form 진입");
			WebUtil.forward(request, response,"/WEB-INF/form.jsp");
			
			/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/form.jsp");
			rd.forward(request, response);//포워드는 이 두줄 
*/			
			
		}else if ("insert".equals(actionName)) {
			System.out.println("insert 진입");
			
			String lastName = request.getParameter("ln");
			String firstName = request.getParameter("fn");
			String email = request.getParameter("email");
			
			
			EmaillistVo vo = new EmaillistVo();
			vo.setLastName(lastName);
			vo.setFirstName(firstName);
			vo.setEmail(email);
			
			System.out.println(vo.toString());
			
			EmaillistDao dao = new EmaillistDao();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/emaillist2/el?a=list");
			
			/*response.sendRedirect("/emaillist2/el?a=list");*/
			
			
		}
		else if("list".equals(actionName)) {
			System.out.println("list 진입");
			EmaillistDao dao = new EmaillistDao();
			
			List<EmaillistVo> list = dao.getList();
			
			request.setAttribute("list", list );//부를 이름, 실제 데이터
			
			WebUtil.forward(request, response, "/WEB-INF/list.jsp");
			/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response );*/
			
		}else {
			System.out.println("잘못된 a값 처리");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
