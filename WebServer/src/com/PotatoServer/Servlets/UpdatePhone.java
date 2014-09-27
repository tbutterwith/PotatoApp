package com.PotatoServer.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.PotatoServer.Models.UpdatePhoneModel;
import com.PotatoServer.lib.Dbutils;

/**
 * Servlet implementation class UpdatePhone
 */
@WebServlet(urlPatterns = { 
				"/UpdatePhone", 
				"/UpdatePhone/*"
		}, 
		initParams = { 
			@WebInitParam(name = "data-source", value = "jdbc/potatodb")
		})
public class UpdatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource _ds = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePhone() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Dbutils db = new Dbutils();
		//db.createSchema();
	
        _ds=db.assemble(config);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdatePhoneModel uPM = new UpdatePhoneModel();
		uPM.setDatasource(_ds);
		
		String lastUpdate = request.getParameter("t");
		
		ServletOutputStream out = response.getOutputStream();
		
		if(lastUpdate == null) { lastUpdate = "00000000000000";}
		
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<data>");
		uPM.getUpdatedProblems(lastUpdate, out);
		uPM.getUpdatesSymptoms(lastUpdate, out);
		out.println("</data>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
