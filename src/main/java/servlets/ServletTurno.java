package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.EstadoTurno;
import entidad.Medico;
import entidad.Paciente;
import entidad.Turno;
import negocio.*;
import negocioImpl.*;

/**
 * Servlet implementation class ServletTurno
 */
@WebServlet("/ServletTurno")
public class ServletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTurno() {
        
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("Usuario") != null) {
			String op = request.getParameter("op");
			if (op == null && request.getAttribute("op") != null)
				op = request.getAttribute("op").toString();
			if (op == null || !(op.equals("add") || op.equals("edit") || op.equals("delete"))) {
				response.sendError(400);
				System.out.println("op catch");
				return;
			}
		
		EspecialidadNegocio espNegImp = new EspecialidadNegocioImpl();
		ArrayList<Especialidad> especialidades = espNegImp.readAll();
		
		request.setAttribute("especialidades", especialidades);
		
		EstadoTurnoNegocio etNegImp = new EstadoTurnoNegocioImpl();
		ArrayList<EstadoTurno> estadoTurnos = etNegImp.readAll();
		
		request.setAttribute("estadoTurnos", estadoTurnos);
		
		MedicoNegocio medicoNegImp = new MedicoNegocioImpl();
		ArrayList<Medico> medicos = medicoNegImp.readAll(1);
		
		request.setAttribute("medicos", medicos);
		
		
		if (op.equals("add")) {

		} else {
			int id;
			try {
				if (request.getParameter("id") != null)
					id = Integer.parseInt(request.getParameter("id"));
				else
					id = Integer.parseInt(request.getAttribute("id").toString());
			} catch (Exception e) {
				response.sendError(400);
				System.out.println("id catch");
				return;
			}

			try {
				Turno turno = getTurno(request, id);
				request.setAttribute("turno", turno);
			} catch (Exception e) {
				response.sendError(500);
				return;
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("ABMLTurno.jsp");
		request.setAttribute("op", op);
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
	
	protected Turno getTurno(HttpServletRequest request, int id) {
		Object ps = request.getSession().getAttribute("turnos");
		
		if (ps != null) {
			@SuppressWarnings("unchecked")
			List<Turno> turnos = (List<Turno>) request.getSession().getAttribute("turnos");
			for (Turno turno : turnos) {
				if (turno.getIdTurno() == id)
					return turno;
			}
			request.setAttribute("error", "No se encontró el turno solicitado en la base de datos.");		
		}
		
		return null;
	}

}
