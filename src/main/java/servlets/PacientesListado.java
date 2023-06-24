package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Paciente;
import negocio.PacienteNegocio;
import negocioImpl.PacienteNegocioImpl;

/**
 * Servlet implementation class PacientesListado
 */
@WebServlet("/PacientesListado")
public class PacientesListado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PacienteNegocio negocio = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacientesListado() {
        super();
        // TODO Auto-generated constructor stub
        negocio =  new PacienteNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("Usuario")!=null) {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			request.getSession().setAttribute("pacientes", negocio.readAll(1)); //Por default solo los pacientes activos 
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Hubo un problema al intentar listar los pacientes");
		}
		RequestDispatcher rd = request.getRequestDispatcher("ListarPacientes.jsp");
		rd.forward(request, response);
		}
		else {
		    response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}