package entidades;

public class ObraSocial {
    private String nombre;
    private int codigo;
    public ObraSocial(String nombre, int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
