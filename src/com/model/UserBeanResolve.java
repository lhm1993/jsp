/**
 * function: �����û�����Ϣ-��¼,c u r d
 * ��rs�е���Ϣת��v�е�ԭ�� ������ݿ�����ӹر��Ժ�rs�ͻᱻ�رգ����������Ҳ�Ͳ���ʹ����
 * �����ݿ���һ����Ҫ����Դ����Ҫ��ʱ�������ͷţ�������ʱ����Ҫ��rs�е�����ת�Ƴ����������Ϳ��Թر����ݿ�������
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
	
	//1. �û���¼��֤
	public boolean loginCheck(String userId,String passwd){
		boolean b=false;
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//�ɹ����ӵ����ݿ�
		if(isConnect){
			
			//System.out.println(" ubr -- isConnect  "+isConnect);
			ub=dbm.loginCheck(userId, passwd);
			if(ub!=null){
				b=true;
			}
			//�ֶ��ر���������
			dbm.closeDBConnection();
		}
		return b;
	}
	
	//2. �õ���ǰ��ҳ���û���Ϣ
	public Vector getUsersInfo(int pageNow){
		Vector<UserBean> v=new Vector<UserBean>();
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//�ɹ����ӵ����ݿ�
		if(isConnect){
			
			ResultSet rs=dbm.returnUesrsInfo(pageNow,pageSize);
			//��rs�е�����ת�� v��
			
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
			
			//�ֶ��ر���������
			dbm.closeDBConnection();
		}
		return v;
	}
	
	//3. �õ���ҳ������
	public int getPageCount(){
		int pageCount=0;
		DBManual dbm=new DBManual();
		boolean isConnect=dbm.startDBConnect();
		//�ɹ����ӵ����ݿ�
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
			//�ֶ��ر���������
			dbm.closeDBConnection();
		}
		return pageCount;
	}
	
}
