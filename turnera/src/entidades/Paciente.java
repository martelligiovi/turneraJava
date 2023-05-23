package entidades;

public class Paciente extends Persona{
    private String obraSocial;
    public Paciente(String nombre, String apellido, int legajo, int dni){
        super(nombre, apellido, dni);
        this.obraSocial = obraSocial;
    }

}
