package serrvice;

import dao.DAOTurno;
import entidades.Turno;

import java.util.ArrayList;

public class TurnoService {
    private DAOTurno daoTurno;
    public TurnoService(){
        daoTurno = new DAOTurno();
    }
    public void guardar(Turno turno) throws ServiceException {
        try {
            daoTurno.guardar(turno);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void modificar(Turno turno) throws ServiceException {
        try {
            daoTurno.modificar(turno);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void eliminar(Turno turno) throws ServiceException {
        try {
            daoTurno.eliminar(1);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public Turno buscar(String dni) throws ServiceException {
        try {
            return daoTurno.buscar(1);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList buscarTodos() throws ServiceException {
        try {
            return daoTurno.buscarTodos();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList buscarHorariosPorMedico(String fecha,int legajoMedico) throws ServiceException {
        try {
            return daoTurno.buscarHorariosPorMedico(fecha,legajoMedico);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList<String> buscarHorariosPorPaciente(String fecha,int dniPaciente) throws ServiceException {
        try {
            return daoTurno.buscarHorariosPorPaciente(fecha,dniPaciente);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList<Turno> buscarTurnosMedico(String fecha, int legajoMedico) throws ServiceException {
        try {
            return daoTurno.buscarTurnosMedico(fecha,legajoMedico);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList<Turno>buscarCobros(String fecha1, String fecha2, int legajo) throws ServiceException {
        try {
            return daoTurno.buscarCobros(fecha1,fecha2,legajo);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public ArrayList<Turno> buscarTurnosPaciente(int dni) throws ServiceException {
        try {
            return daoTurno.buscarTurnosPaciente(dni);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
