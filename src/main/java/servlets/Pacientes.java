package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Paciente;

/**
 * Servlet implementation class Pacientes
 */
@WebServlet("/Pacientes")
public class Pacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pacientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if(op==null || !(op.equals("add") || op.equals("edit") || op.equals("delete")) ) {
			response.sendError(400);
			return;
		}
		
		if(op.equals("add")) {
			//TODO -> return max id + 1
			request.setAttribute("maxIdPaciente", 1001);
		}
		else {
			int id;
			try{
				id = Integer.parseInt(request.getParameter("id"));
			}catch(Exception e) {
				response.sendError(400);
				return;					
			}
			
			try {
				request.setAttribute("paciente", getPaciente(id));
			}catch(Exception e) {
				response.sendError(500);
				return;
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ABMLPaciente.jsp");
		request.setAttribute("op", op);
		rd.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected Paciente getPaciente(int id) {
		//TODO ->  call negocio
		return new Paciente(id, id, "Pepe", "Juarez", 2, "argentino", "2000-04-03", "Sarmiento 1234", "JJL", "Buenos Aires", "ppja@email.com", "1112345678", 1);
	}

}
