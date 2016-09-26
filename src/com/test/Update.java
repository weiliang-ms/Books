package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class Update extends HttpServlet{
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
		
		String bookName= new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		String authorGet= new String(request.getParameter("author").getBytes("iso8859-1"),"utf-8");
		String typeGet= new String(request.getParameter("type").getBytes("iso8859-1"),"utf-8");
		// 修改前的名字
		String nameObject = new String(request.getParameter("nameObject").getBytes("iso8859-1"),"utf-8");
		System.out.println("修改前的名字"+nameObject+'\n'+"修改内容"+bookName+authorGet+typeGet);
		request.setCharacterEncoding("utf-8");
	   	response.setContentType("text/html;charset=utf-8");  
			try { 
			    Class.forName("com.mysql.jdbc.Driver"); 
			    String url= "jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8"; 
			    String user="root"; 
			    String password="root"; 
			    con =DriverManager.getConnection(url,user,password); 
			    stmt = con.createStatement();
			    String sql1 = "UPDATE t_books SET name='"+bookName+"' WHERE name='"+nameObject+"'";
			    String sql2 = "UPDATE t_books SET author='"+authorGet+"' WHERE name='"+bookName+"'";
			    String sql3 = "UPDATE t_books SET type='"+typeGet+"' WHERE name='"+bookName+"'";
				stmt.executeUpdate(sql1);
				stmt.executeUpdate(sql2);
				stmt.executeUpdate(sql3);
			    } catch (ClassNotFoundException | SQLException e) { 
			      // TODO Auto-generated catch block 
			      e.printStackTrace(); 
			    }
	}
	
}
