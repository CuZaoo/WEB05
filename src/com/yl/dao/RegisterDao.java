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
 * @date 2019年3月4日
 * @version  
 * @message 
 */
public class RegisterDao {
	//核心类
	private QueryRunner qr;
	public RegisterDao(){
		//实例化核心类
		qr = new QueryRunner(C3p0_pool.getDataSource());
	}
	public boolean dealMessage(String...message){
		//检查账号是否可用
		if(checkAccount(message[0])|insertAccount(message)){
			return true;
		}
		return false;
	}
	private boolean insertAccount(String... message){
		String sql = "insert into cz_user(uaccount,uname,upassword,umail) values (?,?,?,?)";
		try {
			int result = qr.update(sql, message);
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	private boolean checkAccount(String account){
		String sql = "select * from cz_user where uaccount = ?";
		String[] param={account};
		try {
			User user = qr.query(sql,param, new BeanHandler<User>(User.class));
			//存在返回存在
			if(user==null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
