package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Save
 */
@WebServlet("/Save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("name");
	    String url=request.getParameter("url");
	    String password=request.getParameter("password");
	    RequestDispatcher dispatcher = null;
	    Connection con=null;
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/save?useSSL=false","root","pc-1430");
	    	PreparedStatement pst = con.prepareStatement("insert into password(name,url,password) values (?,?,?)");
	    	pst.setString(1, name);
	    	pst.setString(2, url);
	    	pst.setString(3, password);
	    	
	    	int rowCount = pst.executeUpdate();
	    	dispatcher = request.getRequestDispatcher("index.jsp");
	    	if(rowCount>0)
	    	{
	    		request.setAttribute("status","success");
	    		}
	    	else {
	    		request.setAttribute("status", "failed");
	    	}
	    	dispatcher.forward(request, response);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
		}

	



	}


