package entidades;

public class Paciente extends Persona{
    private int codObraSocial;
    public Paciente(String nombre, String apellido, int codObrasocial, int dni){
        super(nombre, apellido, dni);
        this.codObraSocial = codObraSocial;
    }
    public int getCodObraSocial() {
        return codObraSocial;
    }
    public void setCodObraSocial(int codObraSocial) {
        this.codObraSocial = codObraSocial;
    }
    public Paciente(){
    super();
    }
}
