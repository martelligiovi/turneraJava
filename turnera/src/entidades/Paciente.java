package entidades;

public class Paciente extends Persona{
    private String codObraSocial;
    public Paciente(String nombre, String apellido, int codObrasocial, int dni){
        super(nombre, apellido, dni);
        this.codObraSocial = codObraSocial;
    }

    public String getCodObraSocial() {
        return codObraSocial;
    }

    public void setCodObraSocial(String codObraSocial) {
        this.codObraSocial = codObraSocial;
    }
}
