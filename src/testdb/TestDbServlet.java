package testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//setup connection variables.
		String user="springstudent";
		String password="springstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		String driver="com.mysql.cj.jdbc.Driver";
		
		//get connection to database
		try {
			Class.forName(driver);
			PrintWriter out=response.getWriter();
			out.println("Hey I am connecting to database: "+jdbcUrl);
			Connection mycon=DriverManager.getConnection(jdbcUrl,user,password);
			out.println("Coonection successful");
			mycon.close();
		}catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
		
		
		
		
		
	}

}
