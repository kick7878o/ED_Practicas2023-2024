package cat.urv.deim;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase IteratorHashMap.java
 *
 * Clase que implementa la interfaz Iterator
 *  para recorrer un HashMap de forma ordenada segun K.
 *
 * @author David Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class IteratorHashMap<K extends Comparable<K>,V> implements Iterator<V> {
    private ArrayList<EntryHashMap<K, V>> tablaHashOrdenada; // Tabla hash
    private int posActual; // Posición actual de la tabla


    public IteratorHashMap(ArrayList<EntryHashMap<K, V>> tablaHash) {
        this.tablaHashOrdenada = new ArrayList<>(); // Inicializamos la tabla ordenada

        // Recorremos la tabla estática hash
        for (EntryHashMap<K, V> entryHashMap : tablaHash) {
            if (entryHashMap != null) { // Si hay una entrada
                this.tablaHashOrdenada.add(entryHashMap); // Añadimos la entrada
                EntryHashMap<K, V> entAct = entryHashMap.getSiguiente(); // Guardamos la siguiente entrada

                while (entAct != null) { // Recorremos las colisiones de la entrada
                    this.tablaHashOrdenada.add(entAct);
                    entAct = entAct.getSiguiente();
                }
            }
        }
        this.ordenarPorClave();
        this.posActual = 0; // Inicializamos la posición actual
    }

    /**
     * Metodo que ordena la tabla hash
     *  por clave usando el metodo de
     *  Ordenación por burbuja.
     */
    private void ordenarPorClave() {
        // Bucle que recorre la lista de entradas
        for (int indiceActual = 0; indiceActual < tablaHashOrdenada.size() - 1; indiceActual++) {
            // Para cada entrada comparamos su clave con las claves de las siguientes entradas
            for (int siguienteIndice = indiceActual + 1; siguienteIndice < tablaHashOrdenada.size(); siguienteIndice++) {
                // Obtenemos las entradas de las posiciones 'i' y 'j'
                EntryHashMap<K, V> entryIndAct = tablaHashOrdenada.get(indiceActual);
                EntryHashMap<K, V> entryNextInd = tablaHashOrdenada.get(siguienteIndice);
                // Comparamos las claves de las entradas
                if (entryIndAct.getKey().compareTo(entryNextInd.getKey()) > 0) {
                    // Si 'i' es mayor que 'j' intercambiamos las posiciones 'i' y 'j
                    tablaHashOrdenada.set(indiceActual, entryNextInd);
                    tablaHashOrdenada.set(siguienteIndice, entryIndAct);
                }
            }
        }
    }

    @Override
    public boolean hasNext() { // Método que indica si hay un siguiente elemento
        return posActual < tablaHashOrdenada.size();
    }

    @Override
    public V next() { // Método que devuelve el siguiente elemento
        EntryHashMap<K, V> entry = tablaHashOrdenada.get(posActual++);
        return entry.getValue();
    }
}
