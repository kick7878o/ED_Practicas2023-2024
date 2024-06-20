package cat.urv.deim.EstructuraDatos.hashMap;

import java.util.ArrayList;
import java.util.Iterator;
import cat.urv.deim.EstructuraDatos.linkedList.*;

import cat.urv.deim.exceptions.ElementNoTrobat;

public class HashMapIndirecte<K extends Comparable<K>, V> implements IHashMap<K, V> {
    private static final float FACTOR_MAX = 0.75f; // Factor de carga máximo
    private ArrayList<EntryHashMap<K, V>> tablaHash; // Tabla hash
    private int nElem;

    /**
     * Constructor del HashMapIndirecto
     * con el tamaño indicado por parametro
     *
     * @param size tamaño maximo de la tabla
     */
    public HashMapIndirecte(int size) {
        this.tablaHash = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            tablaHash.add(null); // rellenamos la tabla con nulls
        this.nElem = 0;
    }

    @Override
    public void inserir(K key, V value) {
        int indice = obtenerIndice(key); // Obtenemos el indice de la tabla
        EntryHashMap<K, V> entAct = tablaHash.get(indice); // Guardamos la entrada actual

        if (entAct == null) { // Si no hay entradas
            tablaHash.set(indice, new EntryHashMap<K, V>(key, value));
            nElem++;
        } else { // Hay alguna entrada
            if (entAct.getKey().compareTo(key) == 0) { // Caso clave existe al principio
                entAct.setValue(value); // Actualizamos
            } else {
                EntryHashMap<K, V> entAnt = null; // Entrada anterior
                // Recorremos las colisiones
                while (entAct != null && entAct.getKey().compareTo(key) < 0) {
                    entAnt = entAct;
                    entAct = entAct.getSiguiente();
                }

                if (entAct != null && entAct.getKey().compareTo(key) == 0) { // Caso clave existe
                    entAct.setValue(value); // Actualizamos
                } else { // Caso clave no existe
                    EntryHashMap<K, V> nuevaEntrada = new EntryHashMap<K, V>(key, value);
                    nuevaEntrada.setSiguiente(entAct);
                    if (entAnt != null) // Si hay una entrada anterior
                        entAnt.setSiguiente(nuevaEntrada); // Insertamos la nueva entrada
                    else
                        tablaHash.set(indice, nuevaEntrada); // Si no, insertamos al principio
                    nElem++;
                }
            }
        }

        // Comprobamos si hay que redimensionar la tabla
        if (factorCarrega() > FACTOR_MAX)
            resizeHashMap();
    }

    @Override
    public void esborrar(K key) throws ElementNoTrobat {
        int indice = obtenerIndice(key); // Obtenemos el indice de la tabla
        EntryHashMap<K, V> entrada = buscarEntradas(indice, key, true); // Buscamos la clave

        // Si es la primera entrada a borrar y es la anterior la que buscamos
        if (entrada == tablaHash.get(indice) && entrada.getKey().equals(key))
            tablaHash.set(indice, entrada.getSiguiente()); // Reemplazamos la entrada
        else // Si es otra entrada
            entrada.setSiguiente(entrada.getSiguiente().getSiguiente()); // Ignoramos la entrada
        nElem--;
    }

    @Override
    public V consultar(K key) throws ElementNoTrobat {
        int indice = obtenerIndice(key); // Obtenemos el indice de la tabla
        EntryHashMap<K, V> entrada = buscarEntradas(indice, key, false); // Buscamos la clave
        return entrada.getValue();
    }

    @Override
    public boolean buscar(K key) {
        try {
            consultar(key); // Si no lanza excepcion, la clave existe
            return true;
        } catch (ElementNoTrobat e) {
            return false;
        }
    }

    @Override
    public ILlistaGenerica<K> obtenirClaus() {
        LlistaOrdenada<K> claus = new LlistaOrdenada<>(); // Creamos una lista ordenada

        // Recorremos la tabla hash
        for (EntryHashMap<K, V> entry : tablaHash) {
            if (entry != null) { // Si hay una entrada
                claus.inserir(entry.getKey()); // Insertamos la clave
                EntryHashMap<K, V> entAct = entry.getSiguiente(); // Guardamos la siguiente entrada
                while (entAct != null) { // Recorremos las colisiones
                    claus.inserir(entAct.getKey()); // Insertamos la clave
                    entAct = entAct.getSiguiente();
                }
            }
        }
        return claus;
    }

    @Override
    public Iterator<V> iterator() {
        return new IteratorHashMap<>(tablaHash);
    }

    @Override
    public boolean esBuida() {
        return nElem == 0;
    }

    @Override
    public int numElements() {
        return nElem;
    }

    @Override
    public float factorCarrega() {
        return (float) numElements() / midaTaula();
    }

    @Override
    public int midaTaula() {
        return tablaHash.size();
    }

    // METODOS ADICIONALES

    /**
     * Metodo que busca entradas en la tabla
     * y guarda la entrada actual y la anterior
     *
     * @param indice     indice de la tabla
     * @param key        clave a buscar
     * @param esAnterior si es la entrada anterior
     * @throws ElementNoTrobat si no se encuentra la clave
     */
    private EntryHashMap<K, V> buscarEntradas(int indice, K key, boolean esAnterior) throws ElementNoTrobat {
        EntryHashMap<K, V> entAct = tablaHash.get(indice); // Primera posicion de la entrada
        EntryHashMap<K, V> entAnt = null; // Entrada anterior

        // Recorremos las colisiones
        while (entAct != null && entAct.getKey().compareTo(key) < 0) {
            entAnt = entAct;
            entAct = entAct.getSiguiente();
        }
        if (entAct != null && entAct.getKey().equals(key))
            // Devolvemos la entrada anterior o actual
            // pero si es la primera entrada, devolvemos la actual
            return esAnterior ? (entAnt == null ? entAct : entAnt) : entAct;

        throw new ElementNoTrobat();
    }

    /**
     * Metodo que obtiene el indice
     * para la tabla estática
     *
     * @param key clave a buscar
     * @return indice de la tabla
     */
    private int obtenerIndice(K key) {
        return Math.abs(key.hashCode()) % tablaHash.size();
    }

    /**
     * Metodo que redimensiona
     * la tabla hash duplicandolo
     */
    private void resizeHashMap() {
        // Guardamos la tabla actual en una tabla temporal
        ArrayList<EntryHashMap<K, V>> tablaHashTemporal = tablaHash;
        int nuevaMida = midaTaula() * 2; // Duplicamos la mida de la tabla
        tablaHash = new ArrayList<>(nuevaMida); // Creamos una nueva tabla con la nueva mida

        // Rellenamos la nueva tabla con nulls
        for (int i = 0; i < nuevaMida; i++)
            tablaHash.add(null);

        nElem = 0; // Reiniciamos el numero de elementos

        // Recorremos la tabla temporal y añadimos las entradas a la nueva tabla
        for (int i = 0; i < tablaHashTemporal.size(); i++) {
            EntryHashMap<K, V> actual = tablaHashTemporal.get(i);

            // Recorremos las colisiones
            while (actual != null) {
                inserir(actual.getKey(), actual.getValue());
                actual = actual.getSiguiente();
            }
        }
    }
}
