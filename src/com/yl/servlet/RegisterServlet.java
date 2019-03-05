package com.yl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.dao.RegisterDao;

/**
 * �����ݿ���ϼ���û�ע���Ƿ�Ϸ�
 * */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegisterDao rd;
	public RegisterServlet() {
		rd = new RegisterDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String uaccount = request.getParameter("account");
		String uname = request.getParameter("name");
		String upassword = request.getParameter("password");
		String umail = request.getParameter("mail");
		System.out.println(uaccount);
		boolean dealMessage = rd.dealMessage(uaccount,uname,upassword,umail);
		PrintWriter out = response.getWriter();
		//��������
		String name = new String(uname.getBytes("ISO8859-1"),"UTF-8");
		if(dealMessage){
			registerSuccess(out,name,uaccount,upassword);
		}else{
			registerFail(out,uaccount);
		}
	}
	private void registerFail(PrintWriter out,String... message){
		String docType = "<!DOCTYPE html> \n";
		out.println(docType +
			    "<html>\n" +
			    "<head><title>ע��ʧ��</title></head>\n" +
			    "<body bgcolor=\"#f0f0f0\">\n" +
			    "<h1 align=\"center\">�˺�"+message[0]+"�Ѵ���,������ע��!</h1>\n" +
			    "</body></html>");
	}
	private void registerSuccess(PrintWriter out,String... message){
		String docType = "<!DOCTYPE html> \n";
		String url = "localhost:8080";
		String loginMessage="/WEB05/login";
		out.println(docType +
		    "<html>\n" +
		    "<head><title>ע��ɹ�</title></head>\n" +
		    "<body bgcolor=\"#f0f0f0\"  style=\"align-content: center;\" >\n" +
		    "<h1 align=\"center\">ע��ɹ�!</h1>\n" +
		    "�𾴵�"+message[0]+"��ϲ��ע��ɹ�!<br/>"+
		    "�����·�����ǰ����½ҳ��"+
			"<a href=\"cz/login.html\">��¼</a>\""+
		    "</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
