package cat.urv.deim;

/** Clase NodeLlista.java
 *
 * Contiene la estructura básica del nodo
 *   con los datos de la persona.
 *
 * @author David Frent Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class NodeLlista<E> {
    private NodeLlista<E> seguent;
    private E dadesPersona;

    public NodeLlista(E dadesPersona) {
        this.dadesPersona = dadesPersona;
        this.seguent = null;
    }

    // Getters y setters basicos
    public NodeLlista<E> getSeguent() { return seguent; }
    public E getDadesPersona() { return dadesPersona; }

    public void setSeguent(NodeLlista<E> seguent) { this.seguent = seguent; }
    public void setDadesPersona(E dadesPersona) { this.dadesPersona = dadesPersona; }
}
