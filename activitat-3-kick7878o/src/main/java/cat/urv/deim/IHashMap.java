package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import java.util.Iterator;

public interface IHashMap<K, V> extends Iterable<V> {

    // Metode per insertar un element a la taula. Si existeix un element amb aquesta clau s'actualitza el valor
    public void inserir(K key, V value);

    // Metode per a obtenir un array amb tots els elements de K
    public V consultar(K key) throws ElementNoTrobat;

    // Metode per a esborrar un element de la taula de hash
    public void esborrar(K key) throws ElementNoTrobat;

    // Metode per a comprovar si un element esta a la taula de hash
    public boolean buscar(K key);

    // Metode per a comprovar si la taula te elements
    public boolean esBuida();

    // Metode per a obtenir el nombre d'elements de la llista
    public int numElements();

    // Metode per a obtenir les claus de la taula
    public K[] obtenirClaus();

    // Metode per a saber el factor de carrega actual de la taula
    public float factorCarrega();

    // Metode per a saber la mida actual de la taula (la mida de la part estatica)
    public int midaTaula();

    // Metode per a poder iterar pels elements de la taula
    // IMPORTANT: El recorregut s'ha de fer de forma ORDENADA SEGONS LA CLAU
    public Iterator<V> iterator();
}
