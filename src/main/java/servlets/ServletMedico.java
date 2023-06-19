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

import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Paciente;
import entidad.Medico;
import entidad.Provincia;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class ServletMedico
 */
@WebServlet("/ServletMedico")
public class ServletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMedico() {
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

			// TODO-> llenar nacionalidades,provincias,localidades
			NacionalidadNegocio negNac = new NacionalidadNegocioImpl();
			ArrayList<Nacionalidad> nacionalidades = negNac.readAll();

			ProvinciaNegocio negProv = new ProvinciaNegocioImpl();
			ArrayList<Provincia> provincias = negProv.readAll();

			LocalidadNegocio negLoc = new LocalidadNegocioImpl();
			ArrayList<Localidad> localidades = negLoc.readAll();
			request.setAttribute("nacionalidades", nacionalidades);
			request.setAttribute("provincias", provincias);
			request.setAttribute("localidades", localidades);
			if (op.equals("add")) {
				// TODO -> return max id
				request.setAttribute("maxIdPaciente", 1001);

			} else {
				int dni;
				try {
					if (request.getParameter("dni") != null)
						dni = Integer.parseInt(request.getParameter("dni"));
					else
						dni = Integer.parseInt(request.getAttribute("dni").toString());
				} catch (Exception e) {
					response.sendError(400);
					System.out.println("dni catch");
					return;
				}

				try {
					Medico medico = getMedico(request, dni);
					request.setAttribute("medico", medico);
				} catch (Exception e) {
					response.sendError(500);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher("ABMMedico.jsp");
			request.setAttribute("op", op);
			rd.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<font color=red size18>No tiene autorizacion, debe ingresar con usuario!<br>");
			out.println("<a href=Login.jsp>Ir al Login!</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected Medico getMedico(HttpServletRequest request, int dni) {
		Object md = request.getSession().getAttribute("medicos");
		if (md != null) {
			@SuppressWarnings("unchecked")
			List<Medico> medicos = (List<Medico>) request.getSession().getAttribute("medicos");
			for (Medico medico : medicos) {
				if (medico.getDni() == dni)
					return medico;
			}

			request.setAttribute("error", "No se encontró el paciente solicitado");
			// TODO -> Excepcion personalizada?
		}
		// TODO -> call negocio
		return null;
	}

}
