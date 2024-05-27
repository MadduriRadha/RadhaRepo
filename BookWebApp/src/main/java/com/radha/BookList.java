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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class BookList
 */
@WebServlet("/BookList")
public class BookList extends HttpServlet {
	private static final String query="select bookname,bookedition,bookprice from booksdata";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&&password=radha");){
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getFloat(3)+"</td>");
				pw.println("</tr>");
				pw.println("</table>");
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
