/**
 * function:数据库相关操作
 */
package com.model;

import java.sql.*;

public class DBManual {
	private String url="jdbc:mysql://127.0.0.1:3306/jsp";
	private String u="root";
	private String p="root";
	private String driverPath="com.mysql.jdbc.Driver";
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	//1. 连接数据库
	public boolean startDBConnect(){
		boolean b=false;
		
		try {
			Class.forName(driverPath);
			ct=DriverManager.getConnection(url,u,p);
			b=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			b=false;
		}finally{
			return b;
		}

	}
	
	//2. 关闭数据库连接
	public void closeDBConnection(){
		
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
				ct.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//3 loginCheck
	public UserBean loginCheck(String u,String p){
		UserBean ub=new UserBean();
		try {
			ps=ct.prepareStatement("select * from userstable where userid=? and passwd=?");
			ps.setString(1, u);
			ps.setString(2, p);
			rs=ps.executeQuery();
			if(rs.next()){
				//System.out.println("dbm--- rs.getString(1)  "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				ub.setUserId(rs.getString(1));
				ub.setNickname(rs.getString(2));
				ub.setEmail(rs.getString(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return ub;
	}

	//4 查询用户信息
	public ResultSet returnUesrsInfo(int position,int size){
		try {
			ps=ct.prepareStatement("select * from userstable order by userid limit ?,?");
			ps.setInt(1, (position-1)*size);
			ps.setInt(2, size);
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	//5 查询用户总量
	public int getInfoCount(){
		int infoCount=0;
		try {
			ps=ct.prepareStatement("select count(*) from userstable ");
			rs=ps.executeQuery();
			if(rs.next()){
				infoCount=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return infoCount;
	}
	
}
