package cat.urv.deim;

/** Clase Node_Pila.java
 *
 * Sirve para definir los nodos para la pila dinamica
 * @author Joan David Frent Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class Node_Pila {
    // Conjunto de atributos del elemento
    private Persona persona; // Datos de la persona
    private Node_Pila seguent; // Apunta al siguiente nodo

    /** Constructor del nodo_pila que encapsula
     * la Persona y el nodo que apunta al siguiente nodo_pila
     *
     * @param persona Datos de la persona
     * @param next Nodo siguiente en la pila
     */
    public Node_Pila(Persona persona, Node_Pila next) {
        this.persona = persona;
        this.seguent = next;
    }

    // Getters basicos, no son necesarios los setters
    public Persona getPersona() { return persona; }
    public Node_Pila getSeguent() { return seguent; }
}
