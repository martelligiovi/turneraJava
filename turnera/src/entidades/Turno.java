package entidades;

public class Turno {
    private int dniPaciente;
    private int legajoMedico;
    private String fecha;
    private Double costo;
    public Turno(String fecha, Double costo, int dniPaciente, int legajoMedico){
        this.dniPaciente = dniPaciente;
        this.legajoMedico = legajoMedico;
        this.fecha = fecha;
        this.costo = costo;
    }
    public Turno(){
    }
public String getHora(){
    String[] partes = this.fecha.split(" ");
    String hora = partes[1]; // Segunda parte es la hora
    return hora;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
