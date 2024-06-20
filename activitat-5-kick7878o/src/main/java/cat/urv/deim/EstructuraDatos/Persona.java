package cat.urv.deim.EstructuraDatos;

/**
 * Clase Comunidad.java
 *
 * Contiene la comunidad a la que pertenece un nodo.
 *
 * @author David Frent Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class Persona {
    private int identificador;
    private int comunidad;

     /** Constructor de residente en la comunidad
     *
     * @param identificador identificador del nodo
     * @param comunidad comunidad a la que pertenece [0-N]
     */
    public Persona(int identificador, int comunidad) {
        this.identificador = identificador;
        this.comunidad = comunidad;
    }

    // Getters y setters basicos
    public int getComunidad() { return comunidad; }
    public void setComunidad(int comunidad) { this.comunidad = comunidad; }

    public int getIdentificador() { return identificador; }
    public void setIdentificador(int identificador) { this.identificador = identificador; }

    // Metodo para mostrar los datos en una cadena de texto
    public String toString() {
        return "El ID -> " +identificador+ " pertenece a la comunidad -> " +comunidad+ "\n";
    }
}
