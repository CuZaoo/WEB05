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
 * @date 2019年2月27日
 * @version  
 * @message 
 */
public class C3p0_pool {
	//使用默认配置
	private static ComboPooledDataSource cp_default;
	//使用自定义配置
	private static ComboPooledDataSource cp_my;
	//初始化配置
	static{
		cp_default = new ComboPooledDataSource();
		cp_my = new ComboPooledDataSource("my_c3p0");
	}
	/**
	 * 获取数据源
	 * */
	public static DataSource getDataSource(){
		return cp_default;
	}
	/**
	 * 获取默认配置连接
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
	 * 获取自定义配置连接
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
