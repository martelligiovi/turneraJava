package entidades;

public class Medico extends Persona{
    private int legajo;


    public Medico(String nombre, String apellido, int legajo, int dni){
        super(nombre, apellido, dni);
        this.legajo = legajo;
    }


    private Turno crearTurno(Paciente paciente, String fecha, double costo){
        Turno turno = new Turno(paciente, this, fecha, costo);
        return turno;
    }


    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
}







