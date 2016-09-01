package com.smashdishes.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnectionUtil;

public class UserAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DBConnectionUtil dbutil=DBConnectionUtil.getDBConnectionUtil();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionType=request.getParameter("actionType");
		
		//Ö´ÐÐ×¢²á
		if(actionType.equals("register")){
		
			String username=request.getParameter("username");
			System.out.println(username);
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			Connection con=null;
			Statement st=null;
			try {
				con=dbutil.getConnection();
				st=con.createStatement();
				String sql="insert into tb_user values('"+username+"','"+password+"','"+email+"')";
				int i=st.executeUpdate(sql);
				if(i>=1){
					response.getOutputStream().write("success".getBytes());
				}else{
					response.getOutputStream().write("error".getBytes());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(st!=null&&con!=null){
					try {
						st.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else if(actionType.equals("login")){
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			try {
				con=dbutil.getConnection();
				st=con.createStatement();
				String sql="select * from tb_user where username='"+username+"' and password='"+password+"'";
				rs=st.executeQuery(sql);
				if(rs.next()){
					if(rs.getString(1).equals(username)&&rs.getString(2).equals(password)){
						response.getOutputStream().write("success".getBytes());
					}
				}else{
					response.getOutputStream().write("error".getBytes());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null&&st!=null&&con!=null){
					try {
						rs.close();
						st.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
