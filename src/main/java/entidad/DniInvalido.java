package entidad;

import java.io.IOException;

public class DniInvalido extends IOException{
	 
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		
		return "Dni invalido, no tiene el formato numerico";
	}
  
}
