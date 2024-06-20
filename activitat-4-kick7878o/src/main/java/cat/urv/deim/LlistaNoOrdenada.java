package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PosicioForaRang;

import java.util.Iterator;

public class LlistaNoOrdenada<E> extends ListaRedundancias<E> {
    /**
     * Constructor de la lista no ordenada
     * vacia con el elemento fantasma sin incluirlo en el conteo.
     */
    public LlistaNoOrdenada() {
        super();
    }

    @Override
    public void inserir(E e) {
        // Creamos un nuevo nodo para insertar al final de la lista
        NodeLlista<E> newNode = new NodeLlista<E>(e);

        if (esBuida()) { // Caso extremo 1: lista vacía
            this.fantasma.setSeguent(newNode);
            this.primero = newNode;
        } else // Caso general: insertar al final
            this.ultimo.setSeguent(newNode);

        this.ultimo = newNode; // Ultimo nodo apunta al nuevo nodo
        nElem++;
    }

    @Override
    public void esborrar(E e) throws ElementNoTrobat {
        if (esBuida()) // Caso extremo 1: lista vacia
            throw new ElementNoTrobat();

        // Buscamos el nodo a borrar
        buscarNodos(e, -1);

        if (nodoBuscado == null) // Caso extremo 2: nodo buscado no existe
            throw new ElementNoTrobat();

        // Ignoramos el nodo a borrar
        nodoAntBuscado.setSeguent(nodoBuscado.getSeguent());
        nElem--;
    }

    @Override
    public E consultar(int pos) throws PosicioForaRang {
        if (pos < 0 || pos >= nElem) // Caso extremo 1: posicion fuera de rango
            throw new PosicioForaRang();

        buscarNodos(null, pos); // Buscamos el nodo
        return nodoBuscado.getDadesPersona();
    }

    @Override
    public int buscar(E e) throws ElementNoTrobat {
        if (esBuida()) // Caso extremo 1: lista vacia
            throw new ElementNoTrobat();

        // Caso extremo 2: nodo buscado es...
        if (primero.getDadesPersona().equals(e)) // ...el primero
            return 0;
        if (ultimo.getDadesPersona().equals(e)) // ...el ultimo
            return nElem - 1;

        buscarNodos(e, -1); // Buscamos el nodo
        return posicion;
    }

    @Override
    public boolean existeix(E e) {
        if (esBuida()) // Caso extremo 1: lista vacia
            return false;

        buscarNodos(e, -1); // Caso general: buscamos el nodo
        return nodoBuscado != null;
    }

    @Override
    public boolean esBuida() {
        return super.esBuida();
    }

    @Override
    public int numElements() {
        return super.numElements();
    }

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    // METODOS ADICIONALES

    /**
     * Metodo auxiliar que busca segun el elemento
     * el nodo dentro de la lista.
     *
     * NOTA: no se puede probar directamente el caso extremo
     * en el final de la lista ya que no podemos acceder directamente
     * al nodo anterior al ultimo sin antes recorrer toda la lista.
     *
     * @param e   elemento a buscar
     *            (null si se busca por posicion)
     * @param pos posicion del elemento
     *            (-1 si se busca por elemento)
     */
    private void buscarNodos(E e, int pos) {
        posicion = 0; // Posicion del nodo buscado

        // Caso general: buscamos el nodo
        nodoBuscado = primero;
        nodoAntBuscado = fantasma; // Iniciamos los nodos de busqueda
        boolean encontrado = false;

        // Iteramos sobre los nodos de la lista
        while (nodoBuscado != null && !encontrado) {
            // Acaparamos tambien el caso de estar en la posicion buscada
            if (nodoBuscado.getDadesPersona().equals(e) || posicion == pos)
                encontrado = true;
            else {
                nodoAntBuscado = nodoBuscado;
                nodoBuscado = nodoBuscado.getSeguent();
                posicion++;
            }
        }
    }
}
