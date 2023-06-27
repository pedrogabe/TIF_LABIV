package servlets;

import java.io.IOException;
import java.sql.Date;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				if(request.getAttribute("success")!=null) {
					RequestDispatcher rd = request.getRequestDispatcher("ServletListarTurnos");
					rd.forward(request, response);
					return;
				}
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		postOp(request);
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

	protected void postOp(HttpServletRequest request) {
		PacienteNegocio pacNeg = new PacienteNegocioImpl();
		MedicoNegocio medNeg = new MedicoNegocioImpl();
		TurnosNegocio turnoNeg = new TurnosNegocioImpl(medNeg, pacNeg);

		if (request.getParameter("btnBuscarDni") != null) {
			buscarPaciente(request, pacNeg);
		}
		if (request.getParameter("btnBuscarFecha") != null ) {
			buscarPaciente(request, pacNeg);
			buscarHorarios(request, medNeg, turnoNeg);
		}
		
		if(request.getParameter("btnGrabar") != null) {
			buscarPaciente(request, pacNeg);
			buscarHorarios(request, medNeg, turnoNeg);
			addTurno(request, turnoNeg);
		}

	}
	protected void buscarPaciente(HttpServletRequest request, PacienteNegocio pacNeg) {
		int dni = 0;
		try {
			dni = Integer.parseInt(request.getParameter("txtDniPaciente"));
		} catch (Exception ex) {
			
		}
		
		Paciente paciente;
		paciente = pacNeg.searchDni(dni);
		if (paciente != null) {
			request.setAttribute("paciente", paciente);
		} else {
			request.setAttribute("error", "El DNI ingresado no existe en la base de datos.");
		}
	}
	
	protected void buscarHorarios(HttpServletRequest request, MedicoNegocio medNeg, TurnosNegocio turnoNeg) {
		try {
			int dniMedico = Integer.parseInt(request.getParameter("selMedico"));
			Date fechaTurno = Date.valueOf(request.getParameter("txtFechaReserva"));
			Medico medico = medNeg.searchDni(dniMedico);
			
			if(medico!=null){
				ArrayList<Integer> horas = turnoNeg.turnosDisponiblesMedicoFecha(medico, fechaTurno);
				request.setAttribute("horas", horas);
				request.setAttribute("medico", medico);
				request.setAttribute("fechaTurno", fechaTurno);
			}else {
				request.setAttribute("error", "Hubo un error al recopilar los datos del médico.");
			}
		} catch (Exception ex) {
			request.setAttribute("error", "Error de validacion de datos.");
		}
	}
	
	protected void addTurno(HttpServletRequest request, TurnosNegocio turnoNeg) {
		Turno turno = fillTurno(request);
		if(turno!=null) {
			if(turnoNeg.insert(turno)) {
				request.setAttribute("success", "Se agrego el turno.");
			}else {
				request.setAttribute("error", "Ocurrió un error al grabar el turno.");
			}
		}else {
			request.setAttribute("error", "Error de validacion de datos.");
		}
	}
	
	protected Turno fillTurno(HttpServletRequest request) {
		boolean valid = true;
		Turno turno = null;
		try {
			Medico medico = (Medico)request.getAttribute("medico");
			Paciente paciente = (Paciente)request.getAttribute("paciente");
			EstadoTurno estadoTurno = new EstadoTurno(1, "Ocupado");
			Date fechaReserva;
			String fecha, observacion;
			int idTurno = 0, hora;
			
			fecha = request.getParameter("txtFechaReserva");
			try {
				fechaReserva = Date.valueOf(fecha);
			}catch(Exception e) {
				valid = false;
				fechaReserva = null;
			}
			
			observacion = "";
			try {
				hora = Integer.parseInt(request.getParameter("selHora"));
			}catch(Exception e) {
				valid = false;
				hora = -1;
			}
			
			if(valid) {
				turno = new Turno(idTurno, medico, paciente, fechaReserva, 
						observacion, estadoTurno, hora);				
			}
		}catch(Exception e) {}
		return turno;
	}

}
