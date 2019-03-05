package com.yl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.dao.LoginDao;
/**
 * 配合数据库检测用户登录是否合法
 * */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	与登录界面数据库操作对象建立连接
	private LoginDao loginDao;
	public LoginServlet() {
		loginDao = new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("有接入!");
		//获取表单信息
		String account = request.getParameter("uaccount");
		String password = request.getParameter("upassword");
		//处理表单消息
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
		    "<head><title>用户界面</title></head>\n" +
		    "<body bgcolor=\"#f0f0f0\"  style=\"align-content: center;\" >\n" +
		    "<h1 align=\"center\">登录成功!</h1>\n" +
		    "尊敬的"+message[0]+"恭喜您登录成功!"+
		    "</body></html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
