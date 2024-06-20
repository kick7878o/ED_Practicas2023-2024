package cat.urv.deim.EstructuraDatos.linkedList;

/** Clase NodeLlista.java
 *
 * Contiene la estructura básica del nodo
 *   con los datos de la persona.
 *
 * @author David Frent Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class LlistaNode<E> {
    private LlistaNode<E> seguent;
    private E datoE;

    public LlistaNode(E datoE) {
        this.datoE = datoE;
        this.seguent = null;
    }

    // Getters y setters basicos
    public LlistaNode<E> getSeguent() { return seguent; }
    public E getdatoE() { return datoE; }

    public void setSeguent(LlistaNode<E> seguent) { this.seguent = seguent; }
    public void setdatoE(E datoE) { this.datoE = datoE; }
}
