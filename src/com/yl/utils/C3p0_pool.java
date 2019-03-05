/**
 * 
 */
package com.yl.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author WangYalun
 * @date 2019��2��27��
 * @version  
 * @message 
 */
public class C3p0_pool {
	//ʹ��Ĭ������
	private static ComboPooledDataSource cp_default;
	//ʹ���Զ�������
	private static ComboPooledDataSource cp_my;
	//��ʼ������
	static{
		cp_default = new ComboPooledDataSource();
		cp_my = new ComboPooledDataSource("my_c3p0");
	}
	/**
	 * ��ȡ����Դ
	 * */
	public static DataSource getDataSource(){
		return cp_default;
	}
	/**
	 * ��ȡĬ����������
	 * */
	public static Connection getDefaultConnection(){
		Connection conn = null;
		try {
			conn = cp_default.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}
	/**
	 * ��ȡ�Զ�����������
	 * */
	public Connection getMyConnection() {
		Connection conn = null;
		try {
			conn = cp_my.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
