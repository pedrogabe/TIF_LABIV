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
import entidad.Paciente;

/**
 * Servlet implementation class PacientesListado
 */
@WebServlet("/PacientesListado")
public class PacientesListado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacientesListado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			request.setAttribute("pacientes", obtenerPacientes());
		}
		catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Hubo un problema al intentar listar los pacientes");
		}
		RequestDispatcher rd = request.getRequestDispatcher("ListarPacientes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected List<Paciente> obtenerPacientes(){
		ArrayList<Paciente> x = new ArrayList<Paciente>();
		x.add(new Paciente(1, 1, "Pepe", "Juarez", 2, "argentino", "2000-04-03", "Sarmiento 1234", "JJL", "Buenos Aires", "ppja@email.com", "1112345678", 1));
		x.add(new Paciente(2, 2, "Juana", "Martínez", 2, "argentino", "2023-04-03", "OHuiggins 1234", "JJL", "Buenos Aires", "jjmm@e-mail-e.com", "1112399998", 1));
		return x;
		
	}

}
