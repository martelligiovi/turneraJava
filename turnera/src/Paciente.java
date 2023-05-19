public class Paciente extends persona {
    public String obraSocial;


    public Paciente(String nombre, String apellido, int edad, String dni, String sexo) {
        super(nombre, apellido, edad, dni, sexo);

    }

    @Override
    public int compareToHorario(persona o) {
        return 0;
    }



}
