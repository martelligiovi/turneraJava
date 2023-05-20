package entidades;

public class Medico extends Persona{
    private int legajo;


    public Medico(String nombre, String apellido, int legajo, int dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
    }
    private Turno crearTurno(Paciente paciente, String fecha, double costo){
        Turno turno = new Turno(paciente, this, fecha, costo);
        return turno;
    }






}







