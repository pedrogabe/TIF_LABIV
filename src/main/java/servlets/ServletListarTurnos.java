package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.TurnosNegocio;
import negocioImpl.TurnosNegocioImpl;

/**
 * Servlet implementation class ServletListarTurnos
 */
@WebServlet("/ServletListarTurnos")
public class ServletListarTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TurnosNegocio turnoNeg = null;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarTurnos() {
    	turnoNeg = new TurnosNegocioImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("Usuario") != null) {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			try {
				request.getSession().setAttribute("turnos", turnoNeg.readAll(1)); 
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "Hubo un problema al intentar la lista de turnos");
			}
			RequestDispatcher rd = request.getRequestDispatcher("ListarTurnos.jsp");
			rd.forward(request, response);
		} else {
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
