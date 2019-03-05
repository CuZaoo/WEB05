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
 * @date 2019��3��5��
 * @version 1.0 
 * @message ��¼�������ݿ����
 */
public class LoginDao {
	private QueryRunner qr;
	public LoginDao() {
		qr = new QueryRunner(C3p0_pool.getDataSource());
	}
	/**
	 * �������ݣ���¼���ߵ�¼����
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
	 * ������ݿ����Ƿ���ڸ��˻�
	 * @param account
	 * @return
	 */
	private boolean checkAccountExist(String account){
		//����?����ʹ��*�����Ƿ����Ĺ���?
		//�쳣�ǵô���
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
	 * ��������Ƿ���ȷ
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
