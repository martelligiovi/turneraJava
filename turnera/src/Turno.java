public class Turno {
    public Paciente paciente;
    public Medico medico;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public int getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(int codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public int codigoHorario;

    public Turno(Paciente paciente, Medico medico, int codigoHorario) {
        this.paciente = paciente;
        this.medico = medico;
        this.codigoHorario = codigoHorario;
    }
     @Override
    public int compareToCodigoHorario(int codigoH) {
        if (codigoHorario == codigoH){
            return 0;
        }
        else{
            return 1;
        }
    }

}
