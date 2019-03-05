package com.yl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.dao.RegisterDao;

/**
 * 与数据库配合检查用户注册是否合法
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
		//处理中文
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
			    "<head><title>注册失败</title></head>\n" +
			    "<body bgcolor=\"#f0f0f0\">\n" +
			    "<h1 align=\"center\">账号"+message[0]+"已存在,请重新注册!</h1>\n" +
			    "</body></html>");
	}
	private void registerSuccess(PrintWriter out,String... message){
		String docType = "<!DOCTYPE html> \n";
		String url = "localhost:8080";
		String loginMessage="/WEB05/login";
		out.println(docType +
		    "<html>\n" +
		    "<head><title>注册成功</title></head>\n" +
		    "<body bgcolor=\"#f0f0f0\"  style=\"align-content: center;\" >\n" +
		    "<h1 align=\"center\">注册成功!</h1>\n" +
		    "尊敬的"+message[0]+"恭喜您注册成功!<br/>"+
		    "请点击下方链接前往登陆页面"+
			"<a href=\"cz/login.html\">登录</a>\""+
		    "</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
