/**
 * 
 */
package com.yl.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.yl.domain.User;
import com.yl.utils.C3p0_pool;

/**
 * @author YL
 * @date 2019年3月5日
 * @version 1.0 
 * @message 登录界面数据库操作
 */
public class LoginDao {
	private QueryRunner qr;
	public LoginDao() {
		qr = new QueryRunner(C3p0_pool.getDataSource());
	}
	/**
	 * 处理数据，登录或者登录错误
	 * @param account
	 * @param password
	 */
	public boolean dealMessage(String...message) {
		if(checkAccountExist(message[0]) && checkPassword(message[0], message[1])){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查数据库中是否存在该账户
	 * @param account
	 * @return
	 */
	private boolean checkAccountExist(String account){
		//问题?这里使用*查找是否消耗过大?
		//异常记得处理
		String sql = "select * from cz_user where uaccount=? ";
		try {
			User result = qr.query(sql,new BeanHandler<User>(User.class),account);
			if(result!=null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 检查密码是否正确
	 * @param account
	 * @param password
	 * @return
	 */
	private boolean checkPassword(String account,String password) {
		// TODO Auto-generated method stub
		String sql = "select * from cz_user where uaccount=? and upassword=?";
		String[] param = {account,password};
		try {
			User user = qr.query(sql, new BeanHandler<User>(User.class),param);
			if(user!=null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
