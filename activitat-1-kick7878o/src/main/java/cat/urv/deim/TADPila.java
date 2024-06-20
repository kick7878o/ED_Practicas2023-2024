package cat.urv.deim;

import cat.urv.deim.exceptions.*;

public interface TADPila {

    /* METODES PER A GESTIONAR LA PILA */

	public void apilar(Persona p) throws PilaPlena;

	public void desapilar() throws PilaBuida;

	public Persona cim() throws PilaBuida;

	public int numElem();

    public boolean esBuida();

	public boolean esPlena();

	/* METODES PER A VALIDAR L'ESTRUCTURA DE LA PILA */

    /* Aquest metode retorna l'element anterior a la pila, es a dir,
     * el que fa mes temps que hi es que el que passem per parametre. Per exemple, si fessim
     * apilar(p1), apilar(p2), apilar(p3), i cridem anterior de p2 ha de retornar p1.
     * Si cridem l'anterior de p3 (l'ultim element) ha de retornar null.
     */
	public Persona anterior(Persona p) throws ElementNoTrobat;

    /* Aquest metode retorna l'element seguent a la pila, es a dir,
     * el que fa menys temps que hi es que el que passem per parametre. Per exemple, si fessim
     * apilar(p1), apilar(p2), apilar(p3), i cridem seguent de p2 ha de retornar p3.
     * Si cridem el seguent de p1 (el primer element) ha de retornar null.
     *
     */	public Persona seguent(Persona p) throws ElementNoTrobat;
}
