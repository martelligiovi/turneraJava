
import java.util.ArrayList;
import java.util.List;

//medico es una clase que hereda de persona
public class Medico extends persona {
    public String matricula;

    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Medico(String nombre, String apellido, int edad, String dni, String sexo,String matricula) {
        super(nombre, apellido, edad, dni, sexo);
        this.matricula = matricula;
        this.turnos = new ArrayList<Turno>();
    }
    @Override
    public int compareTo(persona o) {
        return 0;
    }
    // codigoHorario es el codigo del horario que se le asigna al turno, cada codigo tiene una relacion directa con su fecha y hora
    public void agregarTurno(Turno turno){
        if (turnos.size() == 0){
            turnos.add(turno);
        }
        else{//primero que rrecorra toda la lista revisando la existenca que codigoHorario no se repita, y si no se repite lo agrega, no antes


        }
    }

}
