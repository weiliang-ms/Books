package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Delete extends HttpServlet{
	private Connection con;
	private Statement stmt;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String bookName= new String(request.getParameter("bookName").getBytes("iso8859-1"),"utf-8");
		
		System.out.println("删除的是"+bookName);
		request.setCharacterEncoding("utf-8");
	   	response.setContentType("text/html;charset=utf-8");  
    	PrintWriter out = response.getWriter();
			try { 
			    Class.forName("com.mysql.jdbc.Driver"); 
			    String url= "jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8"; 
			    String user="root"; 
			    String password="root"; 
			    con =DriverManager.getConnection(url,user,password); 
			    stmt = con.createStatement();
//			    re=stmt.executeQuery("select * from t_books where name='"+bookName+"'");
			  int  re=stmt.executeUpdate("delete  from t_books where name='"+bookName+"'");
			    if (re==0) {
			    	out.write("删除失败");

				}else {
			    	out.write("删除成功");

				}
			   
			    } catch (ClassNotFoundException | SQLException e) { 
			      // TODO Auto-generated catch block 
			      e.printStackTrace(); 
			    }
	}
	
}
