package cat.urv.deim.EstructuraDatos.linkedList;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

import java.util.Iterator;
/** Clase Redundancias.java
 *
 * Clase abstracta que implementa la interfaz ILlistaGenerica
 *  y que resuelve la redundancia entre los códigos de las clases
 *  LlistaOrdenada y LlistaNoOrdenada.
 *
 * @author David Frent (joandavid.frentf@estudiants.urv.cat)
 */
public abstract class ListaRedundancias<E> implements ILlistaGenerica<E> {
    // Atributos esenciales de las listas
    protected LlistaNode<E> fantasma, primero, ultimo;
    protected int nElem, posicion;
    // Atributos secundarios
    protected LlistaNode<E> nodoBuscado, nodoAntBuscado;

    /** Constructor de la lista vacía no ordenada
     * con el elemento fantasma sin incluir en el conteo.
     */
    public ListaRedundancias() {
        fantasma = new LlistaNode<E>(null); // Fantasma
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
    public boolean esBuida() { return numElements() == 0; }

    @Override
    public int numElements() { return nElem; }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LlistaNode<E> actual = primero; // Nodo actual

            @Override // Comprueba si hay siguiente
            public boolean hasNext() { return actual != null; }

            @Override
            public E next() { // Devuelve el siguiente elemento
                E dades = actual.getdatoE();
                actual = actual.getSeguent();
                return dades;
            }

            @Override // No se puede eliminar
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }
}
