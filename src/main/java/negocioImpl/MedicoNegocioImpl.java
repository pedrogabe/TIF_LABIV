package negocioImpl;

import java.util.ArrayList;

import dao.*;
import daoImpl.*;
import entidad.Medico;
import entidad.Usuario;
import negocio.*;

public class MedicoNegocioImpl implements MedicoNegocio {

	MedicoDao medicoDaoImpl = null;

	@Override
	public boolean insert(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean insertado = false;
		if (medico.getIdUsuario() > 0 && medico.getNombre().trim() != "" && medico.getApellido().trim() != ""
				&& medico.getDni() > 0) {
			insertado = medicoDaoImpl.insert(medico);
		}
		return insertado;
	}

	@Override
	public boolean insert(Medico medico, Usuario usuario) {
		boolean insertOk = false;
		UsuarioDao usuarioDaoImpl = new UsuarioDaoImpl();
		try {
			insertOk = usuarioDaoImpl.insert(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (insertOk) {
			usuario.setIdUsuario(usuarioDaoImpl.selectMaxId());
			medico.setIdUsuario(usuario.getIdUsuario());
			insertOk = insert(medico);
			if (!insertOk) {
				usuarioDaoImpl.update(usuario, true);
			}			
		}
		return insertOk;
	}

	@Override
	public boolean delete(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean update = false;
		if (medico != null)
			update = medicoDaoImpl.update(medico, true);
		return update;
	}

	@Override
	public boolean update(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean update = false;
		if (medico != null)
			update = medicoDaoImpl.update(medico, false);
		return update;
	}

	@Override
	public ArrayList<Medico> readAll(int estado) {
		medicoDaoImpl = new MedicoDaoImpl();
		return medicoDaoImpl.readAll(estado);
	}

	@Override
	public boolean exists(Medico medico) {
		medicoDaoImpl = new MedicoDaoImpl();
		boolean estado = false;
		if (medico.getDni() > 0) {
			Medico m = medicoDaoImpl.searchMedico(medico.getDni());
			if (m != null)
				estado = true;
		}
		return estado;
	}

}
