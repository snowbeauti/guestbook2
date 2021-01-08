package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@WebServlet("/gc")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("controller");
		
		//파라미터 action값을 읽어
		String action = request.getParameter("action");
		System.out.println(action);
		
		//action List 이면 리스트 출력관련
		
		if("addList".equals(action)) {
		System.out.println("리스트 처리");
		GuestDao guestDao = new GuestDao();
		List<GuestVo> gList = guestDao.GList();
		
		//데이터 전달
		request.setAttribute("GList", gList);
		
		//jsp 포워드
		RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/AddList.jsp");
		rd.forward(request, response);
		
		} else if("insert".equals(action)) {
			System.out.println("방명록 저장");
			
			//파라미터 값
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//GuestVo 묶기
			GuestVo guestVo = new GuestVo(name, password, content);
			
			//dao 
			GuestDao guestDao = new GuestDao();
			guestDao.insert(guestVo);
			
			//리스트로 돌아오기
			response.sendRedirect("./gc?action=addList");
			
		} else if("dform".equals(action)) {
			System.out.println("삭제폼");
			
			//jsp 포워드
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/DeleteForm.jsp");
			rd.forward(request, response);
			
		} else if("delete".equals(action)) {
			System.out.println("삭제");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestVo guestVo = new GuestVo(no, password);
			
			
			GuestDao guestDao = new GuestDao();
			GuestVo pw = guestDao.getGuest(no);

			if(password.equals(pw.getPassword())) {
			guestDao.delete(guestVo);
			response.sendRedirect("/guestbook2/gc?action=addList");
			} else {
				System.out.println("삭제실패폼");
				request.setAttribute("no", no);
				
				//jsp 포워드
				RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/DeleteFormFail.jsp");
				rd.forward(request, response);	
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
	}

}
