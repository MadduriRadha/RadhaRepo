package com.radha;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final String query="insert into books.booksdata values(?,?,?)";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String bookname=request.getParameter("bookname");
		String bookedition=request.getParameter("bookedition");
		float bookprice=Float.parseFloat(request.getParameter("bookprice"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=radha");){
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, bookname);
			ps.setString(2, bookedition);
			ps.setFloat(3, bookprice);
			int count=ps.executeUpdate();
			if(count==1){
				pw.println("<h2>Record is Registered Successfully</h2>");
				
			}else {
				pw.println("<h2>Record is not Registered Successfully</h2>");
			}
			
		} catch (SQLException se) {
			
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h2>");
			
		}
		 catch (Exception e) {
				
				e.printStackTrace();
				pw.println("<h1>"+e.getMessage()+"</h2>");
				
			}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='Book List'>Home</a>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
