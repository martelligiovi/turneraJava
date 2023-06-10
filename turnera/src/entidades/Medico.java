package entidades;

public class Medico extends Persona{
    private int legajo;


    public Medico(int dni, String nombre, String apellido, int legajo){
        super(nombre, apellido, dni);
        this.legajo = legajo;
    }

    public Medico(){
        super();
    }
    private Turno crearTurno(int dniPaciente, int fecha, double costo){
        Turno turno = new Turno(fecha, costo, dniPaciente, this.legajo);
        return turno;
    }


    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }





}







