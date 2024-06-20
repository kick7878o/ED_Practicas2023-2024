package cat.urv.deim;

import cat.urv.deim.exceptions.*;

public interface ILlistaGenerica<E> {
    //Metode per insertar un element a la llista. No importa la posicio on s'afegeix l'element
    public void inserir(E e);

    //Metode per a esborrar un element de la llista
    public void esborrar(E e) throws ElementNoTrobat;

    //Metode per a consultar un element de la llista per posicio
    //La primera dada esta a la posicio 0
    public E consultar(int pos) throws PosicioForaRang;

    //Metode per a comprovar en quina posicio esta un element a la llista
    //La primera dada esta a la posicio 0
    public int buscar(E e) throws ElementNoTrobat;

    //Metode per a comprovar si un element esta a la llista
    public boolean existeix(E e);

    //Metode per a comprovar si la llista te elements
    public boolean esBuida();

    //Metode per a obtenir el nombre d'elements de la llista
    public int numElements();

    //Metode per a obtenir un array amb tots els elements
    public Object[] elements();
}
