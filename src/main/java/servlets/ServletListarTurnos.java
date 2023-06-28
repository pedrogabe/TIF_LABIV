package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.MedicoNegocio;
import negocio.TurnosNegocio;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.TurnosNegocioImpl;
import entidad.Turno;
import entidad.Usuario;
import entidad.Especialidad;
import negocio.EspecialidadNegocio;
import negocioImpl.EspecialidadNegocioImpl;

/**
 * Servlet implementation class ServletListarTurnos
 */
@WebServlet("/ServletListarTurnos")
public class ServletListarTurnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TurnosNegocio turnoNeg = null;
	private static MedicoNegocio medicoNeg = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListarTurnos() {
		turnoNeg = new TurnosNegocioImpl();
		medicoNeg = new MedicoNegocioImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("Usuario") != null) {
			// Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");

			ArrayList<Especialidad> especialidades = especialidadesCombo();
			request.setAttribute("especialidades", especialidades);

			try {
				ArrayList<Turno> turnos = turnoNeg.readAll(1);
				if (request.getSession().getAttribute("turnosSinFlitro") == null)
					request.getSession().setAttribute("turnosSinFlitro", turnos);

				ArrayList<Turno> turnosFiltrados = new ArrayList<Turno>();
				String filtroDia = request.getParameter("selFiltroDia");
				if (filtroDia == null || filtroDia.equals("*")) {
					turnosFiltrados = turnos;
				} else {
					Date hoy = Date.valueOf(java.time.LocalDate.now().toString());
					if (filtroDia.equals("<")) {
						for (Turno turno : turnos) {
							if (turno.getFechaReserva().before(hoy))
								turnosFiltrados.add(turno);
						}
					}
					if (filtroDia.equals("=")) {
						for (Turno turno : turnos) {
							if (turno.getFechaReserva().compareTo(hoy) == 0)
								turnosFiltrados.add(turno);
						}
					}
					if (filtroDia.equals(">")) {
						for (Turno turno : turnos) {
							if (turno.getFechaReserva().after(hoy))
								turnosFiltrados.add(turno);
						}
					}
				}
				if (request.getParameter("selFiltroEspecialidad") != null) {

					String idespecilidad = request.getParameter("selFiltroEspecialidad");
					if (0 != Integer.parseInt(idespecilidad))
						turnosFiltrados.removeIf(t -> t.getMedico().getEspecialidad().getIdEspecialidad() != Integer
								.parseInt(idespecilidad));
				}
				request.getSession().setAttribute("turnos", turnosFiltrados);

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

	private ArrayList<Especialidad> especialidadesCombo() {
		EspecialidadNegocio negEspe = new EspecialidadNegocioImpl();
		ArrayList<Especialidad> especialidades = negEspe.readAll();

		Especialidad especialidadTodas = new Especialidad(0, "Ver todas");
		especialidades.add(0, especialidadTodas);
		return especialidades;
	}

}
