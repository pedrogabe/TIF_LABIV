package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if (request.getSession().getAttribute("Usuario") != null) {
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
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<font color=red size18>No tiene autorizacion, debe ingresar con usuario!<br>");
			out.println("<a href=Login.jsp>Ir al Login!</a>");
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
