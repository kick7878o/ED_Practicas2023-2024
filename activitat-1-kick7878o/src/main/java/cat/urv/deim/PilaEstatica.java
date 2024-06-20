package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.PilaBuida;
import cat.urv.deim.exceptions.PilaPlena;

public class PilaEstatica implements TADPila {

    private Persona[] pila; // Vector de personas
    private int nElem; // Numero de elementos ocupados de la pila

    /** Constructor Pila Estatica
     *
     * @param size tamaño maximo de la pila
     */
    public PilaEstatica(int size) {
        pila = new Persona[size]; // Inicializamos el vector de personas
        nElem = 0; // Iniciamos elementos ocupados
    }

    @Override
    public void apilar(Persona p) throws PilaPlena {
        if (esPlena()) throw new PilaPlena(); // Si la pila esta llena lanzamos excepcion

        pila[nElem] = p; // Añadimos la persona a la pila
        nElem++; // Incrementamos el numero de elementos ocupados
    }

    @Override
    public void desapilar() throws PilaBuida {
        if (esBuida()) throw new PilaBuida(); // Si la pila esta vacia lanzamos excepcion

        pila[nElem - 1] = null; // Eliminamos la persona de la cima de la pila
        nElem--; // Decrementamos el numero de elementos ocupados haciendo el efecto de desapilar
    }

    @Override
    public Persona cim() throws PilaBuida {
        if (esBuida()) throw new PilaBuida(); // Si la pila esta vacia lanzamos excepcion

        return pila[nElem - 1]; // Devolvemos la persona que esta en la cima de la pila
    }

    @Override
    public int numElem() { return nElem; }

    @Override
    public boolean esBuida() { return nElem == 0; }

    @Override
    public boolean esPlena() { return nElem == pila.length; }

    @Override
    public Persona anterior(Persona p) throws ElementNoTrobat {
        if (esBuida()) // Comprobamos que no este vacia
            throw new ElementNoTrobat(); // Si la pila esta vacia lanzamos excepcion

        return buscarPersona(p, true); // Buscamos la persona en la pila
    }

    @Override
    public Persona seguent(Persona p) throws ElementNoTrobat {
        if (esBuida()) // Comprobamos que no este vacia
            throw new ElementNoTrobat(); // Si la pila esta vacia lanzamos excepcion

        return buscarPersona(p, false); // Buscamos la persona en la pila
    }

    /** Metodo para buscar una persona en la pila
     *
     * @param p persona
     * @return posicion de la persona en la pila o -1 si no se ha encontrado
     */
    private Persona buscarPersona(Persona p, boolean esAnterior) throws ElementNoTrobat {
        // Iteramos sobre la lista
        for (int pos = 0; pos < numElem(); pos++) {
            if (pila[pos].equals(p)) { // Comprobamos si es la persona buscada
                // Devolvemos la persona segun si es el anterior o siguiente
                //  o null si es el primero(anterior) o ultimo(siguiente) de la pila
                return (esAnterior && pos > 0) ? pila[pos - 1] :
                       (pos < nElem - 1) ? pila[pos + 1] : null;
            }
        }
        throw new ElementNoTrobat();
    }

}
