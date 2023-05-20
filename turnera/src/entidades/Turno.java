package entidades;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private Double costo;
    public Turno(Paciente paciente, Medico medico, String fecha, Double costo){
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.costo = costo;
    }





}
