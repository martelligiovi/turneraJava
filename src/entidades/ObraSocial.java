package entidades;

public class ObraSocial {
    private String nombre;
    private int cod;
    public ObraSocial(String nombre, int codigo){
        this.nombre = nombre;
        this.cod = codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCod(){
        return this.cod;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCodigo(int codigo){
        this.cod = codigo;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
