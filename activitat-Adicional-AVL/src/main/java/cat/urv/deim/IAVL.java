package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import java.util.Iterator;

public interface IAVL<K, V> extends Iterable<V> {

    // Metode per insertar un element a l'arbre. Si existeix un element amb aquesta clau s'actualitza el valor
    public void inserir(K key, V value);

    // Metode per a obtenir el valor associat a la clau K
    public V consultar(K key) throws ElementNoTrobat;

    // Metode per a esborrar un element de l'arbre'
    public void esborrar(K key) throws ElementNoTrobat;

    // Metode per a comprovar si un element esta a l'arbre
    public boolean buscar(K key);

    // Metode per a comprovar si l'arbre
    public boolean esBuida();

    // Metode per a obtenir el nombre d'elements de l'arbre
    public int numElements();

    // Metode per a obtenir les claus de l'arbre
    public K[] obtenirClaus();

    // Metode per a poder iterar pels elements de l'arbre
    // IMPORTANT: El recorregut s'ha de fer de forma ordenada segons la clau
    public Iterator<V> iterator();
}
