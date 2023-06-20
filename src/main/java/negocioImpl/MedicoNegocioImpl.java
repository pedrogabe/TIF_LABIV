package negocioImpl;

import java.util.ArrayList;

import dao.MedicoDao;
import daoImpl.MedicoDaoImpl;
import entidad.Medico;
import negocio.MedicoNegocio;

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
