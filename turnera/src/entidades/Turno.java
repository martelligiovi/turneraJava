package entidades;

import dao.DAOException;
import dao.DAOTurno;

import java.util.ArrayList;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private Double costo;
    private DAOTurno daoTurno;

    public Turno(String fecha, Double costo, Medico medico, Paciente paciente){
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.costo = costo;
    }
    public Turno(){
        this.medico = new Medico();
        this.paciente = new Paciente();
    }
    public String getHora(){
        String[] partes = this.fecha.split(" ");
        String hora = partes[1]; // Segunda parte es la hora
        return hora;
    }
    public void setDniPaciente(int dniPaciente) {paciente.setDni(dniPaciente);}
    public void setLegajoMedico(int legajoMedico) {medico.setLegajo(legajoMedico);}
    public int getDniPaciente() {return paciente.getDni();}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}
    public int getLegajoMedico() {return medico.getLegajo();}
    public void setMedico(Medico medico) {this.medico = medico;}
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public Double getCosto() {return costo;}
    public void setCosto(Double costo) {this.costo = costo;}
    public ArrayList<String> fillarrayHoras(){
        ArrayList<String> horariosTomados = horariosTomados(this.fecha, this.getLegajoMedico(), this.getDniPaciente());
        int horaInicial = 10; // Hora inicial (10:00)
        ArrayList<String> horariosTurnos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            horariosTurnos.add(String.format("%02d:00", horaInicial));
            horariosTurnos.add(String.format("%02d:30", horaInicial));
            horaInicial++;
        }
        horariosTurnos.removeAll(horariosTomados);
        return horariosTurnos;
    }

    private ArrayList<String> horariosTomados(String fecha, int legajoMedico, int dniPaciente) {
        daoTurno = new DAOTurno();
        ArrayList<String> fechasTomadas = new ArrayList<>();
        ArrayList<String> horariosTomados = new ArrayList<>();
        try {
            fechasTomadas = daoTurno.buscarHorariosPorMedico(fecha, legajoMedico);
            fechasTomadas.addAll(daoTurno.buscarHorariosPorPaciente(fecha,dniPaciente));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        for (String fechaHora : fechasTomadas) {
            String[] partes = fechaHora.split(" ");
            String hora = partes[1]; // Segunda parte es la hora
            horariosTomados.add(hora);
        }
        return horariosTomados;
    }
}
