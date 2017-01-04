/**
 * function: 处理用户的信息-登录,c u r d
 * 将rs中的信息转到v中的原因 如果数据库的连接关闭以后，rs就会被关闭，里面的内容也就不能使用了
 * 而数据库是一个重要的资源，需要及时将连接释放，所以这时候需要将rs中的内容转移出来，这样就可以关闭数据库连接了
 */
package com.model;

import java.sql.ResultSet;
import java.util.*;

public class UserBeanResolve {
	private UserBean ub=null;
	private int pageSize=3;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public UserBean getUb() {
		return ub;
	}
	
	public void setUb(UserBean ub) {
		this.ub = ub;
	}
	
	//1. 用户登录验证
	public boolean loginCheck(String userId,String passwd){
		boolean b=false;
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//成功连接到数据库
		if(isConnect){
			
			//System.out.println(" ubr -- isConnect  "+isConnect);
			ub=dbm.loginCheck(userId, passwd);
			if(ub!=null){
				b=true;
			}
			//手动关闭数据连接
			dbm.closeDBConnection();
		}
		return b;
	}
	
	//2. 得到当前分页的用户信息
	public Vector getUsersInfo(int pageNow){
		Vector<UserBean> v=new Vector<UserBean>();
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//成功连接到数据库
		if(isConnect){
			
			ResultSet rs=dbm.returnUesrsInfo(pageNow,pageSize);
			//将rs中的数据转到 v中
			
			try {
				while(rs.next()){
					UserBean ub=new UserBean();
					
					ub.setUserId(rs.getString(1));
					ub.setNickname(rs.getString(2));
					ub.setEmail(rs.getString(3));
					ub.setGrade(rs.getString(5));
					v.add(ub);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//手动关闭数据连接
			dbm.closeDBConnection();
		}
		return v;
	}
	
	//3. 得到分页的总数
	public int getPageCount(){
		int pageCount=0;
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//成功连接到数据库
		if(isConnect){
			
			//System.out.println(" ubr -- isConnect  "+isConnect);
			int infoCount=dbm.getInfoCount();
			if(infoCount!=0){
				if(infoCount%pageSize==0){
					pageCount=infoCount/pageSize;
				}else{
					pageCount=infoCount/pageSize+1;
				}
			}
			//手动关闭数据连接
			dbm.closeDBConnection();
		}
		return pageCount;
	}
	
}
