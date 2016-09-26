package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Add extends HttpServlet{
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
		
		System.out.println("添加新书逻辑");
		String bookName= new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		String authorGet= new String(request.getParameter("author").getBytes("iso8859-1"),"utf-8");
		String typeGet= new String(request.getParameter("type").getBytes("iso8859-1"),"utf-8");
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
			    String selectSql = "select * from t_books where name='"+bookName+"' and type='"+typeGet+"' and author='"+authorGet+"'";
			    
			    ResultSet re = stmt.executeQuery(selectSql);
			    int i=0;
			   while (re.next()) {
				i++;
			}
			
			   if (i!=0) {
				out.write("此书籍已存在");
			} else {
				
				stmt.executeUpdate("INSERT INTO t_books VALUES(null,'"+bookName+"','"+authorGet+"','"+typeGet+"','存在')");
				stmt.close();
				con.close();
				out.write("success");
					
				}
			 
			    } catch (ClassNotFoundException | SQLException e) { 
			      // TODO Auto-generated catch block 
			      e.printStackTrace(); 
			    }
	}
	
}
