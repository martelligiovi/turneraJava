package entidades;

public class Turno {
    private int dniPaciente;
    private int legajoMedico;
    private int fecha;
    private Double costo;
    public Turno(int fecha, Double costo, int dniPaciente, int legajoMedico){
        this.dniPaciente = dniPaciente;
        this.legajoMedico = legajoMedico;
        this.fecha = fecha;
        this.costo = costo;
    }

    public int getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(int dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public int getLegajoMedico() {
        return legajoMedico;
    }

    public void setLegajoMedico(int legajoMedico) {
        this.legajoMedico = legajoMedico;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
