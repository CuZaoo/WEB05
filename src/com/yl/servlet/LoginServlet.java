package com.yl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.dao.LoginDao;
/**
 * ������ݿ����û���¼�Ƿ�Ϸ�
 * */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	���¼�������ݿ��������������
	private LoginDao loginDao;
	public LoginServlet() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		��Ӧ��������
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("�н���!");
		//��ȡ����Ϣ
		String account = request.getParameter("uaccount");
		String password = request.getParameter("upassword");
		//�������Ϣ
		boolean result = loginDao.dealMessage(account,password);
		PrintWriter out = response.getWriter();
		if(result){
			loginSuccess(out, account);
		}
	}
	private void loginSuccess(PrintWriter out,String... message){
		String docType = "<!DOCTYPE html> \n";
		out.println(docType +
		    "<html>\n" +
		    "<head><title>�û�����</title></head>\n" +
		    "<body bgcolor=\"#f0f0f0\"  style=\"align-content: center;\" >\n" +
		    "<h1 align=\"center\">��¼�ɹ�!</h1>\n" +
		    "�𾴵�"+message[0]+"��ϲ����¼�ɹ�!"+
		    "</body></html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
