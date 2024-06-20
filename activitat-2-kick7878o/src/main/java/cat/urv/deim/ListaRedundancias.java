package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

/** Clase Redundancias.java
 *
 * Clase abstracta que implementa la interfaz ILlistaGenerica
 *  y que resuelve la redundancia entre los códigos de las clases
 *  LlistaOrdenada y LlistaNoOrdenada.
 *
 * @author David Frent (joandavid.frentf@estudiants.urv.cat)
 */
public abstract class ListaRedundancias<E extends Comparable<E>> implements ILlistaGenerica<E> {
    // Atributos esenciales de las listas
    protected NodeLlista<E> fantasma, primero, ultimo;
    protected int nElem, posicion;
    // Atributos secundarios
    protected NodeLlista<E> nodoBuscado, nodoAntBuscado;

    /** Constructor de la lista vacía no ordenada
     * con el elemento fantasma sin incluir en el conteo.
     */
    public ListaRedundancias() {
        fantasma = new NodeLlista<E>(null); // Fantasma
        primero = null;
        ultimo = null;
        nElem = 0; // No contamos el fantasma
    }

    @Override
    public abstract void inserir(E e);

    @Override
    public abstract void esborrar(E e) throws ElementNoTrobat;

    @Override
    public abstract E consultar(int pos) throws PosicioForaRang;

    @Override
    public abstract int buscar(E e) throws ElementNoTrobat;

    @Override
    public abstract boolean existeix(E e);

    @Override
    public boolean esBuida() { return nElem == 0; }

    @Override
    public int numElements() { return nElem; }

    @Override
    public Object[] elements() {
        Object[] elements = new Object[nElem]; // Array de elementos
        // Empezamos por el primer nodo (saltando el fantasma)
        NodeLlista<E> act = primero;

        // Recorremos la lista y guardamos los elementos en el array
        for (int i = 0; i < nElem; i++) {
            elements[i] = act.getDadesPersona();
            act = act.getSeguent();
        }
        return elements;
    }
}
