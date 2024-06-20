package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

public class LlistaOrdenada<E extends Comparable<E>> extends ListaRedundancias<E> {
    /** Constructor de la lista no ordenada vacía
     * con el elemento fantasma sin incluirlo en el conteo.
     */
    public LlistaOrdenada() { super(); }

    @Override
    public void inserir(E e) {
        NodeLlista<E> newNode = new NodeLlista<E>(e); // Nodo a insertar

        if (esBuida()) { // Caso extremo 1: lista vacía
            fantasma.setSeguent(newNode);
            primero = newNode;
            ultimo = newNode;
            nElem++;
            return;
        }

        // Caso extremo 2: insertar al principio
        if (primero.getDadesPersona().compareTo(e) > 0) {
            fantasma.setSeguent(newNode); // Nodo fantasma apunta al nuevo nodo
            newNode.setSeguent(primero); // Nuevo nodo apunta al primero
            primero = newNode; // Nuevo nodo es el primero
            nElem++;
            return;
        }

        // Caso extremo 3: insertar al final
        if (ultimo.getDadesPersona().compareTo(e) < 0) {
            ultimo.setSeguent(newNode); // Ultimo nodo apunta al nuevo nodo
            ultimo = newNode; // Nuevo nodo es el ultimo
            nElem++;
            return;
        }

        // Caso general: insertar en la lista de forma ordenada
        nodoBuscado = primero;
        nodoAntBuscado = fantasma; // Definimos el inicio de la lista

        // Buscamos la posicion donde insertar el nodo
        while (nodoBuscado != null && nodoBuscado.getDadesPersona().compareTo(e) < 0) {
            nodoAntBuscado = nodoBuscado;
            nodoBuscado = nodoBuscado.getSeguent();
        }
        nodoAntBuscado.setSeguent(newNode);
        newNode.setSeguent(nodoBuscado); // Insertamos el nodo
        nElem++;
    }

    @Override
    public void esborrar(E e) throws ElementNoTrobat {
        if (esBuida()) // Caso extremo 1: lista vacia
            throw new ElementNoTrobat();

        // Caso extremo 2: nodo a borrar es el primero
        if (primero.getDadesPersona().compareTo(e) == 0) {
            fantasma.setSeguent(primero.getSeguent());
            primero = primero.getSeguent();
            nElem--;
            return;
        }

        // Caso general: borramos de la lista
        int pos = buscar(e); // Buscamos el nodo a borrar

        // Caso extremo 2: nodo buscado no existe
        if (pos == -1) throw new ElementNoTrobat();

        // Ignoramos nodo buscado simulando el borrado
        nodoAntBuscado.setSeguent(nodoBuscado.getSeguent());
        nElem--;

        // Caso extremo 3: borramos el ultimo nodo
        if (nodoBuscado == ultimo) ultimo = nodoAntBuscado;
    }

    @Override
    public E consultar(int pos) throws PosicioForaRang {
        if (pos < 0 || pos >= nElem) // Caso extremo 1: posición fuera de rango
            throw new PosicioForaRang();

        if (pos == 0) // Caso extremo 2: primero de la lista
            return primero.getDadesPersona();
        if (pos == nElem - 1) // Caso extremo 3: ultimo de la lista
            return ultimo.getDadesPersona();

        // Caso general: buscamos el nodo hasta la posicion indicada
        nodoBuscado = primero;
        for (int i=0; i < pos; i++)
            nodoBuscado = nodoBuscado.getSeguent();

        return nodoBuscado.getDadesPersona();
    }

    @Override
    public int buscar(E e) throws ElementNoTrobat {
        // Caso extremo 1: lista vacia
        if (esBuida())
            throw new ElementNoTrobat();

        // Busqueda binaria
        int inf = 0, sup = nElem - 1; // Limites de la lista
        int mitad; // Punto medio
        nodoBuscado = primero; // Nodo a buscar
        nodoAntBuscado = fantasma; // Nodo anterior al buscado

        while (inf <= sup) { // Mientras no se crucen los limites
            // Calculamos el punto medio
            mitad = inf + (sup - inf) / 2;

            // Buscamos el nodo hasta el punto medio
            for (int i = 0; i < mitad; i++) {
                nodoBuscado = nodoBuscado.getSeguent();
                nodoAntBuscado = nodoAntBuscado.getSeguent();
            }

            // Caso que se ha encontrado
            if (nodoBuscado.getDadesPersona().compareTo(e) == 0)
                return posicion = mitad;

            // Caso que no se ha encontrado (comparamos el medio con el buscado)
            if (nodoBuscado.getDadesPersona().compareTo(e) < 0)
                inf = mitad + 1; // Buscamos en la mitad derecha
            else
                sup = mitad - 1; // Buscamos en la mitad izquierda

            nodoBuscado = primero;
            nodoAntBuscado = fantasma; // Reiniciamos la busqueda
        }
        throw new ElementNoTrobat();
    }

    @Override
    public boolean existeix(E e) {
        if (esBuida()) // Caso extremo 1: lista vacia
            return false;

        try { // Caso general: buscamos el nodo en la lista
            buscar(e);
            return true;
        } catch (ElementNoTrobat e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean esBuida() { return super.esBuida(); }

    @Override
    public int numElements() { return super.numElements(); }

    @Override
    public Object[] elements() { return super.elements(); }
}
