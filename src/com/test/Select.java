package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;


@SuppressWarnings("serial")
public class Select extends HttpServlet{
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
		System.out.println("进来查询");
		String information  = new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
//		String sql = "select * from t_books";
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
					    re=stmt.executeQuery("SELECT * FROM t_books WHERE concat(name,author,type) like '%"+information+"%'"); 
					    ArrayList<Group> list = new ArrayList<Group>();
					    while(re.next()){
					    	String name = re.getString(2);
					    	String author = re.getString(3);
					    	String type = re.getString(4);
					    	String have = re.getString(5);
					    	Group group = new Group(name,author,type,have);
					    	list.add(group);
					    }
					    String string = JSON.toJSONString(list,true).toString();
					    if (list.isEmpty()) {
					    	out.write("NOTHING");
						} else {
							out.write(string);
						}
					    System.out.println("查询数据：jsonText=="+string);
					    
			    } catch (ClassNotFoundException | SQLException e) { 
			      // TODO Auto-generated catch block 
			      e.printStackTrace(); 
			    }
	}
	
}
