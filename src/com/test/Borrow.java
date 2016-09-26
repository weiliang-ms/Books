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
public class Borrow extends HttpServlet{
	private Connection con;
	private Statement stmt;
	private ResultSet re;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String bookName= new String(request.getParameter("bookName").getBytes("iso8859-1"),"utf-8");
		String type=request.getParameter("type");
		request.setCharacterEncoding("utf-8");
	    System.out.println("类型"+type);

	   	response.setContentType("text/html;charset=utf-8");  
    	PrintWriter out = response.getWriter();
			try { 
			    Class.forName("com.mysql.jdbc.Driver"); 
			    String url= "jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8"; 
			    String user="root"; 
			    String password="root"; 
			    con =DriverManager.getConnection(url,user,password); 
			    stmt = con.createStatement();
			    re=stmt.executeQuery("select * from t_books where name='"+bookName+"'");
			    while (re.next()) {
				    String state = re.getString(5);
				    if (type.equals("borrow")&&state.equals("借出")) {
				    	out.write("借书失败，书已被借出！");
					} else if(type.equals("borrow")&&state.equals("存在")){
						stmt.executeUpdate("update t_books set have='借出' where name='"+bookName+"'");
						out.write("借书成功！");
					}else if(type.equals("return")&&state.equals("存在")){
						out.write("还书失败，书已被还！");
					}else if(type.equals("return")&&state.equals("借出")){
						stmt.executeUpdate("update t_books set have='存在' where name='"+bookName+"'");
						out.write("还书成功！");
					}
					
				}
			
			   
			    } catch (ClassNotFoundException | SQLException e) { 
			      // TODO Auto-generated catch block 
			      e.printStackTrace(); 
			    }
	}
	
}
