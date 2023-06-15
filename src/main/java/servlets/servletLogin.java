package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import daoImpl.LoginDaoImpl;
import entidad.Usuario;



@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
// TODO: Se Debe llamar al negocio...
		
		if (request.getParameter("txtUsuario") != null && request.getParameter("txtContraseña") != null) {
				
				String UserLogin= (request.getParameter("txtUsuario").toString());
				String Password= (request.getParameter("txtContraseña").toString());
				
				Usuario usuario = ValidarUsuario(request,UserLogin,Password);
				if(usuario != null) {
					
			
				request.setAttribute("Usuario", usuario);
			
				RequestDispatcher rd=request.getRequestDispatcher("Inicio.jsp");
				rd.forward(request, response); 
				
				}else {
						PrintWriter out=response.getWriter();
						response.setContentType("text/html");
					 out.println("<font color=red size18>Login Failed!!<br>");
					 out.println("<a href=Login.jsp>Try Again!</a>");
				}
			}
		

	}
	
	// TODO: Debe estar en la capa de negocio... lo genere para prueba 
	
	protected Usuario ValidarUsuario(HttpServletRequest request, String UserLogin,String Password) {
		LoginDao s = new LoginDaoImpl();
		
		return s.readUser(UserLogin,Password);
	}

}
