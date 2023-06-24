package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.*;
import negocioImpl.*;

/**
 * Servlet implementation class ServletListarMedicos
 */
@WebServlet("/ServletListarMedicos")
public class ServletListarMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MedicoNegocio medicoNeg = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListarMedicos() {
		medicoNeg = new MedicoNegocioImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
		
		if (request.getSession().getAttribute("Usuario") != null && usuario.getIdPerfil() != Usuario.ROL_MEDICO) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			try {
				request.getSession().setAttribute("medicos", medicoNeg.readAll(1)); // Por default solo los pacientes
																					// activos
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "Hubo un problema al intentar la lista de medicos");
			}
			RequestDispatcher rd = request.getRequestDispatcher("ListarMedicos.jsp");
			rd.forward(request, response);
		} else {
		    response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
