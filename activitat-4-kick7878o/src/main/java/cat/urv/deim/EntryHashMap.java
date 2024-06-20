package cat.urv.deim;

/**
 * Clase EntryHashMap.java
 *
 * Clase que guarda la informacion del nodo de un HashMap
 *  en caso de haber colisiones.
 *
 * @author David Frent (joandavid.frentf@estudiants.urv.cat)
 */
public class EntryHashMap<K, V> {
    private K key;
    private V value;
    private EntryHashMap<K, V> siguiente;

    /** Constructor de la clase que
     * manejará las colisiones.
     *
     * @param key Clave del nodo
     * @param value Valor del nodo
     */
    public EntryHashMap(K key, V value) {
        this.key = key;
        this.value = value;
        this.siguiente = null;
    }

    // Getters y Setters basicos
    public K getKey() { return key; }
    public V getValue() { return value; }
    public EntryHashMap<K, V> getSiguiente() { return siguiente; }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public void setSiguiente(EntryHashMap<K, V> siguiente) { this.siguiente = siguiente; }
}
